package com.szq.tank;

/**
 * @author: shizq
 * @Date: 2020年3月15日下午3:49:35
 * @Des:
 * @Version: 1.0
 */
public class TankMain {
	public static void main(String[] args) throws InterruptedException {
			
			TankFrame tankFrame = new TankFrame();
			
			//初始化敌方坦克，这里以后是要单独拿出来的
			for(int i=0; i<5; i++){
				tankFrame.tanks.add(new Tank(80+i*70 , 300 , Dir.DOWN,tankFrame));
			}
			while(true){
				Thread.sleep(20);
				tankFrame.repaint();
			}
		}
}
