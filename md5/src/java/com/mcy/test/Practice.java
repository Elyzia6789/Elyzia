package com.mcy.test;

import java.util.Collections;
import java.util.Vector;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author 马春雨(cy.ma@zuche.com)
 * Date:2018/03/12
 * @Version 1.0
 */
public class Practice {

    //这种方式打印会出错，因为toString覆盖了Object的toString，同时this进行转换为字符串时，进入了一个递归调用，从而内存溢出，产生错误。
//    public String toString() {
//        System.out.print("practice:"+ this);
//        return "success";
//    }
    int test = 10;


    public double get(){
        return test;
    }



    public static void main(String[] args) {
        int j =3;
//        Practice practice = new Practice();
//        System.out.println(practice.toString());
//        for(int i=0;i<j;i++ ){
//            System.out.println(i);
//            i=0;
//            System.out.println(i);
//        }
        Vector<String> vc = new Vector<String>();
        vc.add("1");
        vc.add("2");
        vc.add("3");
        vc.add("4");
        vc.add("5");
        System.out.println("before:"+ vc);
        for(int i =0;i<5;i++)
        Collections.swap(vc,i,i);
        System.out.println("after:"+vc);
        Practice ss = new Practice();
        double testFirst = ss.get();
        System.out.println("int类型的test值："+ ss.test);
        System.out.println("double类型的testFirst值："+ testFirst);

        //double类型相除是double类型，int类型相除是int类型
        double c  = 20;
        double f;
        double g;
        g= 9.0/5.0;
        System.out.println("double类型相除的结果："+ g);
        g = 9/5;
        System.out.println("int类型相除的结果："+ g);
        g = 9.0/5;
        System.out.println("double类型除int类型的结果："+ g);
        g = 9/5.0;
        System.out.println("int类型除double类型的结果："+ g);
        f = 9.0/5.0*c + 32;
        System.out.println("double类型相除,然后乘double的结果："+f);
        f = 1/5*c + 32;
        System.out.println("int类型相除，然后乘double的结果：" + f);
        int degree = 0;
        double i = Math.PI/180 *degree++;
        System.out.println("i=" + i);



    }
}
