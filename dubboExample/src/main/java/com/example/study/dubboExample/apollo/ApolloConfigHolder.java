package com.example.study.dubboExample.apollo;

/**
 * 直接定义apollo的key，方便使用，也是调用工具类 ApolloConfigUtils
 * 这里可以做一些配置数据的基本加工处理，不要嵌套业务逻辑代码
 * 此类不引入任何外部依赖
 * @Author wuyh
 * @Date 2019/10/11 10:54
 **/
public class ApolloConfigHolder {

    //apollp默认namespace（application）配置的key 是 app.version
    private static final String appVersion = "app.version";


    /**
     * 直接使用获取值
     */
    private static String getAppVersion(){
        //从apollp默认namespace获取配置，如果获取不到设定默认 "1.0.0"
        return ApolloConfigUtils.getDefaultValue(appVersion,"1.0.0");
    }


}
