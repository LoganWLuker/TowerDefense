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

public class Snail extends GameObject
{
	private double percentage;
	private int damage;
	Control control;
	State state;
	/**
	 * Control constructor
	 * @param control
	 * 				  snail control
	 */
    public Snail (State state, Control control) 
    {
    	damage = 10;
    	percentage = 0;
        isVisible = true;
        isExpired = false;
        
        this.state = state;
        this.control = control;
    }
    /**
     * Update the snail by moving it a small percentage along the path
     */
	public void update (double elapsedTime) 
	{
		if (percentage <= 1)
		{
			percentage += 0.05 * state.getElapsedTime();
		} else 
		{
			this.isExpired = true;
			
			int currentLives = control.state.getLives();
			control.state.setLives(currentLives - damage);
			//state.addGameObject(new Snail(this.state,this.control));
		}
	}
	/**
	 * Draw the snail image based on its percentage
	 * along the path
	 */
	public void draw (Graphics g) 
	{
		Point loc = control.getPath().convertToCoordinates(percentage);
        g.drawImage(control.getImage("snail.png"), loc.x-20, loc.y-20, null);
	}
}