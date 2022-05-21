package Live_Dome;

import java.util.concurrent.locks.ReentrantLock;

public class ReadCode {

    static ReentrantLock lock=new ReentrantLock();  //非公平锁

    public static void main(String[] args) throws InterruptedException {
        /**
         * AQS
         * compareAndSetState
         * acquire  第一个模板方法
         * getState  不推荐在for循环内部 每次都会 +1
         * addWaiter 主要将我们获取锁实现的线程添加到AQS的一个FIFO的双端队列里边  是一个双端队列
         * 公平锁  ： ABC三个线程 A抢到了锁 BC进入队列 A释放时 D线程来了 会调用一个方法进行判断是否有人
         * 强锁， 有的话就排入队列
         * 非公平锁：ABC三个线程 A抢到了锁 BC进入队列 A释放时 D线程来了 之间和B争取，D抢到了，对B不公平
         *
         * acquireQueued：
         * 对象调用 lock() --> 类方法调用 acquire() ----> 私有静态內部类 AQS的子类的 tryAcquire()
         */
        lock.lock();
        /**
         * AQS 方法: 尝试加锁，成功就成功 失败则失败
         * current == getExclusiveOwnerThread()  判断是否可以重入   判断当前线程是否为执行线程
         * 1.lock方法 必须进行加锁 获取不到 一直等待  涉及同步队列
         * 2.tryLock  就尝试一次 拿不到就算了
         *
         * while + trylock 的方式 对比  lock的方式
         * 1.CAS操作  没有lock获取锁 加入阻塞队列等操作
         */
        lock.tryLock();
        /**
         * 超时获取锁
         */
        lock.tryLock(0,null);
        /**
         * 支持可中断获取锁
         */
        lock.lockInterruptibly();
        /**
         * 解锁  重点复写方法 tryRelease
         */
        lock.unlock();
    }
}
