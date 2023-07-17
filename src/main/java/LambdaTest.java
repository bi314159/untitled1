import com.sun.source.util.Trees;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * ClassName: LambdaTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 毕研政
 * @Create 2023/5/15 10:41
 * @Version 1.0
 */
public class LambdaTest {
    @Test
    public void test1(){
        //不使用lambda表达式
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        //使用lambda表达式
        Runnable r2 = () -> System.out.println("hello");
    }
    
    @Test
    public void test2(){
        //不使用lambda表达式
        TreeSet<String> ts = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });
        //使用lambda表达式
        TreeSet<String> ts1 = new TreeSet<>((o1,o2) -> Integer.compare(o1.length(),o2.length())) ;
    }


    /**
     * 无参，无返回值
     */
    @Test
    public void test01(){
        //不使用lambda表达式
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hrllo");
            }
        };
        r1.run();
        //使用lambda表达式
        Runnable r2 = () -> {
            System.out.println("hello");
        };
        r2.run();
    }
    /**
     * 需要一个参数，无返回值
     */
    @Test
    public void test02(){
        //不使用lambda表达式
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("123");

        //使用lambda表达式
        Consumer<String> con1  = (String s) -> {
            System.out.println(s);
        };
        con1.accept("h");
        //使用lambda表达式,加上类型推断
        Consumer<String> con2 = (s) -> {
            System.out.println(s);
        };
        con2.accept("a");
        ////使用lambda表达式,加上类型推断,一个参数，()可以省略
        Consumer<String> con3 = s -> {
            System.out.println(s);
        };
    }
    /**
     * 2个及以上参数，有返回值，多条执行语句
     */
    @Test
    public void test03(){
        //不使用lambda表达式
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(12,21));
        //使用lambda表达式,加上类型推断
       Comparator<Integer> com2 = (o1,o2) -> {
           System.out.println(o1);
           System.out.println(o2);
           return o1.compareTo(o2);
       };
        System.out.println(com2.compare(12,6));
    }

    
    @Test
    public void test99(){
        List<String> list = Arrays.asList("java","python","c");
        list.forEach(s -> {
            System.out.println(s);
        });
    }

    @Test
    public void test11(){
        Supplier<String> supplier = () -> "尚硅谷";
        System.out.println(supplier.get());
    }


























    // 情况一：对象 :: 实例方法
    //Consumer中的void accept(T t)
    //PrintStream中的void println(T t)
    @Test
    public void test1213() {
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("北京");

        System.out.println("*******************");
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("beijing");
    }

    //Supplier中的T get()
    //Employee中的String getName()
//    @Test
//    public void test212312() {
//        Employee emp = new Employee(1001,"Tom",23,5600);
//
//        Supplier<String> sup1 = () -> emp.getName();
//        System.out.println(sup1.get());
//
//        System.out.println("*******************");
//        Supplier<String> sup2 = emp::getName;
//        System.out.println(sup2.get());
//
//    }

    // 情况二：类 :: 静态方法
    //Comparator中的int compare(T t1,T t2)
    //Integer中的int compare(T t1,T t2)
    @Test
    public void test31312() {
        Comparator<Integer> com1 = (t1,t2) -> Integer.compare(t1,t2);
        System.out.println(com1.compare(12,21));

        System.out.println("*******************");

        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(12,3));

    }

    //Function中的R apply(T t)
    //Math中的Long round(Double d)
    @Test
    public void test41231() {
        Function<Double,Long> func = new Function<Double, Long>() {
            @Override
            public Long apply(Double d) {
                return Math.round(d);
            }
        };

        System.out.println("*******************");

        Function<Double,Long> func1 = d -> Math.round(d);
        System.out.println(func1.apply(12.3));

        System.out.println("*******************");

        Function<Double,Long> func2 = Math::round;
        System.out.println(func2.apply(12.6));
    }

    // 情况三：类 :: 实例方法  (有难度)
    // Comparator中的int comapre(T t1,T t2)
    // String中的int t1.compareTo(t2)
    @Test
    public void test544() {
        Comparator<String> com1 = (s1,s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("abc","abd"));

        System.out.println("*******************");

        Comparator<String> com2 = String :: compareTo;
        System.out.println(com2.compare("abd","abm"));
    }

    //BiPredicate中的boolean test(T t1, T t2);
    //String中的boolean t1.equals(t2)
    @Test
    public void test644() {
        BiPredicate<String,String> pre1 = (s1,s2) -> s1.equals(s2);
        System.out.println(pre1.test("abc","abc"));

        System.out.println("*******************");
        BiPredicate<String,String> pre2 = String :: equals;
        System.out.println(pre2.test("abc","abd"));
    }

    // Function中的R apply(T t)
    // Employee中的String getName();
