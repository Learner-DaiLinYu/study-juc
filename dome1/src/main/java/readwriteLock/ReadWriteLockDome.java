package readwriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDome {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        for (int i = 0; i < 5; i++) {
            final int num=i;  //作用域常量
            new Thread(()->{
                try {
                    myCache.put(String.valueOf(num),num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"线程写"+String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int num=i;  //作用域常量
            new Thread(()->{
                try {
                    myCache.pop(String.valueOf(num));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"线程读"+String.valueOf(i)).start();
        }

    }
}
class MyCache{
    private Map<String,Object> map=new HashMap<>();
    //创建读写锁对象
    private ReadWriteLock rwlock=new ReentrantReadWriteLock();

    //放数据
    public void put(String key,Object o) throws InterruptedException {
        //添加写锁
        rwlock.writeLock().lock();
        try {

            System.out.println(Thread.currentThread().getName()+"正在写数据"+key);
            //暂停一会
            TimeUnit.MILLISECONDS.sleep(300);
            //放数据
            map.put(key,o);
            System.out.println(Thread.currentThread().getName()+"写完了"+key);

        } finally {
            //锁释放
            rwlock.writeLock().unlock();
        }
    }
    //取数据
    public Object pop(String key) throws InterruptedException {
        //获取读锁
        rwlock.readLock().lock();
        Object result=null;
        try {

            System.out.println(Thread.currentThread().getName()+"正在读取"+key);
            //暂停一会
            TimeUnit.MILLISECONDS.sleep(300);
            result=map.get(key);
            System.out.println(Thread.currentThread().getName()+"取完了"+result);

        } finally {
            //释放读锁
            rwlock.readLock().unlock();
        }
        return result;
    }
}
