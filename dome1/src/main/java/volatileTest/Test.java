package volatileTest;

public class Test {
    private static volatile boolean semaphore=true;
    public static void main(String[] args) {
        new Thread(()->{
            while (Test.semaphore){  //不加volatile无法监听到

            }
            System.out.println("阻塞线程执行结束");
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(5000);
                Test.semaphore=false;
                System.out.println("修改了信号量");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
