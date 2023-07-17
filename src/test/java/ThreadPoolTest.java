import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ThreadPoolTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 毕研政
 * @Create 2023/4/27 15:12
 * @Version 1.0
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        //使用静态工厂创建一个具有10个线程固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor executorService1 = (ThreadPoolExecutor) executorService;
        executorService1.setMaximumPoolSize(30);
        executorService1.execute(new MyRunnable());
        //关闭线程池
        executorService.shutdown();
    }
}
/**
 * 实现一个计算1-10000的线程任务
 */
class MyRunnable implements Runnable{
    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < 100000; i++) {
            sum += i;
            System.out.println(Thread.currentThread().getName());
        }
        System.out.println(sum);
    }
}
