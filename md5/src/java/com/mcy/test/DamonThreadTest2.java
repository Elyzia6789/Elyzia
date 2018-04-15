package com.mcy.test;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author Elyzia
 * Date:2018/03/30
 * @Version 1.0
 */
public class DamonThreadTest2 {

    public static void main(String[] args) throws InterruptedException{
        double i = 10.3511;
        int j = (int)i;
        BigDecimal k  = new BigDecimal(i).setScale(0,BigDecimal.ROUND_HALF_UP);
        System.out.println("i="+i+"\nj="+j+"\nk="+k);


        Thread tt = new Thread(new DaemonTest2());
        tt.setDaemon(true);
        tt.start();
        System.out.println("tt.isDamon()=" + tt.isDaemon());
        Date time = Calendar.getInstance().getTime();
        System.out.println("开始休眠时间:"+time.toString());
        TimeUnit.SECONDS.sleep(2);

    }

}

class DaemonTest2 implements Runnable {
    private Thread[] t = new Thread[10];

    public void run(){

        for(int i =0;i<t.length;i++){

            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            Date time = Calendar.getInstance().getTime();
            System.out.println("DaemonSpawn"+ i + "started..." + time.toString());
        }
        for(int i=0;i<t.length;i++){
            Date time = Calendar.getInstance().getTime();
            System.out.println("t[" +i +"].isDaemon()="+ t[i].isDaemon()+"." + time.toString());

        }
        while (true){
            Thread.yield();
        }

    }

}

class DaemonSpawn implements Runnable{
    public void run(){
        while(true){
//            Date time = Calendar.getInstance().getTime();
//            System.out.println("DaemonSpawn"+time.toString());
            Thread.yield();
        }
    }
}
