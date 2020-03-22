package com.szq.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author: shizq
 * @Date: 2020年3月15日下午3:55:06
 * @Des: 炮弹类
 * @Version: 1.0
 */
public class Bullet {
	private static int SPEED = 7;
	private static int BulletWidth = ResourceMgr.bulletU.getWidth();
	private static int BulletHeight = ResourceMgr.bulletU.getHeight();
	private int x, y;
	private Dir dir;
	private boolean live = true;

	TankFrame tf = null;
	public Bullet(int x, int y, Dir dir, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
	public boolean getLive() {
		return live;
	}
	
	public void setLive(boolean live) {
		this.live = live;
	}

	// 画出炮弹
	public void paint(Graphics g) {
		if(!live){
			tf.bullets.remove(this);
		}
		
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x-10, y+28, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x+60, y+28, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x+24, y-15, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x+24, y+60, null);
			break;
		}	
		move();
	}
	
	public void move() {
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		default:
			break;
		}
		//添加判断炮弹是否飞出边界，
		if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT){
			live = false;
		}
	}
}
