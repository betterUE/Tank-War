package com.szq.tank;

public class TankMain {
	public static void main(String[] args) throws InterruptedException {
		/*	//System.out.println("hello world");
			Frame f = new Frame();
			f.setSize(800, 600);
			f.setResizable(true); //可控制大小
			f.setTitle("tank war");
			f.setVisible(true); //设置显示
			
			f.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					//super.windowClosing(e);
					System.exit(0);
				}
				
			});*/
			
			TankFrame tank = new TankFrame();
			while(true){
				Thread.sleep(50);
				tank.repaint();
			}
		}
}
