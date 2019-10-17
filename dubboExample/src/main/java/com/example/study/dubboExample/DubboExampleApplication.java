package com.example.study.dubboExample;

import com.example.elasticsearch.api.dto.Foo;
import com.example.elasticsearch.api.facade.FooFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

@SpringBootApplication
//@ImportResource(locations = {"classpath:META-INF/dubbo/*.xml"})
public class DubboExampleApplication {


	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(DubboExampleApplication.class, args);

//		FooFacade fooFacade = applicationContext.getBean("fooFacade", FooFacade.class);
//		List<Foo> shanghai = fooFacade.searchFoos(5, "Guangzhou");
//
//		System.out.println(shanghai);


	}

}
