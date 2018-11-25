package com.bage.controller.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/echart")
@EnableAutoConfiguration
public class EchartController {

    @RequestMapping("/greeting")
    public String greeting(HttpServletRequest  request,@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    	// TODO
        return "echart";
    }

}
