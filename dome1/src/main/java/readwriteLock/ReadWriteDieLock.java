package readwriteLock;

public class ReadWriteDieLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        new Thread(()->{
            try {
                myCache.put("1","1");
                myCache.pop("2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程1").start();
        new Thread(()->{
            try {
                myCache.pop("1");
                myCache.put("2","2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程2").start();
    }
}
