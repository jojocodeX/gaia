package org.bravo.gaia.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lijian
 * @version $Id: Profiler.java, v 0.1 2018年01月28日 9:52 lijian Exp $
 */
public class Profiler {

    private static final ThreadLocal<Entry> entryStack = new ThreadLocal<>();


    public static void start(String message) {
        entryStack.set(new Entry(message, null, null));
    }

    public static void start() {
        start(StringUtils.EMPTY);
    }

    public static void reset() {
        entryStack.set(null);
    }

    public static void enter(String message) {
        Entry currentEntry = getCurrentEntry();

        if (currentEntry != null) {
            currentEntry.enterSubEntry(message);
        }
    }

    public static void release() {
        Entry currentEntry = getCurrentEntry();

        if (currentEntry != null) {
            currentEntry.release();
        }
    }

    public static long getDuration() {
        Entry entry = entryStack.get();

        if (entry != null) {
            return entry.getDuration();
        } else {
            return -1;
        }
    }

    public static String dump() {
        return dump("", "");
    }

    public static String dump(String prefix) {
        return dump(prefix, prefix);
    }

    public static String dump(String prefix1, String prefix2) {
        Entry entry = entryStack.get();

        if (entry != null) {
            return entry.toString(prefix1, prefix2);
        } else {
            return StringUtils.EMPTY;
        }
    }

    public static Entry getEntry() {
        return (Entry) entryStack.get();
    }

    private static Entry getCurrentEntry() {
        Entry subEntry = entryStack.get();
        Entry entry = null;

        if (subEntry != null) {
            do {
                entry    = subEntry;
                subEntry = entry.getUnReleasedEntry();
            } while (subEntry != null);
        }

        return entry;
    }

    private static final class Entry {
        private List<Entry> subEntries = new ArrayList(4);
        private       String message;
        private       Entry  parentEntry;
        private       Entry  firstEntry;
        private final long   baseTime;/*基准时间，所有显示的性能计算时间都根据基准时间得出相对时间*/
        private final long   startTime;
        private       long   endTime;

        public Entry(String message, Entry parentEntry, Entry firstEntry) {
            this.message = message;
            this.startTime = System.currentTimeMillis();
            this.parentEntry = parentEntry;
            this.firstEntry = (firstEntry == null) ? this : firstEntry;
            this.baseTime = (firstEntry == null) ? 0 : firstEntry.startTime;
        }

        public String getMessage() {
            return this.message;
        }

        public long getStartTime() {
            return (baseTime > 0) ? (startTime - baseTime) : 0;
        }

        public long getEndTime() {
            return (endTime < baseTime) ? -1 : (endTime - baseTime);
        }

        public long getDuration() {
            if (endTime < startTime) {
                return -1;
            } else {
                return endTime - startTime;
            }
        }

        public long getDurationOfSelf() {
            long duration = getDuration();

            if (duration < 0) {
                return -1;
            } else if (subEntries.isEmpty()) {
                return duration;
            } else {
                for (int i = 0; i < subEntries.size(); i++) {
                    Entry subEntry = subEntries.get(i);
                    duration -= subEntry.getDuration();
                }

                if (duration < 0) {
                    return -1;
                } else {
                    return duration;
                }
            }
        }

        public double getPercentage() {
            double parentDuration = 0;
            double duration = getDuration();

            if ((parentEntry != null) && (parentEntry.isReleased())) {
                parentDuration = parentEntry.getDuration();
            }

            if ((duration > 0) && (parentDuration > 0)) {
                return duration / parentDuration;
            } else {
                return 0;
            }
        }

        public double getPecentageOfAll() {
            double firstDuration = 0;
            double duration = getDuration();

            if ((firstEntry != null) && firstEntry.isReleased()) {
                firstDuration = firstEntry.getDuration();
            }

            if ((duration > 0) && (firstDuration > 0)) {
                return duration / firstDuration;
            } else {
                return 0;
            }
        }

        public List<Entry> getSubEntries() {
            return Collections.unmodifiableList(subEntries);
        }

        private void release() {
            this.endTime = System.currentTimeMillis();
        }

        private void enterSubEntry(String message) {
            Entry entry = new Entry(message, this, firstEntry);
            subEntries.add(entry);
        }

        private Entry getUnReleasedEntry() {
            Entry bottomEntry = null;

            if (!subEntries.isEmpty()) {
                bottomEntry = subEntries.get(subEntries.size() - 1);

                if (bottomEntry.isReleased()) {
                    bottomEntry = null;
                }
            }

            return bottomEntry;
        }

        private boolean isReleased() {
            return endTime > 0;
        }

        @Override
        public String toString() {
            return toString("", "");
        }

        private String toString(String prefix1, String prefix2) {
            StringBuffer sb = new StringBuffer();

            toString(sb, prefix1, prefix2);

            return sb.toString();
        }

        private void toString(StringBuffer sb, String prefix1, String prefix2) {
            sb.append(prefix1);

            String message = getMessage();
            long startTime = getStartTime();
            long duration = getDuration();
            long durationOfSelf = getDurationOfSelf();
            double percentage = getPercentage();
            double percentageOfAll = getPecentageOfAll();

            Object[] messageParam = new Object[] {
                message, startTime, duration,
                durationOfSelf, percentage, percentageOfAll
            };

            StringBuffer pattern = new StringBuffer("{1,number} ");

            if (isReleased()) {
                pattern.append("[{2,number}ms");

                if ((durationOfSelf > 0) && (durationOfSelf != duration)) {
                    pattern.append(" ({3,number}ms)");
                }

                if (percentage > 0) {
                    pattern.append(", {4,number,##%}");
                }

                if (percentageOfAll > 0) {
                    pattern.append(", {5,number,##%}");
                }

                pattern.append("]");
            } else {
                pattern.append("[UNRELEASED]");
            }

            if (StringUtils.isNotEmpty(message)) {
                pattern.append(" - {0}");
            }

            sb.append(MessageFormat.format(pattern.toString(), messageParam));

            for (int i = 0; i < subEntries.size(); i++) {
                Entry subEntry = subEntries.get(i);

                sb.append('\n');

                if (i == (subEntries.size() - 1)) {
                    subEntry.toString(sb, prefix2 + "`---", prefix2 + "    "); // ���һ��
                } else if (i == 0) {
                    subEntry.toString(sb, prefix2 + "+---", prefix2 + "|   "); // ��һ��
                } else {
                    subEntry.toString(sb, prefix2 + "+---", prefix2 + "|   "); // �м���
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.start("Root");

        Profiler.enter("1");
        TimeUnit.SECONDS.sleep(1);
        Profiler.release();

        Profiler.enter("2");
        TimeUnit.SECONDS.sleep(1);
        Profiler.release();

        Profiler.enter("3");

        Profiler.enter("3-1");
        TimeUnit.SECONDS.sleep(1);
        Profiler.release();

        Profiler.enter("3-2");
        TimeUnit.SECONDS.sleep(1);
        Profiler.release();

        Profiler.release();

        Profiler.release();

        System.out.println(Profiler.dump());
    }

}