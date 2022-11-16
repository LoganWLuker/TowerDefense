/**
 * Calculate a Number in Binary
 *
 * @author  Logan Luker
 * @version August 31, 2022
 */
package assignment02;

import java.util.Scanner;

public class Binary {

	public static void main(String[] args) 
	{
		int n;
		String nbin = "";
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a number: ");
		n = in.nextInt();
		nbin = ""+ (n/128) + ((n/64)-(2*(n/128))) + ((n/32)-(2*(n/64))) + ((n/16)-(2*(n/32))) + ((n/8)-(2*(n/16))) + ((n/4)-(2*(n/8))) + ((n/2)-(2*(n/4))) + (n-(2*(n/2)));
		System.out.println("The decimal number " + n + " is " + nbin + " in binary.");
		in.close();
	}

}