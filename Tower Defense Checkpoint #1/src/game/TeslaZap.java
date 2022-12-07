package game;

import java.awt.Graphics;

public class TeslaZap extends GameObject 
{
	int xPos,yPos;
	double xSlope,ySlope;
	double velocity;
	Control control;
	State state;
	GameObject source;
	Enemy target;
	public TeslaZap (Control control, State state, Tower source, Enemy target)
	{
		this.isVisible = true;
		this.isExpired = false;
		this.source = source;
		this.control = control;
		this.state = state;
		this.target = target;
		xPos = source.getPosition().x;
		yPos = source.getPosition().y;
		xSlope = target.getPosition().x - xPos;
		ySlope = target.getPosition().y - yPos;
		velocity = 30;
	}
	@Override
	public void update(double elapsedTime) 
	{
		xPos += (velocity*elapsedTime)*xSlope;
		yPos += (velocity*elapsedTime)*ySlope;
	}

	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(control.getImage("lightning.png"), xPos-100, yPos-180, null);
	}

}
