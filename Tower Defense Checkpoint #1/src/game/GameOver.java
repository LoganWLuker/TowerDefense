package game;

import java.awt.Graphics;

public class GameOver extends GameObject 
{
	Control control;
	public GameOver (Control control)
	{
		isVisible = true;
        isExpired = false;
        this.control = control;
	}
	@Override
	public void update(double elapsedTime) 
	{
		
	}

	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(control.getImage("game_over.png"), 0, 0, null);
	}

}
