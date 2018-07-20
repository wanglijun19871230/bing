package com.pdstars.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * 时间处理工具类
 * </p>.
 *
 * @author Frank
 * @version $Id: DateUtil.java, v 0.1 2014年4月21日 下午3:58:36 mrfan Exp $
 */
public class DateUtil {
    
    /** The Constant log. */
    private static final Logger log                  = LoggerFactory
                                                             .getLogger(DateUtil.class);

    /** The Constant ONE_DAY_SECONDS. */
    public static final long    ONE_DAY_SECONDS      = 86400;
    
    public static final long ONE_MINUTE_SECONDS = 60;
    
    public static final long ONE_MINUTE_MILL_SECONDS = 60000;
    
    public static final long ONE_MONTH_SECONDS = 2592000;
    
    /** The Constant MONTHS_OF_ONE_QUARTER. */
    public static final int     MONTHS_OF_ONE_QUARTER = 3;
    
    /** The Constant MONTHS_OF_ONE_YEAR. */
    public static final int     MONTHS_OF_ONE_YEAR = 12;

    public static final long TEN_SECONDS_MILLSECONDS= 10000;
    /*
     * private static DateFormat dateFormat = null; private static DateFormat
     * longDateFormat = null; private static DateFormat dateWebFormat = null;
     */
    /** The Constant YEAR_Format. */
    public static final String  YEAR_FORMAT           = "yyyy";
    
    /** The Constant shortFormat. */
    public static final String  shortFormat          = "yyyyMMdd";
    
    /** The Constant shortFormat. */
    public static final String  YEAR_MONTH_Format          = "yyyyMM";
    
    /** The Constant longFormat. */
    public static final String  longFormat           = "yyyyMMddHHmmss";
    
    /** The Constant webFormat. */
    public static final String  dateFormat            = "yyyy-MM-dd";
    
    /** The Constant timeFormat. */
    public static final String  timeFormat           = "HHmmss";
    
    /** The Constant monthFormat. */
    public static final String  monthFormat          = "yyyyMM";
    
    /** The Constant chineseDtFormat. */
    public static final String  chineseDtFormat      = "yyyy年MM月dd日";
    
    public static final String  fullFormat       = "yyyy-MM-dd HH:mm:ss.SSS";
    /** The Constant newFormat. */
    public static final String  noMillionSecondFormat            = "yyyy-MM-dd HH:mm:ss";

    public static final String sqlFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    /** The Constant noSecondFormat. */
    public static final String  noSecondFormat       = "yyyy-MM-dd HH:mm";
    
    /** The Constant newFormat. */
    public static final String  tx0TimeFormat            = "yyyy/MM/dd HH:mm:ss";
    
    /** The Constant ONE_DAY_MILL_SECONDS. */
    public static final long    ONE_DAY_MILL_SECONDS = 86400000;

    /**
     * The Enum TimeUnitEnum.
     */
    public enum TimeUnitEnum {
        
        /** The month. */
        MONTH("月"),
        /** The quarter. */
        QUARTER("季度"), 
        /** The year. */
        YEAR("年");
        
        /** The message. */
        private String message;
        
        /**
         * Instantiates a new time unit enum.
         *
         * @param message the message
         */
        TimeUnitEnum(String message){
            this.message=message;
        }
        
        /**
         * Convert to months.
         *
         * @param quantity the quantity
         * @param timeUnit the time unit
         * @return the integer
         */
        public static Integer convertToMonths(int quantity, String timeUnit){
            
            Integer months = 0;
            if(timeUnit.equalsIgnoreCase("月")){
                months = quantity;
            }else if(timeUnit.equalsIgnoreCase("季度")){
                months = quantity*DateUtil.MONTHS_OF_ONE_QUARTER;
                
            }else if(timeUnit.equalsIgnoreCase("年")){
                months = quantity*DateUtil.MONTHS_OF_ONE_YEAR;
            }
            return months; 
        }
        
        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString(){
            return this.message;
        }
    }
    
