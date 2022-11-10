package path;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Path 
{
	ArrayList<Point> points;
	/**
	 * Default Constructor
	 */
	public Path()
	{
		this.points = new ArrayList<Point>();
	}
	/**
	 * Constructs the path with a scanner as a parameter
	 * 
	 * @param in
	 * 			the scanner
	 */
	public Path(Scanner in) 
	{
		this.points = new ArrayList<Point>();
		
		for(int n = in.nextInt(); n > 0; n--)
		{
			points.add(new Point(in.nextInt(),in.nextInt()));
		}
	}
	/**
	 * get the number of points
	 * 
	 * @return
	 * 			number of points
	 */
	public int getPointCount()
	{
		return points.size();
	}
	/**
	 * get the x coordinate for a point at index n
	 * 
	 * @param n
	 * 			index of desired point
	 * @return
	 * 			x coordinate of the point at index n
	 */
	public int getX(int n)
	{
		return points.get(n).x;
	}
	/**
	 * get the y coordinate for a point at index n
	 * 
	 * @param n
	 * 			index of desired point
	 * @return
	 * 			y coordinate of the point at index n
	 */
	public int getY(int n)
	{
		return points.get(n).y;
	}
	/**
	 * add a point to the path
	 * 
	 * @param x
	 * 			x coordinate to add
	 * @param y
	 * 			y coordinate to add
	 */
	public void add(int x, int y)
	{
		points.add(new Point(x,y));
	}
	/**
	 * Replace the last point in the list
	 * 
	 * @param x
	 * 			x value to replace
	 * @param y
	 * 			y value to replace
	 */
	public void replaceLast(int x, int y)
	{
		points.remove(points.size()-1);
		points.add(new Point(x,y));
	}
	/**
	 * override toString with a string formatted like the file
	 * 
	 * example:
	 * 
	 * 		4
	 *		0 150
	 *		100 100
	 *		150 125
	 *		200 170
	 * 
	 */
	public String toString()
	{
		String pathString = points.size() + "";
		for(int i = 0; i < points.size(); i++)
		{
			pathString = pathString + "\n" + points.get(i).x + " " + points.get(i).y;
		}
		return pathString;
	}
	/**
	 * Draws out the path with line segments
	 * to the specified graphics object
	 * @param g
	 * 			the graphics object (where to draw to)
	 */
	public void draw(Graphics g)
	{
		if(points.size() > 1)
		{
			for(int i = 0; i < points.size()-1; i++)
			{
				g.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
			}
		}

	}
}
