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
	State state;
	/**
	 * Control constructor
	 * @param control
	 * 				  Krogdor control
	 */
    public Krogdor (State state, Control control) 
    {
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
			percentage += 0.004;
			spriteNum+=0.4;
			if(spriteNum >= 4)
				spriteNum = 0;
		} else
		{
			this.isExpired = true;
			
			int currentLives = control.state.getLives();
			control.state.setLives(currentLives - 1);
			state.addGameObject(new Krogdor(this.state,this.control));
		}
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