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
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;

public class Control implements Runnable,
								ActionListener,
								MouseListener,
								MouseMotionListener
{
	State state;
	View view;
	
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
		ClassLoader myLoader = this.getClass().getClassLoader();
		InputStream pathStream = myLoader.getResourceAsStream("resources/path_2.txt");
		Scanner pathScanner = new Scanner(pathStream);
		
		path = new Path(pathScanner);
		
		state = new State ();
		state.setCash(5000);
		state.setLives(100);
		
		view = new View (this, state);
		
		state.startFrame();  // Prepares the creation of the 'next' frame
        state.addGameObject(new Background(this));  // Add one background object to our list
        state.addGameObject(new Snail(this));  // Add one snail to our list
        state.finishFrame();    // Mark the next frame as ready

        view.repaint();           // Draw it.
		
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
        try
        {
            ClassLoader myLoader = this.getClass().getClassLoader();
            InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
            BufferedImage image = javax.imageio.ImageIO.read(imageStream);
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
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		
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
