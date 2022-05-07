package ForkJoinPoolTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Dome {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        System.out.println(submit.get());

        forkJoinPool.shutdown();
    }
}
class MyTask extends RecursiveTask<Integer> {
    //拆分差值不能超过10，计算10以内的运算
    private static final Integer VALUE=10;
    private int beint;  //拆分开始值
    private int endint; //拆分结束值
    private int result; //结果

    public MyTask(int beint,int endint){
        this.beint=beint;
        this.endint=endint;
    }

    //拆分和合并过程
    @Override
    protected Integer compute() {
        //判断相加两个数值是否大于10
        if((endint-beint)<=VALUE){
            for (int i = beint; i <= endint; i++) {
                result+=i;
            }
        }else {
            //获取中间值
            int middle=(beint+endint)/2;
            //左边拆分
            MyTask myTask01 = new MyTask(beint, middle);
            //右边拆分
            MyTask myTask02 = new MyTask(middle+1, endint);
            myTask01.fork();
            myTask02.fork();
            result=myTask01.join()+myTask02.join();
        }

        return result;
    }
}

class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    public Integer compute() {
        if (n <= 1) return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }
}