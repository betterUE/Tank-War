package com.szq.tank.factory;

import com.szq.tank.Dir;
import com.szq.tank.Group;
import com.szq.tank.TankFrame;

public class RectFactory extends AbstractFactory {
	
	private RectFactory(){
	}
	
	private static class RectHolder{
		private static final RectFactory RECT_INSTANCE = new RectFactory();
	}
	
	public AbstractFactory getInstance(){
		return RectHolder.RECT_INSTANCE;
	}

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		return null;
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new RectBullet(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame tf) {
		return new RectExplode(x, y, tf);
	}

}
