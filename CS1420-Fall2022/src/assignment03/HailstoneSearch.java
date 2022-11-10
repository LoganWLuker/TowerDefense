/**
 * Find N that gives X Iterations
 *
 * @author  Logan Luker
 * @version September 6, 2022
 */
package assignment03;

import java.util.Scanner;

public class HailstoneSearch 
{

	public static void main(String[] args) 
	{
		int X = 0;
		int N;
		int count = 0;//count to compare to X given by user
		int countN = 1;//starting N
		Scanner in = new Scanner(System.in);
		while(X<1)
		{
			System.out.print("Input positive integer for sequence length X: ");
			X = in.nextInt();
		}
		while(X!=count) //loop until resulting count matches X
		{
			countN++;
			count = 0;
			N = countN;
			while(N!=1) //loop until Hailstone Sequence finishes
			{
				count++;
				N = ((N%2)==0)? N/2:(N*3)+1;
			}
		}
		System.out.println("The Hailstone sequence starting at " + countN + " takes " + X + " steps to converge to 1.");
		in.close();

	}

}