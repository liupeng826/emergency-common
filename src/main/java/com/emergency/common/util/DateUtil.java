package com.emergency.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关工具
 * @author yujl
 */
public class DateUtil {

    /** 单例 */
    private static final DateUtil instance = new DateUtil();

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_INT = "yyyyMMdd";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    public static final String DATE_FORMAT_HAS_TIME="yyyyMMddHHmmss";

    public static final String DATE_FORMAT_MONTH_DAY="MM月dd日";

    public static final String DATE_FORMAT_YEAR="yyyy年MM月dd日";

    public static final String DATE_FORMAT_YEAR_MONTH_DAY="yyyy/MM/dd";

    public static final String DATE_FORMAT_ONLG_YEAR="yyyy";
    public static final String DATE_FORMAT_ONLG_YEAR_MONTH="yyyy-MM";

    public static final long S = 1000;
    public static final long M = 1000 * 60;
    public static final long H = 1000 * 60 * 60;
    public static final long D = 1000 * 60 * 60 * 24;

    // 时间相关参数
    private static final long HOURS_PER_DAY = 24;
    private static final long MINUTES_PER_HOUR = 60;
    private static final long SECONDS_PER_MINUTE = 60;
    private static final long MILLIONSECONDS_PER_SECOND = 1000;
    private static final long MILLIONSECONDS_PER_MINUTE = MILLIONSECONDS_PER_SECOND
            * SECONDS_PER_MINUTE;
    private static final long MILLIONSECONDS_PER_HOUR = MILLIONSECONDS_PER_SECOND
            * SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    private static final long MILLION_SECOND_PER_DAY = HOURS_PER_DAY
            * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILLIONSECONDS_PER_SECOND;


    /**
     * 获取系统当前时间
     * @return 系统当前时间 java.sql.Timestamp
     */
    public static Timestamp currentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取系统当前时间戳
     * @return 时间戳
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static Calendar getZeroCalendar(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }


