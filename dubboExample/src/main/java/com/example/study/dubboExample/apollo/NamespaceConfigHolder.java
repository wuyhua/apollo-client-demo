package com.example.study.dubboExample.apollo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc   初始化namespace
 * @Author wuyh
 * @Date 2019/10/11 9:04
 **/
public class NamespaceConfigHolder {

    public static List<String> namespaceList ;

    public static List<String> getNamespaceList() {
        if (namespaceList == null){
            throw new IllegalStateException("namespace config doesn't init.");
        }
        return namespaceList;
    }

    public synchronized static void setNamespaceList(List<String> list) {
        //仅在加载的时候初始化一次
        if (namespaceList == null){
            namespaceList = new ArrayList<>();
            namespaceList.addAll(list);
        }else {
            namespaceList.clear();
            namespaceList.addAll(list);
        }
    }
}
