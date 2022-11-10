/**
 * Print powers of 2
 *
 * @author  Logan Luker
 * @version September 6, 2022
 */
package assignment03;

public class PowersOfTwo 
{
	public static void main(String[] args) 
	{
		int count = 0;
		while(count<17)
		{
			System.out.println((int)Math.pow(2, count));
			count++;
		}

	}

}