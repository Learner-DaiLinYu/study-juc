package PoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dome1 {
    public static void main(String[] args) {
        //一池五线程
//        ExecutorService pool = Executors.newFixedThreadPool(5);
        //一池一线程
//        ExecutorService pool = Executors.newSingleThreadExecutor();
        //一池 可扩容 线程
        ExecutorService pool = Executors.newCachedThreadPool();
        //10个顾客请求
        new Thread(()->{
            try {
                Thread.sleep(4000);
                System.out.println("向线程池内添加一个任务");
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程1").start();

        try {
            for (int i = 0; i < 10; i++) {
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务！");
                });
            }
        } finally {
            //启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务抛出异常启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。 如果已经关闭，调用没有额外的作用。。 如果已经关闭，调用没有额外的作用。
            pool.shutdown();
        }
    }
}
