package game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Salt extends GameObject implements MouseMotionListener
{
	boolean isMoving;
	int xPos, yPos;
	int mouseX,mouseY;
	State state;
	Control control;
	
	public Salt(State state, Control control)
	{
//		this.mouseX = 0;
//		this.mouseY = 0;
		this.isMoving = false;
		this.xPos = 300;
		this.yPos = 300;
		this.state = state;
		this.control = control;
	}
	@Override
	public void update(double elapsedTime) 
	{
		if(isMoving)
		{
			xPos = mouseX;
			yPos = mouseY;
		}
	}

	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(control.getImage("salt.png"), xPos, yPos, null);
	}
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}
	@Override
	public void mouseDragged(MouseEvent e) {}

}
