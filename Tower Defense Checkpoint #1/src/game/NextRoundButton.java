package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class NextRoundButton extends GameObject implements Clickable 
{
	Control control;
	State state;
	/**
	 * Default constructor
	 * @param control
	 * @param state
	 */
	public NextRoundButton (Control control, State state)
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
	 * describes how to draw the Next Round Button
	 */
	@Override
	public void draw(Graphics g) 
	{
		if((state.getRound()/0.5) % 2 != 0 && !state.enemiesAlive())
			g.drawImage(control.getImage("NextRound.png"), 649, 475, null);
		else
			g.drawImage(control.getImage("RoundInProgress.png"), 649, 475, null);
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
		Point mousePos = new Point(mouseX,mouseY);
		Salt thisSalt = new Salt(this.control, this.state);
		if(mousePos.distance(699, 525) <= 50 && !state.gameOver && !state.enemiesAlive())
		{
			if((state.getRound()/0.5) % 2 != 0) //in between rounds
			{
				state.setRound(state.getRound()+0.5);
				control.roundControl.startRound(state.getRound());
			}
			return true;
		}
		return false;
	}

}
