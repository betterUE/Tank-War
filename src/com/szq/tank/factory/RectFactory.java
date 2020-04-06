package com.szq.tank.factory;

import com.szq.tank.Dir;
import com.szq.tank.Group;
import com.szq.tank.TankFrame;
import com.szq.tank.facade.GameModel;

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
	public BaseTank createTank(int x, int y, Dir dir, Group group, GameModel tf) {
		return null;
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel tf) {
		return new RectBullet(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode createExplode(int x, int y, GameModel tf) {
		return new RectExplode(x, y, tf);
	}

}
