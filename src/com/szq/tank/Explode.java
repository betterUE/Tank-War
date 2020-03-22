package com.szq.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Explode {
	private static int Width = ResourceMgr.explodes[0].getWidth();
	private static int Height = ResourceMgr.explodes[0].getHeight();
	private int x, y;
	private boolean living = true;
	TankFrame tf = null;
	private int step = 0;
	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	public boolean getLive() {
		return living;
	}
	
	public void setLive(boolean live) {
		this.living = live;
	}
	
	// 画出炮弹
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if(step >= ResourceMgr.explodes.length){
			step=0;
		}
	}
}
