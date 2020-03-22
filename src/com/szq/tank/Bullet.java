package com.szq.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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
	private boolean living = true;
	private Group group = Group.BAD;
	TankFrame tf = null;
	public Bullet(int x, int y, Dir dir,Group group, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
	}
	public boolean getLive() {
		return living;
	}
	
	public void setLive(boolean live) {
		this.living = live;
	}
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}

	// 画出炮弹
	public void paint(Graphics g) {
		if(!living){
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
			living = false;
		}
	}
	//炮弹碰撞坦克
	public void collideWith(Tank tank) {
		//判断炮弹是不是主战自己的 ，还是 敌方的 
		if(this.group == tank.getGroup()){
			return;
		}
		
		//TODO: 每次检测都要产生Rectangle对象， 导致垃圾频繁产生，可以用一个rect 记录炮弹的位置
		Rectangle bulletRect = new Rectangle(this.x,this.y,this.BulletWidth,this.BulletHeight);
		Rectangle tankRect = new Rectangle(tank.getX(),tank.getY(),tank.getTankWidth(),tank.getTankHeight());
		if(bulletRect.intersects(tankRect)){
			tank.die();
			this.die();
		}
	}
	//炮弹碰撞坦克后 die
	private void die() {
		this.living = false;
	}
}
