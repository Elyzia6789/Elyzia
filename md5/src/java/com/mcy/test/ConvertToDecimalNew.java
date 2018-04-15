package com.mcy.test;

import java.util.Scanner;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author Elyzia
 * Date:2018/02/07
 * @Version 1.0
 */
//写出一个程序，接受一个十六进制的数值字符串，输出该数值的十进制字符串。（多组同时输入 ）
public class ConvertToDecimalNew {

    public int singStringToDecimal(String s){
        String tempString = s;
        int sum =0;
        int tempInt;
        int len = tempString.length();
        for (int index =0;index<len;index++){
                char tempChar = tempString.charAt(len-1-index);
            if(tempChar >='0' && tempChar <= '9'){
               tempInt = tempChar - '0';
            }
            else if(tempChar >= 'a' && tempChar <='z'){
                tempInt = tempChar - 'a';
            }
            else if(tempChar >= 'A' && tempChar <= 'Z'){
                tempInt = tempChar - 'A';
            }
            else
                tempInt = 0;
            sum += tempInt* Math.pow(16,index);
        }
        return sum;
    }
    public static void main(String[] args) {
        ConvertToDecimalNew convertToDecimalNewImpl = new ConvertToDecimalNew();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String stringRow = input.nextLine();
            double tt =convertToDecimalNewImpl.singStringToDecimal(stringRow.substring(2));
            System.out.println(tt);
        }

    }
}
