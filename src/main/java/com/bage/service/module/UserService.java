package com.bage.service.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bage.domain.module.User;
import com.bage.mapper.module.UserMapper;
import com.bage.utils.MD5Utils;

@Component
public class UserService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	public int insert(User user) {
		if (user == null) {
			return 0;
		}
		// 对密码进行加密
		user.setPassword(MD5Utils.encode(user.getPassword()));
		return userMapper.insert(user);
	}

	public User findUser(String account) {
		if (account == null) {
			return null;
		}

		User user = userMapper.queryByAccount(account);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = findUser(username);

		if (user == null) {
			throw new UsernameNotFoundException("");
		}

		user.setRole("USER");
		return user;

	}

}
