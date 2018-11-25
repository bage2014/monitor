package com.bage.domain.module;

import java.io.Serializable;

public class ParamPair implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String qualifier;
	private String value;
	
	public ParamPair() {
		super();	
	}

	public ParamPair(String qualifier, String value) {
		super();
		this.qualifier = qualifier;
		this.value = value;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ParamPair [qualifier=" + qualifier + ", value=" + value + "]";
	}
	
	
}
