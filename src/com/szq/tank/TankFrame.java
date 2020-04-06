package com.szq.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.szq.tank.facade.GameModel;

public class TankFrame extends Frame {
	static final int GAME_WIDTH=800,GAME_HEIGHT=700;
	
	//引入门面模式
	GameModel gm = new GameModel();

	public static int getGameWidth() {
		return GAME_WIDTH;
	}

	public static int getGameHeight() {
		return GAME_HEIGHT;
	}

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
	
	public void paint(Graphics g) {
		gm.paint(g);
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
				gm.getMyTank().fire(gm.getMyTank().group);
			default:
				break;
			}
			setMainTankMoveDir();
		}

		/**
		 * 主坦克的移动方向
		 */
		public void setMainTankMoveDir() {
			//Tank myTank = DefaultFactory.getInstance().createTank(x, y, dir, group, tf);
			// 如果没有按键，则坦克静止
			if (!bL && !bU && !bR && !bD) {
				gm.getMyTank().setMoving(false);
			} else {
				gm.getMyTank().setMoving(true);
				if (bL) {
					gm.getMyTank().setDir(Dir.LEFT);
				}
				if (bU) {
					gm.getMyTank().setDir(Dir.UP);
				}
				if (bR) {
					gm.getMyTank().setDir(Dir.RIGHT);
				}
				if (bD) {
					gm.getMyTank().setDir(Dir.DOWN);
				}
			}

		}
	}

}
