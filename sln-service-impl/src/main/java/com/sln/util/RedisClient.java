package com.sln.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sln.dao.redis.RedisDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Redis使用工具类
 * @author li.biao
 *
 */
@Component(value = "redisClient")
public class RedisClient {
	
	private static Logger log = LogManager.getLogger(RedisClient.class);

	@Autowired
	private RedisDao redisDao;
	
	/**
	 * 设置缓存对象，永久有效
	 * @param key
	 * @param data
	 */
	public void set(String key,Object data){
		redisDao.addRedisInfo(key, data);
	}
	
	/**
	 * 设置缓存对象，可设置失效时间
	 * @param key
	 * @param data
	 * @param maxage 失效时间（单位分钟）
	 */
	public void set(String key,Object data,int maxage){
		redisDao.addRedisInfo(key, data,maxage);
	}
	
	/**
	 * 获取字符串
	 * @param key
	 * @return
	 */
	public String getString(String key){
		if(StringUtils.isEmpty(key)){
			return null ;
		}
		return redisDao.getString(key);
	}
	
	/**
	 * 删除某个缓存
	 * @param key
	 */
	public void del(String key){
		if(StringUtils.isEmpty(key)){
			return ;
		}
		redisDao.del(key);
	}
	
	/**
	 * 设置某个缓存失效时间
	 * @param key
	 * @param maxage 失效时间，单位分钟
	 */
	public void expire(String key, int maxage){
		if(StringUtils.isEmpty(key)){
			return ;
		}
		redisDao.expire(key, maxage);
	}
	
	/**
	 * 获取实体对象
	 * @param key
	 * @param beanClass
	 * @return
	 */
	public <T> T getBean(String key,Class beanClass){
		if(StringUtils.isEmpty(key)){
			return null ;
		}
		String value = redisDao.getString(key);
		if(StringUtils.isEmpty(value)){
			return null ;
		}
		return (T) JSONObject.toBean(JSONObject.fromObject(value),beanClass);
	}
	
	/**
	 * 获取实体集合对象
	 * @param key
	 * @param beanClass
	 * @return
	 */
	public <T> List<T> getBeanList(String key ,Class beanClass){
		if(StringUtils.isEmpty(key)){
			return null ;
		}
		String value = redisDao.getString(key);
		if(StringUtils.isEmpty(value)){
			return null ;
		}
		JSONArray jsonArray = JSONArray.fromObject(value);
		List list = new ArrayList();
		for(int i = 0 ; i < jsonArray.size() ; i ++){
			list.add(JSONObject.toBean(JSONObject.fromObject(jsonArray.get(i)),beanClass));
		}
		return list ;
	}
	
	public void addObject(String key,Object o,Class<? extends Serializable> T){
		redisDao.addByteRedisInfo(key, o, T);
	}
	
	public void addObjectList(String key,List list,Class<?  extends Serializable> T){
		redisDao.addByteRedisListInfo(key, list, T);
	}
	
	public <T extends Serializable> T getObject(String key,Class<T> T){
		return redisDao.getByteObject(key, T);
	}
	
	public <T extends Serializable> List<T> getByteList(String key, Class<T> T){
		return redisDao.getByteList(key, T);
	}
	
	
	public RedisDao getRedisDao() {
		return redisDao;
	}

	public void setRedisDao(RedisDao redisDao) {
		this.redisDao = redisDao;
	}
	
}
