package com.sln.dao.redis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName RedisDao
 * @Description 该接口定义访问Redis，操作Redis数据的方法
 *              <li>RedisDao提供访问redis服务的基本方法，支持字符串、列表、集合、散列表、有序集合等数据格式的存储</li>
 *              <li>本接口暂未定义二进制对象的缓存，比如图片和视频等</li>
 *              <li>现在只是单个redis的应用，集群分布式的时候需要扩展</li>
 *              <li>Redis是一个速度非常快的非关系数据库,系统中常用的公共基础数据，数据字典，不频繁更新，
 *              但是需要频繁使用的数据可以存入redis</li>
 * @author li.biao
 * @date 2017-12-13
 */
public interface RedisDao {

	/**
	 * <li>存储Map数据，无失效时间</li>
	 * @param key
	 * @param data
	 */
	void addRedisInfo(String key, Map<String, String> data);

	/**
	 * <li>存储Map数据，可以设置失效时间，设为-1表示永久不失效</li>
	 * @param key
	 * @param data
	 * @param maxage
	 *            失效时间，单位（分）
	 */
	void addRedisInfo(String key, Map<String, String> data, int maxage);

	/**
	 * <li>设置哈希散列key中的域field字段的值为data。</li>
	 * <li>如果 key 不存在，一个新的哈希表被创建并进行HSET操作。</li>
	 * <li>如果域 field 已经存在于哈希表中，旧值将被覆盖。时间复杂度：O(1)</li>
	 * <li>一般field字段不定项时，可以利用哈希扩展</li>
	 * @param key
	 *            散列key
	 * @param field
	 *            被覆盖的field
	 * @param data
	 *            要设置的field，String对象
	 */
	void addRedisInfo(String key, String field, String data);

	/**
	 * <li>设置哈希散列key中的域field字段的值为data。</li>
	 * <li>如果 key 不存在，一个新的哈希表被创建并进行HSET操作。</li>
	 * <li>如果域 field 已经存在于哈希表中，旧值将被覆盖。时间复杂度：O(1)</li>
	 * <li>一般field字段不定项时，可以利用哈希扩展</li>
	 * 
	 * @param key
	 *            散列key
	 * @param field
	 *            被覆盖的field
	 * @param data
	 *            要设置的field，Object对象
	 */
	void addRedisObjectInfo(String key, String field, Object data);

	/**
	 * <li>将List数据作为jsonarry存入redis</li>
	 * <li>一般field字段不定项时，可以利用哈希扩展</li>
	 * 
	 * @param key
	 *            散列key
	 * @param field
	 *            字段
	 * @param data
	 *            list集合数据
	 */
	void addRedisListInfo(String key, String field, List data);

	/**
	 * <li>将Map对象存入redis,Map中的key键会作为Hset中的field字段</li>
	 * 
	 * @param key
	 *            缓存key
	 * @param data
	 *            要缓存的map对象
	 */
	void addRedisMapInfo(String key, Map<String, Object> data);

	/**
	 * <li>直接缓存Object对象数据，无失效时间</li>
	 * 
	 * @param key
	 *            缓存key
	 * @param data
	 *            Object对象
	 */
	void addRedisInfo(String key, Object data);

	/**
	 * <li>直接缓存Object对象数据，有失效时间</li>
	 * 
	 * @param key
	 *            缓存key
	 * @param data
	 *            Object对象
	 * @maxage 失效时间，单位分钟
	 */
	void addRedisInfo(String key, Object data, int maxage);

	/**
	 * <li>通过Key获取在redis存储的Map数据</li>
	 * 
	 * @param key
	 *            缓存Key
	 * @return Map<String,String>
	 */
	public Map<String, String> get(String key);

	/**
	 * <li>通过Key获取在redis存储的Map，值为json格式数据</li>
	 * 
	 * @param key
	 * @param classary
	 * @return
	 */
	public Map<String, Object> getRedisMapInfo(String key, Map<String, Class> classary);

	/**
	 * <li>直接通过缓存key获取存入的字符串</li>
	 * 
	 * @param key
	 *            缓存key
	 * @return 字符串
	 */
	public String getString(String key);

	/**
	 * <li>直接通过缓存key和字段获取字符串</li>
	 * 
	 * @param key
	 *            缓存key
	 * @param field
	 *            字段
	 * @return 字符串
	 */
	public String getString(String key, String field);

	/**
	 * <li>通过key删除缓存中的fields字段</li>
	 * 
	 * @param key
	 *            缓存key
	 * @param fields
	 *            字段数组
	 */
	public void removeRedisInfo(String key, String[] fields);

	/**
	 * <li>判断缓存中是否已经使用了key</li>
	 * 
	 * @param key
	 *            需判断的key
	 * @return
	 */
	public boolean existRedisInfo(String key);

	/**
	 * <li>设置某个key的失效时间</li>
	 * 
	 * @param key
	 *            需要失效的key
	 * @param maxage
	 *            失效时间，单位分钟
	 */
	public void expire(String key, int maxage);
	
	/**
	 * <li>删除Key信息</li>
	 * @param key
	 */
	public void del(String key);

	/**
	 * <li>获取缓存key中的键集合</li>
	 * 
	 * @param key
	 *            缓存key
	 * @return
	 */
	public Set getListKey(String key);

	/**
	 * <li>获取缓存中List对象</li>
	 * 
	 * @param key
	 *            key对象
	 * @param c
	 *            类对象
	 * @return
	 */
	public List getListData(String key, Class c);

	/**
	 * <li>判断是否使用了redis</li>
	 * <li>判断是否实例化了redis客户端</li>
	 * 
	 * @return
	 */
	public boolean useRedis();
	
	void  addByteRedisInfo(String key,Object o,Class<?  extends Serializable> T);
	
	void  addByteRedisListInfo(String key,List list,Class<?  extends Serializable> T);
	
	public <T extends Serializable> T getByteObject(String key,Class<T> T);
	
	public <T extends Serializable> List<T> getByteList(String key,Class<T> T);
	
	

}
