package com.bage.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.bage.domain.module.Customer;

@WebService
public interface CustomerService {

	@WebMethod
	String getName(@WebParam(name = "userId") Long userId);

	@WebMethod
	Customer getUser(Long userId);

}
