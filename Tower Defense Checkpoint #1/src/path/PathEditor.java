package path;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class PathEditor extends JPanel implements Runnable,
												  MouseListener,
												  MouseMotionListener,
												  ActionListener
{
	//get rid of eclipse warning
	private static final long serialVersionUID = 1L;
	
	//instance variables
	private static final int ANIMATION_SPEED = 500;
	private Timer animationTimer;

	//fields
	JMenuItem loadItem;
	JMenuItem saveItem;
	JMenuItem clearItem;
	BufferedImage backdrop;
	Path path;
	Color lineColor;
	
	/**
	 * Default constructor
	 */
	public PathEditor()
	{
		//set up animation for path color to flash
		lineColor = Color.red;
		animationTimer = new Timer(ANIMATION_SPEED, this);
		animationTimer.start();
		
		path = new Path();
	}
	
	/**
     * Draws the path based on the Points
     *
     * @param g
     *         the Graphics object to draw to
     */
	public void paint (Graphics g)
    {
		g.drawImage(backdrop, 0, 0, null);
		g.setColor(lineColor);
		path.draw(g);
    }
	
	/**
     * The application entry point.
     * 
     * @param args unused
     */
	public static void main(String[] args) throws IOException 
	{
		SwingUtilities.invokeLater(new PathEditor());
	}
	
	/**
     * Builds the GUI for this application.  This method must
     * only be called/executed by the GUI thread. 
     */
	public void run()
	{
		//Make a frame
		JFrame f = new JFrame("Path Editor 2022");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set Size of panel
        this.setMinimumSize(new Dimension(600,600));
        this.setPreferredSize(this.getMinimumSize());
        
        //Set scope to the panel
        f.setContentPane(this); 
        
        //Create a menu Bar
        JMenuBar menuBar = new JMenuBar();
        
        //Create a File Drop-down menu
        JMenu fileMenu = new JMenu("File");
        
        //Create Items for the Drop-down
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        clearItem = new JMenuItem("Clear");
        
        //Add the File drop-down to the bar
        menuBar.add(fileMenu);
        
        //Add the Items to the Drop-down menu
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(clearItem);
        
        //Add Listeners to all the Items
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        clearItem.addActionListener(this);
        
        //Set the menu bar as the one we just made
        f.setJMenuBar(menuBar);
        
        //pack and make the frame visible
        f.pack();
        f.setVisible(true);
        
        //listen to this panel for It's mouse events
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        //try loading the background image
        try 
        {
            backdrop = javax.imageio.ImageIO.read(new File("path_2.jpg"));
        } 
        catch (IOException e) 
        {
            System.out.println("Image could not be found");
        }
	}

	/**
	 * add a path point when mouse is pressed
	 */
	public void mousePressed(MouseEvent e) 
	{
		path.add(e.getX(), e.getY());
		repaint();
	}
	
	/**
	 * Update the point position if line is dragged
	 */
	public void mouseDragged(MouseEvent e)
	{
		path.replaceLast(e.getX(), e.getY());
		repaint();
	}
	
	/**
	 * Check for button presses and animations
	 */
	public void actionPerformed(ActionEvent e) 
	{
		//animate the path for better visibility
		if (e.getSource() == animationTimer)
        {
			//toggle green and red
			lineColor = (lineColor == Color.green) ? Color.red : Color.green;
            repaint();
            return;  // We've handled this event - exit.
        }
		if (e.getSource() == loadItem)
		{
			loadPath();
		}
		if (e.getSource() == saveItem)
		{
			savePath();
		}
		//Create new empty path if 'clear' button is pressed
		if (e.getSource() == clearItem)
		{
			path = new Path();
			repaint();
		}
	}
	
	/**
	 * Load a path from a file
	 */
	private void loadPath ()
	{
		// Ask the user for a file to load.
		JFileChooser chooser = new JFileChooser();
		//FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		//chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(this);
		
		if (result != JFileChooser.APPROVE_OPTION)
			return;  // Bail out - user cancelled
		
		// Get the file the user selected.
		
		File f = chooser.getSelectedFile();
	
		// Load the path.
		
		try
		{
			Scanner in = new Scanner(f);
			path = new Path(in);
		}
		catch (IOException e)
		{
			//error dialog
			JOptionPane.showConfirmDialog(this, "Could not load that file.");
			
			System.out.println("error loading.");
		}
		
		// Repaint the window.  (Changing the array does not change
		// the screen.  We must redraw it.)
		
		repaint();
	}
	
	/**
	 * Save current path to a file
	 */
	private void savePath ()
	{
		// Ask the user for a save filename.
		
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		
		if (result != JFileChooser.APPROVE_OPTION)
			return;  // Bail out - user cancelled
		
		File f = chooser.getSelectedFile();
		
		// Save the file.
		
		try
		{
			//make a print writer
			PrintWriter out = new PrintWriter(f);
			//use our nifty method to print the save
			out.print(path.toString());
			out.close();
		}
		catch (IOException e)
		{
			//error dialog
			JOptionPane.showConfirmDialog(this, "Could not load that file.");

			System.out.println("Error.");
		}
	}
	//required methods for interfaces
	public void mouseClicked(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseMoved(MouseEvent e) { }
	
}
