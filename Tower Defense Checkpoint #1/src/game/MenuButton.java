package game;

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
		// TODO Auto-generated method stub
		
	}

}
