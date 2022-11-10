/*
 * A collection of unit tests for Assignment #7.  To make this work, you'll need to 
 * follow a few steps to get JUnit tests set up in your project.  Do this:
 * 
 *     + Make sure the Package Explorer view tab is visible in Eclipse.
 *           (You can always activate view tabs under Window->Show View.)
 *           
 *     + Right-click the ArrayExercises class, select New -> JUnit Test Case     
 *         - For the name of the test class, choose something that will not collide
 *           with this class name.  (You can delete it later, so "Deleteme" would be fine.)
 *         - Use JUnit Jupiter test if it is available, otherwise use JUnit 4.
 *         - 'Finish' this dialog.
 *         
 *     + Eclipse will warn you that JUnit 5 is not on the build path, and it will give
 *       you the option of adding it.  (We need it, it's the entire point of these steps.)
 *         - Hit 'OK' to complete the dialog.
 *         
 *     + You can delete the new class it created, or you can add unit tests to it.
 *         - You won't submit that class.
 *         
 *     + Copy this class (ArrayExercisesTest) into your assignment07 folder (if you
 *       have not done so already).  As long as JUnit 4 or JUnit 5 is set up in your
 *       project, the tests should run fine!
 *       
 *  Peter Jensen
 *  
 * Modified by:
 * @author ***Put your name here***
 * @version ***Put the date here***
 */
package assignment07;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Arrays;  // OK in tests, not allowed in ArrayExercises

// Imports for JUnit 4 (currently unused)

// import static org.junit.Assert.*;
// import org.junit.Test;

// Imports for JUnit 5

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/* I generated unit tests for all the functions in ArrayExercises.
 * To run the tests, just run this code.
 */
public class ArrayExercisesTest
{
	/* This is my first example unit test.  A unit test is just a piece of
	 * code that calls some method or builds some object, and tests 
	 * for errors.  The code/test design is up to the programmer.  
	 * 
	 * The unit test library provides functions for 'asserting' correctness.
	 * I use assertEquals below.  If the two values are not equal, the
	 * unit test library throws an exception and records a failure.
	 * 
	 * There are other assert functions in the unit test library that are
	 * useful for noting errors.  If the function below completes without
	 * recording a failure, then the test is marked as a success.
	 */
	@Test
	public void testRandomArray01()
	{
		// Call the function, ask for an array of thirty elements.
		
		int[] result;
		int size = 30;
		
		result = ArrayExercises.randomArray(size);  // This calls the function I'm testing.
		
		// The function has a specific contract that guarantees things
		//  about it's return value.  Check to make sure the method
		//  call did what it was supposed to.
		
		// Make sure the size of the result array is correct.
		
		assertEquals(size, result.length);  // If unequal, an error is recorded.
		
		// Make sure the required numbers [0..n-1] are in the array.
		
		numberLoop:  for (int n = 0; n < size; n++)  // Notice that I labeled this loop
		{
			// Search for n in the array.  When found, move on to the next n.
			
			for (int pos = 0; pos < size; pos++)
				if (result[pos] == n)
					continue numberLoop;  // Found it, continue the outer loop.
			
			// If we get this far, the number n was not found.  This is an error.
			
			fail("Number missing from random array: " + n + " in " + Arrays.toString(result));  // Record an error 	
		}		
		
		// If the number loop completes without failing, all tests pass!  
		//   (When this method ends normally, the test is marked as passing.)
	}

	/* I wanted two unit tests for my function.  The first one, above,
	 *   just makes sure the basic operation of 'randomArray' is 
	 *   correct.  This second unit test makes sure the 'randomness'
	 *   is correct.  A truly random shuffle has equal likelihood
	 *   of any outcome.  I repeatedly generate random arrays,
	 *   then I count up results, and then check to make
	 *   sure that each outcome occurred with similar probability.
	 *   
	 * Because of the fact that random numbers may produce results
	 *   that look uneven, I loop many times to reduce the likelihood
	 *   of random results looking like an error. 
	 *   
	 * I do not expect students to study this code - it is poor code.
	 *   (I don't like the way I'm counting permutations.)  There
	 *   are better ways, but you haven't seen the required lectures
	 *   yet, so I'm using a more primitive solution.  I expect your
	 *   unit tests to be much less complex.
	 */ 
	@Test
	public void testRandomArray02()
	{
		// An array of three has six permutations.
		
		// Counts of how many times each permutation appears.
		
		int count012 = 0;
		int count021 = 0;
		int count102 = 0;
		int count120 = 0;
		int count201 = 0;
		int count210 = 0;
		
		// Repeatedly generate arrays (1,000,000 times).
		
		for (int count = 0; count < 1_000_000; count++)
		{
			// Generate an array of 3 elements.
			
			int[] result = ArrayExercises.randomArray(3);  // This calls the function I'm testing.
			
			// Keep counts of each permutation in the array.
			
			if (result[0] == 0 && result[1] == 1)       // [0, 1, 2]
				count012++;
			else if (result[0] == 0 && result[1] == 2)  // [0, 2, 1]
				count021++;
			else if (result[0] == 1 && result[1] == 0)  // [1, 0, 2]
				count102++;
			else if (result[0] == 1 && result[1] == 2)  // [1, 2, 0]
				count120++;
			else if (result[0] == 2 && result[1] == 0)  // [2, 0, 1]
				count201++;
			else // only other possibility is [2, 1, 0]
				count210++;
		}
		
		// Check each permutation.  It should occur 166,666 times on average.  Accept
		//   anything within +/- 3,000.
		
		if (Math.abs(166_666 - count012) > 3_000)
		    fail("Permutation [0, 1, 2] appears an unexpected number of times:  " + count012); 	
		
		if (Math.abs(166_666 - count021) > 3_000)
		    fail("Permutation [0, 1, 2] appears an unexpected number of times:  " + count021); 	
		
		if (Math.abs(166_666 - count102) > 3_000)
		    fail("Permutation [0, 1, 2] appears an unexpected number of times:  " + count102); 	
		
		if (Math.abs(166_666 - count120) > 3_000)
		    fail("Permutation [0, 1, 2] appears an unexpected number of times:  " + count120); 	
		
		if (Math.abs(166_666 - count201) > 3_000)
		    fail("Permutation [0, 1, 2] appears an unexpected number of times:  " + count201); 	
		
		if (Math.abs(166_666 - count210) > 3_000)
		    fail("Permutation [0, 1, 2] appears an unexpected number of times:  " + count210); 	
		
		// If execution completes without failing, the test passes!  
		//   (When this method ends normally, the test is marked as passing.)
	}

	
	@Test
	public void testReverseOrder01()
	{
		// Set up a char[], call the ArrayExercises.reverse(...) function.
		// Test the array to make sure the function did its job.
		char[] initial = {'a','b','c','d','e'};
		char[] result = {'e','d','c','b','a'};
		
		ArrayExercises.reverseOrder(initial);
		
		assertArrayEquals(result, initial);
		
		if(!Arrays.equals(initial, result))
			fail("Array result is not equal to reverse of given Array");
	}
	
