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
         */
        lock.lock();

        lock.tryLock();

        lock.tryLock(0,null);

    }
}
