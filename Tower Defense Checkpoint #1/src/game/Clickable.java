/**
 * Interface Clickable
 * Allows objects to be clickable
 * 
 * @author Bruce Crockett and Logan Luker
 * @version November 22, 2022
 */
package game;

public interface Clickable 
{
	public boolean consumeClick(int mouseX, int mouseY);
}
