/**
 * Finding the Square Root of a fraction
 * using formula Xn+1 = 1/2 (Xn + S/Xn)
 *
 * @author  Logan Luker
 * @version October 2, 2022
 */
package assignment06;

import java.util.Scanner;

public class FractionRoot {

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		int count;
		Fraction newX;
		System.out.print("Input numerator: ");
		long numerator = in.nextLong();
		System.out.print("Input denominator: ");
		long denominator = in.nextLong();
		System.out.print("Input approximation count: ");
		int aCount = in.nextInt();
		Fraction userFractionS = new Fraction(numerator,denominator);
		//x = S/2
		Fraction currentX = userFractionS.divide(new Fraction(2));
		for(count = 0; count < aCount; count++)
		{
			//S/x
			newX = userFractionS.divide(currentX);
			//x + S/x
			newX = currentX.add(newX);
			//1/2 * (x + S/x)
			newX = new Fraction(1,2).multiply(newX);
			currentX = newX;
		}
		System.out.println("The square root of " + userFractionS + " is approximately " + currentX + ".");
	}

}
