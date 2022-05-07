package CompletableFutureTest;

import java.util.concurrent.CompletableFuture;

public class Dome1 {
    public static void main(String[] args) {
        //没有返回值的异步调用
        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"开始了");
        });

        //有返回值的异步调用
        CompletableFuture<Integer> completableFuture1=CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"开始了");
            throw new RuntimeException();
//            return 1024;
        });
        completableFuture1.whenComplete((t,u)->{
            System.out.println("----------"+t);  //获取值
            System.out.println("----------"+u);  //获取异常
        });
    }
}
