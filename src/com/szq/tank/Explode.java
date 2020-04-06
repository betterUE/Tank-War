package com.szq.tank;

import java.awt.Graphics;

import com.szq.tank.facade.GameModel;
import com.szq.tank.factory.BaseExplode;

/**
 * @author: shizq
 * @Date: 2020年3月23日下午11:02:42
 * @Des:
 * @Version: 1.0
 */
public class Explode extends BaseExplode{
	private static int Width = ResourceMgr.explodes[0].getWidth();
	private static int Height = ResourceMgr.explodes[0].getHeight();
	private int x, y;
	private boolean living = true;
	GameModel gm = null;
	private int step = 0;
	public Explode(int x, int y, GameModel gm) {
		this.x = x;
		this.y = y;
		this.gm = gm;
		//爆炸声音
		//new Audio("audio/explode.wav"
	}
	public boolean getLive() {
		return living;
	}
	
	public void setLive(boolean live) {
		this.living = live;
	}
	
	// 画出炮弹
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if(step >= ResourceMgr.explodes.length){
			gm.getExplodes().remove(this);
		}
	}
}
