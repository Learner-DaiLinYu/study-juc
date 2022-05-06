package ThreadTest;

public class test2 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "线程1");
        thread.setDaemon(true);  //设置为守护线程
        thread.start();

        System.out.println(Thread.currentThread().getName()+"over");
    }
}
