package ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDome2 {
    public static void main(String[] args) {
        Show show = new Show();
        Runnable runnable1= () -> {
            while (true) {
                show.incr();
            }
        };
        Runnable runnable2= () -> {
            while (true) {
                show.dcre();
            }
        };
        new Thread(runnable1,"线1").start();
        new Thread(runnable2,"线3").start();
        new Thread(runnable1,"线4").start();
        new Thread(runnable2,"线2").start();
    }
}
class Show{
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    //+1
    public void incr(){
        lock.lock(); //上锁
        try {
            while (number!=0){
                condition.await();  //等待
            }
            System.out.println(Thread.currentThread().getName()+"::"+number++);
            condition.signalAll(); //通知

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //解锁
        }
    }
    //-1
    public void dcre(){
        lock.lock(); //上锁
        try {
            while (number!=1){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"::"+number--);
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //解锁
        }
    }
}
