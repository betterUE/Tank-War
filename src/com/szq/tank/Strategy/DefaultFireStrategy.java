package com.szq.tank.Strategy;

import com.szq.tank.Bullet;
import com.szq.tank.Tank;
import com.szq.tank.factory.DefaultFactory;

/**
 * @author: shizq
 * @Date: 2020年3月29日下午10:01:11
 * @Des: 默认的开火策略,设计成 单例模式 ,使用设计模式中的 第七种
 * @Version: 1.0
 */
public class DefaultFireStrategy implements FireStrategy {
	//首先构造方法私有化
	DefaultFireStrategy() {
	
	}
	
	private static class DefaultFireHolder{
		private static final DefaultFireStrategy defaultFireInstance = new DefaultFireStrategy();
	}
	
	public static DefaultFireStrategy getInstance(){
		return DefaultFireHolder.defaultFireInstance;
	}
	

	@Override
	public void fire(Tank t) {
		//new Bullet(t.getX(), t.getY(), t.dir, t.group ,t.tf);	 
		DefaultFactory.getInstance().createBullet(t.getX(), t.getY(), t.dir, t.group, t.tf);
	}

}
