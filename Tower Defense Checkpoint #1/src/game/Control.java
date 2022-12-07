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
import java.util.List;
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
	RoundControl roundControl;
	
	private int mouseX, mouseY;
	
	Path path;
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
		pathScanner.close();
		//set state
		state = new State ();
		state.setCash(1000);
		state.setLives(100);
		state.setRound(0.5); //0.5
		//load the view
		view = new View (this, state);
		
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		
		state.startFrame();  // Prepares the creation of the 'next' frame
        state.addGameObject(new Background(this));  // Add one background object to our list
        state.addGameObject(new Menu(this, state));	//Add menu object to list
        state.addGameObject(new SaltButton(this, state)); // Add Salt Button to list
        state.addGameObject(new TeslaButton(this, state));
        state.addGameObject(new NextRoundButton(this, state));
        //state.addGameObject(new Krogdor(this.state,this));  // Add one snail to our list
        //state.addGameObject(new Snail(this.state,this));  // Add one snail to our list
        
        roundControl = new RoundControl(this, this.state);
        roundControl.startRound(state.getRound());
        
        state.finishFrame();    // Mark the next frame as ready

        view.repaint();         // Draw it.
        
        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
        //SoundClipTest music = new SoundClipTest("resources/Title Theme-Saying Goodbye.wav");
        SoundClipTest.LEVEL1.play();
	}
	/**
	 * get current path
	 * @return current path
	 */
	public Path getPath ()
	{
		return path;
	}
	
	public int getMouseX () { return this.mouseX; }
	
	public int getMouseY () { return this.mouseY; }
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
            //System.out.println("Loading " + filename);
            
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
    /**
     * Prevents repetitive code.
     * Takes in a string and returns corresponding enemy
     * 
     * @param enemy (type of enemy to return)
     * @return The corresponding enemy object
     */
    public GameObject getEnemy(String enemy)
	{
		if(enemy.equalsIgnoreCase("Krogdor"))
			return new Krogdor(this, this.state);
		if(enemy.equalsIgnoreCase("Snail"))
			return new Snail(this, this.state);
		if(enemy.equalsIgnoreCase("ShockedGuy"))
			return new ShockedGuy(this, this.state);
		return null;
	}
    /**
     * Update game to the next frame
     * when the timer triggers
     * check that game is not over
     */
	@Override
	public void actionPerformed (ActionEvent e)
	{
		if(!state.gameOver)
		{
			state.startFrame();
			roundControl.doNextFrame();
			for (GameObject go : state.getFrameObjects())
				go.update(state.getElapsedTime());
			state.finishFrame();
		}
		else if(state.getLives() != -1) // game is over
		{
			state.setLives(-1); // make sure this if statement only triggers the once
			state.startFrame();
			state.addGameObject(new GameOver(this)); // display the game over screen
			state.finishFrame();
		}
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
		//System.out.println("Round " + state.getRound() + " is on frame #" + roundControl.getFrame() + ".");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Consume a click wherever the mouse is clicked
	 */
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		List<GameObject> list = state.getFrameObjects();
		
		for(GameObject go : list)
			if (go instanceof Clickable)
			{
				Clickable c = (Clickable) go;
				if (c.consumeClick(mouseX, mouseY))
					break;
			}
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
