package com.bage.mapper.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bage.domain.monitor.SessionInfo;
import com.bage.domain.monitor.SqlInfo;


@Mapper
public interface SessionInfoMapper {

	List<SessionInfo> init(@Param("size") int size);
	public List<SessionInfo> queryLatest(int maxId );
	
	int insert(SessionInfo sessionInfo);
	int insertDefault(SessionInfo sessionInfo);
	
	int deleteById(int id );
	
	int updateById(SessionInfo sessionInfo);
	
	SessionInfo queryById(@Param("id") int id);

	int getTotalRow();
	List<SqlInfo> queryByPage(@Param("startId") int startId,@Param("pageLength") int pageLength);
	
}
