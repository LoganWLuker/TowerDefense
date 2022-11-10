package lab08;
import java.awt.Graphics;
import java.awt.Color;

abstract public class Shape 
{
	// Instance variables
	protected int x, y;
    protected Color color;
    /**
     * Constructor
     * @param x
     * 	x position
     * @param y
     *  y position
     * @param color
     * 	color of shape
     */    
    public Shape (int x, int y, Color color)
    {
	this.x = x;
	this.y = y;
	this.color = color;
    }
    
    /**
     * Changes the position of this shape by
     * the specified amount.  Note that this does
     * not set the position absolutely, the deltas
     * specify how far to move the shape from its
     * current position.
     *
     * @param deltaX
     *              how far to move the shape horizontally
     *
     * @param deltaY
     *              how far to move the shape vertically
     */
    public void move (int deltaX, int deltaY)
    {
    	x = x + deltaX;
    	y = y + deltaY;
    }

    abstract public boolean isInside (int targetX, int targetY);

    abstract public void draw (Graphics g);
}
