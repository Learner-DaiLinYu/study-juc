package ThreadTest1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Lock实现可重入锁
 */
public class LockDome {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        new Thread(()->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获取了外层锁");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+"获取了中层锁");
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName()+"获取了内层锁");
                    } finally {
                        lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        },"线程1").start();
    }
}