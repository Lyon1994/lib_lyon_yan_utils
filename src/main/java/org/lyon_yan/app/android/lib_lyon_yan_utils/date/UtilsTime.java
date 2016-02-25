package org.lyon_yan.app.android.lib_lyon_yan_utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by yanni on 2016/2/22.
 */
public class UtilsTime {
    private Calendar calendar=Calendar.getInstance();

    public UtilsTime(long milliseconds) {
        calendar.setTimeInMillis(milliseconds);
    }

    public UtilsTime(String milliseconds) {
        calendar.setTimeInMillis(Long.valueOf(milliseconds));
    }
    public String getFormat(String format){
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    public String getYear(){
        return getFormat("yyyy年");
    }

    public String getMonth(){
        return getFormat("MM月");
    }

    public String getDay(){
        return getFormat("dd日");
    }

    public String getDate(){
        return getFormat("yyyy年MM月dd日");
    }

    public String getHour(){
        return getFormat("HH");
    }

    public String getMin(){
        return getFormat("mm");
    }

    public String getSeconds(){
        return getFormat("ss");
    }

    public String getTime(){
        return getFormat("HH:mm:ss");
    }

    public String getDateAndTime(){
        return getFormat("yyyy年MM月dd日 HH:mm:ss");
    }
}
