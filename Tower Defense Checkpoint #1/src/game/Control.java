/**
 * Control class handles crude game-play
 * holds the timer to update the game
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;


// This is an arbitrary change designed to test the Remote Push capabilities of Eclipse


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;

public class Control implements Runnable,
								ActionListener,
								MouseListener,
								MouseMotionListener
{
	private Map<String, BufferedImage> imageCache;
	
	State state;
	View view;
	Menu menu;
	
	int mouseX, mouseY;
	
	private Path path;
	/**
	 * default constructor,
	 * run the run() method
	 */
	public Control ()
	{
		SwingUtilities.invokeLater(this);
	}

	/**
	 * set up the game view
	 * and start update timer
	 */
	@Override
	public void run() 
	{
		// Build the map.
		
		imageCache = new TreeMap<String,BufferedImage>();
		
		// Create major game parts
		ClassLoader myLoader = this.getClass().getClassLoader();
		InputStream pathStream = myLoader.getResourceAsStream("resources/path_2.txt");
		Scanner pathScanner = new Scanner(pathStream);
		
		path = new Path(pathScanner);
		
		state = new State ();
		state.setCash(5000);
		state.setLives(100);
		
		view = new View (this, state);
		
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		
		state.startFrame();  // Prepares the creation of the 'next' frame
        state.addGameObject(new Background(this));  // Add one background object to our list
        state.addGameObject(new Krogdor(this));  // Add one snail to our list
        state.addGameObject(new Menu(this, state));
        state.finishFrame();    // Mark the next frame as ready

        view.repaint();         // Draw it.
		
        
        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
	}
	/**
	 * get current path
	 * @return current path
	 */
	public Path getPath ()
	{
		return path;
	}
	/**
	 * Get an image from the resources folder
	 * 
	 * @param filename
	 * 				   name of image file to get
	 * @return the image
	 */
    public BufferedImage getImage (String filename)
    {
    	//if image is in the map, return it
    	if(imageCache.containsKey(filename))
    	{
    		return imageCache.get(filename);
    	}
    	//If we get this far, image is not in map. Load it
        try
        {
            ClassLoader myLoader = this.getClass().getClassLoader();
            InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
            BufferedImage image = javax.imageio.ImageIO.read(imageStream);
            //put the image in the map
            imageCache.put(filename, image);
            //Demonstrate that they only load once
            System.out.println("Loading " + filename);
            //return the loaded image
            return image;
        }
        catch (IOException e)
        {
            System.out.println("Could not find or load resources/" + filename);
            System.exit(0);  // Close the frame, bail out.
            return null;  // Does not happen, the application has exited.
        }
    }
    
    public int getMouseX () { return this.mouseX; }
    
    public int getMouseY () { return this.mouseY; }
    
    /**
     * Update game to the next frame
     * when the timer
     */
	@Override
	public void actionPerformed (ActionEvent e) 
	{
		state.startFrame();
        for (GameObject go : state.getFrameObjects())
            go.update(0);
        state.finishFrame();
        view.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{

	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		
		//System.out.println("X: " + mouseX + " | Y: " + mouseY);	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
