package com.bage.mapper.statistics;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bage.domain.statistics.AppHeathyInfoStatistics;

@Mapper
public interface AppHeathyInfoStatisticsMapper {

	int insert(AppHeathyInfoStatistics appHeathyInfoStatistics);

	List<Map<String, Object>> queryAll(@Param("condition") String condition, @Param("startTime") String startTime, @Param("stopTime") String stopTime);
	
	List<Map<String, Object>> queryOne(@Param("appid") String appid);

	
}
