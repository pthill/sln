package com.sln.model.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.dao.shop.read.integral.UnitIntegralReadDao;

@Component(value = "actUnitIntegralModel")
public class ActUnitIntegralModel {
	
	@Resource
	private UnitIntegralReadDao unitIntegralReadDao;
	
	public List getIntegralStatistics(Map<String, String> queryMap,Integer size,Integer start) {
		return unitIntegralReadDao.getIntegralStatistics(queryMap, start, size);
	}
	
	public int getIntegralStatisticsCount(Map<String, String> queryMap) {
		return unitIntegralReadDao.getIntegralStatisticsCount(queryMap);
	}
	
	public List getIntegralDetail(Map<String, String> queryMap,Integer size,Integer start) {
		return unitIntegralReadDao.getIntegralDetail(queryMap, start, size);
	}
	
	public int getIntegralDetailCount(Map<String, String> queryMap) {
		return unitIntegralReadDao.getIntegralDetailCount(queryMap);
	}
}
