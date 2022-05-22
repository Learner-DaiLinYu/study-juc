package JoinAndYield;

public class Test02 {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"结束了");
        }).start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"结束了");
        }).start();
        Thread.yield();
        /**
         * 只有在cup资源比较紧凑时，yield有可能有效果
         */
        System.out.println("主线程");
    }
}
