/**
 * Path class describes how to build a path object
 * enemies move along the path from start to finish
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
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
	 * @return number of points
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
	 * @return x coordinate of the point at index n
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
	 * @return y coordinate of the point at index n
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
	 * Given a percentage between 0% and 100%, this method calculates
	 * the location along the path that is exactly this percentage
	 * along the path. The location is returned in a Point object
	 * (integer x and y), and the location is a screen coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is
	 * returned. If the percentage is greater than 100%, the final
	 * position is returned.
	 * 
	 * Callers must not change the x or y coordinates of any returned
	 * point object (or the caller could be changing the path).
	 * 
	 * @param percentTraveled
	 * 						  a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	 public Point convertToCoordinates(double percentTraveled)
	 {
		 //return the starting point if the percent given is negative
		 if(percentTraveled < 0)
			 return new Point(points.get(0).x,points.get(0).y);
		 //return the ending point if the percent given is greater than 100
		 if(percentTraveled > 1)
			 return new Point(points.get(points.size()-1).x,points.get(points.size()-1).y);
		 //create an array to hold the segment lengths
		 double[] lengths = new double[points.size()-1];
		 double length = 0;
		 double totalPathLength = 0;
		 double percentChecked = 0;
		 double segmentPercent = 0;
		 int currentSegment = 0;
		 //find the total length of the path
		 for(int i = 0; i < points.size()-1; i++)
		 {
			 length = Math.sqrt(
					    Math.pow(
					    (points.get(i+1).x - points.get(i).x),2) + 
					    Math.pow(
					    (points.get(i+1).y - points.get(i).y),2)
					    );
			 //since we'll need these lengths later, store them into an array
			 lengths[i] = length;
			 totalPathLength += length;
		 }
		 //find the segment we're currently on
		 for(currentSegment = 0; percentChecked + lengths[currentSegment]/totalPathLength < percentTraveled; currentSegment++)
		 {
			 percentChecked += lengths[currentSegment]/totalPathLength;
		 }
		 //calculate percent along the segment
		 segmentPercent = (percentTraveled - percentChecked)/(lengths[currentSegment]/totalPathLength);
		 //calculate the x and y coordinate based on the percent along the segment
		 int x = (int)((1-segmentPercent)*points.get(currentSegment).x + (segmentPercent)*points.get(currentSegment+1).x);
		 int y = (int)((1-segmentPercent)*points.get(currentSegment).y + (segmentPercent)*points.get(currentSegment+1).y);
		 //return the point
		 return new Point(x,y);
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
