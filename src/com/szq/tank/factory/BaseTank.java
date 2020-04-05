package com.szq.tank.factory;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.szq.tank.Group;

public abstract class BaseTank {
	public abstract void paint(Graphics g);

	public abstract Group getGroup();

	public abstract Rectangle getRect();

	public abstract void die();

}
