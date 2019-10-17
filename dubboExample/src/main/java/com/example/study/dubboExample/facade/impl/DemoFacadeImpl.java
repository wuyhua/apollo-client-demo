package com.example.study.dubboExample.facade.impl;

import com.example.study.dubboExample.facade.DemoFacade;
import org.springframework.stereotype.Service;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/8/21 15:59
 **/
@Service
public class DemoFacadeImpl implements DemoFacade {

    @Override
    public String getMessage() {
        return "dubbo example return";
    }

}
