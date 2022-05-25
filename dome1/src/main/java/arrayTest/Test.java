package arrayTest;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    private static Object[] obj1={};
    private static Object[] obj2={};
    private static String a="";
    private static String b="";
    public static void main(String[] args) {
        System.out.println(obj1==obj2);
        System.out.println(a==b);
        new ArrayList<Object>(0);
        new HashMap<Object,Object>(5);
    }
}
