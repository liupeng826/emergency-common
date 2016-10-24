package com.emergency.common.util;


import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * redis实现geo功能
 */
public class JedisGeoUtils {

	private static Jedis jedis = null;
	static{
		jedis = new Jedis("192.168.1.90", 6379);
	}
	/**
	 * 添加geo
	 * @param key
	 * @param longitude
	 * @param latitude
	 * @param dName 位置名称
	 * @return
	 */
	public static Long geoADD(String key,double longitude,double latitude,String dName){
		return (Long)jedis.eval("return redis.call('GEOADD',KEYS[1],KEYS[2],KEYS[3],KEYS[4])", 4,key,String.valueOf(longitude),String.valueOf(latitude),dName);
	}

	/**
	 * 查询2位置距离
	 * @param key
	 * @param d1
	 * @param d2
	 * @param unit
	 * @return
	 */
	public static Double geoDist(String key,String d1,String d2,String unit){
		return Double.valueOf((String)jedis.eval("return redis.call('GEODIST',KEYS[1],KEYS[2],KEYS[3],KEYS[4])",4, key,d1,d2,unit));
	}

	/**
	 * 查询位置的geohash
	 * @param key
	 * @param dName
	 * @return
	 */
	public static String geoHash(String key,String dName){
		Object data = jedis.eval("return redis.call('GEOHASH',KEYS[1],KEYS[2])", 2, key,dName);
		List<String> resultList = (List<String>)data;
		if(resultList!=null&&resultList.size() > 0){
			return resultList.get(0);
		}
		return null;
	}

	/**
	 * 查询位置坐标
	 * @param key
	 * @param dName
	 * @return
	 */
	public static List<Double> geoPos(String key,String dName){
		Object data = jedis.eval("return redis.call('GEOPOS',KEYS[1],KEYS[2])", 2, key,dName);
		List<List<Double>> resultList = (List<List<Double>>)data;
		if(resultList!=null&&resultList.size() > 0){
			return resultList.get(0);
		}
		return null;
	}

	/**
	 * 查询附近坐标地址
	 * @param key
	 * @param longitude
	 * @param latitude
	 * @param unit
	 * @param asc
	 * @return
	 */
	public static List<String> geoRadius(String key,double longitude,double latitude,int radius,String unit,boolean asc){
		Object data = jedis.eval("return redis.call('GEORADIUS',KEYS[1],KEYS[2],KEYS[3],KEYS[4],KEYS[5],KEYS[6])", 6, key,String.valueOf(longitude),
				String.valueOf(latitude),String.valueOf(radius),unit,asc?"ASC":"DESC");
		return (List<String>)data;
	}

	/**
	 * 根据位置查询附近点
	 * @param key
	 * @param dName
	 * @param unit
	 * @param asc
	 * @return
	 */
	public static List<String> geoRadiusByMember(String key,String dName,int radius,String unit,boolean asc){
		Object data = jedis.eval("return redis.call('GEORADIUSBYMEMBER',KEYS[1],KEYS[2],KEYS[3],KEYS[4],KEYS[5])", 5, key,dName,String.valueOf(radius),unit,asc?"ASC":"DESC");
		return (List<String>)data;
	}


}
