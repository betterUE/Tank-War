package com.szq.tank.factory;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.szq.tank.Dir;
import com.szq.tank.Group;
import com.szq.tank.ResourceMgr;
import com.szq.tank.TankFrame;
import com.szq.tank.facade.GameModel;

public class RectBullet extends BaseBullet {

	private static int SPEED = 7;
	private static int BulletWidth = ResourceMgr.bulletU.getWidth();
	private static int BulletHeight = ResourceMgr.bulletU.getHeight();
	private int x, y;
	private Dir dir;
	private boolean living = true;
	private Group group = Group.BAD;
	//碰撞检测优化
	Rectangle rect = new Rectangle();
	
	GameModel gm = null;
	public RectBullet(int x, int y, Dir dir,Group group, GameModel gm) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.gm = gm;
		rect.x = this.x;
		rect.y = this.y;
		rect.width = this.BulletWidth;
		rect.height = this.BulletHeight;
		//重构代码，当炮弹被new出来的时候，就被加入到 画布中 bullets 的集合中去
		gm.getBullets().add(this);
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
	@Override
	public void paint(Graphics g) {
		if(!living){
			gm.getBullets().remove(this);
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
		if(x<0 || y<0 || x>TankFrame.getGameWidth() || y>TankFrame.getGameHeight()){
			living = false;
		}
		//更新rect 的位置
		this.rect.x = this.x;
		this.rect.y = this.y;
	}
	//炮弹碰撞坦克
	@Override
	public void collideWith(BaseTank tank) {
		//判断炮弹是不是主战自己的 ，还是 敌方的 
		if(this.group == tank.getGroup()){
			return;
		}
		if(this.rect.intersects(tank.getRect())){
			tank.die();
			this.die();
		}
	}
	//炮弹碰撞坦克后 die
	private void die() {
		this.living = false;
	}

}
