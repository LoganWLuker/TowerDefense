/**
 * State class holds the state of objects
 * prepares the next frame and holds the current frame
 *
 * @author  Logan Luker & Bruce Crockett
 * @version 11/15/2022
 */
package game;

import java.util.ArrayList;
import java.util.List;

public class State 
{
	List<GameObject> currentFrameGameObjects;
	List<GameObject> nextFrameGameObjects;
	/**
	 * default constructor
	 */
	public State ()
	{
		currentFrameGameObjects = new ArrayList<GameObject> ();
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
        nextFrameGameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameGameObjects.addAll(currentFrameGameObjects);  // Add all the current ones to the new list.  This is more clear
    }
	/**
	 * Progress forward a frame
	 */
	public void finishFrame ()
    {
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
}