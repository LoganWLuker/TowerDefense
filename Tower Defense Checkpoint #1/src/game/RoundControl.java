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
	public void startRound(int roundNum)
	{
		ClassLoader myLoader = this.getClass().getClassLoader();
		InputStream roundStream = myLoader.getResourceAsStream("resources/round_" + roundNum + ".txt");
		Scanner roundScanner = new Scanner(roundStream);
		
		//read the round file to check the size
		int roundSize = roundScanner.nextInt();
		String enemy;
		int wait = 0;
		//create GameObject array
		enemies = new GameObject[roundSize];
		//create quantity array
		quantities = new int[roundSize];
		int quantity;
		int repeat;
		int i;
		//Scan the file and set the array values based on it
		for(i = 0; roundScanner.hasNext(); i++)
		{
			repeat = roundScanner.nextInt();
			if (repeat < 0)
			{
				int z;
				quantity = roundScanner.nextInt();
				for(z = 0; quantity > 0; z++)
				{
					quantities[i] = quantity;
					enemy = roundScanner.next();
					
					if(!enemy.equalsIgnoreCase("wait"))
					{
						z++;
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
					quantity = roundScanner.nextInt();
					i++;
				}
				repeat ++;
				int zCount = 0;
				for(i = i; repeat < 0; i++)
				{
					if(!(enemies[i-z] == null))
						enemies[i] = control.getEnemy(enemies[i-z].toString());
					else
						enemies[i] = enemies[i-z];
					quantities[i] = quantities[i-z];
					zCount ++;
					if(zCount == z)
					{
						repeat ++;
						zCount = 0;
					}
				}
				if(roundScanner.hasNext())
					quantities[i] = roundScanner.nextInt();
				
			}else
				quantities[i] = repeat;
			if(roundScanner.hasNext())
				enemy = roundScanner.next();
			else
				return;
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
