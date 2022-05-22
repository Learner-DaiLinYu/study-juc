package Interrupte;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread t1= new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1");
            }
        });
        Thread t2= new Thread(() -> {
            System.out.println(Thread.currentThread().isInterrupted());
            while (!Thread.interrupted()){
                //Thread.currentThread().isInterrupted() 检测是否中断 没有中断返回fasle 中断了返回true
                //Thread.interrupted() 返回的同时 还会请求标志为 到 false
            }
            System.out.println("t2执行完毕");
            System.out.println(Thread.currentThread().isInterrupted());
        });
        //t1.start();
        t2.start();
        /**
         * 如果线程A 因为调用了wait 系列函数、join
         * 方法或者sleep 方法而被阻塞挂起，这时候若线程B 调用线程A 的interrupt（） 方法，线程A
         * 会在调用这些方法的地方抛出InterruptedException 异常而返
         */
        Thread.sleep(1000);

        //t1.interrupt();  //中断线程1
        t2.interrupt();
        System.out.println(t2.isInterrupted());  //看线程是否中断设置成功
        System.out.println("主线程执行完毕");
    }
}
