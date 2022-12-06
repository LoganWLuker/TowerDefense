/**
 * Salt Class describes the salt Tower
 * able to be placed
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Salt extends Tower implements Clickable
{
	boolean isMoving;
	int xPos, yPos;
	int towerRadius;
	int fireRate;
	State state;
	Control control;
	int cost;
	/**
	 * Default constructor
	 * @param state
	 * @param control
	 */
	public Salt(State state, Control control)
	{
		this.cost = 300;
		xPos = control.getMouseX();
		yPos = control.getMouseY();
		towerRadius = 100;
		fireRate = 30; //not a true rate; smaller is faster, 1 is fastest
		this.isVisible = true;
		this.isExpired = false;
		this.isMoving = true;
		this.state = state;
		this.control = control;
	}
	/**
	 * update position to mouse
	 * if tower is being placed
	 */
	@Override
	public void update(double elapsedTime)
	{
		if(isMoving)
		{
			xPos = control.getMouseX();
			yPos = control.getMouseY();
		}else
		{
			Enemy nearestEnemy = state.findNearestFirstEnemy(new Point(xPos,yPos), towerRadius);
			if(nearestEnemy != null && state.getCurrentFrame() % fireRate == 0)
			{
				state.addGameObject(new SaltCrystals(control,state,this,nearestEnemy));
				nearestEnemy.expire();
				//System.out.println("There's an enemy near");
			}
		}
	}
	/**
	 * Describe how to draw Salt tower
	 */
	@Override
	public void draw(Graphics g) 
	{
		if(isMoving)
		{
			g.setColor(new Color(0,0,0,100));
			g.fillOval(xPos - towerRadius, yPos - towerRadius, 2*towerRadius,2*towerRadius);
		}
		g.drawImage(control.getImage("salt.png"), xPos-26, yPos-30, null);
	}
	@Override
	public Point getPosition() 
	{
		Point loc = new Point(xPos,yPos);
		return loc;
	}
	/**
	 * Describe how clicking is handled with Salt tower
	 * @param mouseX
	 * @param mouseY
	 */
	@Override
	public boolean consumeClick(int mouseX, int mouseY) 
	{
		if(isMoving)
		{
			isMoving = false;
			if(mouseX < 0 || mouseX > 600 || mouseY < 0 || mouseY > 600)
			{
				this.isExpired = true;
				//give their money back
			}
			state.setCash(state.getCash() - this.cost);
			return true;
		}
		return false;
	}
	public int getCost() { return this.cost; }

}