//    @Test
//    public void test744() {
//        Employee employee = new Employee(1001, "Jerry", 23, 6000);
//
//
//        Function<Employee,String> func1 = e -> e.getName();
//        System.out.println(func1.apply(employee));
//
//        System.out.println("*******************");
//        Function<Employee,String> func2 = Employee::getName;
//        System.out.println(func2.apply(employee));
//    }
















    @Test
    public void test01a(){
        //1、创建Stream
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6);

        //2、加工处理
        //过滤：filter(Predicate p)
        //把里面的偶数拿出来
        /*
         * filter(Predicate p)
         * Predicate是函数式接口，抽象方法：boolean test(T t)
         */
        stream = stream.filter(t -> t%2==0);

        //3、终结操作：例如：遍历
        stream.forEach(System.out::println);
    }
    @Test
    public void test02a(){
        Stream.of(1,2,3,4,5,6)
                .filter(t -> t%2==0)
                .forEach(System.out::println);
    }
    @Test
    public void test03a(){
        Stream.of(1,2,3,4,5,6,2,2,3,3,4,4,5)
                .distinct()
                .forEach(System.out::println);
    }
    @Test
    public void test04a(){
        Stream.of(1,2,3,4,5,6,2,2,3,3,4,4,5)
                .limit(3)
                .forEach(System.out::println);
    }
    @Test
    public void test05a(){
        Stream.of(1,2,2,3,3,4,4,5,2,3,4,5,6,7)
                .distinct()  //(1,2,3,4,5,6,7)
                .filter(t -> t%2!=0) //(1,3,5,7)
                .limit(3)
                .forEach(System.out::println);
    }
    @Test
    public void test06a(){
        Stream.of(1,2,3,4,5,6,2,2,3,3,4,4,5)
                .skip(5)
                .forEach(System.out::println);
    }
    @Test
    public void test07a(){
        Stream.of(1,2,3,4,5,6,2,2,3,3,4,4,5)
                .skip(5)
                .distinct()
                .filter(t -> t%3==0)
                .forEach(System.out::println);
    }
    @Test
    public void test08a(){
        long count = Stream.of(1,2,3,4,5,6,2,2,3,3,4,4,5)
                .distinct()
                .peek(System.out::println)  //Consumer接口的抽象方法  void accept(T t)
                .count();
        System.out.println("count="+count);
    }
    @Test
    public void test09a(){
        //希望能够找出前三个最大值，前三名最大的，不重复
        Stream.of(11,2,39,4,54,6,2,22,3,3,4,54,54)
                .distinct()
                .sorted((t1,t2) -> -Integer.compare(t1, t2))//Comparator接口  int compare(T t1, T t2)
                .limit(3)
                .forEach(System.out::println);
    }
    @Test
    public void test10a(){
        Stream.of(1,2,3,4,5)
                .map(t -> t+=1)//Function<T,R>接口抽象方法 R apply(T t)
                .forEach(System.out::println);
    }
    @Test
    public void test11a(){
        String[] arr = {"hello","world","java"};

        Arrays.stream(arr)
                .map(t->t.toUpperCase())
                .forEach(System.out::println);
    }
    @Test
    public void test12a(){
        String[] arr = {"hello","world","java"};
        Arrays.stream(arr)
                .flatMap(t -> Stream.of(t.split("|")))//Function<T,R>接口抽象方法 R apply(T t)  现在的R是一个Stream
                .forEach(System.out::println);
    }


















}
