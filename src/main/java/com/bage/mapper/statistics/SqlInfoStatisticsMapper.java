package com.bage.mapper.statistics;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bage.domain.statistics.SqlInfoStatistics;

@Mapper
public interface SqlInfoStatisticsMapper {

	int insertBatchMinute(List<SqlInfoStatistics> list);

	int insertBatchHour(List<SqlInfoStatistics> list);

	int insertBatchDay(List<SqlInfoStatistics> list);

	int insertBatchMonth(List<SqlInfoStatistics> list);

	int insertBatchYear(List<SqlInfoStatistics> list);

	List<Map<String, Object>> queryLatest(@Param("appid") String appid ,@Param("maxId") String maxId );
	
	
	List<Map<String, Object>> topN(@Param("n") int n, @Param("sortType") String sortType,
			@Param("startTime") String startTime, @Param("stopTime") String stopTime);

	List<Map<String, Object>> topNYear(@Param("n") int n, @Param("sortType") String sortType,
			@Param("startTime") String startTime, @Param("stopTime") String stopTime);

	List<Map<String, Object>> topNMonth(@Param("n") int n, @Param("sortType") String sortType,
			@Param("startTime") String startTime, @Param("stopTime") String stopTime);

	List<Map<String, Object>> topNDay(@Param("n") int n, @Param("sortType") String sortType,
			@Param("startTime") String startTime, @Param("stopTime") String stopTime);

	List<Map<String, Object>> topNHour(@Param("n") int n, @Param("sortType") String sortType,
			@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	
	List<Map<String, Object>> latest(@Param("appid") String appid, @Param("n") int n ,@Param("startTime") String startTime, @Param("stopTime") String stopTime);

	int getMinuteTotalRow(@Param("appid") String appid,@Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	List<Map<String, Object>> queryMinuteByPage(@Param("appid") String appid, @Param("startId") int startId,@Param("pageLength") int pageLength, @Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	
	int getHourTotalRow(@Param("appid") String appid,@Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	List<Map<String, Object>> queryHourByPage(@Param("appid") String appid, @Param("startId") int startId,@Param("pageLength") int pageLength, @Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	
	int getDayTotalRow(@Param("appid") String appid,@Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	List<Map<String, Object>> queryDayByPage(@Param("appid") String appid, @Param("startId") int startId,@Param("pageLength") int pageLength, @Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	
	int getMonthTotalRow(@Param("appid") String appid,@Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	List<Map<String, Object>> queryMonthByPage(@Param("appid") String appid, @Param("startId") int startId,@Param("pageLength") int pageLength, @Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	
	int getYearTotalRow(@Param("appid") String appid,@Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	List<Map<String, Object>> queryYearByPage(@Param("appid") String appid, @Param("startId") int startId,@Param("pageLength") int pageLength, @Param("condition") String condition,@Param("startTime") String startTime, @Param("stopTime") String stopTime);
	
}
