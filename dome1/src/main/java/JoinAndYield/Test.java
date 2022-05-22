package JoinAndYield;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread t1= new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2结束");
        });
        Thread t2= new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1结束");
        });
        t1.start();
        //t1.join();  //当前线程 里去调用某个线程的join就会阻塞 等待那个线程执行完毕在继续
        /**
         * thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
         * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
         */
        t2.start();
        Thread.yield();
        /**
         * 让出当前线程  可能没有任何作用
         */
        System.out.println("主线程结束");
    }
}
