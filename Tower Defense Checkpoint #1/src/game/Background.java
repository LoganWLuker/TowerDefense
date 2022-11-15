/**
 * Background class describes
 * how to draw the background
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.Graphics;

public class Background extends GameObject
{
	Control control;
	
	public Background (Control control)
	{
		isVisible = true;
        isExpired = false;
        this.control = control;
	}

	@Override
	public void update (double elapsedTime) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw (Graphics g) 
	{
        g.drawImage(control.getImage("path_2.jpg"), 0, 0, null);

	}

}
