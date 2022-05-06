package CountDownLacthTest;

import java.util.concurrent.CountDownLatch;

//演示CountDownLatch
public class dome {
    public static void main(String[] args) throws InterruptedException {

        //计数器
        CountDownLatch count=new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学离开了教室");
                count.countDown();
            },""+i).start();
        }

        //等待
        count.await();
        System.out.println("班长锁门走人了");
    }
}
