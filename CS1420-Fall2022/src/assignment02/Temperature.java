/**
 * Calculate Temperature in Celsius
 *
 * @author  Logan Luker
 * @version August 31, 2022
 */
package assignment02;

import java.util.Scanner;

public class Temperature {

	public static void main(String[] args) 
	{
		int tempF;
		int tempC;
		Scanner in = new Scanner(System.in);
		System.out.println("Let's find the temperature in Celsius!");
		while(true) 
		{
			System.out.print("Input temperature in Fahrenheit: ");
			try
			{
				tempF = Integer.parseInt(in.next());
				break;
			}
			catch (Exception ex)
			{	
				System.out.println("Not a valid temperature. Must be an integer.");
			}
		}
		tempC = ((tempF-32)*5)/9;
		System.out.println("The temperature in Celsius is: " + tempC);

	}

}
