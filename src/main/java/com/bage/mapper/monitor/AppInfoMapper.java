package com.bage.mapper.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bage.domain.monitor.AppInfo;


@Mapper
public interface AppInfoMapper {

	List<AppInfo> init(@Param("size") int size);
	
	int insert(AppInfo appInfo);
	int insertDefault(AppInfo appInfo);
	
	int deleteById(int id );
	
	int updateById(AppInfo appInfo);
	
	AppInfo queryById(@Param("id") String id);

	int getTotalRow(@Param("condition") String condition);
	
	List<AppInfo> queryByPage(@Param("startId") int startId,@Param("condition") String condition,@Param("pageLength") int pageLength);
	
	List<AppInfo> queryAll();
	
}
