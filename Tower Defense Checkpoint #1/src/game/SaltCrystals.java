package game;

import java.awt.Graphics;

public class SaltCrystals extends GameObject 
{
	int xPos,yPos;
	double xSlope,ySlope;
	double velocity;
	Control control;
	State state;
	GameObject source;
	Enemy target;
	public SaltCrystals (Control control, State state, Tower source, Enemy target)
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
		velocity = 10;
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
		g.drawImage(control.getImage("salt_crystals.png"), xPos-29, yPos-29, null);
	}

}
