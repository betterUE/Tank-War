package com.szq.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

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
	private static final int SPEED = 1;
	// 坦克是否移动
	private boolean moving = true;
	//坦克是否 live
	private boolean living = true;
	//Frame
	private TankFrame tf = null;
	//区分主战坦克 与 敌方坦克, 默认是 敌方坦克
	private Group group = Group.BAD;
	//随机数
	private Random random = new Random();

	public Tank(int x, int y, Dir dir , Group group,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
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
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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
		//敌人坦克随机发射炮弹
		if(this.group==Group.BAD && random.nextInt(100)>95){
			this.fire();
		}
		if(this.group==Group.BAD && random.nextInt(100)>98){
			randomDir();
		}
	}
	//随机换方向
	private void randomDir() {
		this.dir=Dir.values()[random.nextInt(4)];
	}

	/**
	 * 发射炮弹
	 */
	public void fire() {
		tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.group ,tf));
	}
	/**
	 * 坦克与炮弹碰撞后 ，坦克 die
	 */
	public void die() {
		this.living = false;
		// 坦克die 之后，随即爆炸
		tf.explodes.add(new Explode(this.x, this.y, tf));
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
