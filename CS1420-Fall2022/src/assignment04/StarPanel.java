/**
 * This class is not a full application -- it is an
 * extended JPanel.  (You cannot run it.)  When you
 * use this class in a JFrame, the methods here will be
 * automatically called when the GUI needs to be redrawn
 * or resized.
 * 
 * The GUI example class creates a new StarPanel to use it.
 * Note that you can use the other panels as well, just
 * update the GUIExample code.
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

public class StarPanel extends JPanel
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
    	// For debugging.
    	
    	System.out.println("Hi.  This is paint.  GUI thread.");
    	
    	// Make a seeded random number generator.
    	
    	Random r = new Random(421);
    	
    	// Fill the background to black.
    	
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, 800, 800);
    	
    	// Draw some stars.
    	
    	for (int i = 0; i < 5_000; i++)
    	    drawRandomStar(g, r);
    	
    	// Draw two moons (using my helper function)
    	
    	drawMoon(g, 100, 100);
    	drawMoon(g, 400, 600);
    }
    
    /**
     * Draws a moon at a specific location on the panel.
     * 
     * @param g any valid Graphics object
     * @param x the x coordinate of the upper-left corner of the moon
     * @param y the y coordinate of the upper-left corner of the moon
     */
    public void drawMoon (Graphics g, int x, int y)
    {
    	g.setColor(Color.WHITE);
    	g.fillOval(x, y, 40, 40);
    	g.setColor(Color.BLACK);
    	g.fillOval(x+5, y+5, 40, 40);
    }
    
    /**
     * Draws a star randomly in an 800x800 range.
     * 
     * @param g any valid Graphics object
     */
    public void drawRandomStar (Graphics g, Random rand)
    {
    	int x = rand.nextInt(800);
		int y = rand.nextInt(800);
		int size = rand.nextInt(3)+1;
    	g.setColor(Color.YELLOW);
    	g.fillOval(x, y, size, size);
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
