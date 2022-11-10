/**
 * Testing Fraction Class
 *
 * @author  Logan Luker
 * @version September 27, 2022
 */
package assignment06;

public class FractionTester {

	public static void main(String[] args) {
		//System.out.println ("Fraction tester:");
		boolean errors = false;
		Fraction a = new Fraction (2, -3);
		Fraction b = new Fraction (1, 4);
		Fraction c = new Fraction(2);
		/**
		 * Test building a fraction with just an integer as the parameter
		 * and test negative placement
		 * and test getNumerator and getDenominator
		 */
		//System.out.println ("2 = " + c);
		if(a.getNumerator() != -2 || a.getDenominator() != 3 ||
		   b.getNumerator() != 1 || b.getDenominator() != 4 ||
		   c.getNumerator() != 2 || c.getDenominator() != 1)
		{
			//System.out.println("Error - making fraction with an int did not complete correctly.");
			errors = true;
		}
		/**
		 * Test toString
		 */
		//System.out.println("-2/3 as a string = " + a.toString());
		if(!a.toString().equals("-2/3"))
		{
			//System.out.println("Error - toString did not complete correctly.");
			errors = true;
		}
		/**
		 * Test toString
		 */
		//System.out.println(a + " as a double = " + a.toDouble());
		if(a.toDouble() != -2.0/3.0)
		{
			//System.out.println("Error - toDouble did not complete correctly.");
			errors = true;
		}
		/**
		 * Test adding fractions
		 */
		c = a.add(b);
		//System.out.println (a + " + " + b + " = " + c);
		if(c.getNumerator() != -5 || c.getDenominator() != 12)
		{
			//System.out.println("Error - add did not complete correctly.");
			errors = true;
		}
		/**
		 * Test subtracting fractions
		 */
		c = a.subtract(b);
		//System.out.println (a + " - " + b + " = " + c);
		if(c.getNumerator() != -11 || c.getDenominator() != 12)
		{
			//System.out.println("Error - subtract did not complete correctly.");
			errors = true;
		}
		/**
		 * Test multiplying fractions
		 * and Test reducing fractions
		 */
		c = a.multiply(b);
		//System.out.println (a + " * " + b + " = " + c);
		if(c.getNumerator() != -1 || c.getDenominator() != 6)
		{
			//System.out.println("Error - multiply did not complete correctly.");
			errors = true;
		}
		/**
		 * Test dividing fractions
		 */
		c = a.divide(b);
		//System.out.println (a + " / " + b + " = " + c);
		if(c.getNumerator() != -8 || c.getDenominator() != 3)
		{
			//System.out.println("Error - divide did not complete correctly.");
			errors = true;
		}
		if(errors)
			System.out.println("All tests completed, errors found.");
		else
			System.out.println("All tests completed, no errors.");
	}

}
