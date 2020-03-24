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
	public static BufferedImage goodTankL,goodTankR,goodTankU,goodTankD;
	public static BufferedImage glGoodTankL,glGoodTankU,glGoodTankR,glGoodTankD;
	public static BufferedImage badTankL,badTankR,badTankU,badTankD;
	public static BufferedImage glBadTankL,glBadTankR,glBadTankU,glBadTankD;
	public static BufferedImage bulletL,bulletR,bulletU,bulletD;
	public static BufferedImage[] explodes = new BufferedImage[16];
	
	static {
		try {
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			
			glGoodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
			glGoodTankL = ImageUtil.rotateImage(glGoodTankU, -90);
			glGoodTankR = ImageUtil.rotateImage(glGoodTankU, 90);
			glGoodTankD = ImageUtil.rotateImage(glGoodTankU, 180);
			
			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			
			glBadTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
			glBadTankL = ImageUtil.rotateImage(glBadTankU, -90);
			glBadTankR = ImageUtil.rotateImage(glBadTankU, 90);
			glBadTankD = ImageUtil.rotateImage(glBadTankU, 180);
		
			bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
			bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
			bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			//加载爆炸图片
			for(int i=0; i<16; i++){
				explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
