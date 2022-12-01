/**
 * Krogdor Class describes the Krogdor enemy
 * moves along the path each update
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Krogdor extends GameObject
{
	private double percentage;
	private double spriteNum;
	private int damage;
	Control control;
	State state;
	double velocity;
	/**
	 * Control constructor
	 * @param control
	 * 				  Krogdor control
	 */
    public Krogdor (Control control, State state) 
    {
    	velocity = 0.1; //0.1
    	damage = 1;
    	percentage = 0;
    	spriteNum = 0;
        isVisible = true;
        isExpired = false;
        
        this.state = state;
        this.control = control;
    }
    /**
     * Update the Krogdor by moving it a small percentage along the path
     */
	public void update (double elapsedTime)
	{
		if (percentage < 1)
		{
			percentage += velocity * state.getElapsedTime();
			spriteNum += 8 * state.getElapsedTime();
			if(spriteNum >= 4)
				spriteNum = 0;
		} else
		{
			this.isExpired = true;
			
			int currentLives = control.state.getLives();
			state.setLives(currentLives - damage);
		}
	}
	public String toString ()
	{
		return "Krogdor";
	}
	/**
	 * Draw the Krogdor image based on its percentage
	 * along the path
	 */
	public void draw (Graphics g)
	{
		Point loc = control.getPath().convertToCoordinates(percentage);
        g.drawImage(control.getImage("krogdor"+(int)spriteNum+".png"), loc.x-16, loc.y-64, null);
	}
}