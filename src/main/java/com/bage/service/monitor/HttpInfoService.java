package com.bage.service.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bage.domain.monitor.HttpInfo;
import com.bage.mapper.monitor.HttpInfoMapper;

@Service
public class HttpInfoService {
	
	@Autowired
	private HttpInfoMapper sqlInfoMapper;
	

	public List<HttpInfo> init(int size ){
		return sqlInfoMapper.init(size);
	}
	
	public List<HttpInfo> queryLatest(String maxId ){
		return sqlInfoMapper.queryLatest(maxId);
	}
	
	private int getTotalRow(){
		return sqlInfoMapper.getTotalRow();
	}	
	
	public List<HttpInfo> queryByPage(int tagPage, int pageLength){
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
	public int insert(HttpInfo httpInfo){
		return sqlInfoMapper.insert(httpInfo);
	}
	public int insertDefault(HttpInfo httpInfo){
		return sqlInfoMapper.insertDefault(httpInfo);
	}
	public int deleteById(int id ){
		return sqlInfoMapper.deleteById(id);
	}
	public int updateById(HttpInfo httpInfo){
		return sqlInfoMapper.updateById(httpInfo);
	}
	public HttpInfo queryById(@Param("id") int id){
		return sqlInfoMapper.queryById(id);
	}
}
