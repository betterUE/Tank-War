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

	int x, y;
	Dir dir;
	private static int TankWidth = ResourceMgr.goodTankL.getWidth();
	private static int TankHeight = ResourceMgr.goodTankL.getHeight();
	// 坦克的速度
	private static final int SPEED = 1;
	// 坦克是否移动
	private boolean moving = true;
	//坦克是否 live
	private boolean living = true;
	//Frame
	TankFrame tf = null;
	//区分主战坦克 与 敌方坦克, 默认是 敌方坦克
	Group group = Group.BAD;
	//随机数
	private Random random = new Random();
	//计数器，用于实现坦克灯闪烁的效果
	int count = 0;
	//碰撞检测优化
	Rectangle rect = new Rectangle();

	public Tank(int x, int y, Dir dir , Group group,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		rect.x = this.x;
		rect.y = this.y;
		rect.width = this.TankWidth;
		rect.height = this.TankHeight;
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
		if(count>=0 && count<50){
			switch (dir) {
			//使用
			case LEFT:
				g.drawImage(this.group==Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group==Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
				break;
			case UP:
				g.drawImage(this.group==Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group==Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
				break;
			}
		}else{
			switch (dir) {
			//使用
			case LEFT:
				g.drawImage(this.group==Group.GOOD ? ResourceMgr.glGoodTankL : ResourceMgr.glBadTankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group==Group.GOOD ? ResourceMgr.glGoodTankR : ResourceMgr.glBadTankR, x, y, null);
				break;
			case UP:
				g.drawImage(this.group==Group.GOOD ? ResourceMgr.glGoodTankU : ResourceMgr.glBadTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group==Group.GOOD ? ResourceMgr.glGoodTankD : ResourceMgr.glBadTankD, x, y, null);
				break;
			}
		}
		
					
		move();
		count++;
		if(count>=100){
			count=0;
		}
	}

	/**
	 * 给方向的时候让坦克移动
	 */
	public void move() {
		if (!living) {
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
			this.fire(this.group);
		}
		if(this.group==Group.BAD && random.nextInt(100)>98){
			randomDir();
		}
		
		boundCheck();
		
		//更新rect 的位置
		this.rect.x = this.getX();
		this.rect.y = this.getY();
	}

	private void boundCheck() {
		if(this.getX()<2){
			x = 2;
		}
		if(this.getY()<25){
			y = 25;
		}
		if(x>TankFrame.GAME_WIDTH-60){
			x = TankFrame.GAME_WIDTH-60;
		}
		if(this.getY()>TankFrame.GAME_HEIGHT-60){
			y = TankFrame.GAME_HEIGHT-60;
		}
	}

	//随机换方向
	private void randomDir() {
		this.dir=Dir.values()[random.nextInt(4)];
	}

	/**
	 * 发射炮弹
	 */
	/*public void fire() {
		tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.group ,tf));
	}*/
	
	/** 使用单利模式 和 策略 模式  控制 敌方坦克 和 主战坦克的 发射炮弹的方式
	 * @param group
	 */
	public void fire(Group group) {
		FireStrategy fireStrategy = FourDirFireStrategy.getInstance(); ;
		if(group==Group.BAD){
			fireStrategy = DefaultFireStrategy.getInstance();
		}
		fireStrategy.fire(this);
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
