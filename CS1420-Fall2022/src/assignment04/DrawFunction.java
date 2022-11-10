/**
 * This class is not a full application -- it is an
 * extended JPanel.  (You cannot run it.)  When you
 * use this class in a JFrame, the methods here will be
 * automatically called when the GUI needs to be redrawn
 * or resized.
 * 
 * The GUI example class creates a new StarPanel.  To use
 * this class instead, just update the GUIExample to create
 * a new ExampleShapes instead.
 * 
 * 
 * @author Peter Jensen
 * @version Fall 2022
 */
package assignment04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class DrawFunction extends JPanel
{
	/**
	 * Overrides the default painting in a JPanel.  I choose
	 * below how I want the panel to paint itself.  This method
	 * is automatically called (we don't have to call it)
	 * whenever the panel needs to be drawn on the screen.
	 * 
	 * @param g a Graphics object provided by the GUI for drawing
	 */
    public void paint (Graphics g)
    {
    	// Fill the background to black
    	
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, this.getWidth(), this.getHeight());
    
    	// Draw a sin wave
    	// Treat the center line in the window (at y=400)
    	// as the x axis.
    	// Treat the right side of the screen as x=10
    	// Treat the top of the screen as y = 1
    	
    	g.setColor(Color.YELLOW);
    	
    	// Loop through pixel values horizontally.
    	
    	for (int x = 0; x < this.getWidth(); x++)
    	{
    		int center = this.getHeight() / 2;  // Compute the vertical center of the panel
    		
    		double theta = x * 10.0 / this.getWidth();  // Calculates (x / maxX) * 10.  Note that (x / maxX) ranges from [0..1).
    		double sin = Math.sin(theta);
    		double y = center - (sin * center);  // Sin gives a value from [0..1].  Scale it up by 'center' times to make it fit the view
    		
    		g.fillOval(x, (int) y,  2,  2);  // Just draw a dot.
    	}
    }
    
    /**
     * Overrides the same-named function in the JPanel
     * so that we can specify our own size (when the
     * GUI system asks for this panel's size).
     */
    public Dimension getMinimumSize()
    {
    	return new Dimension(800, 800);
    }
    
    /**
     * Overrides the same-named function in the JPanel
     * so that we can specify our own size (when the
     * GUI system asks for this panel's size).
     */
    public Dimension getMaximumSize()
    {
    	return new Dimension(800, 800);
    }
    
    /**
     * Overrides the same-named function in the JPanel
     * so that we can specify our own size (when the
     * GUI system asks for this panel's size).
     */
    public Dimension getPreferredSize()
    {
    	return new Dimension(800, 800);
    }
}
