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
		g.setFont(new Font("TimesRoman", 0, 20));
		g.drawString("Towers", 665, 30);
	}

}
