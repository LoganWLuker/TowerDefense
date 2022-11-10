/**
 * Calculate Area of a Circle
 *
 * @author  Logan Luker
 * @version August 31, 2022
 */
package assignment02;
import java.util.Scanner;
public class CircleArea {

	public static void main(String[] args) 
	{
		double radius;
		Scanner in = new Scanner(System.in);
		System.out.println("Let's find the area of your circle!");
		while(true) 
		{
			System.out.print("Input radius: ");
			try
			{
				radius = Double.valueOf(in.next());
				break;
			}
			catch (Exception ex) 
			{	
				System.out.println("Not a valid radius.");
			}
		}
		System.out.println("The area of the circle is: " + (Math.PI*Math.pow(radius, 2)));
	}

}
