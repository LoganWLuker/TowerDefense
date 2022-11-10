package lab01;

public class Compute {

	public static void main(String[] args) 
	{
		System.out.println ("Starting...");

	    int counter = 0;
	    int sum = 0;

	    while (counter < 10)
	    {
	        counter = counter + 1;
		sum = sum + counter;
	    }
	     
	    System.out.println ("Stopping...");
	    System.out.println ("The sum of the numbers from 1 to " + counter
	                        + " is " + sum + ".");

	}

}
