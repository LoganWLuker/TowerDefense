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
	RoundControl (State state, Control control) 
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
	public void startRound(int roundNum)
	{
		ClassLoader myLoader = this.getClass().getClassLoader();
		InputStream roundStream = myLoader.getResourceAsStream("resources/round_" + roundNum + ".txt");
		Scanner roundScanner = new Scanner(roundStream);
		//uncomment to test round length
		
//		int total = 0;
//		int addThis = 0;
//		int lastInt;
//		int currentInt = 0;
//		String lastWord = "";
//		String currentWord;
//		for(int z = 0; roundScanner.hasNext(); z++)
//		{
//			addThis = roundScanner.nextInt();
//			lastInt = currentInt;
//			currentInt = addThis;
//			total += addThis;
//			currentWord = roundScanner.next();
//			if(currentWord.equalsIgnoreCase("wait"))
//			{
//				total -= addThis;
//				lastWord = currentWord;
//			}
//		}
//		System.out.println(total);
		
		//read the round file to check the size
		int roundSize = roundScanner.nextInt();
		String enemy;
		int wait = 0;
		//create GameObject array
		enemies = new GameObject[roundSize];
		//create quantity array
		quantities = new int[roundSize];
		int quantity;
		//Scan the file and set the array values based on it
		for(int i = 0; roundScanner.hasNext(); i++)
		{
			quantities[i] = roundScanner.nextInt();
			enemy = roundScanner.next();
			quantity = quantities[i];
			if(!enemy.equalsIgnoreCase("wait"))
			{
				wait = roundScanner.nextInt();
				roundScanner.next();
				
				//Add Enemies according to their quantity
				for(int c = 0; c < quantity; c++)
				{
					enemies[i+c] = control.getEnemy(enemy);
					quantities[i+c+1] = wait;
					i+=1;
				}
				i+=quantity-1;
			}
		}
		roundScanner.close();
	}
	/**
	 * iterate through the array and do what it says
	 * wait, or create an enemy
	 */
	public void doNextFrame()
	{
		//if we're past the instructions, stop reading them
		if(frame+1 > enemies.length)
			return;
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
