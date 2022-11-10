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
 * @author Peter Jensen
 * @version Fall 2022
 */
package assignment04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

import javax.swing.JPanel;

public class ExampleShapes extends JPanel
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
    
    	g.setColor((Color.ORANGE).brighter());
    	
    	g.drawLine(100, 100, 300, 150);  // x1 y1 x2 y2
    	
    	g.drawRect(100, 200, 200, 50);  // x1 y1 (upper-left) width height
    	g.fillRect(400, 200, 200, 50);  // x1 y1 (upper-left) width height
    	
    	g.drawOval(100, 300, 200, 50);  // x1 y1 (upper-left) width height
    	g.fillOval(400, 300, 200, 50);  // x1 y1 (upper-left) width height
    	
    	g.drawArc(100, 400, 200, 50, 0, 90);  // x1 y1 (upper-left) width height start angle, end angle
    	g.fillArc(400, 400, 200, 50, 0, 90);  // x1 y1 (upper-left) width height start angle, end angle
    	
    	g.drawRoundRect(100, 500, 200, 50, 20, 20);  // x1 y1 (upper-left) width height xradius yradius
    	g.fillRoundRect(400, 500, 200, 50, 20, 20);  // x1 y1 (upper-left) width height xradius yradius

    	Color c = new Color(1.0f, 0.5f, 0.2f);
    	g.setColor(c);
    	
    	g.drawPolygon(makeTriangle(100, 600));
    	g.fillPolygon(makeTriangle(400, 600));
    	
    	g.drawString("Hello", 100, 700);  // text x (baseline y)
    }
    
    /**
     * This function builds a Polygon that represents
     * a triangle (with a specific orientation).  The
     * newly constructed Polygon is returned to the caller.
     * The triangle is 100 pixels wide and 50 pixels tall,
     * isosceles, and the flat side is on the top.
     * 
     * @param x the x coordinate of the upper-left corner of the triangle
     * @param y the y coordinate of the upper-left corner of the triangle
     * @return a Polygon object with a triangle in it
     */
    public Polygon makeTriangle(int x, int y)
    {
    	Polygon p = new Polygon();
    	p.addPoint(x,     y);
    	p.addPoint(x+100, y);
    	p.addPoint(x+50,  y + 50);
    	return p;
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
