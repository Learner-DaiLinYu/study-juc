package LambdaTest;

public class Test {
    public static void main(String[] args) {
        LambdaClass.forEg();

        LambdaInterface aNew = LambdaClass::new;
        System.out.println(aNew.f());
    }
}
@FunctionalInterface
interface LambdaInterface {
    LambdaClass f();
}
//使用
class LambdaClass {
    public static void forEg() {
        lambdaInterfaceDemo(()-> {System.out.println("自定义函数式接口");return null;});
    }
    //函数式接口参数
    static void lambdaInterfaceDemo(LambdaInterface i){
        System.out.println(i);
        i.f();
    }
}
