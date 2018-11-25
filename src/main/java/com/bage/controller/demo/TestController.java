package com.bage.controller.demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@EnableAutoConfiguration
public class TestController {
	///monitor/src/main/java
	@RequestMapping("/test")
	public String test(){
		System.out.println("hhhh");
		return "hello/gg";
//		String java = new RemoteCall(url).collectJavaInformations();
//		memory = java.memoryInformations;
//		println "\nused memory:\n    " + Math.round(memory.usedMemory / 1024 / 1024) + " Mb";
//		println "\nmax memory:\n    " + Math.round(memory.maxMemory / 1024 / 1024) + " Mb";
//		println "\nused perm gen:\n    " + Math.round(memory.usedPermGen / 1024 / 1024) + " Mb";
//		println "\nmax perm gen:\n    " + Math.round(memory.maxPermGen / 1024 / 1024) + " Mb";
//		println "\nused non heap:\n    " + Math.round(memory.usedNonHeapMemory / 1024 / 1024) + " Mb";
//		println "\nused physical memory:\n    " + Math.round(memory.usedPhysicalMemorySize / 1024 / 1024) + " Mb";
//		println "\nused swap space:\n    " + Math.round(memory.usedSwapSpaceSize / 1024 / 1024) + " Mb";
	}
	
}
