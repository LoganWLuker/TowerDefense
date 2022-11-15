/**
 * GameObject class describes
 * abstract game object methods
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.Graphics;

abstract public class GameObject 
{
	//Abstract fields
	protected boolean isVisible; 
    protected boolean isExpired;
    //Abstract methods
    public boolean isVisible() { return isVisible; }
    public boolean isExpired() { return isExpired; }

    abstract public void update (double elapsedTime);
    abstract public void draw (Graphics g);
}
