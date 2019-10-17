package com.example.study.dubboExample.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/10/10 16:52
 **/
@Slf4j
@Component
public class ApolloConfiguration implements InitializingBean {

    //不同工程需要更改配置文件路径
    private String PATH = "application.properties";

    //这个配置命名就不要更改，apollo的客户端业务使用到这个名称定义
    private String ATTR = "apollo.bootstrap.namespaces";

    @Override
    public void afterPropertiesSet() throws Exception {
        loadProperties();
        registerListener();
    }

    private void registerListener() {
        if (NamespaceConfigHolder.getNamespaceList() != null && NamespaceConfigHolder.getNamespaceList().size() > 0){
            for (String namespace : NamespaceConfigHolder.getNamespaceList()) {
                addListener(ConfigService.getConfig(namespace));
            }
        }else {
            addListener(ConfigService.getAppConfig());
        }
    }

    private void addListener(Config config){
        if (config == null) return;
        config.addChangeListener(new ApolloConfigChangeListener());
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            //使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = ApolloConfiguration.class.getClassLoader().getResourceAsStream(PATH);
            //使用properties对象加载输入流
            properties.load(in);
            //获取key对应的value值
            String property = properties.getProperty(ATTR);
            log.info("apollo.bootstrap.namespaces ：" + property);
            String[] split = property.split(",");
            NamespaceConfigHolder.setNamespaceList(Arrays.asList(split));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("load apollo properties fail , error : {}" , e);
        }
    }


}
