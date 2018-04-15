package com.mcy.test;

import java.util.Date;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author Elyzia
 * Date:2018/03/30
 * @Version 1.0
 */
public class TimerScheduleTest {
    Timer timer;
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
           Date tt = Calendar.getInstance().getTime();
            System.out.println("执行成功，当前时间：" + tt.toString());

        }
    };
    public TimerScheduleTest(int seconds){
        timer = new Timer();
        timer.schedule(timerTask,3000,seconds*1000);
    }


    public static void main(String[] args) {
        Date tt = Calendar.getInstance().getTime();
        System.out.println(tt.toString());
        new TimerScheduleTest(5);
    }
}
