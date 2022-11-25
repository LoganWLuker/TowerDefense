/**
<<<<<<< HEAD
 * Class to hold the parameters of MenuButton (specifically the Salt menu button)
 * @author Bruce Crockett & Logan Luker
=======
 * Menu Button describes behavior of the Salt Button
 * @author Bruce Crockett and Logan Luker
>>>>>>> c732def37beec2eb449342ce40600406f9dd2cc9
 * @version November 22, 2022
 */
package game;

import java.awt.Color;
import java.awt.Graphics;

public class MenuButton extends GameObject implements Clickable
{
	Control control;
	State state;
	/**
	 * Default constructor
	 * @param control
	 * @param state
	 */
	public MenuButton (Control control, State state)
	{
		this.control = control;
		this.state = state;
		
		isVisible = true;
		isExpired = false;
	}
	/**
	 * button does not update appearance
	 * nothing goes here
	 */
	@Override
	public void update(double elapsedTime) 
	{
		// TODO Auto-generated method stub
		
	}
	/**
	 * draw method
	 * describes how to draw the Salt Button
	 */
	@Override
	public void draw(Graphics g) 
	{
		// Draw the salt button
		
		g.setColor(Color.GRAY);
		g.fillRoundRect(630, 80, 140, 140, 0, 0);
		g.drawImage(control.getImage("salt.png"), 674, 120, null);	
	}
	/**
	 * Consume Click
	 * describes what the button does when clicked
	 * 
	 * @param mouseX
	 * @param mouseY
	 */
	@Override
	public boolean consumeClick(int mouseX, int mouseY) 
	{
		if(mouseX > 630 && mouseX < 770
		   && mouseY > 80 && mouseY < 220)
		{
			control.state.startFrame();
			control.state.addGameObject(new Salt(this.state,this.control));
			control.state.finishFrame();
			control.view.repaint();
			return true;
		}
		return false;
	}

}
