package com.example.demo1.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 * Java8时间方法LocalDate和LocalDateTime
 *
 * @Author: chenping
 * @Date: 2019/7/24
 */
public class DateUtils {

    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMSSss = "yyyyMMddHHmmss";
    public static final String YYMM = "yyMM";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYY年MM月 = "yyyy年MM月";
    public static final String DAY_LAST_TIME = "23:59:59";
    public static final String DAY_FIRST_TIME = "00:00:00";

    /**
     * 获取系统当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取系统当前日期
     * LocalDateTime日期
     *
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前日期的年份
     */
    public static int getCurrentYear() {
        return LocalDateTime.now().getYear();
    }

    /**
     * 获取当前日期的月份
     */
    public static int getCurrentMonth() {
        return LocalDateTime.now().getMonthValue();
    }

    /**
     * 获取当天日期
     *
     * @return
     */
    public static int getCurrentDay() {
        return LocalDateTime.now().getDayOfMonth();
    }

    /**
     * 该对象表示的日期是今年第几天
     *
     * @return
     */
    public static int getDayOfYear() {
        return LocalDateTime.now().getDayOfYear();
    }

    /**
     * 获取当前月的总天数
     *
     * @return
     */
    public static int daysOfMonth() {
        return LocalDate.now().lengthOfMonth();
    }

    /**
     * 根据时间格式返回对应的String类型的时间
     *
     * @param format
     * @return
     */
    public static String getCurDateTime(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String dataTime = now.format(dateTimeFormatter);
        return dataTime;
    }

    /**
     * 判断是否是今天
     *
     * @param strDate 格式为 yyyy-MM-dd
     * @return
     */
    public static boolean isCurrentDay(String strDate) {
        boolean bRet = false;
        LocalDate strLocalDate = LocalDate.parse(strDate);
        if (LocalDate.now().getYear() == strLocalDate.getYear()) {
            MonthDay monthDay = MonthDay.from(strLocalDate);
            MonthDay today = MonthDay.from(LocalDate.now());
            return monthDay.equals(today);
        }
        return bRet;
    }

    /**
     * 获取当前时间几小时后的时间
     *
     * @param hour
     * @param format 格式为 HH_MM_SS
     * @return 输出格式为 HH_MM_SS
     */
    public static String getAfterNHoursDateTime(int hour, String format) {
        LocalTime localTime = LocalTime.now().plusHours(hour);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String dataTime = localTime.format(dateTimeFormatter);
        return dataTime;
    }


