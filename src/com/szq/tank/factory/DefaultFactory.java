package com.szq.tank.factory;

import com.szq.tank.Bullet;
import com.szq.tank.Dir;
import com.szq.tank.Explode;
import com.szq.tank.Group;
import com.szq.tank.Tank;
import com.szq.tank.TankFrame;
import com.szq.tank.facade.GameModel;

public class DefaultFactory extends AbstractFactory {
	
	//单例模式第一步，构造方法私有化
	private DefaultFactory(){
		
	}
	//单例第二步， 创建静态内部类
	private static class FactoryHolder{
		private static final DefaultFactory DEFAULT_INSTANCE = new DefaultFactory();
	}
	
	//第三步骤，获取内部类总共的实例
	public static AbstractFactory getInstance(){
		return FactoryHolder.DEFAULT_INSTANCE;
	}

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group,GameModel gm) {
		return new Tank(x, y, dir, group, gm);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir,Group group, GameModel tf) {
		return new Bullet(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode createExplode(int x, int y, GameModel tf) {
		return new Explode(x, y, tf);
	}

}
