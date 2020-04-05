package com.szq.tank.factory;

import com.szq.tank.Dir;
import com.szq.tank.Group;
import com.szq.tank.TankFrame;

/**
 * @author: shizq
 * @Date: 2020年4月5日下午3:13:23
 * @Des:  抽象工厂
 * @Version: 1.0
 */
public abstract class AbstractFactory {
	
	public abstract BaseTank createTank(int x, int y, Dir dir, Group group,TankFrame tf);
	
	public abstract BaseBullet createBullet(int x, int y, Dir dir,Group group, TankFrame tf);
	
	public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
	
}
