package com.bage.controller.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bage.domain.module.User;
import com.bage.service.module.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/insert")
	public @ResponseBody String insert(HttpServletRequest request, HttpServletResponse response) {
		
		User user = new User(0, "13601509223", "陆瑞华", "1362810117", "", "0", "", "", "0", "");
		
		return "finish:" + userService.insert(user );
	}

	@RequestMapping("/login")
	public String hello(HttpServletRequest request, HttpServletResponse response) {

		return "login";
	}

}
