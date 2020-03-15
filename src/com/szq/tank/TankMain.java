package com.szq.tank;

public class TankMain {
	public static void main(String[] args) throws InterruptedException {
			
			TankFrame tank = new TankFrame();
			while(true){
				Thread.sleep(50);
				tank.repaint();
			}
		}
}
