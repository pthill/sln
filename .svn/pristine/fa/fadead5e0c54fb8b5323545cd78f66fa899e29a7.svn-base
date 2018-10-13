package com.sln.util;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName RedisUtil
 * @Description redis 工具类
 * <li>提供从redis客户端链接池中获取链接，以及关闭释放链接对象<li>
 * <li>目前是实现单个JedisPool对象方式，后续涉及到sentinel哨兵监控，实现redis的master/slave切换，再通过shardedjedispool实现</li>
 * <li>一般的项目实现JedisPool够用了</li>
 * @author li.biao
 * @date 2017-12-15
 */
@Component(value = "redisUtil")
public class RedisUtil {
	
	private static Logger log = LogManager.getLogger(RedisUtil.class);

	@Resource(name="jedisPool")
	private JedisPool jedisPool ;
	
	/**
	 * 获取数据库连接
	 * @return Jedis
	 */
	public Jedis getConnection() {
		// 循环三次获取
		Jedis jedis = null;
		try {
			int i = 0;
			do {
				jedis = jedisPool.getResource();
				i++;
			} while (jedis == null && i < 3);
		} catch (Exception e) {
			log.error("RedisUtil.getConnection"+e.getMessage());
		}
		return jedis;
	}

	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public void closeConnection(Jedis jedis) {
		if (null != jedis) {
			try {
				jedisPool.returnResource(jedis);
			} catch (Exception e) {
				log.error("RedisUtil.closeConnection"+e.getMessage());
			}
		}
	}

	public boolean useRedis() {
		return jedisPool != null;
	}
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}
