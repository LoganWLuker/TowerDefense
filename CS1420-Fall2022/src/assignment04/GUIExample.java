/**
 * A small bit of code that shows how to build a JFrame
 * (a top-level window) and JPanel in Java.  Students may use
 * this code in their Assignment #5.
 * 
 * @author Peter Jensen
 * @version Fall 2022
 */
package assignment04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUIExample implements Runnable
{
    /**
     * Application entry point
     * 
     * @param args unused
     */
	public static void main(String[] args) 
	{
		// We will discuss this later, but in short it causes the GUI
		// execution thread to execute our 'run' method below.
		
		SwingUtilities.invokeLater(new GUIExample());
	}

	/**
	 * Replaces 'main' as our application main method.
	 * Builds and displays a JFrame.
	 */
	public void run ()
	{
		//System.out.println("Hello -- I'm the GUI thread.");
		
		// Make a frame object.  I added the second line here
		// because my application was not terminating.  I'll
		// explain it on Wednesday.
		
		JFrame frame = new JFrame ("Your Mother");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Make panel object for our frame.  We will write
		// a class that 'extends' the basic functionality
		// of a JPanel.
		
		JPanel panel = new DrawFunction ();
		frame.setContentPane(panel);
		
		// Notice that I don't set the size here (like my other examples).
		// Instead, the panel will report it's own size.
		
		// Pack and display the frame.
		
		frame.pack();
		frame.setVisible(true);
	}
	
}
//class JuliaSet extends JPanel
//{
//	
//}
