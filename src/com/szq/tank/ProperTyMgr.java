package com.szq.tank;

import java.io.IOException;
import java.util.Properties;

public class ProperTyMgr {
	static Properties props = new Properties();
	
	/**
	 * config文件不需要 properties后缀
	 */
	static {
		try {
			props.load(ProperTyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object get(String key){
		if(props == null){
			return null;
		}
		return props.get(key); 
	}
	
	/*
	 * 程序优化：由于Properties的get(String key) 方法的返回值是Object,在调用的时候需要强制转换，不是太方便，可以封装方法
	 * 从方法名字上判断获取的返回值类型，，这属于重构 如下
	 */
	public static int getInt(String key){
		if(props == null){
			return (Integer) null;
		}
		return Integer.parseInt((String)props.get(key)); 
	}
	
	public static String getString(String key){
		if(props == null){
			return null;
		}
		return (String)props.get(key); 
	}
	
	//单元测试
	public static void main(String[] args) {
		System.out.println(ProperTyMgr.get("initTankCount"));
	}
}
