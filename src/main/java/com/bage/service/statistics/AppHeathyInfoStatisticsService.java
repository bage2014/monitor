package com.bage.service.statistics;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bage.domain.statistics.AppHeathyInfoStatistics;
import com.bage.mapper.statistics.AppHeathyInfoStatisticsMapper;
import com.bage.utils.TimeUtils;


@Service
public class AppHeathyInfoStatisticsService {

	@Autowired
	private AppHeathyInfoStatisticsMapper appHeathyInfoStatisticsMapper;

	/**
	 * 
	 * @param condition
	 * @return
	 */
	public List<Map<String, Object>> queryAll(String condition){
		
		String stopTime = TimeUtils.getCurrentTime();
		String startTime = TimeUtils.getNextTime(stopTime, 0, -1,0, 0,0, 0);
		return appHeathyInfoStatisticsMapper.queryAll(condition,startTime, stopTime);
	}	
	
	/**
	 * 
	 * @param condition
	 * @return
	 */
	public List<Map<String, Object>> queryOne(String appid){
		
		return appHeathyInfoStatisticsMapper.queryOne(appid);
	}	

	/**
	 * 
	 * @param appHeathyInfoStatistics
	 * @return
	 */
	public int insert(AppHeathyInfoStatistics appHeathyInfoStatistics){
		return appHeathyInfoStatisticsMapper.insert(appHeathyInfoStatistics);
	}
	
}