    /*
     * 重置 Date 中的 时/分/秒 为 0.
     */
    public static Date resetTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date resetMonthDate(Date date) {
        date = resetTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    /**
     * 将 时/分/秒 设为最后的时间。Time 为 23:59:59.999
     */
    public static Date setLastTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    public static Date setLastMonthDate(Date date) {
        date = setLastTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    public static String getWeek(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        String week = weekDays[w];
        return week;
    }

    public static String toDateStr(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }

    public static String toDateTimeStr(Date date) {
        if (date == null){
            return "";
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }





    /**
     * 取时间的增量
     */
    public static String getAddDate(Date date, int addDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, addDay);
        return formatDate(cal.getTime());
    }

    /**
     * 取得该类唯一实例
     *
     * @return 该类唯实例
     */
    public static DateUtil instance() {
        return instance;
    }

    /**
     * 获得当天的格式化日期。
     *
     * @return
     */
    public static String getCurrentDateString() {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(new Date());
    }


    public static String getCurrentTimeString() {
        return new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(new Date());
    }

    public static int getDateId() {
        return Integer.parseInt(new SimpleDateFormat(DATE_FORMAT_INT)
                .format(new Date()));
    }

    public static int getDateId(Date date) {
        return Integer.parseInt(new SimpleDateFormat(DATE_FORMAT_INT)
                .format(date));
    }

    public static int getDateId(int val) {
        Date date = getPastDay(new Date(), val);
        return Integer.parseInt(new SimpleDateFormat(DATE_FORMAT_INT).format(date));
    }

    public static int getPastDateId(int dateId, int val) {
        Date date = getPastDay(
                getDate(String.valueOf(dateId), DATE_FORMAT_INT, null), val);
        return Integer.parseInt(new SimpleDateFormat(DATE_FORMAT_INT)
                .format(date));
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为日期对象
     *
     * @param date
     *            待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date) {
        return getDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为日期对象
     *
     * @param date
     *            待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */

    public static Date getDateTime(String date) {
        return getDate(date, DEFAULT_DATETIME_FORMAT, null);
    }


    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date
     *            待转换字符串
     * @param format
     *            日期格式
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date, String format) {
        return getDate(date, format, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date
     *            日期对象
     * @param format
     *            日期格式
     * @param defVal
     *            转换失败时的默认返回值
     * @return 转换后的日期对象
     */
    public static Date getDate(String date, String format, Date defVal) {
        if (StringUtil.isEmpty(date) || StringUtil.isEmpty(format))
            return null;
        Date d;
        try {
            d = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            d = defVal;
        }
        return d;
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd格式的字符串
     *
     * @param date
     *            待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT, null);
    }
    /**
     * 将日期对象格式化成yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date
     *            待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String forDatetime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_FORMAT, null);
    }

    /**
     * 将日期对象格式化成HH:mm:ss格式的字符串
     *
     * @param date
     *            待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatTime(Date date) {
        return formatDate(date, DEFAULT_TIME_FORMAT, null);
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date
     *            待格式化日期对象
     * @param format
     *            格式化格式
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date, String format) {
        return formatDate(date, format, null);
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date
     *            待格式化日期对象
     * @param format
     *            格式化格式
     * @param defVal
     *            格式化失败时的默认返回值
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date, String format, String defVal) {
        if (date == null || StringUtil.isEmpty(format))
            return defVal;
        String ret;
        try {
            ret = new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            ret = defVal;
        }
        return ret;
    }

    /**
     * 获得两个日期之间相差的天数(返回值去掉了小数部分，即只返回)。（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return 返回两个日期之间的天数差，如果date1晚于date2，则返回正数，否则返回负数或者0
     */
    public static int intervalDays(Date date1, Date date2) {
        long intervalMillSecond = setToDayStartTime(date1).getTime()
                - setToDayStartTime(date2).getTime();

        // 相差的天数 = 相差的毫秒数 / 每天的毫秒数 (小数位采用去尾制)
        return (int) (intervalMillSecond / MILLION_SECOND_PER_DAY);
    }

    /**
     * @param time1
     * @param time2
     * @return 返回两个日期之间的天数差，如果date1晚于date2，则返回正数，否则返回负数或者0
     */
    public static int intervalDays(long time1, long time2) {
        Date date1 = new Date(time1);
        Date date2 = new Date(time2);
        long intervalMillSecond = setToDayStartTime(date1).getTime()
                - setToDayStartTime(date2).getTime();
        return (int) (intervalMillSecond / MILLION_SECOND_PER_DAY);
    }

    /**
     * 将时间调整到当天的0：0：0
     *
     * @param date
     * @return
     */
    public static Date setToDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 将时间调整到当天的0：0：0
     *
     * @param date
     * @return
     */
    public static void updateToDayStartTime(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            date.setTime(calendar.getTimeInMillis());
        }

    }

    public static int intervalHours(long time1, long time2) {
        return (int) ((time2 - time1) / MILLIONSECONDS_PER_HOUR);
    }

    /**
     * 获得两个日期之间相差的小时数。（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return 返回两个日期之间相差的小时数。
     */
    public static int intervalHours(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        // 相差的小时数 = 相差的毫秒数 / 每小时的毫秒数 (抛弃剩余的分钟数)
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_HOUR);
    }

    /**
     * 获得两个日期之间相差的分钟数。（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return 返回两个日期之间相差的分钟数。
     */
    public static int intervalMinutes(Date date1, Date date2) {
        return intervalMinutes(date1.getTime(), date2.getTime());
    }

    /**
     * 获得两个日期之间相差的分钟数。（timeMillis1 - timeMillis2）
     *
     * @param timeMillis1
     * @param timeMillis2
     * @return
     */
    public static int intervalMinutes(long timeMillis1, long timeMillis2) {
        long intervalMillSecond = timeMillis1 - timeMillis2;

        // 相差的分钟数 = 相差的毫秒数 / 每分钟的毫秒数 (小数位采用进位制处理，即大于0则加1)
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_MINUTE + (intervalMillSecond
                % MILLIONSECONDS_PER_MINUTE > 0 ? 1 : 0));
    }

