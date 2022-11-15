/**
 * Control class handles crude game-play
 * holds the timer to update the game
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;

public class Control implements Runnable,
								ActionListener
{
	State state;
	View view;
	
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
}
