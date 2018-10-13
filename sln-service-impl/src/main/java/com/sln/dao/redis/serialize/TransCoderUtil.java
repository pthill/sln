package com.sln.dao.redis.serialize;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName TransCoderUtil
 * @Description TODO
 * @author li.biao
 * @date 2015-4-3
 */
public class TransCoderUtil {
	
	public static <T extends Serializable> T deserialize(byte[] key,Class<T> T){
		ObjectsTranscoder<T> obj = new ObjectsTranscoder<T>();
		return obj.deserialize(key);
	}
	
	public static <T extends Serializable> byte[] serial(Object t,Class<T> T){
		return serial(t,T,false);
	}
	
	public static <T extends Serializable> List<T> deserializelist(byte[] key,Class<T> T){
		ListTranscoder<T> obj = new ListTranscoder<T>();
		return obj.deserialize(key);
	}
	
	public static <T extends Serializable> byte[] serial(Object t,Class<T> T,boolean islist){
		byte[] res = null;
		if(islist){
			ListTranscoder<T> obj = new ListTranscoder<T>();
			res =  obj.serialize(t);
		}else{
			ObjectsTranscoder<T> obj = new ObjectsTranscoder<T>();
			res =  obj.serialize(t);
		}
		return res;
	}
	
	public static byte[] serialString(String key){
		try {
			return key.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String deserializeString(byte[] key){
		
		try {
			return new String(key,"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	  public static void  main(String[] args){
		  System.out.println(TransCoderUtil.serialString("LOGIN123"));
	  }
}
