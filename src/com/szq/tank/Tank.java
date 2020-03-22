package com.szq.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author shizq 坦克类 将tank类抽象出来，添加相应的属性和方法
 *
 */
public class Tank {

	private int x, y;
	private Dir dir;
	private static int TankWidth = ResourceMgr.tankU.getWidth();
	private static int TankHeight = ResourceMgr.tankU.getHeight();
	// 坦克的速度
	private static final int SPEED = 5;
	// 坦克是否移动
	private boolean moving = false;
	//坦克是否 live
	private boolean living = true;
	//Frame
	private TankFrame tf = null;

	public Tank(int x, int y, Dir dir , TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public static int getTankWidth() {
		return TankWidth;
	}
	
	public static void setTankWidth(int tankWidth) {
		TankWidth = tankWidth;
	}
	
	public static int getTankHeight() {
		return TankHeight;
	}
	
	public static void setTankHeight(int tankHeight) {
		TankHeight = tankHeight;
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
		if(!living){
			tf.tanks.remove(this);
		}
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
		tf.bullets.add(new Bullet(this.x, this.y, this.dir,tf));
	}
	/**
	 * 坦克与炮弹碰撞后 ，坦克 die
	 */
	public void die() {
		this.living = false;
	}
	// 主战坦克与敌方坦克碰撞
	/*public void tankCollideWith(Tank myTank) {
		Rectangle tankRect = new Rectangle(this.x,this.y,this.getTankWidth(),this.getTankHeight());
		Rectangle myTankRect = new Rectangle(myTank.getX(),myTank.getY(),myTank.getTankWidth(),myTank.getTankHeight());
		if(tankRect.intersects(myTankRect)){
			this.die();
			myTank.die();
		}
	}*/

}
