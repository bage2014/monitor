package com.bage.service.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bage.domain.monitor.AppInfo;
import com.bage.mapper.monitor.AppInfoMapper;
@Service
public class AppInfoService {
	
	@Autowired
	private AppInfoMapper appInfoMapper;
	
	public int getTotalPageNum(String condition,int pageLength){
		int totalRow = getTotalRow(condition);
		int totalPage = totalRow / pageLength;
		if(totalPage *  pageLength < totalRow){ // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		return totalPage;
	}	
	
	private int getTotalRow(String condition){
		return appInfoMapper.getTotalRow(condition);
	}	
	
	public List<AppInfo> queryByPage(int tagPage,String condition, int pageLength){
		
		int totalRow = getTotalRow(condition);
		int totalPage = totalRow / pageLength;
		if(totalPage *  pageLength < totalRow){ // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		if(tagPage <= 0){
			tagPage = totalPage;
		}		
		if(tagPage > totalPage){
			tagPage = totalPage;
		}		
		if(tagPage <= 0){
			tagPage = 1;
		}
		int startId = pageLength * (tagPage - 1 );
		
		return appInfoMapper.queryByPage(startId ,condition, pageLength);
	}	
	public int insert(AppInfo appInfo){
		return appInfoMapper.insert(appInfo);
	}
	public int insertDefault(AppInfo appInfo){
		return appInfoMapper.insertDefault(appInfo);
	}
	public int deleteById(int id ){
		return appInfoMapper.deleteById(id);
	}
	public int updateById(AppInfo appInfo){
		return appInfoMapper.updateById(appInfo);
	}
	public AppInfo queryById(@Param("id") String id){
		
		return appInfoMapper.queryById(id);
	}
}
