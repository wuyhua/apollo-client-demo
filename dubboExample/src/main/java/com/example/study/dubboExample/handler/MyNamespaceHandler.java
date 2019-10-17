package com.example.study.dubboExample.handler;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.lang.Nullable;
import org.w3c.dom.Element;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/8/21 16:38
 **/
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("provider", new BeanDefinitionParser() {
            @Nullable
            @Override
            public BeanDefinition parse(Element element, ParserContext parserContext) {
                return null;
            }
        });
    }

}
