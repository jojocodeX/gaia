package org.bravo.gaia.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author lijian
 * @version $Id: DateTimeUtils.java, v 0.1 2018年04月10日 11:40 lijian Exp $
 */
public class DateTimeUtils extends DateUtils {

    private static final Logger LOG = LoggerFactory.getLogger(DateTimeUtils.class);

    public final static long ONE_DAY_SECONDS      = 86400;
    public final static long ONE_DAY_MILL_SECONDS = 86400000;

    /**
     * 将jdk8新的date转换成老Date
     * @param localDate jdk8新date对象
     */
    public static Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将jdk8新的dateTime转换成老Date
     * @param localDateTime jdk8新date对象
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将老Date转换成jdk8新的date
     * @param date 老Date
     */
    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 将老Date转换成jdk8新的dateTime
     * @param date 老Date
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 根据日期格式获取格式化器
     * @param pattern 格式化字符串
     * @return 日期格式化器
     */
    public static DateFormat getDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        //设置不进行相似日期格式匹配
        df.setLenient(false);
        return df;
    }

    /**
     * 根据日期格式获取格式化器
     * @param dateTimeFormatterEnum 格式化字符串枚举
     * @return 日期格式化器
     */
    public static DateFormat getDateFormat(DateTimeFormatterEnum dateTimeFormatterEnum) {
        return getDateFormat(dateTimeFormatterEnum.pattern());
    }

    /**
     * 根据日期格式获取格式化器
     * @param pattern 格式化字符串
     * @param timeZone 时区
     * @return 日期格式化器
     */
    public static DateFormat getDateFormat(String pattern, TimeZone timeZone) {
        DateFormat df = new SimpleDateFormat(pattern);

        //设置时区
        df.setTimeZone(timeZone);
        //设置不进行相似日期格式匹配
        df.setLenient(false);
        return df;
    }

    /**
     * 根据日期格式获取格式化器
     * @param dateTimeFormatterEnum 格式化枚举
     * @return 日期格式化器
     */
    public static DateFormat getDateFormat(DateTimeFormatterEnum dateTimeFormatterEnum, TimeZone timeZone) {
        DateFormat df = new SimpleDateFormat(dateTimeFormatterEnum.pattern());

        //设置时区
        df.setTimeZone(timeZone);
        //设置不进行相似日期格式匹配
        df.setLenient(false);
        return df;
    }

    /**
     * 将日期对象格式化成字符串
     * @param date 日期对象
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }

    /**
     * 将日期对象格式化成字符串
     * @param date 日期对象
     * @param pattern 日期格式
     * @param timeZone 时区
     * @return
     */
    public static String format(Date date, String pattern, TimeZone timeZone) {
        return getDateFormat(pattern, timeZone).format(date);
    }

    /**
     * 将日期对象格式化成字符串
     * @param date 日期对象
     * @param dateTimeFormatterEnum 日期格式枚举
     * @param timeZone 时区
     * @return
     */
    public static String format(Date date, DateTimeFormatterEnum dateTimeFormatterEnum, TimeZone timeZone) {
        return format(date, dateTimeFormatterEnum.pattern(), timeZone);
    }

    /**
     * 在指定时间之前XXX毫秒的时间
     * @param date 指定时间
     * @param milliSeconds 毫秒
     */
    public static Date minusMilliseconds(Date date, int milliSeconds) {
        return addMilliseconds(date, -milliSeconds);
    }

    /**
     * 在指定时间之前XXX秒的时间
     * @param date 指定时间
     * @param seconds 秒
     */
    public static Date minusSeconds(Date date, int seconds) {
        return addSeconds(date, -seconds);
    }

    /**
     * 在指定时间之前XXX分钟的时间
     * @param date 指定时间
     * @param minutes 分钟
     */
    public static Date minusMinutes(Date date, int minutes) {
        return addMinutes(date, -minutes);
    }

    /**
     * 在指定时间之前XXX小时的时间
     * @param date 指定时间
     * @param hours 小时
     */
    public static Date minusHours(Date date, int hours) {
        return addHours(date, -hours);
    }

    /**
     * 在指定时间之前XXX天的时间
     * @param date 指定时间
     * @param day 天
     */
    public static Date minusDays(Date date, int day) {
        return addDays(date, -day);
    }

    /**
     * 在指定时间之前XXX月的时间
     * @param date 指定时间
     * @param months 月
     */
    public static Date minusMonths(Date date, int months) {
        return addMonths(date, -months);
    }

    /**
     * 在指定时间之前XXX年的时间
     * @param date 指定时间
     * @param years 年
     */
    public static Date minusYears(Date date, int years) {
        return addYears(date, -years);
    }

    /**
     * 在指定时间之后XXX毫秒的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param milliseconds 毫秒
     */
    public static Date addMilliseconds(String dateStr, String pattern, int milliseconds) {
        Date date;
        try {
            date = addMilliseconds(parseDate(dateStr, pattern), milliseconds);
        } catch (ParseException e) {
            LOG.error("日期解析失败:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 在指定时间之后XXX毫秒的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param milliseconds 毫秒
     */
    public static Date addMilliseconds(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int milliseconds) {
        return addMilliseconds(dateStr, dateTimeFormatterEnum.pattern(), milliseconds);
    }

    /**
     * 在指定时间之后XXX秒的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param seconds 秒
     */
    public static Date addSeconds(String dateStr, String pattern, int seconds) {
        Date date;
        try {
            date = addSeconds(parseDate(dateStr, pattern), seconds);
        } catch (ParseException e) {
            LOG.error("日期解析失败:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 在指定时间之后XXX秒的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param seconds 秒
     */
    public static Date addSeconds(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int seconds) {
        return addSeconds(dateStr, dateTimeFormatterEnum.pattern(), seconds);
    }

    /**
     * 在指定时间之后XXX分钟的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param minutes 分钟
     */
    public static Date addMinutes(String dateStr, String pattern, int minutes) {
        Date date;
        try {
            date = addMinutes(parseDate(dateStr, pattern), minutes);
        } catch (ParseException e) {
            LOG.error("日期解析失败:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 在指定时间之后XXX分钟的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param minutes 分钟
     */
    public static Date addMinutes(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int minutes) {
        return addMinutes(dateStr, dateTimeFormatterEnum.pattern(), minutes);
    }

    /**
     * 在指定时间之后XXX小时的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param hours 小时
     */
    public static Date addHours(String dateStr, String pattern, int hours) {
        Date date;
        try {
            date = addHours(parseDate(dateStr, pattern), hours);
        } catch (ParseException e) {
            LOG.error("日期解析失败:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 在指定时间之后XXX小时的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param hours 小时
     */
    public static Date addHours(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int hours) {
        return addHours(dateStr, dateTimeFormatterEnum.pattern(), hours);
    }

    /**
     * 在指定时间之后XXX天的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param days 天
     */
    public static Date addDays(String dateStr, String pattern, int days) {
        Date date;
        try {
            date = addDays(parseDate(dateStr, pattern), days);
        } catch (ParseException e) {
            LOG.error("日期解析失败:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 在指定时间之后XXX天的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param days 天
     */
    public static Date addDays(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int days) {
        return addDays(dateStr, dateTimeFormatterEnum.pattern(), days);
    }

    /**
     * 在指定时间之后XXX月的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param months 月
     */
    public static Date addMonths(String dateStr, String pattern, int months) {
        Date date;
        try {
            date = addMonths(parseDate(dateStr, pattern), months);
        } catch (ParseException e) {
            LOG.error("日期解析失败:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 在指定时间之后XXX月的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param months 月
     */
    public static Date addMonths(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int months) {
        return addMonths(dateStr, dateTimeFormatterEnum.pattern(), months);
    }

    /**
     * 在指定时间之后XXX年的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param years 年
     */
    public static Date addYears(String dateStr, String pattern, int years) {
        Date date;
        try {
            date = addYears(parseDate(dateStr, pattern), years);
        } catch (ParseException e) {
            LOG.error("日期解析失败:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 在指定时间之后XXX年的时间(负数则等同于minus)
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param years 年
     */
    public static Date addYears(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int years) {
        return addYears(dateStr, dateTimeFormatterEnum.pattern(), years);
    }

    /**
     * 在指定时间之前XXX毫秒的时间
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param milliseconds 毫秒
     */
    public static Date minusMilliseconds(String dateStr, String pattern, int milliseconds) {
        return addMilliseconds(dateStr, pattern, -milliseconds);
    }

    /**
     * 在指定时间之前XXX毫秒的时间
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param milliseconds 毫秒
     */
    public static Date minusMilliseconds(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int milliseconds) {
        return addMilliseconds(dateStr, dateTimeFormatterEnum.pattern(), -milliseconds);
    }

    /**
     * 在指定时间之前XXX秒的时间
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param seconds 秒
     */
    public static Date minusSeconds(String dateStr, String pattern, int seconds) {
        return addSeconds(dateStr, pattern, -seconds);
    }

    /**
     * 在指定时间之前XXX秒的时间
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param seconds 秒
     */
    public static Date minusSeconds(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int seconds) {
        return addSeconds(dateStr, dateTimeFormatterEnum.pattern(), -seconds);
    }

    /**
     * 在指定时间之前XXX分钟的时间
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param minutes 分钟
     */
    public static Date minusMinutes(String dateStr, String pattern, int minutes) {
        return addMinutes(dateStr, pattern, -minutes);
    }

    /**
     * 在指定时间之前XXX分钟的时间
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param minutes 分钟
     */
    public static Date minusMinutes(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int minutes) {
        return addMinutes(dateStr, dateTimeFormatterEnum.pattern(), -minutes);
    }

    /**
     * 在指定时间之前XXX小时的时间
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param hours 小时
     */
    public static Date minusHours(String dateStr, String pattern, int hours) {
        return addHours(dateStr, pattern, -hours);
    }

    /**
     * 在指定时间之前XXX小时的时间
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param hours 小时
     */
    public static Date minusHours(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int hours) {
        return addHours(dateStr, dateTimeFormatterEnum.pattern(), -hours);
    }

    /**
     * 在指定时间之前XXX天的时间
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param days 天
     */
    public static Date minusDays(String dateStr, String pattern, int days) {
        return addDays(dateStr, pattern, -days);
    }

    /**
     * 在指定时间之前XXX天的时间
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param days 天
     */
    public static Date minusDays(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int days) {
        return addDays(dateStr, dateTimeFormatterEnum.pattern(), -days);
    }

    /**
     * 在指定时间之前XXX月的时间
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param months 月
     */
    public static Date minusMonths(String dateStr, String pattern, int months) {
        return addMonths(dateStr, pattern, -months);
    }

    /**
     * 在指定时间之前XXX月的时间
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param months 月
     */
    public static Date minusMonths(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int months) {
        return addMonths(dateStr, dateTimeFormatterEnum.pattern(), -months);
    }

    /**
     * 在指定时间之前XXX年的时间
     * @param dateStr 字符串时间
     * @param pattern 时间格式
     * @param years 年
     */
    public static Date minusYears(String dateStr, String pattern, int years) {
        return addYears(dateStr, pattern, -years);
    }

    /**
     * 在指定时间之前XXX年的时间
     * @param dateStr 字符串时间
     * @param dateTimeFormatterEnum 时间格式枚举
     * @param years 年
     */
    public static Date minusYears(String dateStr, DateTimeFormatterEnum dateTimeFormatterEnum, int years) {
        return addYears(dateStr, dateTimeFormatterEnum.pattern(), -years);
    }

    /**
     * 计算两个日期之间相差多少毫秒
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差的毫秒数
     */
    public static long diffOfMilliSeconds(Date startDate, Date endDate) {
        return ChronoUnit.MILLIS.between(convertToLocalDateTime(startDate), convertToLocalDateTime(endDate));
    }

    /**
     * 计算两个日期之间相差多少秒
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差的秒数
     */
    public static long diffOfSeconds(Date startDate, Date endDate) {
        return ChronoUnit.SECONDS.between(convertToLocalDateTime(startDate), convertToLocalDateTime(endDate));
    }

    /**
     * 计算两个日期之间相差多少分钟
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差的分钟数
     */
    public static long diffOfMinutes(Date startDate, Date endDate) {
        return ChronoUnit.MINUTES.between(convertToLocalDateTime(startDate), convertToLocalDateTime(endDate));
    }

    /**
     * 计算两个日期之间相差多少小时
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差的小时数
     */
    public static long diffOfHours(Date startDate, Date endDate) {
        return ChronoUnit.HOURS.between(convertToLocalDateTime(startDate), convertToLocalDateTime(endDate));
    }

    /**
     * 计算两个日期之间相差多少天
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差的天数
     */
    public static long diffOfDays(Date startDate, Date endDate) {
        return ChronoUnit.DAYS.between(convertToLocalDate(startDate), convertToLocalDate(endDate));
    }

    /**
     * 计算两个日期之间相差多少月
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差的月数
     */
    public static long diffOfMonths(Date startDate, Date endDate) {
        return ChronoUnit.MONTHS.between(convertToLocalDate(startDate), convertToLocalDate(endDate));
    }

    /**
     * 计算两个日期之间相差多少年
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差的年数
     */
    public static long diffOfYears(Date startDate, Date endDate) {
        return ChronoUnit.YEARS.between(convertToLocalDate(startDate), convertToLocalDate(endDate));
    }

    /**
     * 将指定时间转换成特定时区的时间
     * @param sourceDate 指定时间
     * @param timeZone 时区
     */
    public static Date convertToTargetTimeZone(Date sourceDate, TimeZone timeZone) {
        DateFormat dateFormat = getDateFormat(DateTimeFormatterEnum.DEFAULT_DATE_TIME_FORMAT);
        dateFormat.setTimeZone(timeZone);
        String format = dateFormat.format(sourceDate);

        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        Date date = null;
        try {
            date = dateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}