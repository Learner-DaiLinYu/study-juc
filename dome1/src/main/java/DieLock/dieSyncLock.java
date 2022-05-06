package DieLock;

import java.util.concurrent.locks.ReentrantLock;

public class dieSyncLock {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        new Thread(()->{
            try {
                lock1.lock();
                try {
                    Thread.sleep(1000);
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName()+"已经双剑合璧了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock2.unlock();
                }
            } finally {
                lock1.unlock();
            }
        },"A").start();
        new Thread(()->{
            try {
                lock2.lock();
                try {
                    Thread.sleep(1000);
                    lock1.lock();
                    System.out.println(Thread.currentThread().getName()+"已经双剑合璧了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock1.unlock();
                }
            } finally {
                lock2.unlock();
            }
        },"B").start();
    }
}
