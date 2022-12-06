/**
 * Menu Button describes behavior of the Salt Button
 * @author Bruce Crockett and Logan Luker
 * @version November 22, 2022
 */
package game;

import java.awt.Color;
import java.awt.Graphics;

public class SaltButton extends GameObject implements Clickable
{
	Control control;
	State state;
	/**
	 * Default constructor
	 * @param control
	 * @param state
	 */
	public SaltButton (Control control, State state)
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
		
	}
	/**
	 * draw method
	 * describes how to draw the Salt Button
	 */
	@Override
	public void draw(Graphics g) 
	{
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
		Salt thisSalt = new Salt(this.state, this.control);
		if(mouseX > 630 && mouseX < 770
		   && mouseY > 80 && mouseY < 220 && state.getCash() >= thisSalt.getCost())
		{
//			if((state.getRound()/0.5) % 2 != 0)
//			{
//				state.setRound(state.getRound()+0.5);
//				control.roundControl.startRound(state.getRound());
//			}
			//SoundClipTest.LEVEL1.getFrameValue();
			state.startFrame();
			state.addGameObject(thisSalt);
			state.finishFrame();
			control.view.repaint();
			return true;
		}
		return false;
	}

}