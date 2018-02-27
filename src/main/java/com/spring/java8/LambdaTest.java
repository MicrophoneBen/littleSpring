package com.spring.java8;

import org.junit.Test;
import org.springframework.util.StringUtils;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author:独角兽zhuojl
 * @date :2017/11/13
 * @saysth:
 */
public class LambdaTest {
    public static void main(String[] args) {
        test222();
//        testUtils();

    }
    public static void test222(){
        System.out.println(null instanceof TestClass);
        int[] ints = {1,2,3,4,5,6};
//        Arrays.stream(ints).map()

    }

    private static void test22(){
        TestClass tt1 = new TestClass();
        TestClass tt2 = new TestClass();
        System.out.println(tt1.equals(tt2));
        System.out.println(tt1==tt2);
        System.out.println(tt1.hashCode());
        System.out.println(tt2.hashCode());
        List<TestClass> testClasses = Arrays.asList(tt1,tt2);
        System.out.println(testClasses.stream().distinct().collect(Collectors.toList()).size());
    }

    private static void test11(ArrayList<TestClass> testClasses){
        testClasses.stream().sorted().reduce((testClass, testClass2) -> {testClass.getI();return testClass;});

    }

    private static void testUtils(){
        List<String> stringList = Collections.emptyList();
        System.out.println(stringList.size());
//        stringList.add("");
//        test11(Collections.emptyList());
    }

    private static void  test(functionalClass f){
        f.test();
    }
    private static void test(){
        //其实就是泛型，优化了匿名内部类
        test(() -> System.out.println(2));
        Optional<TestClass> optional = Optional.of(null);
        //Function接口只有一个apply方法，lambda表达式相当于就是实现的apply方法
        Function<TestClass,String> f = testClass -> testClass.i;
        optional.map(testClass -> testClass.i);
        //Supplier 同上 ，只有一个get方法，
        Supplier<TestClass> supplier = () ->new TestClass();
        optional.orElseGet(() -> new TestClass());
        //Predicate boolean test(T t);
        Predicate<TestClass> predicate = testClass -> true;
        optional.filter(testClass -> true);
        //Consumer  void accept(T t);
        Consumer<TestClass> testClassConsumer = testClass -> System.out.println();
        optional.ifPresent(testClass -> System.out.println(""));
    }

    String getString(TestClass testClass){
        return testClass.i;
    }


}
interface functionalClass{
    void test();
}

class TestClass{

    String i ="1";
    String getI(){
        return i;
    }

    @Override
    public boolean equals(Object obj) {
        return this.i.equals(((TestClass)obj).i);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}