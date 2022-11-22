/**
 * Responsible for the menu on the right side of the screen
 * that contains towers to be bought.
 * @author Bruce Crockett and Logan Luker
 * @version November 22, 2022
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu extends GameObject
{
	Control control;
	State state;
	int lives, cash;
	
	public Menu (Control control, State state)
	{
		this.state = state;
		this.control = control;
		
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
		//Draw background
		//System.out.println("Menu.draw() executed.");
		g.setColor(new Color(100, 170, 56));
		g.fillRect(600, 0, 200, 600);
		
		//Draw title
		g.setColor(Color.BLACK);
		g.setFont(new Font("Proxima Nova", 0, 20));
		g.drawString("Towers", 665, 30);
		
		// TODO: draw bounding boxes around lives and cash
		
		//Draw lives
		g.setColor(Color.RED);
		g.setFont(new Font("Proxima Nova", 0, 13));
		this.lives = state.getLives();
		g.drawString("Lives: " + this.lives, 525, 17);
		
		//Draw cash
		g.setColor(Color.BLUE);
		this.cash = state.getCash();
		g.drawString("Cash: " + this.cash, 525, 34);		
	}
}
