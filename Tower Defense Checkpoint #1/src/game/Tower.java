package game;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Tower extends GameObject 
{
	public int cost;
	public int xPos,yPos;
	
	@Override
	public void update(double elapsedTime) {}

	@Override
	public void draw(Graphics g) {}
	
	public Point getPosition() 
	{
		Point loc = new Point(xPos,yPos);
		return loc;
	}
	
	public int getCost() { return this.cost;}

}