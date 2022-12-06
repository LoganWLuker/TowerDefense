/**
 * State class holds the state of objects
 * prepares the next frame and holds the current frame
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class State 
{
	private List<GameObject> currentFrameGameObjects;
	private List<GameObject> nextFrameGameObjects;
	
	boolean gameOver = false;
	int lives, cash;
	int currentRound;
	int currentFrame; //startFrame ++'s this, towers with fire rate 'n' fire every 'n' frames
	double elapsedTime;
	double totalTime;
	private double lastTime;
	private double currentTime;
	
	/**
	 * default constructor
	 */
	public State ()
	{
		currentFrameGameObjects = new ArrayList<GameObject> ();
        lastTime = System.currentTimeMillis();
        currentFrame = -1;
	}
	/**
	 * get current frame objects
	 * @return current frame objects
	 */
	public List<GameObject> getFrameObjects ()
    {
        return currentFrameGameObjects;
    }
	/**
	 * Start a new frame
	 */
	public void startFrame ()
    {
		currentFrame++;
        nextFrameGameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameGameObjects.addAll(currentFrameGameObjects);  // Add all the current ones to the new list.  This is more clear
        // Set up time calculations
        currentTime = System.currentTimeMillis();
        elapsedTime = (currentTime - lastTime)/1000.0;
        lastTime = currentTime;
        totalTime += elapsedTime;
    }
	/**
	 * Progress forward a frame
	 */
	public void finishFrame ()
    {
		for(GameObject gameObject : currentFrameGameObjects)
		{
			if(gameObject.isExpired())
			{
				nextFrameGameObjects.remove(gameObject);
			}
		}
        currentFrameGameObjects = nextFrameGameObjects;
        nextFrameGameObjects = null;  // I added this -- it makes it clear there is only a current list now.
    }
	/**
	 * add a game object
	 * @param go
	 * 			 object to add
	 */
	public void addGameObject (GameObject go)
    {
        nextFrameGameObjects.add(go);
    }
	public void setLives (int target)
	{
		// check if the result means game over
		if(target <= 0)
		{
			// trigger the boolean and set lives to 0
			gameOver = true;
			target = 0;
		}
		this.lives = target;
	}
	public int getLives () 
	{
		return this.lives;
	}
	
	public void setCash (int target)
	{
		this.cash = target; 
	}
	public int getCash ()
	{
		return this.cash; 
	}
	public double getElapsedTime()
	{
		return this.elapsedTime;
	}
	public double getTotalTime()
	{
		return this.totalTime;
	}
	public int getRound()
	{
		return this.currentRound;
	}
	public void setRound(int target)
	{
		this.currentRound = target;
	}
	public Enemy findNearestFirstEnemy(Point position, int towerRadius)
	{
		Enemy target = null;
		double highestPercent = 0;
		for(GameObject a : nextFrameGameObjects)
		{
			if(a instanceof Enemy)
			{
				Enemy e = (Enemy) a;
				if(e.getPosition().distance(position) <= towerRadius && e.getPercentage() > highestPercent)
				{
					target = e;
					highestPercent = e.getPercentage();
				}
			}
		}
		return target;
	}
	public int getCurrentFrame() { return this.currentFrame; }
}