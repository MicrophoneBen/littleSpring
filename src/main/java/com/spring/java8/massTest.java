package com.spring.java8;

/**
 * @author:独角兽zhuojl
 * @date :2017/11/25
 * @saysth:
 */
public class massTest {
    public static void main(String[] args) {
        int i=0;
        while(i<5){
            if(i++%2==0){
                continue;
            }
            System.out.println(i);
        }
        System.out.println();
    }
}
