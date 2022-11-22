package game;

import java.awt.Graphics;

public class Salt extends GameObject implements Clickable
{
	boolean isMoving;
	int xPos, yPos;
	State state;
	Control control;
	
	public Salt(State state, Control control)
	{
		this.isVisible = true;
		this.isExpired = false;
		this.isMoving = true;
		this.state = state;
		this.control = control;
	}
	@Override
	public void update(double elapsedTime) 
	{
		if(isMoving)
		{
			xPos = control.getMouseX();
			yPos = control.getMouseY();
		}
		
	}

	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(control.getImage("salt.png"), xPos-26, yPos-30, null);
	}
	@Override
	public boolean consumeClick(int mouseX, int mouseY) 
	{
		if(isMoving)
		{
			isMoving = false;
			if(mouseX < 0 || mouseX > 600 || mouseY < 0 || mouseY > 600)
			{
				this.isExpired = true;
			}
			return true;
		}
		return false;
	}

}
