package com.mcy.test;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author Elyzia
 * Date:2018/03/30
 * @Version 1.0
 */
/**
 * 下面的代码运行结果：

 start ADaemon...

 如果将main函数中的t.setDaemon(true);注释掉，运行结果如下：

 start ADaemon...

 This shoud be always run ?
 *
 * */
public class DamonThreadTest {

    public static void main(String[] args) {

        Thread tt = new Thread(new ADamon());
       // Thread tt = new Thread();
        tt.setDaemon(true);
        tt.start();

        System.out.println("sss");

    }



}

class ADamon implements Runnable{
    public void run(){
        try{
            System.out.println("Start ADamon...");
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println("报错");

        }finally {
            System.out.println("This should be always run ?");
        }
    }
}
