package CallableTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

public class test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task=new FutureTask<Integer>(new Call());
        //简化
        FutureTask<Integer> task1=new FutureTask<Integer>(()-> {
            System.out.println(Thread.currentThread().getName()+"正在计算");
            Integer num=0;
            for (int i = 1; i < 50; i++) {
                num+=i;
            }
            System.out.println(Thread.currentThread().getName()+"计算完毕");
            return num;
        });

        new Thread(task1,"线程1").start();
        new Thread(task,"线程2").start();

        while (!task1.isDone()){
            while (!task.isDone()){
                System.out.println("两个线程执行完毕的总和"+(task.get()+task1.get()));
            }
        }

    }
}
class Run implements Runnable{
    @Override
    public void run() {

    }
}
class Call implements Callable{
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"正在计算");
        Integer num=0;
        for (int i = 50; i <= 100; i++) {
            num+=i;
        }
        System.out.println(Thread.currentThread().getName()+"计算完毕");
        return num;
    }
}
