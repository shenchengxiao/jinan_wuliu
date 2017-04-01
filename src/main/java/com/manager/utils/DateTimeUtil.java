package com.manager.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateTimeUtil {

    /** 标准日期格式 */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /** 标准时间格式 */
    public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** TTS订单退款有效期时间格式 */
    public final static String DATETIME_PATTERN_TTS_ORDER_REFUND = "yyyy-MM-dd HH:mm";

    /** 标准年月格式 */
    public final static String MONTH_PATTERN = "yyyy-MM";

    /** 标准年小时分钟格式 */
    public final static String HOUR_MINUTE = "HH:mm";

    /** 标准年小时分钟秒格式 */
    public final static String HOUR_MINUTE_SECOND = "HH:mm:ss";

    /** 标准日期时分秒毫秒格式 */
    public final static String DATETIME_MILL_SECOND = "yyyy-MM-dd HH:mm:ss.SSS";

    public final static String DATE_SLASH_PATTERN = "yyyy/MM/dd";

    public final static String DATE_SHORT_PATTERN = "yyyyMMdd";

    public final static String DATE_CHINESE_PATTERN = "yyyy年MM月dd日";

    public final static String MONTH_CHINESE_PATTERN = "MM月dd日";

    public final static String MONTH_CHINESE_PATTERN_single = "M月d日";

    public final static String MONTH_DAY_PATTERN="MM-dd";

    public final static String DATETIME_SHORT_PATTERN = "yyyyMMddHHmmss";

    private final static String[] WEEK_NAMES = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };

    /** Number of milliseconds in a standard second. */
    public static final long MILLIS_PER_SECOND = 1000;

    /** Number of milliseconds in a standard minute. */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    /** Number of milliseconds in a standard hour. */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    /** Number of milliseconds in a standard day. */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    /** Number of seconds in a standard minute. */
    public static final int SECOND_PER_MINUTE = 60;

    /** Number of seconds in a standard hour. */
    public static final int SECOND_PER_HOUR = 60 * SECOND_PER_MINUTE;

    /** Number of seconds in a standard day. */
    public static final int SECOND_PER_DAY = 24 * SECOND_PER_HOUR;

    private static final Logger LOG = LoggerFactory.getLogger(DateTimeUtil.class);



    /**
     * 将日期或者时间字符串转化为日期对象
     *
     * @param date 日期字符串
     * @param pattern 格式字符串</br> yyyy-MM-DD, yyyy/MM/DD, yyyyMMdd</br> yyyy-MM-dd-HH:mm:ss, yyyy-MM-dd HH:mm:ss
     *            格式字符串可选字符："GyMdkHmsSEDFwWahKzZ"
     * @return Date
     */
    public static Date convertDate(String date, String pattern) {
        if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(date)) {
            String msg = "the date or pattern is empty.";
            throw new IllegalArgumentException(msg);
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern.trim());
        try {
            return df.parse(date.trim());
        } catch (Exception e) {
            String msg = "parse date [" + date + "] with pattern [" + pattern + "] failed";
            LOG.error(msg, e);
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * 将日期或者时间戳转化为日期对象
     *
     * @param date yyyy-MM-dd or yyyy-MM-dd HH:mm:ss or yyyy-MM-dd HH:mm:ss.SSS
     * @return
     */
    public static Date convertDate(String date) {
        if (date.indexOf(":") > 0) {
            return convertDate(date, DATETIME_PATTERN);
        } else if (date.indexOf(".") > 0) {
            return convertDate(date, DATETIME_MILL_SECOND);
        } else {
            return convertDate(date, DATE_PATTERN);
        }
    }

    /**
     * 将long型整数转化为时间。
     *
     * @param date 时间对应的long值
     * @return 时间对象
     */
    public static Timestamp convertDate(Long date) {
        return new Timestamp(date);
    }

    /**
     * 将时间字符串转化为时间对象Time
     *
     * @param time 时间字符串
     * @param pattern 格式字符串 yyyy-MM-dd HH:mm:ss or yyyy-MM-dd HH:mm:ss.SSS
     * @return
     */
    public static Time convertTime(String time, String pattern) {
        Date d = convertDate(time, pattern);
        return new Time(d.getTime());
    }

    /**
     * 格式为时间戳字符串
     *
     * @param date 时间
     * @return yyyy-MM-dd HH:mm:ss Date
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DATETIME_PATTERN);
    }

    /**
     * 格式为时间字符串
     *
     * @param date 日期
     * @return yyyy-MM-dd Date
     */
    public static String formatDate(Date date) {
        return formatDate(date, DATE_PATTERN);
    }

    /**
     * 格式为时间字符串
     *
     * @param date 日期
     * @return HH:mm Date
     */
    public static String formatTime(Date date) {
        return formatDate(date, HOUR_MINUTE);
    }

    /**
     * 按指定格式字符串格式时间
     *
     * @param date 日期或者时间
     * @param pattern 格式化字符串 yyyy-MM-dd， yyyy-MM-dd HH:mm:ss, yyyy年MM月dd日 etc.</br>
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern.trim());
        return format.format(date);
    }

    /**
     * 将当前时间格式为字符串'yyyyMMddHHmmss'.
     *
     * @return
     */
    public static String formatNowToYMDHMS() {
        return formatDateToYMDHMS(new Date());
    }

    /**
     * 将制定时间格式为字符串'yyyyMMddHHmmss'.
     *
     * @return
     */
    public static String formatDateToYMDHMS(Date date) {
        return formatDate(date, DATETIME_SHORT_PATTERN);
    }

    /**
     * 获得当前时间戳
     *
     * @return Timestamp
     */
    public static Timestamp now() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 获得当前日期对象
     *
     * @return
     */
    public static Date today() {
        return convertDate(formatDate(new Date()), DATE_PATTERN);
    }

    /**
     * 获得当前日期字符串,格式为：yyyy-MM-dd
     *
     * @return
     */
    public static String todayDate() {
        return formatDate(new Date());
    }

    public static String yesterday(String pattern){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat(pattern).format(cal.getTime());
        return yesterday;
    }

    /**
     * 获得当前时间字符串,格式为：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String nowDateTime() {
        return formatDate(new Date(), DATETIME_PATTERN);
    }

    /**
     * 获得星期数
     *
     * @param date 日期
     * @return
     */
    public static int getWeekNumber(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int number = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (number == 0)
            number = 7;
        return number;
    }

    /**
     * 获得星期名称
     *
     * @param date
     * @return
     */
    public static String getWeekNumberString(Date date) {
        int dayNum = getWeekNumber(date);
        return WEEK_NAMES[dayNum - 1];
    }

    public static Date monthStart(int year,int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }


    public static Date monthEnd(int year,int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();

    }


    /**
     * 按指定roundType格式化日期。
     *
     * @param date 日期
     * @param roundType
     * @return Date
     */
    public static Date round(Date date, int roundType) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime());
        switch (roundType) {
            case Calendar.MONTH:
                c.set(Calendar.DAY_OF_MONTH, 1);
            case Calendar.DATE:
                c.set(Calendar.HOUR_OF_DAY, 0);
            case Calendar.HOUR:
                c.set(Calendar.MINUTE, 0);
            case Calendar.MINUTE:
                c.set(Calendar.SECOND, 0);
            case Calendar.SECOND:
                c.set(Calendar.MILLISECOND, 0);
                return c.getTime();
            default:
                throw new IllegalArgumentException("invalid round roundType.");
        }
    }

    /**
     * 获得本周第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfThisWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获得本周周六
     *
     * @param date
     * @return
     */
    public static Date getSaturdayOfThisWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return cal.getTime();
    }

    /**
     * 获得本周周日
     *
     * @param date
     * @return
     */
    public static Date getSundayOfThisWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * 获得本月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfThisMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获得本月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfThisMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }


    /**
     * 获得学期开始
     *
     * @return
     */
    public static Date getSemesterBeginDate() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;//月
        if(month >= 9){
            cal.set(Calendar.DAY_OF_MONTH, 1);  //设置日期
            cal.set(Calendar.MONTH, 8);
        }else if(month <=3) {
            cal.add(Calendar.YEAR,-1);
            cal.set(Calendar.DAY_OF_MONTH, 1);  //设置日期
            cal.set(Calendar.MONTH, 8);
        }else if(month >3 && month <9){
            cal.set(Calendar.DAY_OF_MONTH, 1);  //设置日期
            cal.set(Calendar.MONTH, 2);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * 获得学期结束
     *
     * @return
     */
    public static Date getSemesterEndDate() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;//月
        if(month > 9){
            cal.set(Calendar.DAY_OF_MONTH, -1);  //设置日期
            cal.set(Calendar.MONTH, 3);
            cal.add(cal.YEAR, 1);
        }else if(month < 3){
            cal.set(Calendar.DAY_OF_MONTH, -1);  //设置日期
            cal.set(Calendar.MONTH, 3);
        } else {
            cal.set(Calendar.MONTH, 9);
            cal.set(Calendar.DAY_OF_MONTH, -1);
        }
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获得小时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获得年
     *
     * @param time
     * @return
     */
    public static int getYear(Long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得月
     *
     * @param time
     * @return
     */
    public static int getMonth(Long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return c.get(Calendar.MONTH) + 1;
    }
    /**
     * 获得天
     *
     * @param time
     * @return
     */
    public static int getDay(Long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得天
     *
     * @param time
     * @return
     */
    public static int getCurrentMonthLastDay(Long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得小时
     *
     * @param time
     * @return
     */
    public static int getHour(Long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获得分钟数
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 在指定日期增加指定天数
     *
     * @param date 指定日期
     * @param days 指定天数
     * @return
     */
    public static Date addDay(String date, int days) {
        return addDay(convertDate(date), days);
    }

    /**
     * 在指定日期增加指定天数
     *
     * @param date 指定日期
     * @param days 指定天数
     * @return
     */
    public static Date addDay(Date date, int days) {
        if (days == 0)
            return date;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);
        return c.getTime();
    }

    /**
     * 在指定日期增加指定小时
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, int hours) {
        if (hours == 0)
            return date;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hours);
        return c.getTime();
    }

    /**
     * 获得日期相差天数
     *
     * @param date1 日期
     * @param date2 日期
     * @return
     */
    public static int diffDate(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / MILLIS_PER_DAY);
    }

    /**
     * 当前日期之前
     *
     * @param date
     * @return
     */
    public static boolean beforeToday(Object date) {
        Date currentDate = new Date();
        return compareDate(date, currentDate) == -1;
    }

    /**
     * 当前日期之后
     *
     * @param date
     * @return
     */
    public static boolean afterToday(Object date) {
        Date currentDate = new Date();
        return compareDate(date, currentDate) == 1;
    }



    /**
     * 是否同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Object date1, Object date2) {
        return compareDate(date1, date2) == 0;
    }

    /**
     * 比较两个日期date1大于date1 返回1 等于返回0 小于返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Object date1, Object date2) {
        return compareDate(date1, date2, Calendar.DATE);
    }

    /**
     * 比较两个日期date1大于date1 返回1 等于返回0 小于返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Object date1, Object date2, int accuracy) {
        if (date1 == null || date2 == null) {
            String msg = "illegal arguments,date1 and date2 must be not null.";
            throw new IllegalArgumentException(msg);
        }
        Date d1 = (Date) ((date1 instanceof String) ? convertDate((String) date1) : date1);
        Date d2 = (Date) ((date2 instanceof String) ? convertDate((String) date2) : date2);
        return round(d1, accuracy).compareTo(round(d2, accuracy));
    }


    /**
     * 比较两个时间大小，date1大于date2 返回1 等于返回0 小于返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareTime(Object date1, Object date2) {
        if (date1 == null || date2 == null) {
            String msg = "illegal arguments,date1 and date2 must be not null.";
            throw new IllegalArgumentException(msg);
        }
        Date d1 = (Date) ((date1 instanceof String) ? convertDate((String) date1) : date1);
        Date d2 = (Date) ((date2 instanceof String) ? convertDate((String) date2) : date2);
        return d1.compareTo(d2);
    }

    /**
     * 检查时间或者字符串是否合法
     *
     * @param date 时间
     * @param pattern 格式串
     * @return
     */
    public static boolean isValidDate(String date, String pattern) {
        try {
            convertDate(date, pattern);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static String delta(Date date1,Date date2){
        StringBuffer sb = new StringBuffer();
        long l=date1.getTime()-date2.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        //long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        if(day > 0){
            sb.append(day + "天");
        }
        if(hour>0){
            sb.append(hour + "小时");
        }
        if(min>0){
            sb.append(min + "分钟");
        }
        if(day==0&&hour==0&min==0){
            return "1分钟";
        }else{
            return sb.toString();
        }
    }

    public static String deltaSecond(Date date1,Date date2,String effectiveTime){
        StringBuffer sb = new StringBuffer();
        long l=date1.getTime()-date2.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long second = (l/1000-day*24*60*60-hour*60*60-min*60);
        long currentTime = 0l;
        if(day > 0){
            return "0";
        }
        if(hour>0){
            return "0";
        }
        if(min>0){
            currentTime = min * 60;
        }
        if(second >0){
            currentTime +=second;
        }
        List<String> effectiveTimes = Splitter.on(":").splitToList(effectiveTime);
        long effectTime = Integer.valueOf(effectiveTimes.get(0)) *60 + Integer.valueOf(effectiveTimes.get(1));
        if(currentTime >=effectTime){
            return "0";
        } else {
            if(min >0){
                sb.append(min).append(":");
            }
            if(second >0){
                sb.append(second);
            }
            return sb.toString();
        }
    }

    public static Date afterXSeconds(Date date, int x) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.SECOND, x);
        return gc.getTime();
    }

    public static Date afterXMinue(Date date, int x) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.MINUTE, x);
        return gc.getTime();
    }

    /**
     * 判断日期是否跨年
     *
     * @param date
     * @return
     */
    public static boolean isCrossYear(String date, String datePattern) {
        boolean result = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertDate(date, datePattern));
        int year = calendar.get(Calendar.YEAR);
        calendar.setTime(new Date());
        int nowYear = calendar.get(Calendar.YEAR);
        if (year > nowYear) {
            result = true;
        }
        return result;
    }

    public static Set<String> formatDate(String canUseDate, String datePattern) {
        Set<String> result = Sets.newLinkedHashSet();
        try {
            if (StringUtils.isBlank(canUseDate)) {
                return result;
            }

            String[] dateSegments = StringUtils.split(canUseDate, ",");
            if (null != dateSegments && dateSegments.length > 0) {
                for (String dateSegment : dateSegments) {
                    if (StringUtils.contains(dateSegment, "~")) {
                        String[] dateArray = dateSegment.split("~");
                        Date currentDate = convertDate(StringUtils.trim(dateArray[0]));
                        Date segmentEndDate = convertDate(StringUtils.trim(dateArray[1]));
                        for (; compareDate(currentDate, segmentEndDate) <= 0;) {
                            result.add(formatDate(currentDate, datePattern));
                            currentDate = addDay(currentDate, 1);
                        }

                    } else {
                        result.add(formatDate(convertDate(StringUtils.trim(dateSegment)), datePattern));
                    }
                }
            }

        } catch (Exception e) {
            LOG.error("format date error", e);
        }
        return result;

    }

    public static Set<String> sortDate(Set<String> dateSet, String srcDatePattern, String destDatePattern) {
        Set<String> result = Sets.newLinkedHashSet();
        Set<Date> sortDate = sortDate(dateSet, srcDatePattern);
        if(StringUtils.isBlank(destDatePattern)) {
            destDatePattern = srcDatePattern;
        }

        for (Date date : sortDate) {
            result.add(formatDate(date, destDatePattern));
        }
        return result;
    }

    public static Set<Date> sortDate(Set<String> dateSet, String srcDatePattern) {
        Set<Date> result = Sets.newTreeSet();
        for (String date : dateSet) {
            result.add(convertDate(date, srcDatePattern));
        }
        return result;
    }

    public static Set<String> getDaySet(String startDate, int delta, String srcDatePattern, String destDatePattern) {
        Set<String> daySet = Sets.newHashSet();
        Date day = convertDate(startDate, srcDatePattern);
        do {
            daySet.add(formatDate(day, destDatePattern));
            day = addDay(day, 1);
        } while (--delta > 0);
        return daySet;
    }

    public static Set<String> getDaySet(Date startDate, int delta, String destDatePattern) {
        Set<String> daySet = Sets.newHashSet();
        Date day = startDate;
        do {
            daySet.add(formatDate(day, destDatePattern));
            day = addDay(day, 1);
        } while (--delta > 0);
        return daySet;
    }

    /**
     * 判断是否为时间格式
     *
     * @param str
     */
    public static boolean isTime(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        String regex = "(([0-1][0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static Date addMinute(Date date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    public static long compareDateTime(Object date1, Object date2) {
        if (date1 == null || date2 == null) {
            String msg = "illegal arguments,date1 and date2 must be not null.";
            throw new IllegalArgumentException(msg);
        }
        Date d1 = (Date) (date1 instanceof String ? convertDate((String) date1) : date1);
        Date d2 = (Date) (date2 instanceof String ? convertDate((String) date2) : date2);
        return d1.getTime() - d2.getTime();
    }

    public static long diffSeconds(Date date1, Date date2) {
        return ((date1.getTime() - date2.getTime()) / MILLIS_PER_SECOND);
    }

    /**
     * 获得当前年份
     * @return 年份
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static void main(String[] args) {
        System.out.println(getCurrentMonthLastDay(System.currentTimeMillis()));
    }

    private DateTimeUtil() {
        throw new UnsupportedOperationException("Unsupported new DateTimeUtil();");
    }
}
