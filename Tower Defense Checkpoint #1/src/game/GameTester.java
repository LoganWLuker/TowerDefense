/**
 * This class is meant to test the various classes in the game package, using 
 * TestObjects to customize results.
 * @author Bruce Crockett & Logan Luker
 * @version November 21, 2022
 */
package game;

public class GameTester {

	public static void main(String[] args) {
		
		
		State stateTest = new State();

		TestObject[] objectArray = new TestObject[]
		{
				new TestObject(true),
				new TestObject(true),
				new TestObject(false),
				new TestObject(true),
				new TestObject(false)	
		};
		
		/*
		for (TestObject ob : objectArray)
		{
			stateTest.addGameObject(ob);
			System.out.println(ob.isExpired());
		}
		
		stateTest.startFrame();
		System.out.println("startFrame executed");

		stateTest.finishFrame();
		System.out.println("finishFrame executed");

		System.out.println(stateTest.getFrameObjects());
		
		for (GameObject ob : stateTest.getFrameObjects())
		{
			System.out.print(ob.isExpired);
		}
		*/
		
	}

}
