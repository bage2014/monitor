package com.bage.service.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bage.domain.monitor.SqlInfo;
import com.bage.mapper.monitor.SqlInfoMapper;

@Service
public class SqlInfoService {
	
	@Autowired
	private SqlInfoMapper sqlInfoMapper;
	

	public List<SqlInfo> init(int size ){
		return sqlInfoMapper.init(size);
	}
	
	public List<SqlInfo> queryLatest(int maxId ){
		return sqlInfoMapper.queryLatest(maxId);
	}
	
	private int getTotalRow(){
		return sqlInfoMapper.getTotalRow();
	}	
	
	public List<SqlInfo> queryByPage(int tagPage, int pageLength){
		int totalRow = getTotalRow();
		int totalPage = getTotalRow() / pageLength;
		if(totalPage *  pageLength < totalRow){ // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		if(tagPage <= 0){
			tagPage = totalPage;
		}		
		int startId = pageLength * (tagPage - 1 );
		return sqlInfoMapper.queryByPage(startId , pageLength);
	}	
	public int insert(SqlInfo sqlInfo){
		return sqlInfoMapper.insert(sqlInfo);
	}
	public int insertDefault(SqlInfo sqlInfo){
		return sqlInfoMapper.insertDefault(sqlInfo);
	}
	public int deleteById(int id ){
		return sqlInfoMapper.deleteById(id);
	}
	public int updateById(SqlInfo SqlInfo){
		return sqlInfoMapper.updateById(SqlInfo);
	}
	public SqlInfo queryById(@Param("id") int id){
		return sqlInfoMapper.queryById(id);
	}
}
