package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
// @RequestMapping 注解就是通過它來處理URL的請求，而這個動作相等於 Servlet 中在web.xml的配置
import org.springframework.web.bind.annotation.RestController;
// @RestController注解就是表示當下的java是一個控制器(Controller)

//需有view
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@RequestMapping("/")
	public String hello(){
		return "Hey, This is Spring Boot's Hello World ! ";
	}

}
