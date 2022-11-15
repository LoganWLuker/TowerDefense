package game;

import java.awt.Graphics;

public class Snail extends GameObject
{
	private double percentage;
    private int test;  // Remove this later
	public Control control;

    public Snail (Control control) 
    {
    	percentage = 0;
        test = 100;  // Remove this later
        isVisible = true;
        isExpired = false;
        
        this.control = control;
    }
	@Override
	public void update (double elapsedTime) 
	{
		percentage += 0.001;
        test++;
	}

	@Override
	public void draw (Graphics g) 
	{
		g.drawImage(control.getImage("snail.png"), test, test, null);
		
	}



}
