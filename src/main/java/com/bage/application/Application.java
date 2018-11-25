package com.bage.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages={"com.bage","com.bage"}) /* 配置扫描包*/
@EnableScheduling 
@MapperScan("com.bage.mapper")						 /*扫描Mapper*/
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}
	
	public void run(String... args) throws Exception {
		System.out.println("Application.run is work");
	}
}