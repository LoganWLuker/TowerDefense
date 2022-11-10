/**
 * Calculate Hypotenuse
 *
 * @author  Logan Luker
 * @version August 31, 2022
 */
package assignment02;

import java.util.Scanner;

public class Hypotenuse {

	public static void main(String[] args) 
	{
		double side1;
		double side2;
		Scanner in = new Scanner(System.in);
		System.out.println("Let's find the length of the hypotenuse!");
		while(true) 
		{
			System.out.print("Input length of side 1: ");
			try
			{
				side1 = Double.valueOf(in.next());
				break;
			}
			catch (Exception ex) 
			{	
				System.out.println("Not a valid length.");
			}
		}
		while(true) 
		{
			System.out.print("Input length of side 2: ");
			try
			{
				side2 = Double.valueOf(in.next());
				break;
			}
			catch (Exception ex) 
			{	
				System.out.println("Not a valid length.");
			}
		}
		System.out.println("The length of the hypotenuse is: " + Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2)));

	}

}
