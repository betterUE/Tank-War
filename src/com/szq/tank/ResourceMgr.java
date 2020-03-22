package com.szq.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author: shizq
 * @Date: 2020年3月22日上午12:12:14
 * @Des: 加载图片资源
 * @Version: 1.0
 */
public class ResourceMgr {
	public static BufferedImage tankL,tankR,tankU,tankD;
	public static BufferedImage bulletL,bulletR,bulletU,bulletD;
	
	static {
		try {
			tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTankL.png"));
			tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTankR.png"));
			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTankU.png"));
			tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTankD.png"));
		
			bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
			bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
			bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
