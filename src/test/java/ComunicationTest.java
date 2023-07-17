/**
 * ClassName: ComunicationTest
 * Package: PACKAGE_NAME
 * Description:
 * 生产者消费者案例
 * @Author 毕研政
 * @Create 2023/4/26 17:13
 * @Version 1.0
 */
public class ComunicationTest {



    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);
        producer.start();
        consumer.start();
    }
}
/**
 * 资源类
 */
class Clerk{
    private int number = 0;

    public synchronized void produce() throws InterruptedException {
            if(number<20){
                number++;
                System.out.println("生产者生产了第"+number+"个产品");
                notifyAll();
            }else{
                Thread.sleep(50);
                wait();
            }
    }

    public synchronized void consume() throws InterruptedException {
            if(number>0){
                System.out.println("消费者消费了第"+number+"个产品");
                number--;
                notifyAll();
            }else{
                Thread.sleep(200);
                wait();
            }
    }
}

/**
 * 生产者线程
 */
class Producer extends Thread{
    private Clerk clerk;
    public Producer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while(true){
            try {
                clerk.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者线程
 */
class Consumer extends Thread{
    private Clerk clerk;
    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while(true){
            try {
                clerk.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}