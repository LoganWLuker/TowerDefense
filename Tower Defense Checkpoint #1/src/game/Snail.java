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
	Control control;
	/**
	 * Control constructor
	 * @param control
	 * 				  snail control
	 */
    public Snail (Control control) 
    {
    	percentage = 0;
        isVisible = true;
        isExpired = false;
        
        this.control = control;
    }
    /**
     * Update the snail by moving it a small percentage along the path
     */
	public void update (double elapsedTime) 
	{
		percentage += 0.001;
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