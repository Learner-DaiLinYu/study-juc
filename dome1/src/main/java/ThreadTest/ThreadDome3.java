package ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDome3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        Runnable runnable1=()->{
            for (int i = 1; i < 10; i++) {
                shareResource.print5(i);
            }
        };
        Runnable runnable2=()->{
            for (int i = 1; i < 10; i++) {
                shareResource.print10(i);
            }
        };
        Runnable runnable3=()->{
            for (int i = 1; i < 10; i++) {
                shareResource.print15(i);
            }
        };
        new Thread(runnable1,"AA").start();
        new Thread(runnable2,"BB").start();
        new Thread(runnable3,"CC").start();
    }
}
class ShareResource{
    //标注位
    private int flag=1;  //AA1 BB2 CC3
    //线程锁
    private Lock lock=new ReentrantLock();

    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    //打印5次 参数几轮
    public void print5(int loop){
        lock.lock();
        try {
            while (flag!=1){
                c1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+"轮数："+loop);
            }
            //通知
            flag=2;//修改标志位 2
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print10(int loop){
        lock.lock();
        try {
            while (flag!=2){
                c2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+"轮数："+loop);
            }
            //通知
            flag=3;//修改标志位 3
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print15(int loop){
        lock.lock();
        try {
            while (flag!=3){
                c3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+"轮数："+loop);
            }
            //通知
            flag=1;//修改标志位 1
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}