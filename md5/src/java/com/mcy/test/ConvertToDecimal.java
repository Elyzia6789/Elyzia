package com.mcy.test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author Elyzia
 * Date:2018/02/05
 * @Version 1.0
 */
//写出一个程序，接受一个十六进制的数值字符串，输出该数值的十进制字符串。（多组同时输入 ）
public class ConvertToDecimal {

    public Integer singleCharToDecimal(char s){
        if ('A'== s) return 10;
        else if ('B'== s) return 11;
        else if ('C'== s) return 12;
        else if ('D'== s) return 13;
        else if ('E'== s) return 14;
        else if ('F'== s) return 15;
        else return 0;
    }

    public static void main(String[] args) {
        ConvertToDecimal convertToDecimalImpl = new ConvertToDecimal();
        Scanner input = new Scanner(System.in);
        int sToInt;
        while (input.hasNextLine()){
            String aa = input.nextLine();
            String[] strArray = aa.split(" ");
            //对数组中的每个字符串进行数值转换
            for (int index =0;index<strArray.length;index++){
                String tempString = strArray[index];
                //对于非0x开头的输入
                if(tempString.toUpperCase().indexOf("0X") !=0){
                    //一个字符串转换成十进制
                    Integer tempSum = 0;
                    for (int indexString =0;indexString<tempString.length();indexString++){

                        Integer tempSingleCharToDecimal;
                        char tempChar = tempString.charAt(indexString);
                        if (!Character.isDigit(tempChar)){
                         tempSingleCharToDecimal = convertToDecimalImpl.singleCharToDecimal(tempChar);
                        }else {
                            String temp = String.valueOf(tempChar);
                            tempSingleCharToDecimal = Integer.valueOf(temp).intValue();
                        }
                        tempSum = tempSum*16 + tempSingleCharToDecimal;
                    }

                   // sum.add(tempSum);
                    System.out.print(tempSum + " ");
                }
                else{
                    //对于0x开头的输入
                    Integer tempSum = 0;
                    for (int indexString =2;indexString<tempString.length();indexString++){

                        Integer tempSingleCharToDecimal;
                        char tempChar = tempString.charAt(indexString);
                        if (!Character.isDigit(tempChar)){
                            tempSingleCharToDecimal = convertToDecimalImpl.singleCharToDecimal(tempChar);
                        }else {
                            String temp = String.valueOf(tempChar);
                            tempSingleCharToDecimal = Integer.valueOf(temp).intValue();
                        }
                        tempSum = tempSum*16 + tempSingleCharToDecimal;
                    }

                  //  sum.add(tempSum);
                    System.out.print(tempSum+" ");
                }
               // System.out.println(sum.toString());

            }

        }
    }
}
