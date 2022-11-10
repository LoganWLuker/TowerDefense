/**
 * Making Fraction Class
 *
 * @author  Logan Luker
 * @version September 27, 2022
 */
package assignment06;

public class Fraction 
{
	private long numerator, denominator;
	/**
	 * Constructor method to build a fraction object with numerator n,
	 * and denominator d
	 * 
	 * reduces the fraction
	 * @param n
	 * @param d
	 */
	public Fraction (long n, long d)
	{
	    if (d < 0)
	    {
	    	n = -n;
	    	d = -d;
	    }
	    long gcd = greatestDivisor(n,d);
	    this.numerator   = n/gcd;
	    this.denominator = d/gcd;
	}
	/**
	 * Helper Method to find the greatest common Divisor of x and y
	 * @param x
	 * @param y
	 * @return
	 */
	public long greatestDivisor(long x,long y)
	{
		long gcd = x;
	    long remainder = y;
	    long temp=1;

	    while (remainder != 0)
	    {
	        temp = remainder;
	        remainder = gcd % remainder;
	        gcd = temp;
	    }
	    if (gcd < 0)
	    	gcd = -gcd;
	    return gcd;
	}
	/**
	 * Creates a fraction from an integer as numerator/1
	 * @param n
	 */
	public Fraction (long n)
	{
		this.numerator = n;
		this.denominator = 1;
	}
	/**
	 * Convert Fraction object to string, returning "n/d"
	 * @return String
	 */
	public String toString ()
	{
		return this.numerator + "/" + this.denominator;
	}
	/**
	 * return a decimal approximation of a fraction object
	 * @return double
	 */
	public double toDouble ()
	{
		return Double.valueOf(this.numerator)/Double.valueOf(this.denominator);
	}
	/**
	 * return numerator of a fraction object
	 * @return long
	 */
	public long getNumerator ()
	{
	    return this.numerator;
	}
	/**
	 * return denominator of a fraction object
	 * @return
	 */
	public long getDenominator ()
	{
	    return this.denominator;
	}
	/**
	 * Return a fraction equal to the sum of the leftHandSide and the rightHandSide
	 * @param rightHandSide
	 * @return Fraction object
	 */
	public Fraction add (Fraction rightHandSide)
	{
		// Create a variable to hold the result
	    Fraction result; 

	    // Build a new Fraction object - send the products to the constructor.
	    result = new Fraction (this.numerator * rightHandSide.denominator + rightHandSide.numerator * this.denominator,
	                           this.denominator * rightHandSide.denominator);

	     // Pass the resulting fraction back to the caller.
	     return result; 
	}
	/**
	 * Return a fraction equal to the difference of the leftHandSide and the rightHandSide
	 * @param rightHandSide
	 * @return Fraction object
	 */
	public Fraction subtract (Fraction rightHandSide)
	{
		// Create a variable to hold the result
	    Fraction result; 

	    // Build a new Fraction object - send the products to the constructor.
	    result = new Fraction (this.numerator * rightHandSide.denominator - rightHandSide.numerator * this.denominator,
	                           this.denominator * rightHandSide.denominator);

	     // Pass the resulting fraction back to the caller.
	     return result; 
	}
	/**
	 * Return a new Fraction equal to the product of the leftHandSide and the rightHandSide
	 * @param rightHandSide
	 * @return Fraction object
	 */
	public Fraction multiply (Fraction rightHandSide)
	{
		// Create a variable to hold the result
	    Fraction result;  

	    // Build a new Fraction object - send the products to the constructor.
	    result = new Fraction (this.numerator * rightHandSide.numerator,
	                           this.denominator * rightHandSide.denominator);

	     // Pass the resulting fraction back to the caller.
	     return result; 
	}
	/**
	 * Return a new Fraction equal to the divisor of the leftHandSide and the rightHandSide
	 * @param rightHandSide
	 * @return Fraction Object
	 */
	public Fraction divide (Fraction rightHandSide)
	{
		// Create a variable to hold the result
	    Fraction result;

	    // Build a new Fraction object - send the products to the constructor.
	    result = new Fraction (this.numerator * rightHandSide.denominator,
	                           this.denominator * rightHandSide.numerator);

	     // Pass the resulting fraction back to the caller.
	     return result; 
	}
}
