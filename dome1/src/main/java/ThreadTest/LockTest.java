package ThreadTest;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        LTicket lTicket = new LTicket();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                lTicket.sale();
            }
        },"线程1").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                lTicket.sale();
            }
        },"线程2").start();
    }
}
//指定资源类
class LTicket{
    //票数量
    private int number=30;
    //创建可重入锁   传入参数true，可以实现公平锁
    private final ReentrantLock lock=new ReentrantLock(true);
    //卖票方法
    public void sale(){
        //上锁
        lock.lock();
        try {
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"::"+number--);
            }
        } finally {
            //解锁
            lock.unlock();
        }
    }
}