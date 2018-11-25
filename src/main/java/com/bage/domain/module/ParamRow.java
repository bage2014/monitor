package com.bage.domain.module;

import java.io.Serializable;

public class ParamRow implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fimally;
	private String qualifier;
	private String value;
	
	public ParamRow() {
		super();	
	}

	public ParamRow(String fimally, String qualifier, String value) {
		super();
		this.fimally = fimally;
		this.qualifier = qualifier;
		this.value = value;
	}

	public String getFimally() {
		return fimally;
	}

	public void setFimally(String fimally) {
		this.fimally = fimally;
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
		return "ParamRow [fimally=" + fimally + ", qualifier=" + qualifier + ", value=" + value + "]";
	}
	
}
