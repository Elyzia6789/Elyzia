package com.mcy.test;

import java.util.Scanner;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author Elyzia
 * Date:2018/02/05
 * @Version 1.0
 */
public class FindLastWordLength {
    public static void main(String[] args) {
        String aa;
        String s = "";
        Scanner abc = new Scanner(System.in);
        while (abc.hasNext()){
            aa = abc.nextLine();
            System.out.println(aa.length());
            System.out.println(aa.lastIndexOf(" "));
            System.out.println(aa.length() - 1 - aa.lastIndexOf(" "));
        }
    }
}
