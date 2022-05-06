package DieLock;

public class dieLock {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(()->{
            synchronized(a){
                try {
                    Thread.sleep(1000);   //等待一秒防止其他线程还么启动就执行完了
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println("a,"+"b"+"已经集结，天下无敌");
                }
            }
        },"aa").start();
        new Thread(()->{
            synchronized(b){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    System.out.println("a,"+"b"+"已经集结，天下无敌");
                }
            }
        },"bb").start();
    }
}
