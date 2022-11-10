/**
 * A few small experiments in support of lab #2.  Students will fix the code here,
 * then use the code fragments to make a rock-paper-scissors game (roshambo).
 * See the lab instructions.
 * 
 * Peter Jensen and (student name here)
 * August 30, 2022
 */
package lab02;

import java.util.Scanner;

public class Experiments
{
	public static void main(String[] args)
	{
		// You will get input from the user, so you'll need a Scanner object.  
		// Use this one, stored in 'in'.  
		
		Scanner in = new Scanner(System.in);
		
		/*****************
		 * Experiment #1 *
		 *****************/		
		// Create a random 'word' using random numbers.  This will represent
		// a choice made by the computer.
		
		// Generate a random value, either 0, 1, or 2.
		
		int value = (int) (Math.random()*3);
		
		/** Your work here -- fix the code so that it prints a 0, 1, or 2 randomly. **/
		
		System.out.println("Experiment #1:  A random value: " + value);
		
		// This code converts the random value to a word.  When it's working,
		// the computer word should be either 'rock', 'paper', or 'scissors' with 
		// equal probability.  (Run it several times.)
		
		String computerWord = "No choice";
		
		if (value == 0)
			computerWord = "rock";
		else if (value == 1)
			computerWord = "paper";
		else if (value == 2)
			computerWord = "scissors";
		
		System.out.println("Experiment #1:  The computer chose: " + computerWord);
		
		
		/*****************
		 * Experiment #2 *
		 *****************/
		// Use a loop to get input, wait for valid input */
		
		// Create a Boolean 'flag' to hold true or false.  It will represent
		//   the idea that the user has supplied valid input.  At the start
		//   of the program run, it's false.  (No valid user input yet.)
		
		boolean userInputIsOK = false; 
		
		// We need a variable to hold the user input.  For this experiment,
		// we'll have the user input a string.  We should initialize the variable
		// with something, but since the user hasn't typed anything in yet,
		// we need a 'nothing' string.
		
		String userWord = "";  // It may be better to use a null object.  We'll cover it soon.
		
		// Our input loop
		
		while ( ! userInputIsOK )  // Loop as long as the user input is NOT ok
		{
		    // Get input from the user
			
			System.out.print("Experiment #2:  Type a word and press enter: ");
			userWord = in.next();

		    // Test the input for validity
		    // If valid, set userInputIsOK to true
			
			if (userWord.equals("rock") || userWord.equals("paper") || userWord.equals("scissors"))  // Remove this after testing.
				userInputIsOK = true;			
			
			/** Your work here -- accept "rock", accept "paper", and accept "scissors" as input. **/
			/**   Also remove "valid" as valid input.  **/
		}
		
		// Done.
		
		System.out.println("Experiment #2:  You entered: " + userWord);

		
		/*****************
		 * Experiment #3 *
		 *****************/
		// Compare two strings to see if they are equal.
		
		/** This code is incorrect.  It seems to partially work, but doesn't.  Your work will be to fix it. **/
		
	    if (userWord.equals(computerWord))
	        System.out.println ("Experiment #3:  The words '" + computerWord + "' and '" + userWord + "' are equal.");
	    else
	        System.out.println ("Experiment #3:  The words '" + computerWord + "' and '" + userWord + "' are not equal.");
	}
}
