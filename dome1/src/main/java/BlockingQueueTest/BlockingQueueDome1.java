package BlockingQueueTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDome1 {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);

        //第一组
        System.out.println(blockingQueue.add("1"));
        System.out.println(blockingQueue.add("2"));//底层就是offer
        System.out.println(blockingQueue.add("3"));
        System.out.println(blockingQueue.element());
        //System.out.println(blockingQueue.add("4"));   //IllegalStateException
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());//底层就是poll
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());  NoSuchElementException

        System.out.println("------------------------------------------------");
        //第二组
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));   //并不抛出异常，返回false
        System.out.println(blockingQueue.offer("G",3L, TimeUnit.SECONDS));   //等待特定时间
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());    //没获取到元素返回null
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));   //等待特定时间


        System.out.println("------------------------------------------------");
        //第三组
        new Thread(()->{
            try {
                Thread.sleep(4000);
                System.out.println(blockingQueue.size()+"-----"+blockingQueue.take());
                Thread.sleep(4000);
                blockingQueue.put("FFF");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程读取").start();
        blockingQueue.put("q");
        blockingQueue.put("w");
        blockingQueue.put("e");
        blockingQueue.put("r");  //如果队列满无法放入将一直阻塞


        System.out.println(blockingQueue.size()+"-----"+blockingQueue.take());
        System.out.println(blockingQueue.size()+"-----"+blockingQueue.take());
        System.out.println(blockingQueue.size()+"-----"+blockingQueue.take());
        System.out.println(blockingQueue.size()+"-----"+blockingQueue.take()+blockingQueue.size());  //队列为空也会一直阻塞

    }
}
