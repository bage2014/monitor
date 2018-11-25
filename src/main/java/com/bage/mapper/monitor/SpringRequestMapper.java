package com.bage.mapper.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bage.domain.monitor.MemoryInfo;
import com.bage.domain.monitor.SpringInfo;


@Mapper
public interface SpringRequestMapper {

	int insert(SpringInfo springRequest);
	int insertDefault(SpringInfo springRequest);
	int batchInsertDefault(List<SpringInfo> springRequests);
	
	int deleteById(int id );
	
	int updateById(SpringInfo springRequest);
	
	MemoryInfo queryById(@Param("id") int id);
	
	List<SpringInfo> queryByPage(@Param("startId") int startId,@Param("pageLength") int pageLength);
	
}
