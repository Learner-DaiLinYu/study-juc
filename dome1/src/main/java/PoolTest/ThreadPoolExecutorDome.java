package PoolTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
    自定义线程池对象创建
*/
public class ThreadPoolExecutorDome {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 0; i < 10; i++) {
                poolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务！！！");
                });
            }
        } finally {
            poolExecutor.shutdown();
        }
    }
}
