/**
 * Tesla Class describes the tesla Tower
 * able to be placed
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Tesla extends Tower implements Clickable
{
	boolean isMoving;
	int towerRadius;
	int fireRate;
	int fires;
	State state;
	Control control;
	/**
	 * Default constructor
	 * @param state
	 * @param control
	 */
	public Tesla(State state, Control control)
	{
		this.cost = 700;
		xPos = control.getMouseX();
		yPos = control.getMouseY();
		towerRadius = 100;
		fires = 0;
		fireRate = 10; //not a true rate; smaller is faster, 1 is fastest
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
			if(nearestEnemy != null && fires <= 0)
			{
				state.addGameObject(new TeslaZap(control,state,this,nearestEnemy));
				fires = fireRate;
				nearestEnemy.expire();
				state.setCash(state.getCash() + nearestEnemy.getReward());
			}
			fires --;
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
			if(control.path.isOver(this.xPos, this.yPos))
				g.setColor(new Color(255,0,0,100));
			g.fillOval(xPos - towerRadius, yPos - towerRadius, 2*towerRadius,2*towerRadius);
		}
		g.drawImage(control.getImage("tesla.png"), xPos-50, yPos-50, null);
	}
	/**
	 * Describe how clicking is handled with Salt tower
	 * @param mouseX
	 * @param mouseY
	 */
	@Override
	public boolean consumeClick(int mouseX, int mouseY)
	{
		if(isMoving && !control.path.isOver(mouseX, mouseY))
		{
			isMoving = false;
			if(mouseX < 0 || mouseX > 600 || mouseY < 0 || mouseY > 600)
			{
				this.isExpired = true;
				state.setCash(state.getCash() + this.getCost());
			}
			return true;
		}
		return false;
	}
}
