package com.bage.domain.response;

public class EmptyResponseParam extends ResponseParam {

	private static final long serialVersionUID = 1L;

	public EmptyResponseParam(String code) {

		super(new Header(code, ""), "","");

	}

}
