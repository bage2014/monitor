package com.bage.service.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bage.domain.monitor.CpuInfo;
import com.bage.mapper.monitor.CpuInfoMapper;

@Service
public class CpuInfoService {
	
	@Autowired
	private CpuInfoMapper cpuInfoMapper;
	
	private int getTotalRow(){
		return cpuInfoMapper.getTotalRow();
	}	
	
	public List<CpuInfo> queryByPage(int tagPage, int pageLength){
		
		int totalRow = getTotalRow();
		int totalPage = getTotalRow() / pageLength;
		if(totalPage *  pageLength < totalRow){ // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		if(tagPage <= 0){
			tagPage = totalPage;
		}		
		int startId = pageLength * (tagPage - 1 );
		return cpuInfoMapper.queryByPage(startId , pageLength);
	}	
	public int insert(CpuInfo cpuInfo){
		return cpuInfoMapper.insert(cpuInfo);
	}
	public int insertDefault(CpuInfo cpuInfo){
		return cpuInfoMapper.insertDefault(cpuInfo);
	}
	public int deleteById(int id ){
		return cpuInfoMapper.deleteById(id);
	}
	public int updateById(CpuInfo cpuInfo){
		return cpuInfoMapper.updateById(cpuInfo);
	}
	public CpuInfo queryById(@Param("id") int id){
		return cpuInfoMapper.queryById(id);
	}
}
