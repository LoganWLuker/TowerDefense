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
	Control control;
	/**
	 * Control constructor
	 * @param control
	 * 				  Krogdor control
	 */
    public Krogdor (Control control) 
    {
    	percentage = 0;
    	spriteNum = 0;
        isVisible = true;
        isExpired = false;
        
        this.control = control;
    }
    /**
     * Update the Krogdor by moving it a small percentage along the path
     */
	public void update (double elapsedTime)
	{
		if(percentage > 1)
			percentage = 0;
		percentage += 0.01;
		
		spriteNum+=0.4;
		if(spriteNum >= 4)
			spriteNum = 0;
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