package game;

import java.util.ArrayList;
import java.util.List;

public class State 
{
	List<GameObject> currentFrameGameObjects;
	List<GameObject> nextFrameGameObjects;
	
	public State ()
	{
		currentFrameGameObjects = new ArrayList<GameObject> ();
	}
	
	public List<GameObject> getFrameObjects ()
    {
        return currentFrameGameObjects;
    }
	
	public void startFrame ()
    {
        nextFrameGameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameGameObjects.addAll(currentFrameGameObjects);  // Add all the current ones to the new list.  This is more clear
    }
	
	public void finishFrame ()
    {
        currentFrameGameObjects = nextFrameGameObjects;
        nextFrameGameObjects = null;  // I added this -- it makes it clear there is only a current list now.
    }
	
	public void addGameObject (GameObject go)
    {
        nextFrameGameObjects.add(go);
    }
}
