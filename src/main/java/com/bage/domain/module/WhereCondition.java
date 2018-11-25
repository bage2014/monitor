package com.bage.domain.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * String attribute; 	// 对应表中的字段,比如 id、name 	<br>
 * String operator;		// 运算符,比如 =、<				<br>
 * String value;		// 查询条件值,比如 '1'、'张三'	<br>
 * 
 * @author bage
 *
 */
public class WhereCondition implements Serializable {
	
	public static String operatorsString = "=,!=,<=,>=,<,>";
	public static List<String> operators = new ArrayList<String>();
	static {
		operators.addAll(Arrays.asList(operatorsString.split(",")));
	}
	private static final long serialVersionUID = 1L;

	private String attribute; 	// 对应表中的字段
	private String operator;	// 运算符
	private String value;		// 查询条件值

	public WhereCondition() {
		super();
	}

	public WhereCondition(String attribute, String operator, String value) {
		super();
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WhereCondition other = (WhereCondition) obj;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WhereCondition [attribute=" + attribute + ", operator=" + operator + ", value=" + value + "]";
	}

}