    /**
     * Gets the new date format.
     *
     * @param pattern the pattern
     * @return the new date format
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * Format.
     *
     * @param date the date
     * @param format the format
     * @return the string
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * Parses the date no time.
     *
     * @param sDate the s date
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() < shortFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * Parses the date no time.
     *
     * @param sDate the s date
     * @param format the format
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date parseDate(String sDate, String format)throws ParseException 
    {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * Parses the date no time with delimit.
     *
     * @param sDate the s date
     * @param delimit the delimit
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date parseDateNoTimeWithDelimit(String sDate, String delimit)
            throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() != shortFormat.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * Parses the date long format.
     *
     * @param sDate the s date
     * @return the date
     */
    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);
        Date d = null;

        if ((sDate != null) && (sDate.length() == longFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    /**
     * Parses the date new format.
     *
     * @param sDate the s date
     * @return the date
     */
    public static Date parseDateNewFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(noMillionSecondFormat);
        Date d = null;
        if ((sDate != null) && (sDate.length() == noMillionSecondFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }
    
    /**
     * Parses the date new format.
     *
     * @param sDate the s date
     * @return the date
     */
    public static Date parseDateNewFormatForXj(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(noMillionSecondFormat);
        Date d = null;
        if ((sDate != null)) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }
    
    public static Date parseDateFullFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(fullFormat);
        Date d = null;
        if ((sDate != null)) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }
    
    /**
     * Parses the year format.
     *
     * @param sDate the s date
     * @return the date
     */
    public static Date parseYearFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(YEAR_FORMAT);
        Date d = null;
        if ((sDate != null) && (sDate.length() == YEAR_FORMAT.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 计算当前时间几小时之后的时间.
     *
     * @param date the date
     * @param hours the hours
     * @return the date
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间.
     *
     * @param date the date
     * @param minutes the minutes
     * @return the date
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * Adds the seconds.
     *
     * @param date1 the date1
     * @param secs the secs
     * @return the date
     */

    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }

    /**
     * 判断输入的字符串是否为合法的小时.
     *
     * @param hourStr the hour str
     * @return true/false
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtils.isEmpty(hourStr) && StringUtils.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            if ((hour >= 0) && (hour <= 23)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒.
     *
     * @param str the str
     * @return true/false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtils.isEmpty(str) && StringUtils.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            if ((hour >= 0) && (hour <= 59)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 取得新的日期.
     *
     * @param date1            日期
     * @param days            天数
     * @return 新的日期
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * ONE_DAY_SECONDS);
    }

    /**
     * Gets the tomorrow date string.
     *
     * @param sDate the s date
     * @return the tomorrow date string
     * @throws ParseException the parse exception
     */
    public static String getTomorrowDateString(String sDate)
            throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * Gets the tomorrow web date.
     *
     * @param sDate the s date
     * @return the tomorrow web date
     * @throws ParseException the parse exception
     */
    public static String getTomorrowWebDate(String sDate) throws ParseException {
        String tomorrowShortDate = getTomorrowDateString(convertWeb2ShortFormat(sDate));

        return convert2WebFormat(tomorrowShortDate);
    }

    /**
     * Gets the long date string.
     *
     * @param date the date
     * @return the long date string
     */
    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * Gets the new format date string.
     *
     * @param date the date
     * @return the new format date string
     */
    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(noMillionSecondFormat);
        return getDateString(date, dateFormat);
    }
    
    public static String getFullFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(fullFormat);
        return getDateString(date, dateFormat);
    }

    /**
     * Gets the date string.
     *
     * @param date the date
     * @param dateFormat the date format
     * @return the date string
     */
    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    /**
     * Gets the yester day date string.
     *
     * @param sDate the s date
     * @return the yester day date string
     * @throws ParseException the parse exception
     */
    public static String getYesterDayDateString(String sDate)
            throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * Gets the date string.
     *
     * @param date the date
     * @return 当天的时间格式化为"yyyyMMdd"
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(date);
    }

    /**
     * Gets the web date string.
     *
     * @param date the date
     * @return the web date string
     */
    public static String getWebDateString(Date date) {
        DateFormat format = getNewDateFormat(dateFormat);

        return getDateString(date, format);
    }

    /**
     * Gets the no second date string.
     *
     * @param date the date
     * @return the no second date string
     */
    public static String getNoSecondDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(noSecondFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日”的日期格式.
     *
     * @param date the date
     * @return the chinese date string
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseDtFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * Gets the today string.
     *
     * @return the today string
     */
    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(new Date(), dateFormat);
    }

    public static String getTodayLongString() {
        DateFormat dateFormat = getNewDateFormat(noMillionSecondFormat);

        return getDateString(new Date(), dateFormat);
    }
    
    public static Date getTodayLongFormat(){
    	Date d = null;
    	DateFormat dateFormat = getNewDateFormat(noMillionSecondFormat);
        String time = getDateString(new Date(), dateFormat);
        d =  parseDateNewFormat(time);
        return d;
    }
    /**
     * Gets the time string.
     *
     * @param date the date
     * @return the time string
     */
    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(timeFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * Gets the before day string.
     *
     * @param days the days
     * @return the before day string
     */
    public static String getBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis()
                - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）.
     *
     * @param one            日期1
     * @param two            日期2
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    /**
     * Gets the diff minutes.
     *
     * @param one the one
     * @param two the two
     * @return the diff minutes
     */
    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数.
     *
     * @param one the one
     * @param two the two
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                / (24 * 60 * 60 * 1000);
    }

    /**
     * Gets the before day string.
     *
     * @param dateString the date string
     * @param days the days
     * @return the before day string
     */
    public static String getBeforeDayString(String dateString, int days) {
        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }

        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

        return df.format(date);
    }

    /**
     * Checks if is valid short date format.
     *
     * @param strDate the str date
     * @return true, if is valid short date format
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != shortFormat.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); // ---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(shortFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Checks if is valid short date format.
     *
     * @param strDate the str date
     * @param delimiter the delimiter
     * @return true, if is valid short date format
     */
    public static boolean isValidShortDateFormat(String strDate,
            String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式.
     *
     * @param strDate the str date
     * @return true, if is valid long date format
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != longFormat.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); // ---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(longFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式.
     *
     * @param strDate the str date
     * @param delimiter the delimiter
     * @return true, if is valid long date format
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    /**
     * Gets the short date string.
     *
     * @param strDate the str date
     * @return the short date string
     */
    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    /**
     * Gets the short date string.
     *
     * @param strDate the str date
     * @param delimiter the delimiter
     * @return the short date string
     */
    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    /**
     * Gets the short first day of month.
     *
     * @return the short first day of month
     */
    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(cal.getTime());
    }

    /**
     * Gets the web today string.
     *
     * @return the web today string
     */
    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(dateFormat);

        return df.format(new Date());
    }

    /**
     * Gets the web first day of month.
     *
     * @return the web first day of month
     */
    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(dateFormat);

        return df.format(cal.getTime());
    }

    /**
     * Convert.
     *
     * @param dateString the date string
     * @param formatIn the format in
     * @param formatOut the format out
     * @return the string
     */
    public static String convert(String dateString, DateFormat formatIn,
            DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            log.warn("convert() --- orign date error: " + dateString);
            return "";
        }
    }

    /**
     * Convert web2 short format.
     *
     * @param dateString the date string
     * @return the string
     */
    public static String convertWeb2ShortFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(dateFormat);
        DateFormat df2 = getNewDateFormat(shortFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * Convert2 web format.
     *
     * @param dateString the date string
     * @return the string
     */
    public static String convert2WebFormat(String dateString) {
        dateString = StringUtils.defaultIfBlank(dateString, "");
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(dateFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * Convert2 chinese dt format.
     *
     * @param dateString the date string
     * @return the string
     */
    public static String convert2ChineseDtFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(chineseDtFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * Convert from web format.
     *
     * @param dateString the date string
     * @return the string
     */
    public static String convertFromWebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(dateFormat);

        return convert(dateString, df2, df1);
    }

    /**
     * Web date not less than.
     *
     * @param date1 the date1
     * @param date2 the date2
     * @return true, if successful
     */
    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(dateFormat);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * Date not less than.
     *
     * @param date1 the date1
     * @param date2 the date2
     * @param format the format
     * @return true, if successful
     */
    public static boolean dateNotLessThan(String date1, String date2,
            DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            log.debug("dateNotLessThan() --- ParseException(" + date1 + ", "
                    + date2 + ")");
            return false;
        }
    }

    /**
     * Gets the email date.
     *
     * @param today the today
     * @return the email date
     */
    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * Gets the sms date.
     *
     * @param today the today
     * @return the sms date
     */
    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * Format time range.
     *
     * @param startDate the start date
     * @param endDate the end date
     * @param format the format
     * @return the string
     */
    public static String formatTimeRange(Date startDate, Date endDate,
            String format) {
        if ((endDate == null) || (startDate == null)) {
            return null;
        }

        String rt = null;
        long range = endDate.getTime() - startDate.getTime();
        long day = range / DateUtils.MILLIS_PER_DAY;
        long hour = (range % DateUtils.MILLIS_PER_DAY)
                / DateUtils.MILLIS_PER_HOUR;
        long minute = (range % DateUtils.MILLIS_PER_HOUR)
                / DateUtils.MILLIS_PER_MINUTE;

        if (range < 0) {
            day = 0;
            hour = 0;
            minute = 0;
        }

        rt = format.replaceAll("dd", String.valueOf(day));
        rt = rt.replaceAll("hh", String.valueOf(hour));
        rt = rt.replaceAll("mm", String.valueOf(minute));

        return rt;
    }

    /**
     * Format month.
     *
     * @param date the date
     * @return the string
     */
    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(monthFormat).format(date);
    }

    /**
     * 获取系统日期的前一天日期，返回Date.
     *
     * @return the before date
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
    }

    /**
     * 获得指定时间当天起点时间.
     *
     * @param date the date
     * @return date
     */
    public static Date getDayBegin(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * Gets the day end.
     *
     * @param date the date
     * @return the day end
     */
    public static Date getDayEnd(Date date){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    
    /**
     * 获得指定时间本周起点时间.
     *
     * @param date date
     * @return date
     */
    public static Date getWeekBegin(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(getDayBegin(date));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }
    
    /**
     * 获得指定时间本月起点时间.
     *
     * @param date date
     * @return date
     */
    public static Date getMonthBegin(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(getDayBegin(date));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
    
    /**
     * 获得指定时间下月起点时间.
     *
     * @param date date
     * @return date
     */
    public static Date getNextMonthBegin(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(getDayBegin(date));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int thisMonth = calendar.get(Calendar.MONTH);
        if(thisMonth!=11)
        {
        	thisMonth+=1;
        }else{
        	thisMonth = 0;
        }
            
        calendar.set(Calendar.MONTH, thisMonth);
        return calendar.getTime();
    }
    
    /**
     * 获得指定时间本年度起点时间.
     *
     * @param date date
     * @return date
     */
    public static Date getYearBegin(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(getDayBegin(date));
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }
    
    /**
     * 获得指定时间本年度起点时间.
     *
     * @param date date
     * @return date
     */
    public static Date getYearEnd(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(getDayEnd(date));
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return calendar.getTime();
    }
    
   
   

    /**
     * 判断参date上min分钟后，是否小于当前时间.
     *
     * @param date the date
     * @param min the min
     * @return true, if successful
     */
    public static boolean dateLessThanNowAddMin(Date date, long min) {
        return addMinutes(date, min).before(new Date());

    }

    /**
     * 是否在现在时间之前.
     *
     * @param date the date
     * @return true, if is before now
     */
    public static boolean isBeforeNow(Date date) {
        if (date == null) {
            return false;
        }
        return date.compareTo(new Date()) < 0;
    }

    /**
     * 是否有效.
     *
     * @param requestTime            请求时间
     * @param effectTime            生效时间
     * @param expiredTime            失效时间
     * @return true, if is validate
     */
    public static boolean isValidate(Date requestTime, Date effectTime,
            Date expiredTime) {
        if (requestTime == null || effectTime == null || expiredTime == null) {
            return false;
        }
        return effectTime.compareTo(requestTime) <= 0
                && expiredTime.compareTo(requestTime) >= 0;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        System.out.println("[" + convert2WebFormat(null) + "]");
    }

    /**
     * Parses the no second format.
     *
     * @param sDate the s date
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date parseNoSecondFormat(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(noSecondFormat);

        if ((sDate == null) || (sDate.length() < noSecondFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 判断两个日期是否是同一天.
     *
     * @param date1 the date1
     * @param date2 the date2
     * @return true, if is same day
     */
    public static boolean isSameDay(Date date1, Date date2) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                dateFormat);
        return sdf.format(date1).equals(sdf.format(date2));
    }

    /**
     * 获取系统当前时间.
     *
     * @return the current ts
     */
    public static Date getCurrentTS() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }
    
    /**
     * 
     * @return unix时间戳, unit sec
     */
    public static long getCurrentUnixTS(){
    	return (long) (getCurrentTS().getTime()*0.001);
    }

    /**
     * 获取系统当前时间.
     *
     * @return date
     */
    @Deprecated
    public static Date getWeekBefore() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取当前时间的前几天或者后几天的日期.
     *
     * @param days the days
     * @return the date near current
     */
    public static Date getDateNearCurrent(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        Date date = calendar.getTime();
        return date;

    }
    
    /**
     * Adds the months.
     *
     * @param date date
     * @param months 几个月
     * @return date
     */
    public static Date addMonths(Date date,  int months){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }
    
    /**
     * 获取JAVA的月份值和对应字符串的列表
     * @return
     */
    public static Map<Integer, String> getMonthList(){
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(int i=0; i<12; i++){
            map.put(i, String.valueOf(i+1)+"月");
        }
        return map;
        
    }

}
