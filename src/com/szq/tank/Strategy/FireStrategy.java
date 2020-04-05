package com.szq.tank.Strategy;

import com.szq.tank.Tank;

/**
 * @author: shizq
 * @Date: 2020年3月29日下午9:59:10
 * @Des:  坦克发射炮弹的策略模式
 * @Version: 1.0
 */
public interface FireStrategy {
	void fire(Tank t);
}
