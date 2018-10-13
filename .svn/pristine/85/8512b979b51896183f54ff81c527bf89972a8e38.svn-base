package com.sln.dao.redis.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.dao.redis.RedisDao;
import com.sln.dao.redis.serialize.TransCoderUtil;
import com.sln.util.RedisUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import redis.clients.jedis.Jedis;

/**
 * @ClassName RedisDaoImpl
 * @Description 该类实现RedisDao接口的业务方法，访问Redis，操作Redis数据
 * @author li.biao
 * @date 2017-12-15
 */
@Component(value = "redisDao")
public class RedisDaoImpl implements RedisDao {

	private static Logger log = LogManager.getLogger(RedisDaoImpl.class);
	
	@Autowired
	private RedisUtil redisUtil; 	// 客户端链接redis操作工具类

	@Override
	public void addRedisInfo(String key, Map<String, String> data) {
		// -1代表不失效
		addRedisInfo(key, data, -1);
	}

	@Override
	public Map<String, String> get(String key) {
		if (!redisUtil.useRedis())
			return new HashMap<String, String>();
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		Map<String, String> result = jedis.hgetAll(this.getAppKey(key));
		redisUtil.closeConnection(jedis);
		return result;
	}

	@Override
	public void addRedisInfo(String key, Object obj) {
		this.addRedisInfo(key, obj, -1);
	}

	@Override
	public void addRedisInfo(String key, Object obj, int maxage) {
		if (!redisUtil.useRedis())
			return;
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		String data = "";
		if (JSONUtils.isArray(obj.getClass())) {// 如果obj为json格式的数组对象
			data = JSONArray.fromObject(obj).toString();
		} else if (JSONUtils.isObject(obj)) {// 如果obj为json格式的对象
			data = JSONObject.fromObject(obj).toString();
		} else {
			// 字符串对象
			data = obj.toString();
		}
		jedis.set(this.getAppKey(key), data);
		// 如果设置了失效时间大于0
		if (maxage > 0){
			jedis.expire(this.getAppKey(key), maxage * 60);
		}
		//释放客户端链接
		redisUtil.closeConnection(jedis);
	}

	@Override
	public String getString(String key) {
		if (!redisUtil.useRedis())
			return null;
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		String result = jedis.get(this.getAppKey(key));
		//释放客户端链接
		redisUtil.closeConnection(jedis);
		return result;
	}

	@Override
	public void addRedisInfo(String key, String field, String data) {
		if (!redisUtil.useRedis())
			return;
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		jedis.hset(this.getAppKey(key), field, data);
		//释放客户端链接
		redisUtil.closeConnection(jedis);
	}

	@Override
	public String getString(String key, String field) {
		if (!redisUtil.useRedis())
			return null;
		Jedis jedis = redisUtil.getConnection();
		// 获取redis客户端链接Jedis
		String result = jedis.hget(this.getAppKey(key), field);
		//释放客户端链接
		redisUtil.closeConnection(jedis);
		return result;
	}

	@Override
	public void removeRedisInfo(String key, String[] fields) {
		if (!redisUtil.useRedis())
			return;
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		jedis.hdel(this.getAppKey(key), fields);
		//释放客户端链接
		redisUtil.closeConnection(jedis);
	}
	
	public void del(String key){
		if (!redisUtil.useRedis()){
			return ;
		}
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		jedis.del(this.getAppKey(key));
		//释放客户端链接
		redisUtil.closeConnection(jedis);
	}

	@Override
	public boolean existRedisInfo(String key) {
		if (!redisUtil.useRedis())
			return false;
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		boolean b = jedis.exists(this.getAppKey(key));
		//释放客户端链接
		redisUtil.closeConnection(jedis);
		return b;
	}

	@Override
	public void addRedisInfo(String key, Map<String, String> data, int maxage) {
		if (!redisUtil.useRedis())
			return;
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		jedis.hmset(this.getAppKey(key), data);
		// 失效时间
		if (maxage > 0)
			jedis.expire(this.getAppKey(key), maxage * 60);
		//释放客户端链接
		redisUtil.closeConnection(jedis);
	}

	@Override
	public void addRedisObjectInfo(String key, String field, Object data) {
		if (!redisUtil.useRedis())
			return;
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		// 转换成json对象存入缓存
		jedis.hset(this.getAppKey(key), field, JSONObject.fromObject(data).toString());
		//释放客户端链接
		redisUtil.closeConnection(jedis);
	}

	@Override
	public void addRedisListInfo(String key, String field, List data) {
		if (!redisUtil.useRedis())
			return;
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		// 转换成json数组对象存入缓存
		jedis.hset(this.getAppKey(key), field, JSONArray.fromObject(data).toString());
		//释放客户端链接
		redisUtil.closeConnection(jedis);
	}

