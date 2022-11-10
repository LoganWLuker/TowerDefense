/**
 * This class represents and application/JPanel that
 * draws a few shapes for the user and allows the
 * user to drag the shapes around with the mouse.
 * 
 * The user can load and save the arrangement of shapes
 * to a file with an extension of .shapes.  (It's just
 * a plain text file.)
 *
 * @author Peter Jensen
 * @version Spring 2022
 */

package solution;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ShapeMover extends JPanel implements MouseListener,
                                                  MouseMotionListener,
                                                  ActionListener,
                                                  Runnable
{
	// This gets rid of an Eclipse warning.  Note that we do
	// not use versioning of applications.
	
	private static final long serialVersionUID = 1L;
	
    // Instance variables.

	Shape[] shapes;

    int lastX, lastY;
    Shape current;
    
    // Usually you don't keep menu items in a field.  In our
    // case, we need the references later (so we can compare
    // action sources to them).
    
    JMenuItem loadItem;
    JMenuItem saveItem;

    // A buffered image is just a screen area, but off-screen.
    // You can load files (such as jpg, gif, or png) into a
    // buffered image.  See the constructor below for where we load
    // an image (which creates the BufferedImage object).
    
    BufferedImage backdrop;

    /**
     * Initialize this (our JPanel).  Create the shapes, and
     * register this object as a listener to mouse events
     * produced by this object.
     */
    public ShapeMover ()
    {
    	try
    	{
    		// Read an image from the top level of our Eclipse project.
    		
    	    backdrop = javax.imageio.ImageIO.read(new File("fun.png"));
    	}
    	catch (IOException e)
    	{
    		System.out.println("Could not read fun.png.");
    	}
    	
    	// Make the shapes.
    	
    	shapes = new Shape[]
    	    {
    	       new Circle    (130,  40,  60, Color.RED),
               new Circle    (230,  40,  20, Color.BLUE),
               new Circle    (330,  40,  80, Color.GREEN.darker()),
               new Circle    (130, 140, 100, new Color (0.8f, 0.6f, 0.2f)),
    	       new Circle    ( 30, 140,  40, Color.YELLOW),
    	       new Rect (300, 300,  25, 40, Color.CYAN),
    	       new Rect (400, 400,  70, 70, Color.BLACK),
    	       new Rect (200, 400,  70, 70, Color.WHITE),
    	       new Rect (400, 200,  60, 20, Color.PINK.darker())
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
    	
    	// Draw the image we loaded in the constructor.  Note that
    	// you do not want to load the image in paint -- it
    	// would load many times.  Load once in the constructor.
    	
    	if (backdrop != null)  // only draw it if it successfully loaded
    		g.drawImage(backdrop, 0, 0, null);
    	
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
     * lab), but is not a good idea for larger projects.
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
    	// Make a frame (the window you'll see)
    	
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 'this' is our Runnable object, but it's also our JPanel.
        
        // Make a second JPanel to be our 'top-level' JPanel.  
        // JPanels can 'contain' each other. Add 'this' panel to the
        // top-level panel in the center, then add a button to the
        // top-level panel in the south.
        // (Complex user interfaces can have panels in panels in panels...)
        
        JPanel topLevel = new JPanel();
        topLevel.setLayout(new BorderLayout());  // Need a layout manager to arrange added items
        topLevel.add(this,BorderLayout.CENTER);
        
        // Add a button.  We're not listening to it's action events,
        // so nothing happens when the user presses this button.
        
        topLevel.add(new JButton("Don't press me"), BorderLayout.SOUTH);
        
        // The frame can have one panel set as its contents.  
        
        f.setContentPane(topLevel); 
        
        // Add a menu bar, menu, and menu items.  Note that we
        // save the menu items in fields so that we'll have access
        // to their references.  (The others are in local variables.)
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        
        menuBar.add(fileMenu);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        
        // When a menu is activated, it creates an action event.
        // The GUI event loop will automatically call action listeners
        // that are listening to menu items.  Register 'this' object
        // as the listener to both the load and save menu items.
        
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        
        // Put the menu bar in the frame.
        
        f.setJMenuBar(menuBar);
        
        // Ask the frame to calculate how much screen space it needs.
        
        f.pack();
        
        // Make it visible.
        
        f.setLocationRelativeTo(null);  // Centers window
        f.setVisible(true);
    }

    /**
     * Automatically called when an action event is triggered AND
     * if we've registered this object as a listener to the event.
     * 
     * In our case, this object is listening to the load and save menu items,
     * so this method will be called when those menu items are
     * selected by the user.
     * 
     * @param e an object containing the event details
     */
	@Override
	public void actionPerformed (ActionEvent e)
	{
		// Determine which menu item was clicked, then
		// take the appropriate action.  (We saved our menu item objects
		// in fields so that we can compare the source of the
		// event to each menu item, by reference.)
		
		if (e.getSource() == loadItem)
		{
			loadShapes();
			//System.out.println("Load clicked.");
		}
		if (e.getSource() == saveItem)
		{
			saveShapes();
			//System.out.println("Save clicked.");
		}
		
	}
	
	/**
	 * Called when the user has selected the 'load' menu item.
	 * Prompts the user for a file to load, then loads the 
	 * shapes from that file.  The existing arrangement of shapes
	 * is lost.
	 * 
	 * If the user cancels the load dialog, no action is taken.
	 */
	private void loadShapes ()
	{
		// Ask the user for a file to load.  Restrict their choices
		// to files that end in .shapes
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Shape files", "shapes");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(this);
		
		if (result != JFileChooser.APPROVE_OPTION)
			return;  // Bail out - user cancelled
		
		// Get the file the user selected.
		
		File f = chooser.getSelectedFile();
	
		// Load the shapes.
		
		try
		{
			Scanner in = new Scanner(f);
			
			// The first integer in the file specifies the number
			// of shapes.  Rebuild the shape array to be the correct
			// size.
			
			int size = in.nextInt();
			shapes = new Shape[size];
			
			// The file should contain one shape for each array
			// location.  Read the first word from each shape
			// specification, then build the appropriate shape
			// and store it in the array.
			
			for (int i = 0; i < size; i++)
			{
				String type = in.next();
				if (type.equals("rect"))
					shapes[i] = new Rect(in);
				if (type.equals("circle"))
					shapes[i] = new Circle(in);
			}
			
		}
		catch (IOException e)
		{
			// It would be better to put up an error dialog box, like this:
			// JOptionPane.showConfirmDialog(this, "Could not load that file.");
			
			System.out.println("error loading.");
		}
		
		// Repaint the window.  (Changing the array does not change
		// the screen.  We must redraw it.)
		
		repaint();
	}
	
	/**
	 * Called when the user has selected the 'save' menu item.
	 * The user is asked to specify a save filename.  The shapes
	 * array is written to that file (so that it can be loaded later).
	 * The filename extension is guaranteed to be .shapes and the
	 * file is a plain text file.  (You can view the file in any
	 * editor that will open a plain text file.)
	 * 
	 * If the user cancels the filename selection dialog, no action
	 * is taken.
	 */
	private void saveShapes ()
	{
		// Ask the user for a save filename.
		
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		
		if (result != JFileChooser.APPROVE_OPTION)
			return;  // Bail out - user cancelled
		
		File f = chooser.getSelectedFile();
		
		// Added after lecture:  It would be a good idea to make sure
		// the filename ends in .shapes 
		// If not, add .shapes to the end of the filename.
		
		String completeFilePath = f.getAbsolutePath();
		if(!completeFilePath.endsWith(".shapes"))
		    f = new File(completeFilePath + ".shapes");
		
		// Save the file.
		
		try
		{
			PrintWriter out = new PrintWriter(f);
			
			out.println(shapes.length);  // Output the array size first.
			for (Shape s : shapes)
				out.println(s.createFileString());
			out.close();
		}
		catch (IOException e)
		{
			// It would be better to put up an error dialog box, like this:
			// JOptionPane.showConfirmDialog(this, "Could not load that file.");

			System.out.println("Error.");
		}
	}
}
