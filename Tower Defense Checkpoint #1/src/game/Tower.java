package game;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Tower extends GameObject 
{
	public int cost;
	
	@Override
	public void update(double elapsedTime) {}

	@Override
	public void draw(Graphics g) {}
	
	public abstract Point getPosition();
	
	public int getCost() { return this.cost;}

}