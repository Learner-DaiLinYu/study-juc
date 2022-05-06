package ThreadTest1;

//验证synchronized是可重入锁
public class SyncLockDome {

    public synchronized void add(){
        add();
    }

    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "外层");
                synchronized (o){
                    System.out.println(Thread.currentThread().getName() + "中层");
                    synchronized(o){
                        System.out.println(Thread.currentThread().getName() + "内层");
                    }
                }
            }
        }, "线程1").start();
    }
}
