package com.example.study.dubboExample.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/10/5 16:57
 **/
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/get")
    public String test(String key){
        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
        String someKey = key;
        String value = config.getProperty(someKey, "null");
        String result = someKey + " = " + value;
        log.info(result);
        return value;
    }

    @RequestMapping(value = "/getpublic")
    public String getPubilc(String namespace, String key){
        String somePublicNamespace = namespace;
        Config config = ConfigService.getConfig(somePublicNamespace); //config instance is singleton for each namespace and is never null
        String someKey = key;
        String value = config.getProperty(someKey, "null");
        log.info(value);
        return value;
    }

}
