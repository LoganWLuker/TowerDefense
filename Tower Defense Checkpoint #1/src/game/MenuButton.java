package game;

import java.awt.Color;
import java.awt.Graphics;

public class MenuButton extends GameObject implements Clickable
{
	Control control;
	State state;
	
	public MenuButton (Control control, State state)
	{
		this.control = control;
		this.state = state;
		
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
		g.setColor(Color.GRAY);
		g.fillRoundRect(630, 80, 140, 140, 0, 0);
		g.drawImage(control.getImage("salt.png"), 674, 120, null);
	}

	@Override
	public boolean consumeClick(int mouseX, int mouseY) 
	{
		if(mouseX > 630 && mouseX < 770
		   && mouseY > 80 && mouseY < 220)
		{
			control.state.startFrame();
			control.state.addGameObject(new Salt(this.state,this.control));
			control.state.finishFrame();
			control.view.repaint();
			return true;
		}
		return false;
	}

}
