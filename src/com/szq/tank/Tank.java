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
	// 坦克的速度
	private static final int SPEED = 5;
	// 坦克是否移动
	private boolean moving = false;

	public Tank(int x, int y, Dir dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
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
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 50, 50);
		g.setColor(c);
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

}
