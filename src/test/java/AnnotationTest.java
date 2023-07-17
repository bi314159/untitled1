import org.junit.Test;

import java.util.*;

/**
 * ClassName: AnnotationTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 毕研政
 * @Create 2023/4/21 16:11
 * @Version 1.0
 */
public class AnnotationTest {


    /**
     * 创建一个Map,key为数组元素，value为数组元素出现的次数
     * 借助小顶堆，小顶堆维护K个map元素的顺序，以次数排序
     * 遍历完map，即可得到k个满足要求的元素
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        //创建一个Map,key为数组元素，value为元素出现的次数
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i],hashMap.getOrDefault(nums[i],0)+1);
        }
        //创建小顶堆，元素为存放2个元素的数组，第一个位置存放nums元素，第二个位置存放nums元素的出现次数
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        //遍历map
        for (Map.Entry<Integer,Integer> entry:hashMap.entrySet()) {
            if (priorityQueue.size() >= k){
                if (entry.getValue() > priorityQueue.peek()[1]){
                    priorityQueue.add(new int[]{entry.getKey(),entry.getValue()});
                }
            }else{
                priorityQueue.add(new int[]{entry.getKey(),entry.getValue()});
            }
        }
        //取出结果
        int[] result = new int[k];
        for (int i = k-1; i >=0 ; i--) {
            result[i] = priorityQueue.poll()[0];
        }
        return result;
    }




    public static void main(String[] args) {
//        new MyThread("自定义线程"){
//            @Override
//            public void run() {
//                for (int i = 1; i < 100; i++) {
//                    if(i%2!=0){
//                        System.out.println(i);
//                    }
//                }
//            }
//        }.start();
//        Thread t1 = new Thread(new MyThread1());
//        t1.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        }).start();
    }

}
class MyThread extends Thread{
    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            if(i%2==0){
                System.out.println(i);
            }
        }
    }
}

class MyThread1 implements Runnable{
    @Override
    public void run() {
        System.out.println("实现接口的线程执行");
    }
}
