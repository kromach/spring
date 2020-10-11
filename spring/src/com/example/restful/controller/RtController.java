package com.example.restful.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // @Rest api 기능을 위한 어노테이션
@RequestMapping("/sample/")
public class RtController {
	
	@RequestMapping("hello")
	public String sayHello() {
		return "sample/hello";
	}

}
