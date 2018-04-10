package org.bravo.gaia.utils;

/**
 * 日期时间格式枚举
 *
 * @author lijian
 * @version $Id: DateTimeFormatterEnum.java, v 0.1 2018年04月10日 11:52 lijian Exp $
 */
public enum DateTimeFormatterEnum {

    CLOSED_DATE_FORMAT("yyyyMMdd"),

    CLOSED_DATE_TIME_FORMAT("yyyyMMddHHmmss"),

    STANDARD_DATE_FORMAT("yyyy-MM-dd"),

    CLOSED_TIME_FORMAT("HHmmss"),

    CLOSED_MONTH_FORMAT("yyyyMM"),

    CHINESE_DT_FORMAT("yyyy年MM月dd日"),

    STANDARD_DATE_TIME_FORMAT("yyyy-MM-dd HH:mm:ss"),

    STANDARD_DATE_TIME_NO_SECOND_FORMAT("yyyy-MM-dd HH:mm"),

    DEFAULT_DATE_TIME_FORMAT("yyyy-MM-dd HH:mm:ss.SSS");

    private String pattern;

    DateTimeFormatterEnum(String pattern){
        this.pattern = pattern;
    }

    public String pattern() {
        return pattern;
    }

}