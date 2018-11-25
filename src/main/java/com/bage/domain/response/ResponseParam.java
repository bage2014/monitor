package com.bage.domain.response;

import java.io.Serializable;

import com.bage.constraints.ResCodeConstraints;

public class ResponseParam implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Header header;
	private Object data;
	private Object bundle;
	
	public ResponseParam() {
		super();
	}

	public ResponseParam(Header header, Object data, Object bundle) {
		super();
		this.header = header;
		this.data = data;
		this.bundle = bundle;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
	
	public void setHeader(String code,String des) {
		this.header = new Header(code, des);
	}
	
	public void setHeaderSuccess() {
		this.header = new Header(ResCodeConstraints.SUCCESS, "SUCCESS");
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getBundle() {
		return bundle;
	}

	public void setBundle(Object bundle) {
		this.bundle = bundle;
	}

	@Override
	public String toString() {
		return "ResponseParam [header=" + header + ", data=" + data + ", bundle=" + bundle + "]";
	}

	
}

class Header{
	
	private String code;
	private String des;
	
	public Header() {
		super();
	}
	
	
	public Header(String code, String des) {
		super();
		this.code = code;
		this.des = des;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDes() {
		return des;
	}


	public void setDes(String des) {
		this.des = des;
	}


	@Override
	public String toString() {
		return "Header [code=" + code + ", des=" + des + "]";
	}


}
