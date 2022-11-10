package lab08;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class represents and application/JPanel that
 * draws a few shapes for the user and allows the
 * user to drag the shapes around with the mouse.
 *
 * @author Peter Jensen
 * @version Fall 2022
 */
public class ShapeMover extends JPanel implements MouseListener,
                                                  MouseMotionListener,
                                                  Runnable
{
    // Instance variables.
    
    Shape[] shapes;

    int lastX, lastY;
    Shape current;

    /**
     * Initialize this (our JPanel).  Create the shapes, and
     * register this object as a listener to mouse events
     * produced by this object.
     */
    public ShapeMover ()
    {
    	// Make the shapes.
    	
    	shapes = new Shape[]
    	    {
    	       new Rect    (130,  40,  60, 20, Color.RED),
               new Rect    (230,  40,  20, 80, Color.BLUE),
               new Rect    (330,  40,  80, 100, Color.GREEN.darker()),
               new Rect    (130, 140, 100, 40, new Color (0.8f, 0.6f, 0.2f)),
    	       new Rect    ( 30, 140,  40, 60, Color.YELLOW),
    	       new Circle    (242,  234,  60, Color.RED),
               new Circle    (214,  132,  20, Color.BLUE),
               new Circle    (123,  123,  80, Color.GREEN.darker()),
               new Circle    (431, 324, 100, new Color (0.8f, 0.6f, 0.2f)),
    	       new Circle    ( 321, 213,  40, Color.YELLOW)
    	    };
    
    	// Set the size of this panel.
    	
    	Dimension d = new Dimension (600, 600);
    	this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
    	
    	// Register this object as a listener to its own events.
    	
    	this.addMouseListener (this);
    	this.addMouseMotionListener (this);
    }

    /**
     * Draws the shapes at their current locations
     * and colors.
     *
     * @param g
     *         the Graphics object to draw to
     */
    public void paint (Graphics g)
    {
    	// Clear the background to a nice light blue color.
    	
    	g.setColor(new Color (0.8f, 0.8f, 1.0f));
    	g.fillRect(0, 0, this.getWidth(), this.getHeight());
    	
    	// Draw all of the shapes.
    	
    	for (Shape s : shapes)
    	    s.draw(g);
    }

    /**
     * This method is part of the MouseListener interface.
     * Because we registered this application object as a listener
     * to its own mouse events, this method will be automatically
     * called whenever the mouse button is pressed down.
     *
     * In this method, we determine if the mouse click occurred
     * in any of our shapes.  If so, we record that shape object
     * as the current shape.  This has the effect of selecting
     * a shape to drag around.
     *
     * @param e
     *         the mouse event
     */
    public void mousePressed  (MouseEvent e) 
    {
    	// Get the location of the mouse click within this window.
    	
    	int x = e.getX (); 
    	int y = e.getY ();
    
    	// Save it for later use.
    	
    	lastX = x;
    	lastY = y;
    
    	// Determine if the mouse click is within any shape.
    	//   If so, save the shape as the current shape.
    
    	for (Shape s : shapes)
    	    if (s.isInside (x, y))
    	        current = s;
    }
    
    /**
     * This method is part of the MouseListener interface.
     * Because we registered this applet object as a listener
     * to its own mouse events, this method will be automatically
     * called whenever the mouse button is let down.
     *
     * In this method, we mark the current shape as null.  This
     * has the effect of dropping whatever shape we are dragging
     * around.
     *
     * @param e
     *         the mouse event
     */
    public void mouseReleased (MouseEvent e) 
    {
        current = null;
    }
    
    /**
     * This method is part of the MouseMotionListener interface.
     * Because we registered this applet object as a listener
     * to its own mouse events, this method will be automatically
     * called whenever the mouse is moved with the button pressed down.
     *
     * In this method, we adjust the position of the shape the user
     * is dragging around.
     *
     * @param e
     *         the mouse event
     */
    public void mouseDragged  (MouseEvent e) 
    {
    	// Compute how far the mouse moved since the last event.
    	
    	int x = e.getX (); 
    	int y = e.getY ();
    	
    	int deltaX = x - lastX;
    	int deltaY = y - lastY;
    
    	// Save the current mouse position.
    	
    	lastX = x;
    	lastY = y;
    
    	// If the user is dragging around a shape, move it by
    	//   the same amount that the mouse moved.
    	
    	if (current != null)
    	{
    		current.move (deltaX, deltaY); 
    		repaint ();
    	}
    }

    // Unused event methods (required by the interfaces).

    public void mouseClicked  (MouseEvent e) { }
    public void mouseEntered  (MouseEvent e) { }
    public void mouseExited   (MouseEvent e) { }
    public void mouseMoved    (MouseEvent e) { }
    
    /* Above this point are the methods and variables that we use in the JPanel */
    /* Below this point are the methods and variables that launch the application */
    
    /* I violated separation of concerns.  The JPanel and Application classes
     * are merged, and 'main' is below.  This works for simple code (like this
     * lab), but it is not a good idea for larger projects.
     */
    
    /**
     * The application entry point.
     * 
     * @param args unused
     */
    public static void main (String[] args)
    {
        // Main runs in the 'main' execution thread, and the GUI
        //   needs to be built by the GUI execution thread.
        //   Ask the GUI thread to run our 'run' method (at some
        //   later time).
        
        SwingUtilities.invokeLater(new ShapeMover());

        // Done.  Let the main thread of execution finish.  All the
        //   remaining work will be done by the GUI thread.
    }
    
    /**
     * Builds the GUI for this application.  This method must
     * only be called/executed by the GUI thread. 
     */
    public void run ()
    {
        JFrame f = new JFrame("Shape Mover 2021");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 'this' is our Runnable object, but it is also our JPanel.
        
        f.setContentPane(this); 
        
        f.pack();
        f.setLocationRelativeTo(null);  // Centers window
        f.setVisible(true);
    }
}
