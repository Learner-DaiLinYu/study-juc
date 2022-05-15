package Live_Dome;

public class Dome1 {

    static CreateLock createLock=new CreateLock();

    public static void main(String[] args) {
        Thread A=new Thread(()->{
            testLock();
            while (true){}
        },"unLike-HeBeiWangXiaoZhang-you-head");
        Thread B=new Thread(()->{
            testLock();
            while (true){}
        },"unLike-HeBeiWangXiaoZhang-you-head");
        A.start();
        B.start();
    }

    public static void testLock(){
        createLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获取到了锁");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            createLock.unlock();
        }
        System.out.println("");
    }
}
