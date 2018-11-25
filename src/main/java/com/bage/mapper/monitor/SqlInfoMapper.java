package com.bage.mapper.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bage.domain.monitor.SqlInfo;


@Mapper
public interface SqlInfoMapper {

	List<SqlInfo> init(@Param("size") int size);
	public List<SqlInfo> queryLatest(int maxId );
	
	int insert(SqlInfo sqlInfo);
	int insertDefault(SqlInfo sqlInfo);
	
	int deleteById(int id );
	
	int updateById(SqlInfo sqlInfo);
	
	SqlInfo queryById(@Param("id") int id);

	int getTotalRow();
	List<SqlInfo> queryByPage(@Param("startId") int startId,@Param("pageLength") int pageLength);
	
}
