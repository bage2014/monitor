package com.bage.mapper.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bage.domain.monitor.MemoryInfo;


@Mapper
public interface MemoryInfoMapper {
	
	List<MemoryInfo> topN(int n);

	List<MemoryInfo> init(@Param("size") int size);
	
	List<MemoryInfo> queryLatest(@Param("appid") String appid);
	
	int insert(MemoryInfo memoryInfo);
	int insertDefault(MemoryInfo memoryInfo);
	
	int deleteById(int id );
	
	int updateById(MemoryInfo memoryInfo);
	
	MemoryInfo queryById(@Param("id") int id);

	int getTotalRow();
	List<MemoryInfo> queryByPage(@Param("startId") int startId,@Param("pageLength") int pageLength);
	
}
