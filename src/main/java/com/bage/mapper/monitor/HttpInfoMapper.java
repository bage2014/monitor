package com.bage.mapper.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bage.domain.monitor.HttpInfo;


@Mapper
public interface HttpInfoMapper {

	List<HttpInfo> init(@Param("size") int size);
	List<HttpInfo> queryLatest(String maxId );
	
	int insert(HttpInfo httpInfo);
	int insertDefault(HttpInfo httpInfo);
	
	int deleteById(int id );
	
	int updateById(HttpInfo httpInfo);
	
	HttpInfo queryById(@Param("id") int id);

	int getTotalRow();
	List<HttpInfo> queryByPage(@Param("startId") int startId,@Param("pageLength") int pageLength);
	
}
