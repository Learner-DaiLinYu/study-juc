package CollectionsLock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Dome1 {
    public static void main(String[] args) {
        List<String> list1=new Vector();
        List<String> list2= Collections.synchronizedList(new ArrayList<>());
        List<String> list3=new CopyOnWriteArrayList<>();


        Map<Object,Object> map=Collections.synchronizedMap(new HashMap<>());
        Hashtable<Object, Object> hashtable = new Hashtable<>();

        Set<Object> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set1=new CopyOnWriteArraySet<>();
    }
}
