package game;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Enemy extends GameObject 
{

	@Override
	public void update(double elapsedTime) {}

	@Override
	public void draw(Graphics g) {}
	
	public abstract Point getPosition();
	public abstract double getPercentage();
	public abstract double getVelocity();

}