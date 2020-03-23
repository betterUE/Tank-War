package com.szq.tank;

import java.awt.Graphics;

/**
 * @author: shizq
 * @Date: 2020年3月23日下午11:02:42
 * @Des:
 * @Version: 1.0
 */
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
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if(step >= ResourceMgr.explodes.length){
			tf.explodes.remove(this);
		}
	}
}
