package com.mcy.test;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author Elyzia
 * Date:2017/06/26
 * @Version 1.0
 */
//题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
//1.程序分析：兔子的规律为数列1,1,2,3,5,8,13,21....
    /*
public class Demo01 {
    public static void main(String args[]) {
        for (int i = 1; i <= 20; i++)
            System.out.println(f(i));
    }
    public static int f(int x) {
        if (x == 1||x == 2)
            return 1;
        else
            return f(x - 1) + f(x - 2);
    }
}
或
public class Demo01 {
    public static void main(String args[]) {
        math mymath = new math();
        for (int i = 1; i <= 20; i++)
            System.out.println(mymath.f(i));
    }
}
*/
public class Test {

    public static void main(String[] args) {

        //System.out.println(md.encrypt("15010111373", "fsdfjk2390jqIOJSDKL")
       // );
        Test aa = new Test();
        for(int i=101;i<201;i++){
         int j = aa.findSuShu(i);
            if (j != 0){
                    System.out.println(j);
            }

        }
    }

    public int findSuShu(int i){
        for(int k=2;k<=(i/2);k++){
            if (i % k == 0) return 0;
        }
        return i;
    }
/*
    public int calcRabbit(int i){

       int m,n;
        m=i;
        if(m==1| m==2){
            return 2;
        }
        else if((m-2)>=1){
            int k = 1;
            if((m-5) >= 1){

                for(int j=m;j>3;j--){
                    for (int kk =1;kk<m-8;kk++){
                        calcRabbitFinal(m-8);
                    }
                }

                return 1;

            }

        }
    }

    public int calcRabbitFinal(int aa){
        int sum =0;
        for(int i=1;i<aa;i++){
            sum +=i;
        }
        return sum;
    }
    */
}

