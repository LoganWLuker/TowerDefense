/**
 * Controls logic for reading round files
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.io.InputStream;
import java.util.Scanner;

public class RoundControl 
{
	State state;
	Control control;
	GameObject[] enemies;
	int[] quantities;
	int counter;
	int frame;
	/**
	 * Default constructor
	 * 
	 * @param state
	 * @param control
	 */
	RoundControl (Control control, State state)
	{
		counter = 0;
		frame = 0;
		this.state = state;
		this.control = control;
	}
	/**
	 * Reads the file corresponding to the given round
	 * Sets up arrays to hold the enemies, and quantities
	 * @param roundNum
	 * 					round we want to start
	 */
	public void startRound(double roundNum)
	{
//		if((state.getRound()/0.5) % 2 != 0)
//			return;
		ClassLoader myLoader = this.getClass().getClassLoader();
		InputStream roundStream = myLoader.getResourceAsStream("resources/round_" + (int)roundNum + ".txt");
		Scanner roundScanner = new Scanner(roundStream);
		
		// read the round file to check the size
		int roundSize = roundScanner.nextInt();
		String enemy;
		int wait = 0;
		// create GameObject array
		enemies = new GameObject[roundSize];
		// create quantity array
		quantities = new int[roundSize];
		int quantity;
		int repeat;
		int i;
		// Scan the file and set the array values based on it
		for(i = 0; roundScanner.hasNext(); i++)
		{
			// store the first int, assuming its a repeat count
			repeat = roundScanner.nextInt();
			if (repeat < 0) // if it is indeed a repeat count, continue as such
			{
				int z; // count the commands
				quantity = roundScanner.nextInt(); // store the enemy quantity
				for(z = 0; quantity > 0; z++) // count the commands
				{
					quantities[i] = quantity; // store our quantity in the array
					enemy = roundScanner.next(); // get our enemy string
					
					if(!enemy.equalsIgnoreCase("wait")) // if it's not a wait, treat it as an enemy
					{
						z++; // advance the counter for the wait that comes after the enemy
						wait = roundScanner.nextInt(); // store how long to wait after spawning the enemy
						roundScanner.next(); //advance to the end of the line past the wait
						
						// Add Enemies according to their quantity
						for(int c = 0; c < quantity; c++)
						{
							enemies[i+c] = control.getEnemy(enemy); // grab the enemy type and add it to the array
							quantities[i+c+1] = wait; // grab the wait time and add it after the enemy
							i++; // advance i accordingly to move through the array
						}
						i+=quantity-1; // advance i based on the quantity
					}
					quantity = roundScanner.nextInt(); // set up the next quantity
					i++; // advance through the array
				}
				repeat ++; // account for already doing the commands once
				int zCount = 0; // to check if we've done all the commands before incrementing repeat
				for(i = i; repeat < 0; i++)
				{
					if(enemies[i-z] != null) // if the enemy isn't null, copy a new version into the array
						enemies[i] = control.getEnemy(enemies[i-z].toString());
					else
						enemies[i] = enemies[i-z]; // just copy the null without getting a new instance
					quantities[i] = quantities[i-z]; // copy the quantity to the array
					zCount ++; // we've copied one command, add to the count
					if(zCount == z) // have we completed all the commands?
					{
						repeat ++; // if so, one repeat is complete, increment it.
						zCount = 0; // reset our command count
					}
				}
				if(roundScanner.hasNext()) // if we're not at the very end, set up the next quantity
					quantities[i] = roundScanner.nextInt();
			}else
				quantities[i] = repeat; // if we're not repeating, just set up the quantity as normal
			
			if(roundScanner.hasNext()) // if we're not at the very end, check the next enemy type
				enemy = roundScanner.next();
			else
				enemy = "wait"; // otherwise, just treat it as a wait to skip the if statement
			quantity = quantities[i]; // store the current quantity
			if(!enemy.equalsIgnoreCase("wait")) // if it's a wait, ignore it, otherwise It's an enemy
			{
				wait = roundScanner.nextInt();
				roundScanner.next();
				
				//Add Enemies according to their quantity
				for(int c = 0; c < quantity; c++)
				{
					enemies[i+c] = control.getEnemy(enemy); // get the enemy type and add it to the array
					quantities[i+c+1] = wait; // get how long to wait and add it to the array
					i+=1; // advance i
				}
				i+=quantity-1; // advance i however far we've gone
			}
		}
		//Uncomment to print out the round length
		System.out.println(i);
		roundScanner.close();
	}
	/**
	 * iterate through the array and do what it says
	 * wait, or create an enemy
	 */
	public void doNextFrame()
	{
//		if((state.getRound()/0.5) % 2 != 0)
//			return;
		//if we're past the instructions, stop reading them
		if(frame+1 > enemies.length)
		{
			frame ++;
//			state.setRound(state.getRound()+0.5);
//			startRound(state.getRound());
			return;
		}
		//code for waiting
		if(enemies[frame] == null)
		{
			if(counter < quantities[frame])
			{
				counter++;
			}else
			{
				counter = 0;
				frame ++;
			}
		}else
		{
			//code for making enemies
			state.addGameObject(enemies[frame]);
			frame ++;
		}
	}
}
