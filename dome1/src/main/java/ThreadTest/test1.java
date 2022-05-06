package ThreadTest;

import javax.sound.midi.Track;

public class test1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Run(), "线程1");
        thread.start();
        Thread thread1 = new Thread(new Run(), "线程2");
        thread1.start();
    }
}
class Run implements Runnable{
    public static int i=30;
    @Override
    public void run() {
        while (true) {
//            getname();
            synchronized (this){
                if(i>0){
                    System.out.println(Thread.currentThread().getName()+"::"+i--);
                }
            }
        }
    }
    public synchronized void getname(){
        if(i>0){
            System.out.println(Thread.currentThread().getName()+"::"+i--);
        }
    }
}