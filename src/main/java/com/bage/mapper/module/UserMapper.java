package com.bage.mapper.module;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bage.domain.module.User;


@Mapper
public interface UserMapper {

	int insert(User user);
	
	int deletebyId(@Param("id") int id );
	int deleteByAccount(@Param("account") String account );
	
	int update(User user);
	
	User queryById(@Param("id") int id);
	
	User queryByAccount(@Param("account") String account);
	
}
