package game;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Enemy extends GameObject 
{
	public int reward;
	@Override
	public void update(double elapsedTime) {}

	@Override
	public void draw(Graphics g) {}
	
	public abstract Point getPosition();
	public abstract double getPercentage();
	public abstract double getVelocity();
	public int getReward() { return this.reward; }

}