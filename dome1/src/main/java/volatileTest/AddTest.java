package volatileTest;

public class AddTest {
    private static volatile int i=1;
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                System.out.println("线程1--"+i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
                System.out.println("线程2--"+i);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);
    }
}
