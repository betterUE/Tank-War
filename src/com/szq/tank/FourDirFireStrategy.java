package com.szq.tank;

/**
 * @author: shizq
 * @Date: 2020年3月30日上午12:19:13
 * @Des:  策略模式， 朝四个方向发射炮弹
 * @Version: 1.0
 */
public class FourDirFireStrategy implements FireStrategy {
	
	private FourDirFireStrategy() {
	}
	
	private static class FourDirFireHolder{
		private static final FourDirFireStrategy FourDirFireInstance = new FourDirFireStrategy();
	}
	
	public static FourDirFireStrategy getInstance(){
		return FourDirFireHolder.FourDirFireInstance;
	}

	/* 
	 *朝四个方向发射炮弹
	 */
	@Override
	public void fire(Tank t) {
		Dir[] dirs = Dir.values();
		for(int i=0; i<dirs.length; i++){
			new Bullet(t.x, t.y, dirs[i], t.group ,t.tf);	 
		}
		new Bullet(t.x, t.y, t.dir, t.group ,t.tf);	 
	}

}
