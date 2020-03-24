package com.szq.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {
	static final int GAME_WIDTH=800,GAME_HEIGHT=700;
	//创建坦克对象 属性
	Tank myTank = new Tank(30, 30, Dir.DOWN, Group.GOOD,this);
	//创建炮弹对象 属性
	//Bullet myBullet = new Bullet(20, 20, Dir.DOWN);
	// 打出多个炮弹
	List<Bullet> bullets = new ArrayList<Bullet>();
	//创建敌方多个坦克
	List<Tank> tanks = new ArrayList<>();
	//爆炸
	Explode explode = new Explode(300, 300, this);
	//可能多个位置爆炸
	List<Explode> explodes = new ArrayList<>();
	
	// 构造方法
	public TankFrame() {
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setResizable(true);
		this.setTitle("̹坦克大战");
		this.setVisible(true);

		this.addKeyListener(new MyKeyListener());

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// 使用双缓冲解决闪烁问题（看不懂不用管）
	Image offScreenImage = null;
	@Override
	public void update(Graphics g){
		if(offScreenImage == null){
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics goffScreen = offScreenImage.getGraphics();
		Color c = goffScreen.getColor();
		goffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		goffScreen.setColor(c);
		paint(goffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		//输出子弹数目
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("炮弹的数量："+bullets.size(), 30, 50);
		g.drawString("敌方坦克的数量："+tanks.size(), 140, 50);
		g.setColor(c);
		
		//画出坦克
		myTank.paint(g);
		//画出子弹
		//myBullet.paint(g);
		//发射一堆子弹，这种方法会出现迭代器中删除异常，避免方法如下：
		/*for(Bullet b : bullets){   
			b.paint(g);
		}*/
		for(int i=0;i<bullets.size();i++){
			bullets.get(i).paint(g);
		}
		
		/*第二种方法
		 * for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();){
			Bullet b = it.next();
			if(!b.getLive()){
				it.remove();
			}
		}*/
		
		//画坦克
		for(int j=0; j<tanks.size(); j++){
			tanks.get(j).paint(g);
		}
		//嵌套循环 判断炮弹碰撞 坦克
		for(int i=0; i<bullets.size(); i++){
			for(int j=0; j<tanks.size(); j++){
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
		/*// 主战坦克与敌方坦克碰撞
		for(int j=0; j<tanks.size(); j++){
			tanks.get(j).tankCollideWith(myTank);
		}*/
		
		//画出一个爆炸
		//explode.paint(g);
		for(int m=0; m<explodes.size(); m++){
			explodes.get(m).paint(g);
		}
	}

	//
	class MyKeyListener extends KeyAdapter {

		private boolean bL = false;
		private boolean bR = false;
		private boolean bU = false;
		private boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			default:
				break;
			}
			setMainTankMoveDir();
		}

		/**
		 * * 松开键 ,要将 四个方向定义为 false
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			//按下ctrl键时，发射炮弹
			case KeyEvent.VK_CONTROL:
				myTank.fire();
			default:
				break;
			}
			setMainTankMoveDir();
		}

		/**
		 * 主坦克的移动方向
		 */
		public void setMainTankMoveDir() {
			// 如果没有按键，则坦克静止
			if (!bL && !bU && !bR && !bD) {
				myTank.setMoving(false);
			} else {
				myTank.setMoving(true);
				if (bL) {
					myTank.setDir(Dir.LEFT);
				}
				if (bU) {
					myTank.setDir(Dir.UP);
				}
				if (bR) {
					myTank.setDir(Dir.RIGHT);
				}
				if (bD) {
					myTank.setDir(Dir.DOWN);
				}
			}

		}
	}

}
