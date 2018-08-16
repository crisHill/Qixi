package zls.com.qixi.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by oop on 2018/8/16.
 */

public class CollectionUtil {

    public static boolean empty(Collection c){
        return c == null || c.size() == 0;
    }

    public static <T> boolean empty(T[] arr){
        return arr == null || arr.length == 0;
    }

    public static <T> List<T> arr2List(T[] arr){
        List<T> list = new ArrayList<>();
        if(empty(arr)){
            return list;
        }

        for (T t : arr){
            list.add(t);
        }

        return list;
    }

}
