package com.szq.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author: SZQ
 * @Date: 2020年3月15日下午3:55:06
 * @Des: 炮弹类
 * @Version: 1.0
 */
public class Bullet {
	private static int SPEED = 7;
	private static int WIDTH = 25,HEIGHT = 25;
	private int x, y;
	private Dir dir;

	public Bullet(int x, int y, Dir dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	// 画出炮弹
	public void paint(Graphics g) {
		g.setColor(Color.red);
		//g.fillRect(x, y, 50, 50);
		g.fillOval(x, y, WIDTH, HEIGHT);
		
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
	}
}
