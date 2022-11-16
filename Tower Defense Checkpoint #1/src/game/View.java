/**
 * View class creates the panel
 * and paints everything
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel
{
	// Gets rid of Eclipse error message
	private static final long serialVersionUID = 1L;
	
	//fields
	Control control;
	State state;
	/**
	 * Makes a frame for the game to view in
	 * 
	 * @param control
	 * 				  view control
	 * @param state
	 * 				 state control
	 */
	public View (Control control, State state)
	{
		this.control = control;
		this.state = state;
		
		// Make a frame
		
		JFrame frame = new JFrame ();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set size of panel
		
        this.setMinimumSize(new Dimension(600,600));
        this.setPreferredSize(this.getMinimumSize());
        
		// Set scope of panel
        
        frame.setContentPane(this);
		
		// Pack and make the frame visible
		
		frame.pack();
		frame.setVisible(true);
		
		
	}
	/**
	 * Paints everything that's visible
	 * and not expired
	 * @param g
	 * 			object to paint
	 */
	@Override
	public void paint (Graphics g)
	{
		for (GameObject go : state.getFrameObjects())
            if (go.isVisible() && !go.isExpired())
                go.draw(g);	
	}
}