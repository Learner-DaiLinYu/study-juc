package volatileTest;

import java.io.*;
import java.util.ArrayList;

public class Test02 {
    private static boolean semaphore=true;
    private static ArrayList<String> al=new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            int i=1;
            Student student = new Student();
            StringBuffer stringBuffer = new StringBuffer();
            while (Test02.semaphore){
//                System.out.println("现在的值");
                /**
                 * 如果线程在while循环里面没有去读semaphore不会保证可见性
                 */
                i++;
                student.setName("呼呼");
                student.setAge(12);

                /**
                 * 使用了主存的东西导致 去主存拉取了最新的数据
                 */
//                stringBuffer.append(12);
            }
            System.out.println("线程收到信号退出！！！");
        }).start();
        Thread.sleep(2000);
        semaphore=false;
        System.out.println("主线程修改了信号量");
    }
}
class Student{
    private String name;
    private Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
