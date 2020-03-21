package com.szq.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author shizq 坦克类 将tank类抽象出来，添加相应的属性和方法
 *
 */
public class Tank {
	private int x, y;
	private Dir dir;
	private static final int TankWidth=50,TankHeigh=50;
	// 坦克的速度
	private static final int SPEED = 5;
	// 坦克是否移动
	private boolean moving = false;
	//Frame
	private TankFrame tf = null;

	public Tank(int x, int y, Dir dir , TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}

	public boolean getMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	// 画出坦克
	public void paint(Graphics g) {
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		}			
		move();
	}

	/**
	 * 给方向的时候让坦克移动
	 */
	public void move() {
		if (!moving) {
			return;
		}
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
	}

	/**
	 * 发射炮弹
	 */
	public void fire() {
		//tf.myBullet = new Bullet(this.x+12, this.y+12, this.dir);  
		tf.bullets.add(new Bullet(this.x, this.y, this.dir,tf));
	}

}