	@Override
	public void addRedisMapInfo(String key, Map<String, Object> data) {
		if (!redisUtil.useRedis())
			return;
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		Set<String> keyset = data.keySet();
		Iterator<String> ite = keyset.iterator();
		while (ite.hasNext()) {
			String field = ite.next();
			Object obj = data.get(field);
			if (null == obj)
				continue;
			if (obj.getClass().equals(String.class)) {//String
				jedis.hset(this.getAppKey(key), field, obj.toString());
			} else if (JSONUtils.isArray(obj.getClass())) {//json数组
				jedis.hset(this.getAppKey(key), field, JSONArray.fromObject(obj).toString());
			} else {//json对象
				jedis.hset(this.getAppKey(key), field, JSONObject.fromObject(obj).toString());
			}
		}
		//释放客户端链接
		redisUtil.closeConnection(jedis);
	}

	@Override
	public Map<String, Object> getRedisMapInfo(String key, Map<String, Class> classary) {
		if (!redisUtil.useRedis())
			return new HashMap<String, Object>();
		// 获取redis客户端链接Jedis
		Jedis jedis = redisUtil.getConnection();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> data = jedis.hgetAll(this.getAppKey(key));
		Set<String> keyset = data.keySet();
		Iterator<String> ite = keyset.iterator();
		while (ite.hasNext()) {
			String field = ite.next();
			String jsondata = data.get(field);
			if (jsondata.startsWith("[")) {//json数组
				result.put(field, JSONArray.toCollection(JSONArray.fromObject(jsondata), classary.get(field)));
			} else if (jsondata.startsWith("{")) {//json对象
				result.put(field, JSONObject.toBean(JSONObject.fromObject(jsondata), classary.get(field)));
			} else {
				result.put(field, jsondata);
			}
		}
		//释放客户端链接
		redisUtil.closeConnection(jedis);
		return result;
	}

	@Override
	public void expire(String key, int maxage) {
		if (!redisUtil.useRedis())
			return;
		Jedis jedis = redisUtil.getConnection();
		//设置失效时间
		jedis.expire(this.getAppKey(key), maxage * 60);
		redisUtil.closeConnection(jedis);
	}

	@Override
	public boolean useRedis() {
		return redisUtil.useRedis();
	}

	@Override
	public Set getListKey(String key) {
		if (!redisUtil.useRedis())
			return null;
		Jedis jedis = redisUtil.getConnection();
		Set<String> setlist = jedis.keys(this.getAppKey(key));
		redisUtil.closeConnection(jedis);
		return setlist;
	}

	@Override
	public List getListData(String key, Class c) {
		if (!redisUtil.useRedis())
			return null;
		Jedis jedis = redisUtil.getConnection();
		Set<String> setlist = jedis.keys(this.getAppKey(key));
		Iterator<String> ite = setlist.iterator();
		//集合对象
		List<Object> objlist = new ArrayList<Object>();
		while (ite.hasNext()) {
			String field = ite.next();
			String jsondata = jedis.get(field);
			if (jsondata.startsWith("{")) {
				objlist.add(JSONObject.toBean(JSONObject.fromObject(jsondata), c));
			} else {
				objlist.add(jsondata);
			}
		}
		redisUtil.closeConnection(jedis);
		return objlist;
	}
	
	/**
	 * 获取在应用上的真正缓存Key
	 * @param key
	 * @return
	 */
	private String getAppKey(String key){
		//暂时不区分系统级缓存，公用同一Key
		//return DomainUrlUtil.getREDIS_APP() + "_" + key ;
		return  key ;
	}
	
	public RedisUtil getRedisUtil() {
		return redisUtil;
	}

	public void setRedisUtil(RedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}
	
	@Override
	public  void  addByteRedisInfo(String key,Object o,Class<? extends Serializable> T){
		if(!redisUtil.useRedis()) return;
		Jedis jedis = redisUtil.getConnection();
		jedis.set(TransCoderUtil.serialString(key), TransCoderUtil.serial(o, T));
		redisUtil.closeConnection(jedis);
	}
	@Override
	public  void addByteRedisListInfo(String key,List list,Class<?  extends Serializable> T){
		if(!redisUtil.useRedis()) return;
		Jedis jedis = redisUtil.getConnection();
		jedis.set(TransCoderUtil.serialString(key), TransCoderUtil.serial(list,T,true));
		redisUtil.closeConnection(jedis);
	}

	@Override
	public <T extends Serializable> T getByteObject(String key, Class<T> T) {
		if(!redisUtil.useRedis()) return null;
		byte[] keyby = TransCoderUtil.serialString(key);	
		Jedis jedis = redisUtil.getConnection();
		byte[] valueby = jedis.get(keyby);
		T tt = TransCoderUtil.deserialize(valueby, T);
		redisUtil.closeConnection(jedis);	
		return tt;
	}

	@Override
	public <T extends Serializable> List<T> getByteList(String key, Class<T> T) {
		if(!redisUtil.useRedis()) return null;
		byte[] keyby = TransCoderUtil.serialString(key);	
		Jedis jedis = redisUtil.getConnection();
		byte[] valueby = jedis.get(keyby);
		List<T> tt = TransCoderUtil.deserializelist(valueby, T);
		redisUtil.closeConnection(jedis);	
		return tt;
	}

}
