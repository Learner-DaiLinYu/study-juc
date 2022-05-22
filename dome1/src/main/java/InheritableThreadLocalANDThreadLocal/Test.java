package InheritableThreadLocalANDThreadLocal;

public class Test {
    private static  InheritableThreadLocal  local =new InheritableThreadLocal();
    public static void main(String[] args) throws InterruptedException {
        local.set("主线程");
        Thread t1 = new Thread(() -> {
            //local.set("线程1");
            System.out.println("线程1——————>"+local.get());  //会获取父线程的值
        });
        Thread t2 = new Thread(() -> {
            //local.set("线程2");
            System.out.println("线程2——————>"+local.get());  //会获取父线程的值
        });

        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("主----->"+local.get());
    }
}
