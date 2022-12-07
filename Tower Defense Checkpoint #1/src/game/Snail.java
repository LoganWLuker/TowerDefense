package game;

import java.awt.Graphics;
import java.awt.Point;
/**
 * Snail Class describes the snail enemy
 * moves along the path each update
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
public class Snail extends Enemy
{
	private double percentage;
	private int damage;
	Control control;
	State state;
	double velocity;
	/**
	 * Control constructor
	 * @param control
	 * 				  snail control
	 */
    public Snail (Control control, State state) 
    {
    	super();
    	velocity = 0.05; //0.05
    	damage = 1;
    	percentage = 0;
        isVisible = true;
        isExpired = false;
        
        this.state = state;
        this.control = control;
    }
    /**
     * Update the snail by moving it a small percentage along the path
     */
	public void update (double elapsedTime) 
	{
		if (percentage <= 1)
		{
			percentage += velocity * state.getElapsedTime();
		} else 
		{
			this.isExpired = true;
			
			int currentLives = control.state.getLives();
			control.state.setLives(currentLives - damage);
		}
	}
	public String toString ()
	{
		return "Snail";
	}
	/**
	 * Draw the snail image based on its percentage
	 * along the path
	 */
	public void draw (Graphics g) 
	{
		Point loc = control.getPath().convertToCoordinates(percentage);
        g.drawImage(control.getImage("snail.png"), loc.x-20, loc.y-20, null);
	}
	@Override
	public Point getPosition() 
	{
		Point loc = control.getPath().convertToCoordinates(percentage);
		return loc;
	}
	@Override
	public double getPercentage() 
	{
		return this.percentage;
	}
	@Override
	public double getVelocity()
	{
		return this.velocity;
	}
}