    /**
     * 获得两个日期之间相差的秒数。（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int intervalSeconds(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        return (int) (intervalMillSecond / MILLIONSECONDS_PER_SECOND + (intervalMillSecond
                % MILLIONSECONDS_PER_SECOND > 0 ? 1 : 0));
    }

    public static int intervalSeconds(long date1, long date2) {
        long intervalMillSecond = date1 - date2;
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_SECOND + (intervalMillSecond
                % MILLIONSECONDS_PER_SECOND > 0 ? 1 : 0));
    }

    /**
     * 取得过去的某个云上的日子
     *
     * @param mark
     *            基准时间点
     * @param interval
     *            离传入时间之前的天数
     * @return
     */
    public static Date getPastDay(Date mark, int interval) {
        Calendar c = Calendar.getInstance();
        c.setTime(mark);
        c.add(Calendar.DAY_OF_YEAR, -interval);

        return c.getTime();
    }

    /**
     * 取得未来某个绚丽的日子
     *
     * @param mark
     * @param interval
     * @return
     */
    public static Date getFutureDay(Date mark, int interval) {
        Calendar c = Calendar.getInstance();
        c.setTime(mark);
        c.add(Calendar.DAY_OF_YEAR, interval);

        return c.getTime();
    }

    /**
     * 取得未来几个小时后的时间
     *
     * @param mark
     * @param interval
     * @return
     */
    public static Date getFutureHour(Date mark, int interval) {
        Calendar c = Calendar.getInstance();
        c.setTime(mark);
        c.add(Calendar.HOUR, interval);

        return c.getTime();
    }

    /**
     * 时间检测器
     *
     * @param date
     *            被检测时间
     * @return
     */
    public static String timeCheck(Date date) {
        String time_desc;
        int num;
        long check = Math.abs(System.currentTimeMillis() - date.getTime());
        if (check < M) {
            num = (int) (check / S);
            time_desc = String.valueOf(num) + "秒";
        } else if (check >= M && check < H) {
            num = (int) (check / M);
            time_desc = String.valueOf(num) + "分钟";
        } else if (check >= H && check < D) {
            num = (int) (check / H);
            time_desc = String.valueOf(num) + "小时";
            num = (int) (check % H / M);
            time_desc += String.valueOf(num) + "分钟";
        } else if (check >= D && check < 8 * D) {
            num = (int) (check / D);
            time_desc = String.valueOf(num) + "天";
        } else {
            time_desc = new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
        }

        return time_desc;
    }

    /**
     * 时间检测器哟
     *
     * @param date
     *            被检测时间
     * @return
     */
    public static String timeDiffCheck(Date date) {
        String time_desc = timeCheck(date);
        long diff = System.currentTimeMillis() - date.getTime();
        if (diff != 0) {
            time_desc += diff > 0 ? "前" : "后";
        }
        return time_desc;
    }

    /**
     * 体力回复时间检测
     *
     * @param date
     *            上次恢复时间点
     * @return
     */
    public static String energyTiemCheck(int regainTime, Date date) {

        long interval = System.currentTimeMillis() - date.getTime();
        long remain = regainTime * S - interval; // 剩余时间
        return getTimeRemainString(remain);
    }

    public static String getTimeRemainString(long remain) {
        String desc;
        int num;
        if (remain < M) {
            num = (int) (remain / S);
            desc = String.valueOf(num) + "秒后";
        } else if (remain >= M && remain < H) {
            int min = (int) (remain / M);
            desc = String.valueOf(min) + "分";
            long sec = remain % M;
            desc += String.valueOf(sec / S) + "秒后";
        } else {
            int hour = (int) (remain / H);
            desc = String.valueOf(hour) + "小时";
            long min = remain % H;
            desc += String.valueOf(min / M) + "分后";
        }

        return desc;
    }

    /**
     * 根据偏移量计算一个变更后的date时间
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date changeDate(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, amount);
        return cal.getTime();
    }

    /**
     * 计算两个日期段相交的秒数
     *
     * @param s1
     *            第一段日期的开始时间
     * @param e1
     *            第一段日期的结束时间
     * @param s2
     *            第二段日期的开始时间
     * @param e2
     *            第二段日期的结束时间
     * @return
     */
    public static int intersectionSeconds(Date s1, Date e1, Date s2, Date e2) {
        if (s1.after(e1) || s2.after(e2)) {
            throw new IllegalArgumentException(
                    "Date s1(s2) must before e1(e2).");
        }

        Date s = s1.before(s2) ? s2 : s1;
        Date e = e1.before(e2) ? e1 : e2;

        return e.before(s) ? 0 : intervalSeconds(e, s);
    }

}
