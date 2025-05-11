package com.store.mgmtAPI.utils;

public class ProductUtils {
    public int add(int a, int b){
        return Math.addExact(a, b);
    }

    public Object checkNull(Object obj){
        return (obj != null) ? obj : null;
    }

    public Boolean isGreater(int a, int b){
        return (Math.abs(b) > Math.abs(a)) ? true : false;
    }

    public static String getMethodName(){
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}
