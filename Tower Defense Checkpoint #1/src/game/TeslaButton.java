/**
 * Menu Button describes behavior of the Salt Button
 * @author Bruce Crockett and Logan Luker
 * @version November 22, 2022
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TeslaButton extends GameObject implements Clickable
{
	Control control;
	State state;
	/**
	 * Default constructor
	 * @param control
	 * @param state
	 */
	public TeslaButton (Control control, State state)
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
		g.setColor(Color.CYAN);
		g.fillRoundRect(630, 300, 140, 140, 0, 0);
		g.drawImage(control.getImage("tesla.png"), 650, 320, null);
		g.setFont(new Font("Proxima Nova", 0, 13));
		g.setColor(Color.black);
		g.drawString("700 cash", 674, 430);
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
		Tesla thisTesla = new Tesla(this.state, this.control);
		if(mouseX > 630 && mouseX < 770
		   && mouseY > 300 && mouseY < 440 && state.getCash() >= thisTesla.getCost())
		{
			state.startFrame();
			state.addGameObject(thisTesla);
			state.finishFrame();
			control.view.repaint();
			state.setCash(state.getCash() - thisTesla.getCost());
			return true;
		}
		return false;
	}

}
