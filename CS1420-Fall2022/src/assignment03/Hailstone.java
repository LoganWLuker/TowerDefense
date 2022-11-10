/**
 * Print out Hailstone for N
 *
 * @author  Logan Luker
 * @version September 6, 2022
 */
package assignment03;

import java.util.Scanner;

public class Hailstone 
{

	public static void main(String[] args) 
	{
		int N = 0;
		int count = 0;
		Scanner in = new Scanner(System.in);
		while(N<1)
		{
			System.out.print("Input initial positive integer N: ");
			N = in.nextInt();
		}
		while(N!=1)
		{
			count++;
			System.out.print((N==2) ? N + " 1\n" : N + " ");
			N = ((N%2)==0)? N/2:(N*3)+1;
			
		}
		System.out.print(count==0 ? "1\n" : "");
		System.out.println("The number of iterations performed was " + count);
		in.close();
	}

}
