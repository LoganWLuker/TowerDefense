/**
 * Calculations and Strings
 *
 * @author  Logan Luker
 * @version August 24, 2022
 */
package assignment01;
public class TimeCalculation 
{
	public static void main(String[] args) 
	{
		int uID = 1386433;
		int hours = uID / 3600;
		int minutes = uID % 3600 / 60;
		int seconds = uID % 3600 % 60;
		System.out.print("My uID number is u");
		System.out.print(uID);
		System.out.println(".");
		System.out.print(uID+" seconds is "+hours+" hour(s), "+minutes+" minute(s), and "+seconds+" second(s).");
	}
}