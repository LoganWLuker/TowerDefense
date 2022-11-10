
/**
 * This application simulates a game of life.  The user can
 * draw the initial position and then start the simulation.
 * (See 'Conway's Game of Life' for details.)
 * 
 * @author Peter Jensen  
 * @version Fall 2022
 */
package lab09;

import javax.swing.SwingUtilities;

public class LifeApplication implements Runnable
{

    /**
     * The application entry point.
     * 
     * @param args unused
     */
    public static void main (String[] args)
    {
        // Build an object from this class, then ask
        //    the GUI thread to execute its 'run' method.
        
        SwingUtilities.invokeLater(new LifeApplication());
    }
    
    /** 
     * Code that builds the GUI and starts the application.
     * 
     * For convenience, the application is a JPanel that knows
     * how to build it's own window. 
     * 
     * This run method should only execute from within the GUI thread.
     */    
    public void run ()
    {
        // Create a new LifeSimulation object.  The constructor builds
        //   the GUI when the object is built.  There is nothing
        //   else to do here, as the GUI will deal with events.
        //
        // You don't -have- to store the object in a variable.  We just
        // need its constructor called, so just build one and forget about it.
    	// The object will live as long as the window is open on the
    	// screen.
        
        new LifeSimulation();
    }

}
