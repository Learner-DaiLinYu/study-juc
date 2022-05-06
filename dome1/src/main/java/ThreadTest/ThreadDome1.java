package ThreadTest;

public class ThreadDome1 {
    public static void main(String[] args) {
        Share share = new Share();
        Runnable runnable1=()->{
            while (true) {
                try {

                    share.add();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable2=()->{
            while (true) {
                try {

                    share.pop();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable1,"线程1").start();
        new Thread(runnable2,"线程2").start();
        new Thread(runnable1,"线程3").start();
        new Thread(runnable2,"线程4").start();

    }
}
class Share{
    //初始值
    private int number=0;
    //+1的方法
    public synchronized void add() throws InterruptedException {
        while (number!=0){  //加上while关键字防止虚假唤醒问题
            this.wait();
        }
        System.out.println(Thread.currentThread().getName()+"::"+number++);
        this.notifyAll(); //通知其他线程
    }
    public synchronized void pop() throws InterruptedException {
        while (number!=1){
            this.wait();
        }
        System.out.println(Thread.currentThread().getName()+"::"+number--);
        this.notifyAll();//通知其他线程
    }
}
