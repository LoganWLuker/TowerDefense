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
		//read the round file to check the size
		int roundSize = roundScanner.nextInt();
		String enemy;
		//create GameObject array
		enemies = new GameObject[roundSize];
		//create quantity array
		quantities = new int[roundSize];
		//Scan the file and set the array values based on it
		for(int i = 0; roundScanner.hasNext(); i++)
		{
			quantities[i] = roundScanner.nextInt();
			enemy = roundScanner.next();
			//check if Krogdor
			if(enemy.equalsIgnoreCase("Krogdor"))
			{
				//Account for quantities higher than 1
				for(int c = 0; c < quantities[i]; c++)
				{
					enemies[i+c] = new Krogdor(this.state, this.control);
					i+=c;
				}
			}
			//check if Snail
			else if(enemy.equalsIgnoreCase("Snail"))
			{			
				//Account for quantities higher than 1
				for(int c = 0; c < quantities[i]; c++)
				{
					enemies[i+c] = new Snail(this.state, this.control);
					i+=c;
				}
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
