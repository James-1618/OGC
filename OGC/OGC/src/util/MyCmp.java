package util;

import java.util.Comparator;

public class MyCmp implements Comparator<Object> {

    // 实现倒序
    @Override
    public int compare(Object o1, Object o2) {
        int x =  o2.toString().compareTo(o1.toString());
        return x;
    }
}