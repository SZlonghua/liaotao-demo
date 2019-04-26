package com.liaotao.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LiaotaoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiaotaoDemoApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hello() {
		return "<h1>Hello Spring-Boot Maven Docker</h1>";
	}

}
