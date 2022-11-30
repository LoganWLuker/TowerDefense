/**
 * Salt Class describes the salt Tower
 * able to be placed
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.Graphics;

public class Salt extends GameObject implements Clickable
{
	boolean isMoving;
	int xPos, yPos;
	State state;
	Control control;
	/**
	 * Default constructor
	 * @param state
	 * @param control
	 */
	public Salt(State state, Control control)
	{
		xPos = control.getMouseX();
		yPos = control.getMouseY();
		this.isVisible = true;
		this.isExpired = false;
		this.isMoving = true;
		this.state = state;
		this.control = control;
	}
	/**
	 * update position to mouse
	 * if tower is being placed
	 */
	@Override
	public void update(double elapsedTime) 
	{
		if(isMoving)
		{
			xPos = control.getMouseX();
			yPos = control.getMouseY();
		}
		
	}
	/**
	 * Describe how to draw Salt tower
	 */
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(control.getImage("salt.png"), xPos-26, yPos-30, null);
	}
	/**
	 * Describe how clicking is handled with Salt tower
	 * @param mouseX
	 * @param mouseY
	 */
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
