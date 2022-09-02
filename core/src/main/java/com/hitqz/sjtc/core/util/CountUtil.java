package com.hitqz.sjtc.core.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import java.util.Date;

public final class CountUtil {

    public static String getRecentYearStartTime(String year){
        Date endTime;
        if(year == null || year == ""){
            endTime = new Date();
        }else{
            endTime = DateUtil.parse(year);
        }
        DateTime recentYear = DateUtil.offsetMonth(endTime,-12);
        return DateUtil.format(recentYear,"yyyy-MM-dd");
    }

    public static String getRecentYearEndTime(String year){
        if(year != null && year != ""){
            return year;
        }
        return DateUtil.today();
    }

    public static String getRecentWeekStartTime(String week){
        Date endTime;
        if(week == null || week == ""){
            endTime = new Date();
        }else{
            endTime = DateUtil.parse(week);
        }
        DateTime recentWeek = DateUtil.offsetDay(endTime,-7);
        return DateUtil.format(recentWeek,"yyyy-MM-dd");
    }


    public static String getRecentMonthStartTime(String month){
        Date endTime;
        if(month == null || month == ""){
            endTime = new Date();
        }else{
            endTime = DateUtil.parse(month);
        }
        DateTime recentMonth = DateUtil.offsetDay(endTime,-30);
        return DateUtil.format(recentMonth,"yyyy-MM-dd");
    }


    public static String getRecentMonthEndTime(String month){
        if(month != null && month != ""){
            return month;
        }
        return null;
    }

    public static String getRecentWeekEndTime(String week){
        if(week != null && week != ""){
            return week;
        }
        return DateUtil.today();
    }

    public static String getToday(){
        return DateUtil.today();
    }
}
