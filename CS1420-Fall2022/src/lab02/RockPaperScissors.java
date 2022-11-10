/**
 * This program plays a simple game of rock-paper-scissors (roshambo)
 * with the user.  (Students will complete this program as part of
 * lab #2.)  See the lab instructions.
 * 
 * Peter Jensen and (student name here)
 * August 30, 2022
 */
package lab02;

import java.util.Scanner;

public class RockPaperScissors
{
	public static void main(String[] args)
	{
		// Create a Scanner object.
		Scanner in = new Scanner(System.in);
		
		// Create two String variables.  Name them computerWord and userWord.
		// Initialize them to empty strings.

		String computerWord = "";
		String userWord = "";
		
	    // Make a random integer between [0...2].  If the random integer is a
	    //   0, set computerWord to "rock", if it's a 1, set computerWord
	    //   to "paper", otherwise set computerWord to "scissors".

		int value = (int) (Math.random()*3);
		
		computerWord = value == 0 ? "rock" : value == 1 ? "paper" : "scissors";
		
		boolean userInputIsOK = false;
		// Using a Boolean flag, create an input loop that asks the player to enter
		//   their guess.  Print reasonable instructions.  Don't allow the program 
		//   to proceed until the player enters either "rock", "paper", or "scissors".
		//   Store it in userWord.
		while (! userInputIsOK) 
		{
			System.out.print("Enter rock, paper, or scissors: ");
			userWord = in.next();
			userInputIsOK = userWord.equals("rock") || userWord.equals("paper") || userWord.equals("scissors");
		}

		
		// Print out the user's guess:  "You picked rock.", etc.
		// Print out the computer's guess:  "I picked rock.", etc.

		System.out.println("You picked " + userWord +".");
		System.out.println("I picked " + computerWord +".");
		
	    // Write a few 'if' statements to determine the result of the game and
	    //   print out the winner:  "I win",  "You win", "It's a tie"
	    // If the strings are the same, its a tie.
	    //  else if the player picks "rock" and the computer picks "scissors", the player wins,
	    //  else if ... (etc.)
		System.out.println(userWord.equals(computerWord) ? "tie!" : userWord.equals("rock") && computerWord.equals("scissors") ? "You win." : userWord.equals("paper")&&computerWord.equals("rock") ? "You win." : userWord.equals("scissors")&&computerWord.equals("paper") ? "You win." : "I win.");
//		if(userWord.equals(computerWord))
//			System.out.println("tie!");
//		else if(userWord.equals("rock") && computerWord.equals("scissors"))
//			System.out.println("You win.");
//		else if(userWord.equals("paper")&&computerWord.equals("rock"))
//			System.out.println("You win.");
//		else if(userWord.equals("scissors")&&computerWord.equals("paper"))
//			System.out.println("You win.");
//		else
//			System.out.println("I win.");
		
	}
}
