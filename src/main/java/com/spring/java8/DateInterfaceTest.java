package com.spring.java8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * @author:独角兽zhuojl
 * @date :2017/11/14
 * @saysth:
 */
public class DateInterfaceTest {
    public static void main(String[] args) {
        System.out.println(new Date().getYear());
        test();
//        System.out.println(stringArrToList("1,23,3",",",Integer::parseInt));
    }

    public static void test(){
        LocalDate localDate = LocalDate.now();
        localDate = LocalDate.of(2017,3,3);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth().getValue());
//        localDate.getChronology()
    }

}
