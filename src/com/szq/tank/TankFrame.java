package com.szq.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
	// 构造方法
	public TankFrame() {
		this.setSize(800, 900);
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

	//创建坦克对象
	Tank myTank = new Tank(20, 20, Dir.DOWN);
	//创建炮弹对象
	Bullet myBullet = new Bullet(20, 20, Dir.DOWN);

	@Override
	public void paint(Graphics g) {
		//画出坦克
		myTank.paint(g);
		//画出子弹
		myBullet.paint(g);
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
