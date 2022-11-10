package lab09;

/**
 * A grid object represents the grid part of a life simulation.
 * The grid object keeps track of the state of each square
 * (alive, dead), and allows the user to make one step in the
 * life simulation.
 * 
 * Note that this class does not do any drawing or other I/O.
 * It just represents a grid.
 * 
 * @author Peter Jensen and (your name here)
 * @version (date here)
 */
public class Grid
{
    // Instance variables here.
	boolean[][] grid;
					 //width(column)
	/*int[][] george = {{1,2, 3, 4 },//height(row)
					  {5,6, 7, 8 },
					  {9,10,11,12}}; */
	//int[height(row)][width(column)]
    
    /**
     * Constructor - creates an empty grid of the
     * specified dimensions.
     * 
     * @param width The width of the new grid
     * @param height The height of the new grid
     */
    public Grid (int width, int height)
    {
    	this.grid = new boolean[height][width];
    }

    /**
     * Returns true if the specified cell in the grid
     * is alive, false otherwise.  If the coordinates
     * are illegal (outside the grid), false is returned.
     * 
     * @param row  a row number
     * @param column a column number
     * @return true iff that cell is alive
     */
    public boolean isAlive (int row, int column)
    {
    	return grid[row][column];  // Stub - replace 
    }
    
    /** 
     * Returns the width of the grid.
     * 
     * @return the width of this grid
     */
    public int getWidth ()
    {
    	return grid[0].length;  // Stub - replace 
    }

    /** 
     * Returns the height of the grid.
     * 
     * @return the height of this grid
     */
    public int getHeight ()
    {
    	return grid.length;  // Stub - replace 
    }
    
    /**
     * Sets the state of the specified cell in the
     * grid.
     * 
     * @param row a row number
     * @param column a column number
     * @param isAlive true if the grid cell should be alive
     */
    public void setCellState(int row, int column, boolean isAlive)
    {
    	grid[row][column] = isAlive;
    }
    
 
    /**
     * Clears the grid, all cells marked as
     * dead.
     */
    public void clear ()
    {
    	grid = new boolean[getHeight()][getWidth()];
    }
    
    /**
     * Performs one 'life' step using the standard rules
     * of life:
     * 
     * Any live cell with fewer than two neighbors dies, as if by loneliness.
     * Any live cell with more than three neighbors dies, as if by overcrowding.
     * Any live cell with two or three neighbors lives, unchanged, to the next generation.
     * Any dead cell with exactly three neighbors comes to life.
     * 
     * Care must be taken to make sure the next generation is kept separate from the 
     * current generation.
     */
    public void stepOneGeneration ()
    {
    	// Create a spare grid
    	boolean[][] newGrid = new boolean[getHeight()][getWidth()];
    	boolean[] isAliveGrid = new boolean[9];
    	int neighbors = 0;
    	
    	// Loop through each grid location
    	  	// Count the neighbors of the current location (using the helper function 8 times)
    	    // Set the alive/dead state in the spare grid according to the rules
    	for(int y = 0; y < getHeight(); y++)
    	{
    		for(int x = 0; x < getWidth(); x++)
    		{
    			isAliveGrid = new boolean[9];
    			neighbors = 0;
//    			if(x == 39 && y == 29)
//    			{
//    				System.out.println("pause");
//    			}
    			//check if it's a corner
    			if(y > 0 && x > 0)
    				isAliveGrid[0] = isAlive(y-1,x-1);
    			
    			if(y < getHeight()-1 && x < getWidth()-1)
    				isAliveGrid[8] = isAlive(y+1,x+1);
    			
    			if(y > 0 && x < getWidth()-1)
    				isAliveGrid[2] = isAlive(y-1,x+1);
    			
    			if(y < getHeight()-1 && x > 0)
    				isAliveGrid[6] = isAlive(y+1,x-1);
    			
    			//check if it's an edge
    			if(y > 0)
    				isAliveGrid[1] = isAlive(y-1,x);
    			
    			if(x > 0)
    				isAliveGrid[3] = isAlive(y,x-1);
    			
    			if(y < getHeight()-1)
    				isAliveGrid[7] = isAlive(y+1,x);
    			
    			if(x < getWidth()-1)
    				isAliveGrid[5] = isAlive(y,x+1);
    			
	    		/*
	    		 * isAliveGrid = new boolean[]{isAlive(y-1,x-1),isAlive(y-1,x),isAlive(y-1,x+1),
	    		 *								isAlive(y,x-1),  false,		    isAlive(y,x+1),
	    		 *								isAlive(y+1,x-1),isAlive(y+1,x),isAlive(y+1,x+1)}; 
	    		 */

    			for(int i = 0; i < isAliveGrid.length; i++)
    			{
    				if(isAliveGrid[i])
    					neighbors ++;
    			}
    			
    			newGrid[y][x] = 
    					neighbors < 2 ? false 
    					: neighbors > 3 ? false 
    					: neighbors == 3 ? true 
    					: isAlive(y,x);

				isAliveGrid = new boolean[9];
    			neighbors = 0;
    		}
    	}
    	
    	// Store the spare grid in the grid field (replacing the old grid with the new one)
    	grid = newGrid;
    }
}
