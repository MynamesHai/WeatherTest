package com.tksoft.weather2018.ui.details;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.tksoft.weather2018.services.LoginService;

import java.util.Calendar;

public class LoginNotificationUtils {
    private static int INDEX = 1;
    public static void create(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, LoginService.class);
        for (int i = 0; i < 3; i ++){
            intent.putExtra("KEY_TYPE", INDEX);
            PendingIntent pIntent = PendingIntent.getService(context, INDEX, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            INDEX ++;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, INDEX);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
            }else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
            }
        }
    }
}
