package com.example.study.dubboExample.facade.impl;

import com.example.elasticsearch.api.dto.Foo;
import com.example.elasticsearch.api.facade.FooFacade;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/20 11:11
 **/
@Service("fooFacade2")
public class FooFacadeImpl2 implements FooFacade {

    @Override
    public List<Foo> searchFoos(Integer num, String location) {
        System.out.println("调用到了本地方法");
        return null;
    }

    @Override
    public Boolean completed(Long id) {
        return null;
    }
}
