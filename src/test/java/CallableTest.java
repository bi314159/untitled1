import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

/**
 * ClassName: CallableTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 毕研政
 * @Create 2023/4/27 15:02
 * @Version 1.0
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //2.创建Callable接口的实例
        MyCallable myCallable = new MyCallable();
        //3.创建FutureTask
        FutureTask futureTask = new FutureTask<>(myCallable);
        //4.放在Thread中
        Thread thread = new Thread(futureTask);
        thread.start();
        //5.获取返回值
        Object o = futureTask.get();
        System.out.println(o);
    }
}
//1.实现Callable接口
class MyCallable implements Callable{

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <=10000; i++) {
            if(i%2==0){
                sum+=i;
            }
        }
        return sum;
    }
}
