package DaemonTest;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                while (true){
                    Thread.sleep(500);
                    System.out.println("线程执行！！");
                    //throw new RuntimeException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                /**
                 * 由于是守护线程这里不会执行 直接结束
                 */
                System.out.println("线程关闭！！");
            }
        });
        /**
         * 必须设置为守护线程在启动 isAlive()
         */
        thread.setDaemon(true);  //设置守护线程
        thread.start();

        Thread.sleep(5000);
        System.out.println("主线程结束！！！");
    }
}
