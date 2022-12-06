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
	int lives, cash, extraCashWidth, extraLivesWidth, extraCashDigits, extraLivesDigits;
	/**
	 * Default constructor
	 * 
	 * @param control
	 * @param state
	 */
	public Menu (Control control, State state)
	{
		this.state = state;
		this.control = control;
		
		isVisible = true;
		isExpired = false;
	}
	/**
	 * Menu does not change
	 * nothing to update
	 */
	@Override
	public void update(double elapsedTime) 
	{
		// TODO Auto-generated method stub
		
	}
	/**
	 * Describes how to draw the Menu
	 */
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
		
		this.lives = state.getLives();
		this.cash = state.getCash();
		
		this.extraCashDigits = (int) Math.ceil(Math.log10((double) (this.cash + 1))) - 3;
		this.extraCashWidth = 10*this.extraCashDigits;
		
		this.extraLivesDigits = (int) Math.ceil(Math.log10((double) (this.lives + 1))) - 3;
		this.extraLivesWidth = 10*this.extraLivesDigits;
		
		
		//Draw yellow bounding box
		g.setColor(Color.YELLOW);
		g.fillRect(522 - Math.max(this.extraCashWidth, this.extraLivesWidth), 6, 68 + Math.max(this.extraCashWidth, this.extraLivesWidth), 48);
		
		//Draw lives
		g.setColor(Color.RED);
		g.setFont(new Font("Proxima Nova", 0, 13));
		g.drawString("Lives: " + this.lives, 525 - Math.max(this.extraCashWidth, this.extraLivesWidth), 17);
		
		//Draw cash
		g.setColor(Color.BLUE);
		g.drawString("Cash: " + this.cash, 525 - Math.max(this.extraCashWidth, this.extraLivesWidth), 34);
		
		//Draw round
		g.setColor(Color.BLACK);
		g.drawString("Round: " + state.getRound(), 525 - Math.max(this.extraCashWidth, this.extraLivesWidth), 51);
	}
}
