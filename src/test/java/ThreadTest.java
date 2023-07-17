/**
 * ClassName: ThreadTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 毕研政
 * @Create 2023/4/26 10:48
 * @Version 1.0
 */
public class ThreadTest {
    public static void main(String[] args) {
        //2.创建资源对象
        Ticket ticket = new Ticket();
        //3.创建多个线程卖票
        Thread thread1 = new Thread("线程1"){
            @Override
            public void run() {
                while(true){
                    synchronized (ticket){
                        ticket.saleTicket();
                    }
                }
            }
        };
        Thread thread2 = new Thread("线程2"){
            @Override
            public void run() {
                while(true){
                    synchronized (ticket){
                        ticket.saleTicket();
                    }
                }
            }
        };
        thread1.start();
        thread2.start();
    }
}

/**
 * 1.创建资源类
 */
class Ticket{
    private int ticket = 100;
    public void saleTicket(){
        if (ticket>0){
            System.out.println(Thread.currentThread().getName()+"卖掉了"+ticket+"号票");
            ticket--;
        }else{
            throw new RuntimeException("没有票了");
        }
    }
}
