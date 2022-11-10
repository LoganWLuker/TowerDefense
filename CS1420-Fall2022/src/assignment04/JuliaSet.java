/**
 * Draw Something on JPanel
 *
 * @author  Logan Luker
 * @version September 17, 2022
 */
package assignment04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JuliaSet extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public JuliaSet()
	{
		setTitle("Julia Set");
	}
	private static void createAndShowGUI() //set up the panel and frame
	{
		JFrame frame = new JuliaSet();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new DrawJulia();
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) //run it
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{ 
			public void run() 
			{
			    createAndShowGUI(); 
			}
		});
	}
}
class DrawJulia extends JPanel implements MouseListener, Serializable, ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private static final int ANIMATION_SPEED = 0;
	private Timer animationTimer;
	/**This program displays Julia Sets in the imaginary plane,
	 * where the x-axis is real numbers, and the y-axis is imaginary numbers.
	 * 
	 * The 'seed' for each Julia Set is based on a point around a circle with radius r=0.7885,
	 * which I animate around using a timer
	 * 
	 * Julia Sets are formed with the formula Znew = Zold^2 + c   , where z and c are complex numbers
	 * 
	 * c is the 'seed' (which is animating around a circle)
	 * 
	 * z is every pixel on the screen, and it is converted to a coordinate that is 
	 * inside a circle about the origin with radius 2 (the bounds of the fractals),
	 * and is iterated in the formula until it escapes the bounds, or reaches the max iterations
	 * */
	//complex number seed for the fractal
	//double cReal = -0.512511498387847167;	//real part
	//double cImaginary = 0.521295573094847167;	//imaginary part
	double aPImod = 0; //a goes from 0 -> 2PI
	double cReal = 0.7885*Math.cos(aPImod); //x coordinate of a point on a circle with r=0.7885
	double cImaginary = 0.7885*Math.sin(aPImod); //y coordinate of a point on a circle with r=0.7885
	
	//position of the circle containing the fractal based on the center of the circle
	double xPos = 0;	//x position of the circle
	double yPos = 0;	//y position of the circle
	
	double radius = 1.5;//radius of the circle
	double zReal,zImaginary; //complex number z used in fractal formula
	double graphX, graphY; //graph coordinates, obtained using pixel coords
	int n; //iteration counter
	int iterations = 1500; //max iterations
	int iterationZoom = 0; //zoom to get more detail when zooming in on click
	int red, green, blue; //rgb color variables
	int windowS = 200; //window size
	double logM = 255/Math.log(iterations + 1); //calculate multiplier to make sure rgb doesn't go over 255
	
	public DrawJulia()
	{
		animationTimer = new Timer(ANIMATION_SPEED, this);
		animationTimer.start();
		addMouseListener(this);
	}

	public void paint(Graphics g) 
	{
		
		for (int x = 0; x < this.getWidth(); x++) //iterate through every x pixel
		{
			for (int y = 0; y < this.getHeight(); y++)//iterate through every y pixel for every x pixel
			{
				graphX = xPos - radius + x*((2*radius)/this.getWidth()); //convert x pixels to a coordinate plane based on circle position
				graphY = yPos + radius - y*((2*radius)/this.getHeight()); // convert y pixels ^
				zReal = graphX; //create a complex number 'z' with 2 variables, real part
				zImaginary = graphY; //and imaginary part, based on the current point being evaluated
				
				//iterate the Julia Set equation until z either breaks out to infinity or the max # of iterations occur
				for (n = 0; n < iterations && Math.sqrt((zReal*zReal) + (zImaginary*zImaginary)) < 2; n++) 
				{
					//new z = z^2 + c
					zReal = zReal*zReal - zImaginary*zImaginary + cReal; //calculate real part of new z
					zImaginary = 2*graphX*zImaginary + cImaginary; //calculate imaginary part of new z
					graphX = zReal; //update graphX so we can calculate zImaginary next iteration despite zReal changing in line 49
				}
				//a really complicated setColor in order to make it look really pretty
				red = n <= iterationZoom ? 0 : n <= 0.21*iterations+iterationZoom ? 0 : (int)(logM*Math.log(n-0.2*iterations-iterationZoom));
				green = n <= iterationZoom ? 0 :(int)(logM*Math.log(n+1-iterationZoom));
				blue = n <= iterationZoom ? 80 : n <= 0.3*iterations+iterationZoom ? (int)((0.5*logM)*Math.log(0.3*iterations-n+1+iterationZoom)) : 0;
				try
				{
					g.setColor(new Color(red,green,blue));
				}
				catch(Exception e)
				{
					System.out.println(red + " " + green + " " + blue);
					throw e;
				}
				g.fillOval(x, y,  2,  2); //paint the dot
			}
		}
	}
	public Dimension getMinimumSize()
    {
    	return new Dimension(windowS, windowS);
    }
    
    public Dimension getMaximumSize()
    {
    	return new Dimension(windowS, windowS);
    }
    
    public Dimension getPreferredSize()
    {
    	return new Dimension(windowS, windowS);
    }

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//position circle at click location
		xPos = xPos - radius + e.getX()*((2*radius)/this.getWidth());
		yPos = yPos + radius - e.getY()*((2*radius)/this.getHeight());
		radius = radius/2; //half the radius to 'zoom in'
		iterations += 50; //add iterations for more detail
		iterationZoom += 50; //add to zoom mod to shift colors
		logM = 255/Math.log(iterations+iterationZoom + 1);	//re-calculate log mod
		repaint();
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == animationTimer) 
		{
			aPImod = aPImod > 2*Math.PI ? 0 : aPImod + 0.005; //iterate a from 0 -> 2*PI, which is then used with cos and sin to find x and y coord around circle
			cReal = 0.7885*Math.cos(aPImod); //move real part a step around the circle of radius 0.7885
			cImaginary = 0.7885*Math.sin(aPImod); //move imaginary part around the circle
			repaint(); //redraw it
		}
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}