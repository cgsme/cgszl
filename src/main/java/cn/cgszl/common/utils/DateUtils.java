package cn.cgszl.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    /**
     * 默认日期格式
     */
    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认中文日期格式
     */
    public static String CHN_DATE_FORMAT = "yyyy年MM月dd日";

    /**
     * 默认时间格式(24小时制)
     */
    public static String DEFAULT_TIME_FORMAT = "HH:mm";

    /**
     * 完整时间格式，包含秒数
     */
    public static String FULL_TIME_FORMAT = "HH:mm:ss";


    /**
     * 返回该月可能的最大日期。
     * <p>
     * Date date = new Date(); Date new = DateUtils.actualMaximumDate(date);
     * <p>
     * 例如传入的日期为2008年4月1日， 则返回的日期是2008年4月30日。
     *
     * @param date 指定日期
     * @return 返回最大日期
     */
    public static Date actualMaximumDate(Date date) {
        Calendar calendar = calendar(date);
        int actualMaximumDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, actualMaximumDay);
        return calendar.getTime();
    }

    /**
     * 获取该月的最大天数
     *
     * @param date 指定日期
     * @return 返回最大天数
     */
    public static int getMaxDayOfMonth(Date date) {
        Calendar calendar = calendar(date);
        int actualMaximumDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return actualMaximumDay;
    }

    /**
     * 返回该月可能的最小日期。
     * <p>
     * Date date = new Date(); Date new = DateUtils.actualMinimumDate(date);
     * <p>
     * 例如传入的日期为2008年4月20日， 则返回的日期是2008年4月1日。
     *
     * @param date 指定日期
     * @return 返回最小日期
     */
    public static Date actualMinimumDate(Date date) {
        Calendar calendar = calendar(date);
        int actualMininumDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, actualMininumDay);
        return calendar.getTime();
    }

    /**
     * 获取制定日期的月数差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 月数差 int
     */
    public static int monthBetween(Date startDate, Date endDate) {
        int months = 0;
        Calendar startDay = calendar(startDate);
        Calendar endDay = calendar(endDate);

        int startYear = startDay.get(Calendar.YEAR);
        int startMonth = startDay.get(Calendar.MONTH);

        int endYear = endDay.get(Calendar.YEAR);
        int endMonth = endDay.get(Calendar.MONTH);

        months = (endYear - startYear) * 12 + (endMonth - startMonth);
        return months;
    }

    /**
     * 将指定日期转换为相应的Calendar对象
     * <p>
     * Date date = new Date(); Calendar calendar = DateUtils.calendar(date);
     *
     * @param date
     * @return
     */
    public static Calendar calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 返回一个指定日期的Calendar实例
     *
     * @param date
     * @return
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 返回指定日期的默认格式字符串输出
     *
     * @param date 指定日期
     * @return
     */
    public static String getTimeByCustomPattern(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 返回天
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回月
     *
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回指定日期的年份
     *
     * @param date 指定日期 Date
     * @return 返回对应年份
     */
    public static int getYear(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回指定日期的年份
     *
     * @param dateStr 指定日期 String
     * @return 返回对应年份
     */
    public static int getYear(String dateStr) {
        Date date = parseToDate(dateStr, DEFAULT_DATE_FORMAT);
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 字符串转成日期
     *
     * @param date    日期字符串
     * @param pattern 日期格式
     * @return 日期对象 Date
     */
    public static Date parseToDate(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式转换错误", e);
        }
    }

    /**
     * 日期字符串转成长整型
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return 返回日期对应的长整型
     */
    public static long parseDateStrToTime(String dateStr, String pattern) {
        try {
            Date date = new SimpleDateFormat(pattern).parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("日期格式转换错误", e);
        }
    }

    /**
     * 日期转成字符串
     *
     * @param date    日期对象
     * @param pattern 日期格式
     * @return 日期字符串 String
     */
    public static String parseToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 返回两个日期相关天数
     *
     * @param begStr 开始日期
     * @param endStr 结束日期
     * @return 返回天数
     */
    public static long getDiffDay(String begStr, String endStr) {
        Date begDate = parseToDate(begStr, DEFAULT_DATE_FORMAT);
        Date endDate = parseToDate(endStr, DEFAULT_DATE_FORMAT);
        long l = endDate.getTime() - begDate.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 日期的比较 ，当开始日期大于结束日期时，会互换
     *
     * @param begDate 开始日期
     * @param endDate 结束日期
     */
    private static void compareDate(Date begDate, Date endDate) {
        Date temp = null;
        if (begDate.getTime() > endDate.getTime()) {
            temp = begDate;
            begDate = endDate;
            endDate = temp;
        }
    }

    /**
     * 日期的比较 ，当开始日期大于结束日期时，会互换
     *
     * @param begStr 开始日期
     * @param endStr 结束日期
     */
    private static void compareDate(String begStr, String endStr) {
        String temp = null;
        Date begDate = parseToDate(begStr, DEFAULT_DATE_FORMAT);
        Date endDate = parseToDate(endStr, DEFAULT_DATE_FORMAT);
        if (begDate.getTime() > endDate.getTime()) {
            temp = begStr;
            endStr = begStr;
            begStr = temp;
        }
    }

    /**
     * 对指定日期增加指定天数  若day为0返回date
     *
     * @param date 指定日期 String
     * @param day  天数 int
     * @return 返回增加后的日期 String
     */
    public static String addDay(String date, int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            if (0 == day) {
                return date;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            calendar.add(Calendar.DATE, day);// 增加数天

            return simpleDateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw new RuntimeException("日期新增天数错误", e);
        }
    }

    /**
     * 对指定日期时间，精确到秒，增加指定天数  若day为0返回date
     *
     * @param date 指定日期 String
     * @param day  天数 int
     * @return 返回增加后的日期 String
     */
    public static String addDayToDateTime(String date, int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT +
                " " + FULL_TIME_FORMAT);
        try {
            if (0 == day) {
                return date;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            calendar.add(Calendar.DATE, day);// 增加数天

            return simpleDateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw new RuntimeException("日期新增天数错误", e);
        }
    }

    /**
     * 对指定日期增加指定天数  若day为0返回date
     *
     * @param date 指定日期 Date
     * @param day  天数 int
     * @return 返回增加后的日期
     */
    public static Date addDay(Date date, int day) {
        try {
            if (0 == day) {
                return date;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, day);// 增加数天

            return calendar.getTime();
        } catch (Exception e) {
            throw new RuntimeException("日期新增天数错误", e);
        }
    }

    /**
     * 获取日期差返回 月份中的最大日期List<Date>
     *
     * @param begDate 起始日期 Date
     * @param endDate 终止日期 Date
     * @return 月份中的最大日期 List<Date>
     */
    public static List<Date> getDate(Date begDate, Date endDate) {
        int betweenMonth = monthBetween(begDate, endDate);
        int begMonth = getMonth(begDate);
        int years = getYear(begDate);// 年
        String dates = "";
        String months = "";
        int month = 0; // 月
        int year = 12;// 计算月份用 年
        List<Date> result = new ArrayList<Date>();
        for (int i = begMonth; i <= begMonth + betweenMonth; i++) {
            month = i;// 从当前月开始
            if (i > 12) {
                month = i - year;// 大于12,重置为1,起始年加1
                if (month == 1) {
                    years += 1;
                }
                if (month >= 12) {
                    year += 12;// 增加1年,为计算月份用
                }
            }
            if (month < 10) {
                months = "-0" + month;
            } else {
                months = "-" + month;
            }
            dates = years + months + "-01";
            result.add(DateUtils.actualMaximumDate(DateUtils.parseToDate(dates,
                    DEFAULT_DATE_FORMAT)));
        }
        return result;
    }

    /**
     * 获取日期差返回区间中的日期List<String>
     *
     * @param begStr 起始日期 String
     * @param endStr 终止日期 String
     * @return 返回日期列表 List<String>
     */
    public static List<String> getDateStrList(String begStr, String endStr) {
        List<String> result = new ArrayList<String>();
        compareDate(begStr, endStr);
        long diffDay = getDiffDay(begStr, endStr);
        if (diffDay > 0) {
            for (int i = 0; i <= diffDay; i++) {
                String newDate = addDay(begStr, i);
                result.add(newDate);
            }
        }
        return result;
    }

    /**
     * 获取日期差返回区间中的日期List<String>
     *
     * @return 返回日期列表 List<String>
     */
    public static List<String> getDateStrList(long begTime, long endTime) {
        List<String> result = new ArrayList<String>();
        if (begTime >= endTime) {
            return null;
        }
        Date begDate = new Date(begTime);
        Date endDate = new Date(endTime);

        result = getDateStrList(begDate, endDate);

        return result;
    }

    /**
     * 根据时间毫秒数获取对应的日期(yyyy-MM-dd)
     *
     * @param time
     * @return
     */
    public static String getDateFromTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取日期差返回区间中的日期List<String>
     *
     * @param begDate 起始日期  Date
     * @param endDate 终止日期  Date
     * @return 返回日期列表 List<String>
     */
    public static List<String> getDateStrList(Date begDate, Date endDate) {
        List<String> result = new ArrayList<String>();
        compareDate(begDate, endDate);
        String begStr = parseToString(begDate, DEFAULT_DATE_FORMAT);
        String endStr = parseToString(endDate, DEFAULT_DATE_FORMAT);
        long diffDay = getDiffDay(begStr, endStr);
        if (diffDay >= 0) {
            for (int i = 0; i <= diffDay; i++) {
                String newDate = addDay(begStr, i);
                result.add(newDate);
            }
        }
        return result;
    }

    /**
     * 获取两个日期的相差天数
     *
     * @param begLong 开始日期毫秒数
     * @param endLong 结束日期毫秒数
     * @param dayHour 一天按几个小时算 范围(0,24]
     * @return 返回相差的天数 long
     */
    public static long diffDay(long begLong, long endLong, int dayHour) {
        if (0 >= dayHour || 24 < dayHour) {
            dayHour = 24;
        }
        long diffDay = 0;
        if (begLong > endLong) {
            return diffDay;
        }

        diffDay = (endLong - begLong) / (dayHour * 60 * 60 * 1000);

        return diffDay;
    }

    /**
     * 计算两个时间的分钟数差(24小时制的)
     *
     * @param begStr 开始时间 String
     * @param endStr 结束时间 String
     * @return 返回两个时间的分钟数差 long
     */
    public static long diffTime(String begStr, String endStr) {
        long result = 0l;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
            Date begDate = dateFormat.parse(begStr);
            Date endDate = dateFormat.parse(endStr);
            result = (endDate.getTime() - begDate.getTime()) / (60 * 1000);
        } catch (ParseException e) {
            throw new RuntimeException("时间格式转换错误", e);
        }
        return result;
    }

    /**
     * 计算两个时间的毫秒数差(24小时制的)
     *
     * @param begStr 开始时间，如：12:01:30
     * @param endStr 结束时间，如：13:30:00
     * @return 返回两个时间的毫秒数差 long
     */
    public static long getDiffMills(String begStr, String endStr) {
        long result = 0l;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FULL_TIME_FORMAT);
            Date begDate = dateFormat.parse(begStr);
            Date endDate = dateFormat.parse(endStr);
            result = endDate.getTime() - begDate.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("时间格式转换错误", e);
        }
        return result;
    }

    /**
     * 获取一个日期是一星期中第几天，星期天为第一天，星期五为最后一天
     *
     * @param dateStr 指定日期(yyyy-MM-dd)
     * @return 返回该日期在一星期中的第几天
     */
    public static int getWeekDay(String dateStr) {
        int result = 0;
        Date date = parseToDate(dateStr, DEFAULT_DATE_FORMAT);
        Calendar calendar = getCalendar(date);
        result = calendar.get(Calendar.DAY_OF_WEEK);
        return result;
    }

    /**
     * 通过毫秒数获取日期
     *
     * @param time 指定毫秒数
     * @return 返回日期 String
     */
    public static String getDateStrByMills(long time) {
        String dateStr = "";
        Date date = new Date(time);
        dateStr = parseToString(date, DEFAULT_DATE_FORMAT);
        return dateStr;
    }

    /**
     * 通过毫秒数获取日期中时间,如：17:30
     *
     * @param time 指定毫秒数
     * @return 返回日期中时间 String
     */
    public static String getTimeStrByMills(long time) {
        String timeStr = "";
        Date date = new Date(time);
        timeStr = parseToString(date, DEFAULT_TIME_FORMAT);
        return timeStr;
    }

    /**
     * 通过毫秒数获取日期中完整时间,如：17:30:20
     *
     * @param time 指定毫秒数
     * @return 返回日期中完整的时间 String
     */
    public static String getFullTimeStrByMills(long time) {
        String timeStr = "";
        Date date = new Date(time);
        timeStr = parseToString(date, FULL_TIME_FORMAT);
        return timeStr;
    }

    /**
     * 时间加减方法
     *
     * @param date 时间
     * @param type 用于计算的部分（1代表用于计算的部分是年，值来自Calendar.YEAR）
     * @param i    计数的参数(正数为加法，负数为减法)
     * @return 计算后的时间
     * @author lfeng
     * @since 2016-04-06
     */
    public static Date addOrMinusDate(Date date, int type, int time) {
        Date rtn = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, time);
        rtn = cal.getTime();
        return rtn;
    }
}
