package com.bage.config.webservice;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bage.webservice.CustomerService;
import com.bage.webservice.CustomerServiceImpl;

@Configuration
public class WebServiceConfig {
	//@Bean
	public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
		CXFServlet cxfServlet = new CXFServlet();
		ServletRegistrationBean bean = new ServletRegistrationBean(cxfServlet, "/webservice/*");
		//bean.setLoadOnStartup(0);
		//bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public CustomerService customerService() {
		return new CustomerServiceImpl();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), customerService());
		endpoint.publish("/customer");
		return endpoint;
	}
}
