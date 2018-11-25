package com.bage.service.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bage.domain.monitor.MemoryInfo;
import com.bage.mapper.monitor.MemoryInfoMapper;

@Service
public class MemoryInfoService {
	
	@Autowired
	private MemoryInfoMapper memoryInfoMapper;
	

	public List<MemoryInfo> topN(int n){
		return memoryInfoMapper.topN(n);
	}
	
	public List<MemoryInfo> init(int size ){
		return memoryInfoMapper.init(size);
	}
	
	public List<MemoryInfo> queryLatest(String appid ){
		return memoryInfoMapper.queryLatest(appid);
	}
	
	private int getTotalRow(){
		return memoryInfoMapper.getTotalRow();
	}	
	
	public List<MemoryInfo> queryByPage(int tagPage, int pageLength){
		int totalRow = getTotalRow();
		int totalPage = getTotalRow() / pageLength;
		if(totalPage *  pageLength < totalRow){ // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		if(tagPage <= 0){
			tagPage = totalPage;
		}		
		int startId = pageLength * (tagPage - 1 );
		return memoryInfoMapper.queryByPage(startId , pageLength);
	}	
	public int insert(MemoryInfo memoryInfo){
		return memoryInfoMapper.insert(memoryInfo);
	}
	public int insertDefault(MemoryInfo memoryInfo){
		return memoryInfoMapper.insertDefault(memoryInfo);
	}
	public int deleteById(int id ){
		return memoryInfoMapper.deleteById(id);
	}
	public int updateById(MemoryInfo memoryInfo){
		return memoryInfoMapper.updateById(memoryInfo);
	}
	public MemoryInfo queryById(@Param("id") int id){
		return memoryInfoMapper.queryById(id);
	}
}
