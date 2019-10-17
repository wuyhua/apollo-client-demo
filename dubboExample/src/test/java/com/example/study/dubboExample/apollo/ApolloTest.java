package com.example.study.dubboExample.apollo;

import com.alibaba.fastjson.JSON;
import com.example.study.dubboExample.DubboExampleApplicationTests;
import com.example.study.dubboExample.dto.Some;
import org.junit.Test;

import java.util.List;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/10/11 10:06
 **/
public class ApolloTest extends DubboExampleApplicationTests {


    private String some = "some";
    private String someList = "some.list";
    private String intArr = "int.arr";
    private String strArr = "str.arr";
    private String common = "common";

    @Test
    public void getvalue(){

        String some_value = ApolloConfigUtils.getValue(some, null);
        System.out.println("some_value : " + some_value);

        String someList_value = ApolloConfigUtils.getValue(someList, null);
        System.out.println("someList_value : " + someList_value);

        String intArr_value = ApolloConfigUtils.getValue(intArr, null);
        System.out.println("intArr_value : " + intArr_value);

        String strArr_value = ApolloConfigUtils.getValue(strArr, null);
        System.out.println("strArr_value : " + strArr_value);

        String common_value = ApolloConfigUtils.getValue(common, null);
        System.out.println("common_value : " + common_value);

        String common2_value = ApolloConfigUtils.getValue("common2", null);
        System.out.println("common2_value : " + common2_value);

        Some someObj = ApolloConfigUtils.getValueToObject(some, Some.class);
        System.out.println("someObj : " + JSON.toJSONString(someObj));

        List<Some> someObjList = ApolloConfigUtils.getValueToObjectList(someList, Some.class);
        System.out.println("someObjList : " + JSON.toJSONString(someObjList));

        List<Integer> intArrList = ApolloConfigUtils.getValueToIntegerList(intArr);
        System.out.println("intArrList : " + JSON.toJSONString(intArrList));

        List<String> strArrList = ApolloConfigUtils.getValueToStringList(strArr);
        System.out.println("strArrList : " + JSON.toJSONString(strArrList));

        List<String> nullList = ApolloConfigUtils.getValueToStringList("null");
        System.out.println("nullList : " + JSON.toJSONString(nullList));

    }




}
