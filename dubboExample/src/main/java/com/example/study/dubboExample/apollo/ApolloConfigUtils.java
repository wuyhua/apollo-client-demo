package com.example.study.dubboExample.apollo;

import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc   配置中心配置获取工具类
 * @Author wuyh
 * @Date 2019/10/11 8:55
 **/
public class ApolloConfigUtils {


    private static String defaultNamespace = "application";

    /**
     * 指定namespace 指定key  获取值，如果获取不到取自定义默认值 defaultValue
     * @param namespace
     * @param key
     * @param defaultValue
     */
    public static String getSpecifiedValue(String namespace, String key, String defaultValue){
        //config instance is singleton for each namespace and is never null
        Config config = ConfigService.getConfig(namespace);
        if (config == null){
            return defaultValue;
        }
        return config.getProperty(key, defaultValue);
    }

    /**
     * 取默认namespace（application 指定的key值，如果获取不到，取自定义默认值 defaultValue
     * @param key
     * @param defaultValue
     */
    public static String getDefaultValue(String key, String defaultValue){
        return getSpecifiedValue(defaultNamespace,key,defaultValue);
    }

    /**
     * 遍历所有namespace指定的key值，如果获取不到，取自定义默认值 defaultValue
     * 优先级：参见apollo.bootstrap.namespaces 的配置顺序
     * @param key
     * @param defaultValue
     */
    public static String getValue(String key, String defaultValue){
        //这么做确保下配置apollo.bootstrap.namespaces有误也能从默认namespace优先级第一
        String result = getDefaultValue(key, null);
        if (result == null) {
            //apollo.bootstrap.namespaces的配置顺序，后者的namespace的key会覆盖前者
            for (String namespace : NamespaceConfigHolder.getNamespaceList()) {
                if (namespace.trim().equals(defaultNamespace)) continue;
                result = getSpecifiedValue(namespace, key, null);
                if (result != null) {
                    break;
                }
            }
        }
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }


    /**
     * 将json值转为Object
     * 例如配置如下： key = {"somekey":"key1","somevalue":"value1"}
     * @param key
     * @param clazz
     */
    public static <T> T getValueToObject(String key,Class<T> clazz){
        String json = getValue(key, null);
        if (json == null){
            return null;
        }
        return JSON.parseObject(json, clazz);
    }

    /**
     * 将json值转为List<Object>
     * 例如配置如下： key = [{"somekey":"key1","somevalue":"value1"},{"somekey":"key2","somevalue":"value2"}]
     * @param key
     * @param clazz
     */
    public static <T> List<T> getValueToObjectList(String key,Class<T> clazz){
        String json = getValue(key, null);
        if (json == null){
            return null;
        }
        return JSON.parseArray(json, clazz);
    }

    /**
     * 将值转为List<Integer>
     * 例如配置如下： key = [1,2,3,4]
     */
    public static List<Integer> getValueToIntegerList(String key){
        String json = getValue(key, null);
        if (json == null){
            return null;
        }
        String replace = json.replaceAll("\\[","").replaceAll("\\]","");
        json = null;
        String[] split = replace.split(",");
        List<Integer> arrayList = new ArrayList<>();
        for (String s : split){
            try {
                arrayList.add(Integer.parseInt(s));
            }catch (Exception e){
            }
        }
        return arrayList;
    }

    /**
     * 将值转为List<String>
     * 例如配置如下： key = [aa,bb,cc,dd]
     */
    public static List<String> getValueToStringList(String key){
        String json = getValue(key, null);
        if (json == null){
            return null;
        }
        String replace = json.replaceAll("\\[","").replaceAll("\\]","");
        json = null;
        String[] split = replace.split(",");
        return new ArrayList<String>(Arrays.asList(split));
    }


}
