/**
 * This class is used to create custom dummy objects to experiment with
 */
package game;

import java.awt.Graphics;

public class TestObject extends GameObject
{
	public TestObject (Boolean expiration)
	{
		this.isExpired = expiration;
	}
	
	@Override
	public void update(double elapsedTime) 
	{
		
	}

	@Override
	public void draw(Graphics g) 
	{
		
	}
}


