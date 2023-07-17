import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

/**
 * ClassName: MyTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 毕研政
 * @Create 2023/5/2 15:38
 * @Version 1.0
 */
public class MyTest {
    //实现方式1
    @Test
    public void test1() throws IOException {
        //1. 创建File类的对象，对应着物理磁盘上的某个文件
        File file = new File("hello.txt");
        //2. 创建FileReader流对象，将File类的对象作为参数传递到FileReader的构造器中
        FileReader fr = new FileReader(file);
        //3. 通过相关流的方法，读取文件中的数据
//        int data = fr.read(); //每调用一次读取一个字符
//        while (data != -1) {
//            System.out.print((char) data);
//            data = fr.read();
//        }
        int data;
        while ((data = fr.read()) != -1) {
            System.out.print((char) data);
        }

        //4. 关闭相关的流资源，避免出现内存泄漏
        fr.close();

    }

    //实现方式2：在方式1的基础上改进，使用try-catch-finally处理异常。保证流是可以关闭的
    @Test
    public void test25() {
        FileReader fr = null;
        try {
            //1. 创建File类的对象，对应着物理磁盘上的某个文件
            File file = new File("hello.txt");
            //2. 创建FileReader流对象，将File类的对象作为参数传递到FileReader的构造器中
            fr = new FileReader(file);
            //3. 通过相关流的方法，读取文件中的数据
            /*
             * read():每次从对接的文件中读取一个字符。并将此字符返回。
             * 如果返回值为-1,则表示文件到了末尾，可以不再读取。
             * */
//            int data = fr.read();
//            while(data != -1){
//                System.out.print((char)data);
//                data = fr.read();
//            }

            int data;
            while ((data = fr.read()) != -1) {
                System.out.println((char) data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. 关闭相关的流资源，避免出现内存泄漏
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //实现方式3：调用read(char[] cbuf),每次从文件中读取多个字符
    @Test
    public void test3() {
        FileReader fr = null;
        try {
            //1. 创建File类的对象，对应着物理磁盘上的某个文件
            File file = new File("hello.txt");
            //2. 创建FileReader流对象，将File类的对象作为参数传递到FileReader的构造器中
            fr = new FileReader(file);
            //3. 通过相关流的方法，读取文件中的数据
            char[] cbuf = new char[5];
            /*
             * read(char[] cbuf) : 每次将文件中的数据读入到cbuf数组中，并返回读入到数组中的
             * 字符的个数。
             * */
            int len; //记录每次读入的字符的个数
            while ((len = fr.read(cbuf)) != -1) {
                //处理char[]数组即可
                //错误：
//                for(int i = 0;i < cbuf.length;i++){
//                    System.out.print(cbuf[i]);
//                }
                //错误：
//                String str = new String(cbuf);
//                System.out.print(str);
                //正确：
//                for(int i = 0;i < len;i++){
//                    System.out.print(cbuf[i]);
//                }
                //正确：
                String str = new String(cbuf, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. 关闭相关的流资源，避免出现内存泄漏
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //注意：应该使用try-catch-finally处理异常。这里出于方便阅读代码，使用了throws的方式
    @Test
    public void test011()throws IOException {
        // 使用文件名称创建流对象
        FileWriter fw = new FileWriter(new File("fw.txt"));
        // 写出数据
        fw.write(97); // 写出第1个字符
        fw.write('b'); // 写出第2个字符
        fw.write('C'); // 写出第3个字符
        fw.write(30000); // 写出第4个字符，中文编码表中30000对应一个汉字。

        //关闭资源
        fw.close();
    }

    //注意：应该使用try-catch-finally处理异常。这里出于方便阅读代码，使用了throws的方式
    @Test
    public void test02()throws IOException {
        // 使用文件名称创建流对象
        FileWriter fw = new FileWriter(new File("fw.txt"));
        // 字符串转换为字节数组
        char[] chars = "尚硅谷".toCharArray();

        // 写出字符数组
        fw.write(chars); // 尚硅谷

        // 写出从索引1开始，2个字符。
        fw.write(chars,1,2); // 硅谷

        // 关闭资源
        fw.close();
    }


    //注意：应该使用try-catch-finally处理异常。这里出于方便阅读代码，使用了throws的方式
    @Test
    public void test03()throws IOException {
        // 使用文件名称创建流对象
        FileWriter fw = new FileWriter("fw.txt");
        // 字符串
        String msg = "尚硅谷";

        // 写出字符数组
        fw.write(msg); //尚硅谷

        // 写出从索引1开始，2个字符。
        fw.write(msg,1,2);	// 硅谷

        // 关闭资源
        fw.close();
    }

    @Test
    public void test04(){
        FileWriter fw = null;
        try {
            //1. 创建File的对象
            File file = new File("personinfo1.txt");
            //2. 创建FileWriter的对象，将File对象作为参数传递到FileWriter的构造器中
            //如果输出的文件已存在，则会对现有的文件进行覆盖
            fw = new FileWriter(file);
//            fw = new FileWriter(file,false);
            //如果输出的文件已存在，则会在现有的文件末尾写入数据
//            fw = new FileWriter(file,true);

            //3. 调用相关的方法，实现数据的写出操作
            //write(String str) / write(char[] cbuf)
            fw.write("I love you,");
            fw.write("you love him.");
            fw.write("so sad".toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. 关闭资源，避免内存泄漏
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }










    @Test
    public void test01(){
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        coll.add("佛地魔");
        System.out.println("coll = " + coll);

        coll.removeIf(new Predicate() {
            @Override
            public boolean test(Object o) {
                String str = (String) o;
                return str.contains("地");
            }
        });
        System.out.println("删除包含\"地\"字的元素之后coll = " + coll);
    }
    @Test
    public void test(){
        String[] str = new String[5];
        for (String myStr : str) {
            myStr = "atguigu";
            System.out.println(myStr);
        }
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }


    /**
     * 双指针思路
     * @param nums
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //声明一个List存放最后的结果集
        ArrayList<List<Integer>> result = new ArrayList<>();
        //先对数组进行排序，为去重做准备,Arrays.sort()方法将nums从小到大排序
        Arrays.sort(nums);
        //声明左右指针
        int left,right;
        //开始找满足条件的结果
        for (int i = 0; i < nums.length; i++) {
            //剪枝
            if (nums[i]>target && nums[i]>0){
                break;
            }
            //去重
            if (i>0 && nums[i]==nums[i-1]){
                continue;
            }
            for (int j = i+1; j < nums.length; j++) {
                //去重
                if (j>i+1 && nums[j]==nums[j-1]){
                    continue;
                }
                //初始化左右指针
                left = j+1;
                right = nums.length-1;
                while (left < right){
                    //如果加和<target,则左指针右移
                    if (nums[i]+nums[j]+nums[left]+nums[right]<target){
                        left++;
                    }else if (nums[i]+nums[j]+nums[left]+nums[right]>target){
                        //如果加和>0,则右指针左移
                        right--;
                    }else{
                        //收割结果
                        result.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        //去重
                        while (left<right && nums[left]==nums[left+1]){
                            left++;
                        }
                        while (left<right && nums[right]==nums[right-1]){
                            right--;
                        }
                        //移动左右指针
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void test5(){
        // 创建List集合对象
        List<String> list = new ArrayList<String>();

        // 往 尾部添加 指定元素
        list.add("图图");
        list.add("小美");
        list.add("不高兴");

        System.out.println(list);
        // add(int index,String s) 往指定位置添加
        list.add(1,"没头脑");

        System.out.println(list);
        // String remove(int index) 删除指定位置元素  返回被删除元素
        // 删除索引位置为2的元素
        System.out.println("删除索引位置为2的元素");
        System.out.println(list.remove(2));

        System.out.println(list);

        // String set(int index,String s)
        // 在指定位置 进行 元素替代（改）
        // 修改指定位置元素
        list.set(0, "三毛");
        System.out.println(list);

        // String get(int index)  获取指定位置元素
        // 跟size() 方法一起用  来 遍历的
        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        //还可以使用增强for
        for (String string : list) {
            System.out.println(string);
        }
    }
    @Test
    public void test6(){
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");

        set.add(p1);
        set.add(p2);
        p1.name = "CC";
        set.remove(p1);
        System.out.println(set);

        set.add(new Person(1001,"CC"));
        System.out.println(set);

        set.add(new Person(1001,"AA"));
        System.out.println(set);

        //其中Person类中重写了hashCode()和equal()方法
    }

    @Test
    public void test09(){
        LinkedHashSet set = new LinkedHashSet();
        set.add("张三");
        set.add("张三");
        set.add("李四");
        set.add("王五");
        set.add("王五");
        set.add("赵六");

        System.out.println("set = " + set);//不允许重复，体现添加顺序
    }

    /*
     * 自然排序：针对User类的对象
     * */
    @Test
    public void test2(){
        TreeSet set = new TreeSet();

        set.add(new User("Tom",12));
        set.add(new User("Rose",23));
        set.add(new User("Jerry",2));
        set.add(new User("Eric",18));
        set.add(new User("Tommy",44));
        set.add(new User("Jim",23));
        set.add(new User("Maria",18));
        //set.add("Tom");

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println(set.contains(new User("Jack", 23))); //true
    }
    @Test
    public void test61(){
            //创建 map对象
            HashMap map = new HashMap();

            //添加元素到集合
            map.put("黄晓明", "杨颖");
            map.put("李晨", "李小璐");
            map.put("李晨", "范冰冰");
            map.put("邓超", "孙俪");
            System.out.println(map);

            //删除指定的key-value
            System.out.println(map.remove("黄晓明"));
            System.out.println(map);

            //查询指定key对应的value
            System.out.println(map.get("邓超"));
            System.out.println(map.get("黄晓明"));
    }
    @Test
    public void test89(){
        HashMap map = new HashMap();
        map.put("许仙", "白娘子");
        map.put("董永", "七仙女");
        map.put("牛郎", "织女");
        map.put("许仙", "小青");

        System.out.println("所有的key:");
        Set keySet = map.keySet();
        for (Object key : keySet) {
            System.out.println(key);
        }

        System.out.println("所有的value:");
        Collection values = map.values();
        for (Object value : values) {
            System.out.println(value);
        }

        System.out.println("所有的映射关系:");
        Set entrySet = map.entrySet();
        for (Object mapping : entrySet) {
            //System.out.println(entry);
            Map.Entry entry = (Map.Entry) mapping;
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }
    @Test
    public void test77() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc.properties"));
        String username = (String) properties.get("username");
        System.out.println(username);
    }

    @Test
    public void test87(){
//        List<?> list = null;
//        list = new ArrayList<String>();
//        list = new ArrayList<Double>();
//        // list.add(3);//编译不通过
//        list.add(null);

        List<String> l1 = new ArrayList<String>();
        List<Integer> l2 = new ArrayList<Integer>();
        l1.add("尚硅谷");
        l2.add(15);
        read(l1);
        read(l2);
    }
    @Test
    public void test76(){
        Date date = new Date();
        long time = date.getTime();
        ArrayList<Integer> objects = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            objects.add(i);
        }
        Date date1 = new Date();
        long time1 = date1.getTime();
        System.out.println(time1-time);


        Date date2 = new Date();
        long time2 = date2.getTime();
        ArrayList<Integer> objects1 = new ArrayList<>(1000);
        for (int i = 0; i < 100000; i++) {
            objects1.add(i);
        }
        Date date3 = new Date();
        long time3 = date3.getTime();
        System.out.println(time3-time2);
    }

    /**
     * 借助List解决本题
     * 先magazine的每个字符放进List
     * 再按照索引依次取出ransomNote的每个字符，判断List中是否存在该字符，如果存在，则调用remove(Object obj)方法，删除一个该字符
     * 如果不存在，则返回false
     * 顺利遍历完ransomNote的每个字符，返回true
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        //将magazine变为字符数组
        char[] chars = magazine.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (char aChar : chars) {
            list.add(aChar);
        }
        //按照索引依次取出ransomNote的每个字符，判断List中是否存在该字符，如果存在，则调用remove(Object obj)方法，删除一个该字符
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (list.contains(c)){
                list.remove(Character.valueOf(c));
            }else{
                return false;
            }
        }
        //顺利遍历完，返回true
        return true;
    }

    public static <T> void read(List<T> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        // 文件路径名
        String pathname = "D:\\aaa.txt";
        File file1 = new File(pathname);

        // 文件路径名
        String pathname2 = "D:\\aaa\\bbb.txt";
        File file2 = new File(pathname2);

        // 通过父路径和子路径字符串
        String parent = "d:\\aaa";
        String child = "bbb.txt";
        File file3 = new File(parent, child);

        // 通过父级File对象和子路径字符串
        File parentDir = new File("d:\\aaa");
        String childFile = "bbb.txt";
        File file4 = new File(parentDir, childFile);
        System.out.println(file4);
    }



}
class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
class Person{
     int age;
     String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }



}
