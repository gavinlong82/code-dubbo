package com.ytd.mds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:spring/*-dubbo.xml")
@SpringBootApplication
public class OcWebBootStrap {
	public static void main(String[] args) {
		SpringApplication.run(OcWebBootStrap.class, args);
	}
}
