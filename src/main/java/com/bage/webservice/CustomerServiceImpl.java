package com.bage.webservice;

import com.bage.domain.module.Customer;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public String getName(Long userId) {

		System.out.println("****************************////////////");
		
		return "hello";
	}

	@Override
	public Customer getUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
