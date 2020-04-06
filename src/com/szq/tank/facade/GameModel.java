package com.szq.tank.facade;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.szq.tank.Dir;
import com.szq.tank.Explode;
import com.szq.tank.Group;
import com.szq.tank.ProperTyMgr;
import com.szq.tank.Tank;
import com.szq.tank.factory.BaseBullet;
import com.szq.tank.factory.BaseExplode;
import com.szq.tank.factory.BaseTank;

/**
 * @author: shizq
 * @Date: 2020年4月7日上午12:09:24
 * @Des: 使用门面模式改造TankFrame
 * @Version: 1.0
 */
public class GameModel {
	
	// 创建坦克对象 属性
	Tank myTank = new Tank(30, 30, Dir.DOWN, Group.GOOD, this);
	// 创建炮弹对象 属性
	// Bullet myBullet = new Bullet(20, 20, Dir.DOWN);
	// 打出多个炮弹
	List<BaseBullet> bullets = new ArrayList<>();
	// 创建敌方多个坦克
	List<BaseTank> tanks = new ArrayList<>();
	// 爆炸
	Explode explode = new Explode(300, 300, this);
	// 可能多个位置爆炸
	List<BaseExplode> explodes = new ArrayList<>();
	
	public GameModel() {
		int initTankCount = ProperTyMgr.getInt("initTankCount");
		
		//初始化敌方坦克，这里以后是要单独拿出来的
		for(int i=0; i<initTankCount; i++){
			this.getTanks().add(new Tank(80+i*70 , 300 , Dir.DOWN,Group.BAD,this));
		}
	}

	public Tank getMyTank() {
		return myTank;
	}

	public void setMyTank(Tank myTank) {
		this.myTank = myTank;
	}

	public Explode getExplode() {
		return explode;
	}

	public void setExplode(Explode explode) {
		this.explode = explode;
	}

	

	public List<BaseBullet> getBullets() {
		return bullets;
	}

	public void setBullets(List<BaseBullet> bullets) {
		this.bullets = bullets;
	}

	public List<BaseTank> getTanks() {
		return tanks;
	}

	public void setTanks(List<BaseTank> tanks) {
		this.tanks = tanks;
	}

	public List<BaseExplode> getExplodes() {
		return explodes;
	}

	public void setExplodes(List<BaseExplode> explodes) {
		this.explodes = explodes;
	}
	
	public void paint(Graphics g) {
		//输出子弹数目
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("炮弹的数量："+this.getBullets().size(), 30, 50);
		g.drawString("敌方坦克的数量："+this.getTanks().size(), 140, 50);
		g.setColor(c);
		
		//画出坦克
		this.getMyTank().paint(g);
		//画出子弹
		//myBullet.paint(g);
		//发射一堆子弹，这种方法会出现迭代器中删除异常，避免方法如下：
		/*for(Bullet b : bullets){   
			b.paint(g);
		}*/
		for(int i=0;i<this.getBullets().size();i++){
			this.getBullets().get(i).paint(g);
		}
		
		/*第二种方法
		 * for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();){
			Bullet b = it.next();
			if(!b.getLive()){
				it.remove();
			}
		}*/
		
		//画坦克
		for(int j=0; j<this.getTanks().size(); j++){
			this.getTanks().get(j).paint(g);
		}
		//嵌套循环 判断炮弹碰撞 坦克
		for(int i=0; i<this.getBullets().size(); i++){
			for(int j=0; j<this.getTanks().size(); j++){
				this.getBullets().get(i).collideWith(this.getTanks().get(j));
			}
		}
		/*// 主战坦克与敌方坦克碰撞
		for(int j=0; j<tanks.size(); j++){
			tanks.get(j).tankCollideWith(myTank);
		}*/
		
		//画出一个爆炸
		//explode.paint(g);
		for(int m=0; m<this.getExplodes().size(); m++){
			this.getExplodes().get(m).paint(g);
		}
	}
	
	
}