    /**
     * 当前日期时间戳(YYYYMMDDHHMMSSssss)
     *
     * @return
     */
    public static String getTimeStamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSSss);
        return now.format(dateTimeFormatter);
    }

    /**
     * 日期转字符串
     *
     * @param thedate
     * @param format
     * @return
     */
    public static String parseDateToString(Date thedate, String format) {
        if (thedate != null) {
            Instant instant = thedate.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(dateTimeFormatter);
        }
        return null;
    }

    /**
     * 字符串转日期
     *
     * @return Date
     */
    public static Date parseStringToDate(String thedate, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        Date dd1 = null;
        try {
            dd1 = sdf.parse(thedate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dd1;
    }

    /**
     * 由String型日期转成format形式String
     *
     * @param format1 原先格式
     * @param format2 转化格式
     * @return String
     */
    public static String changeFormatDateString(String format1, String format2, String strDate) {
        if (strDate == null) {
            return "";
        }
        return parseDateToString(parseStringToDate(strDate, format1), format2);
            
    }

    /**
     * 将一种类型的时间字符串转换为另一种类型的时间字符串
     *
     * @param strDate
     * @param format
     * @return
     */
//     public static String formatStrdateToStrdate(String strDate, String format) {
//         if (strDate == null) {
//             return "";
//         }
//         Date date = parseStringToDate(strDate, format);
//         return parseDateToString(date, format);
//     }

    /**
     * 得到当前日期的前N天时间 yyyymmdd
     *
     * @param format
     * @param day
     * @return
     */
    public static String beforeNDaysDate(String format, int day) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        if (day > 0) {
            return LocalDateTime.now().minusDays(day).format(dateTimeFormatter);
        }
        return null;
    }

    /**
     * 获得指定时间N个月后的日期
     * theDate 日期 与format格式保持一致
     * int month 月数
     * format 格式
     */
    public static String afterNMonthDate(String theDate, int month, String format) {
        String preDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(theDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        c.setTime(date);
        int months = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month + months);
        preDate = sdf.format(c.getTime());
        return preDate;
    }

    /**
     * @description:  获取指定日期的后n天
     * @param: strData
     * @param: days
     * @param: format 日期格式，与strData格式一致
     * @return: java.lang.String
     * @author: chenping
     * @date: 2019/11/28
     */
    public static String getAfterNDaysDate(String strData, int days, String format) {
        String preDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(strData);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 + days);
        preDate = sdf.format(c.getTime());
        return preDate;
    }

    /**
     * @Description:  得到指定时间N小时后的日期
     * @param: theDate
     * @param: nHour
     * @param: format
     * @return: java.lang.String
     * @Author: chenping
     * @Date: 2020/4/4
     */
    public static String getAfterNHoursDate(String theDate, Integer nHour, String format) {
        String preDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(theDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        c.setTime(date);
        int hours = c.get(Calendar.HOUR);
        c.set(Calendar.HOUR, hours + nHour);
        preDate = sdf.format(c.getTime());
        return preDate;
    }

    /**
     * @Description:  得到指定时间N分钟后的日期
     * @param: theDate
     * @param: nMinute
     * @param: format
     * @return: java.lang.String
     * @Author: chenping
     * @Date: 2020/4/4
     */
    public static String getAfterNMinutesDate(String theDate, Integer nMinute, String format) {
        String preDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(theDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        c.setTime(date);
        int minutes = c.get(Calendar.MINUTE);
        c.set(Calendar.MINUTE, minutes + nMinute);
        preDate = sdf.format(c.getTime());
        return preDate;
    }

    /**
     * @Description:  得到指定时间N秒后的日期
     * @param: theDate
     * @param: second
     * @param: format
     * @return: java.lang.String
     * @Author: chenping
     * @Date: 2020/4/4
     */
    public static String getAfterNSecondsDate(String theDate, Integer second, String format) {
        String preDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(theDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        c.setTime(date);
        int seconds = c.get(Calendar.SECOND);
        c.set(Calendar.SECOND, second + seconds);
        preDate = sdf.format(c.getTime());
        return preDate;
    }
    
    /**
     * 比较两个字符串格式日期大小,带格式的日期
     *
     * @param strdat1
     * @param strdat2
     * @param format
     * @return
     */
    public static boolean isBefore(String strdat1, String strdat2, String format) {
        try {
            Date dat1 = parseStringToDate(strdat1, format);
            Date dat2 = parseStringToDate(strdat2, format);
            return dat1.before(dat2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 比较两个字符串格式日期大小,带格式的日期
     * 返回相差的毫秒数
     * @param strdat1
     * @param strdat2
     * @param format
     * @return
     */
    public static long isBefore_int(String strdat1, String strdat2, String format) {
        long result = 0;
        try {
            Date dat1 = parseStringToDate(strdat1, format);
            Date dat2 = parseStringToDate(strdat2, format);
            return dat2.getTime() - dat1.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /*
     * 得到上一个月或者下一个月的日期
     */
    public static String getDayafterMonth(String theDate, int month, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatStr);
        return LocalDateTime.parse(theDate).plusMonths(month).format(dateTimeFormatter);
    }

    /**
     * 将秒转换为小时分秒等
     *
     * @param sec
     * @return
     */
    public String changeTime(int sec) {
        String temp = "";
        if (sec < 60) {
            temp = "" + sec + "秒";
        } else if (sec < 3600) {
            temp = "" + sec / 60 + "分" + sec % 60 + "秒";
        } else {
            temp = "" + sec / 3600 + "小时" + (sec % 3600) / 60 + "分" + sec % 60 + "秒";
        }
        return temp;
    }

    /**
     * 方法描述:
     * 计算两个日期相差天数
     *
     * @param end   结束日期
     * @param start 开始日期
     */
    public static int getSubDays(String end, String start) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        Long between = ChronoUnit.DAYS.between(startDate, endDate);
        return between.intValue();
    }

    /**
     * 获取两个时间相差的时间
     *
     * @param time1
     * @param time2
     * @return
     * @throws Exception
     */
    public static String getTimeDiff(Date time1, Date time2) throws Exception {
        long l = time1.getTime() - time2.getTime();
        String returnStr = "";
        long day = l / (24 * 60 * 60 * 1000);
        if (day > 0) {
            returnStr += (day + "天");
        }
        long hour = (l / (60 * 60 * 1000) - day * 24);
        if (hour > 0) {
            returnStr += (hour + "小时");
        }
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        if (min > 0) {
            returnStr += (min + "分");
        }
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (s > 0) {
            returnStr += (s + "秒");
        }
        return returnStr;
    }

    public static void main(String[] args) {
        String time = "2020-04-01 11:12:13";
        String time1 = "2020-04-01";
        System.out.println(afterNMonthDate(time1,9,YYYY_MM_DD));
    }

}
