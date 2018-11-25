package com.bage.mapper.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bage.domain.monitor.CpuInfo;


@Mapper
public interface CpuInfoMapper {

	List<CpuInfo> init(@Param("size") int size);
	
	int insert(CpuInfo cpuInfo);
	int insertDefault(CpuInfo cpuInfo);
	
	int deleteById(int id );
	
	int updateById(CpuInfo cpuInfo);
	
	CpuInfo queryById(@Param("id") int id);

	int getTotalRow();
	List<CpuInfo> queryByPage(@Param("startId") int startId,@Param("pageLength") int pageLength);
	
}
