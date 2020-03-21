package com.szq.tank;

/**
 * @author: shizq
 * @Date: 2020年3月15日下午3:49:35
 * @Des:
 * @Version: 1.0
 */
public class TankMain {
	public static void main(String[] args) throws InterruptedException {
			
			TankFrame tank = new TankFrame();
			while(true){
				Thread.sleep(20);
				tank.repaint();
			}
		}
}
