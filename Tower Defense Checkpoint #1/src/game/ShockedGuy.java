/**
 * Snail Class describes the snail enemy
 * moves along the path each update
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class ShockedGuy extends GameObject
{
	private double percentage;
	private int damage;
	Control control;
	State state;
	double velocity;
	/**
	 * Control constructor
	 * @param control
	 * 				  snail control
	 */
    public ShockedGuy (Control control, State state)
    {
    	damage = 1;
    	percentage = 0;
        isVisible = true;
        isExpired = false;
        
        this.state = state;
        this.control = control;
    	velocity = 0.05 + .02 * state.getRound(); //0.05
    }
    /**
     * Update the snail by moving it a small percentage along the path
     */
	public void update (double elapsedTime) 
	{
		if (percentage <= 1)
		{
			percentage += velocity * state.getElapsedTime();
		} else 
		{
			this.isExpired = true;
			
			int currentLives = control.state.getLives();
			control.state.setLives(currentLives - damage);
		}
	}
	public String toString ()
	{
		return "ShockedGuy";
	}
	/**
	 * Draw the snail image based on its percentage
	 * along the path
	 */
	public void draw (Graphics g) 
	{
		Point loc = control.getPath().convertToCoordinates(percentage);
        g.drawImage(control.getImage("shockedGuySmall.png"), loc.x-56, loc.y-50, null);
	}
}