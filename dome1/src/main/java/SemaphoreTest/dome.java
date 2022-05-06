package SemaphoreTest;

import java.util.concurrent.Semaphore;

//6辆汽车，停3个车位
public class dome {
    public static void main(String[] args) {
        //创建许可数量
        Semaphore semaphore=new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    //站住车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到了车位！！！");
                    Thread.sleep(2000);

                    //释放车位
                    System.out.println(Thread.currentThread().getName()+"开走了！！！");
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}