	@Test
	public void testReverseOrder02()
	{
		// Set up a char[], call the ArrayExercises.reverse(...) function.
		// Test the array to make sure the function did its job.
		char[] initial = {'1','2','3','4','5'};
		char[] result = {'5','4','3','2','1'};
		
		ArrayExercises.reverseOrder(initial);
		
		assertArrayEquals(result, initial);
		
		if(!Arrays.equals(initial, result))
			fail("Array result is not equal to reverse of given Array");
	}
	@Test
	public void testReverseOrder03()
	{
		// Set up a char[], call the ArrayExercises.reverse(...) function.
		// Test the array to make sure the function did its job.
		char[] initial = {'a','p','p','l','e'};
		char[] result = {'e','l','p','p','a'};
		
		ArrayExercises.reverseOrder(initial);
		
		assertArrayEquals(result, initial);
		
		if(!Arrays.equals(initial, result))
			fail("Array result is not equal to reverse of given Array");
	}
	/* Note:  You'll want more unit test functions.  Cut-and-paste existing tests, but increase
	 * the number in the function names.  Make sure to include @test before each function header.
	 * 
	 * testReverseOrder01
	 * testReverseOrder02
	 * testReverseOrder03  ...etc...
	 * 
	 * You can then put different test code in each test to be thorough.
	 */

	@Test
	public void testCount01()
	{
		Object[] things = {6.0, "6", 2,'3', 5.0, 6L, 6, 6, 6, "s", "f", null, null};
		int result;
		
		result = ArrayExercises.count(things, null);
		
		if(result != 2)
			fail("The returned number does not match expected number of target objects");
	}

	@Test
	public void testReplace01()
	{
		String[] list = {"Hello","Hello","oh boy",null,"cheese","45","Hello"};
		String original = null;
		String replacement = "Goodbye";
		String[] ExpectResult = {"Hello","Hello","oh boy","Goodbye","cheese","45","Hello"};
		
		ArrayExercises.replace(list, original, replacement);
		
		if(!Arrays.equals(list, ExpectResult))
			fail("list result was not equal to expected");
	}

	@Test
	public void testComputeAreas01()
	{
		double[] widths = {1,2,3,4,5};
		double[] heights = {2,3,4,5,6};
		double[] expectResult = {2,6,12,20,30};
		double[] result;
		
		result = ArrayExercises.computeAreas(widths, heights);
		
		if(!Arrays.equals(expectResult, result))
			fail("Result Array doesn't equal expected areas");
	}

	@Test
	public void testRemove01()
	{
		Color target = Color.blue;
		Color[] pixels = {Color.black,Color.blue,Color.green,Color.blue};
		Color[] expectResult = {Color.black,Color.green};
		Color[] result;
		
		result = ArrayExercises.remove(pixels, target);
		
		if(!Arrays.equals(expectResult, result))
			fail("Resulting Array does not equal expected");
	}

	@Test
	public void testSort01()
	{
		int[] data = {5,6,8,1,2,4,9,7,3,3};
		int[] expectResult = {9,8,7,6,5,4,3,3,2,1};
		
		ArrayExercises.sort(data);
		
		if(!Arrays.equals(expectResult, data))
			fail("Array did not sort correctly");
	}

	@Test
	public void testFindSmallest01()
	{
		Rectangle[] rectangles = {new Rectangle(0,0,2,2),new Rectangle(1,0,3,3), new Rectangle(1,0,20,20), new Rectangle(1,0,4,4), new Rectangle(1,0,5,5)};
		Rectangle expectResult = new Rectangle(0,0,2,2);
		Rectangle result;
		
		result = ArrayExercises.findSmallest(rectangles);
		
		
		if(!expectResult.equals(result))
			fail("Resulting rectangle did not equal expected");
	}
	
	@Test
	public void testHistogram01()
	{
		int[] data = {1,1,1,2,2,3,3,3,3,3,4,4,7,7,7,5,8,9,9};
		int[] expectResult = {0,3,2,5,2,1,0,3,1,2};
		int[] result;
		
		result = ArrayExercises.histogram(data);
		
		if(!Arrays.equals(expectResult, result))
			fail("Resulting Array did not match expected histogram");
	}
}
