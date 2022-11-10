package assignment07;

import java.awt.Color;

public class ArrayTests {

	public static void main(String[] args) 
	{
		boolean[] flags = {true,false,true,false,true,false};
		int posA = 1;
		int posB = 4;
		double[] weightEstimates = {15.6,16.8,19.1,20.2};
		//1
		Color[] shades = new Color[1000];
		//2
		shades[shades.length-1] = new Color(15,16,17);
		//3
		shades[0] = shades[shades.length-1];
		//4
		//null
		//5
		//you will get an error because you can't invoke .getRed() on null
		//6
		//use conditionals to only execute the code if the reference is not null
		//7
		int[] distX = {12,4,19,-7};
		//8
		System.out.println(distX.length);
		//9
		for(int num : distX)
		{
			System.out.println(num);
		}
		//10
		//because distX is declared as an integer array, and you can't convert null to int
		//11
		boolean temp = flags[posA];
		flags[posA] = flags[posB];
		flags[posB] = temp;
		//12
		int[] weights = new int[weightEstimates.length];
		for(int i = 0; i < weights.length; i++)
		{
			weights[i] = (int)weightEstimates[i];
		}
		for(int num : weights)
		{
			System.out.println(num);
		}
		
	}

}
