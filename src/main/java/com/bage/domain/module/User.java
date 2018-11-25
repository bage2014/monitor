package com.bage.domain.module;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements Serializable, UserDetails {// impliments

	private static final long serialVersionUID = 1L;

	private int id;
	private String account;
	private String username;
	private String password;
	private String icon;
	private String roleId;
	private String role;
	private String queryTime;
	private String deleteStatus;
	private String des;

	public User() {
		super();
	}

	public User(int id, String account, String username, String password, String icon, String roleId, String role,
			String queryTime, String deleteStatus, String des) {
		super();
		this.id = id;
		this.account = account;
		this.username = username;
		this.password = password;
		this.icon = icon;
		this.roleId = roleId;
		this.role = role;
		this.queryTime = queryTime;
		this.deleteStatus = deleteStatus;
		this.des = des;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", username=" + username + ", password=" + password
				+ ", icon=" + icon + ", roleId=" + roleId + ", role=" + role + ", queryTime=" + queryTime
				+ ", deleteStatus=" + deleteStatus + ", des=" + des + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Arrays.asList(new SimpleGrantedAuthority(getRole()));
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
