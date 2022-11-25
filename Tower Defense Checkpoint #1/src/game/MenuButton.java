/**
 * Class to hold the parameters of MenuButton (specifically the Salt menu button)
 * @author Bruce Crockett & Logan Luker
 * @version November 22, 2022
 */
package game;

import java.awt.Color;
import java.awt.Graphics;

public class MenuButton extends GameObject
{
	Control control;
	State state;
	
	public MenuButton (Control control, State state)
	{
		this.control = control;
		this.state = state;
		
		isVisible = true;
		isExpired = false;
	}

	@Override
	public void update(double elapsedTime) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) 
	{
		// Draw the salt button
		
		g.setColor(Color.GRAY);
		g.fillRoundRect(630, 80, 140, 140, 0, 0);
		g.drawImage(control.getImage("salt.png"), 674, 120, null);	
	}

}
