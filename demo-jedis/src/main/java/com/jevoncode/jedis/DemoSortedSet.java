package com.jevoncode.jedis;

import java.util.ResourceBundle;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

public class DemoSortedSet {
	private static JedisPool jedisPool = null;
	static {
		ResourceBundle config = ResourceBundle.getBundle("jedis");
		String redisHost = config.getString("redis.host");
		String redisPort = config.getString("redis.port");
		String redisPasswd = config.getString("redis.passwd");

		JedisPoolConfig jedisConf = new JedisPoolConfig();
		jedisConf.setMinIdle(100);
		jedisConf.setMaxIdle(1000);
		jedisConf.setMaxActive(1000);
		jedisPool = new JedisPool(jedisConf, redisHost, Integer.parseInt(redisPort), 1000, redisPasswd);
	}

	public static void zadd(String key, double score, String memeber) {
		Jedis jedis = jedisPool.getResource();
		Long count = jedis.zadd(key, score, memeber);
		jedisPool.returnResource(jedis);
		System.out.println("save success "+count );
	}

	public static void zprintAll(String key) {
		Jedis jedis = jedisPool.getResource();
		Set<Tuple> set = jedis.zrangeByScoreWithScores(key, 0, Double.MAX_VALUE);
		for (Tuple t : set)
			System.out.println("score:" + t.getScore() + ",member:" + t.getElement());
		jedisPool.returnResource(jedis);
	}

	public static void zremoveAll(String key) {
		Jedis jedis = jedisPool.getResource();
		Long count = jedis.zremrangeByScore(key, 0, Double.MAX_VALUE);
		jedisPool.returnResource(jedis);
		System.out.println("deleted " + count);
	}

	public static void main(String[] args) {
		String key = "jc.settest";
//		for (int i = 0; i < 100; i++)
//			zadd(key, System.currentTimeMillis(), "testSET" + i);
		zprintAll(key);
//		zremoveAll(key);
	}
}
