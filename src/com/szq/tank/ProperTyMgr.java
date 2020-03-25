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
	
	public static void main(String[] args) {
		System.out.println(ProperTyMgr.get("initTankCount"));
	}
}
