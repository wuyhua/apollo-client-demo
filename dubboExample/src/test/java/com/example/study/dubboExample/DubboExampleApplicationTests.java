package com.example.study.dubboExample;

import com.example.elasticsearch.api.dto.Foo;
import com.example.elasticsearch.api.facade.FooFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ImportResource(locations = {"classpath:/META-INF/dubbo/*.xml"})
public class DubboExampleApplicationTests {


}
