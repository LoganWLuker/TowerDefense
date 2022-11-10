package path;

class Bomb 
{
	boolean isArmed;
	String attachedMethod;
	String location;
	
	public Bomb(boolean isArmed, String attachedMethod, String location)
	{
		this.isArmed = isArmed;
		this.attachedMethod = attachedMethod;
		this.location = location;
	}	
}

public class Krogdor
{
    Bomb krogdorBomb;
    public Krogdor()
    {
        this.krogdorBomb = new Bomb(true, "STRAPPED", "MY CHEST");
    }
    
    public String getPlea()
    {
    	return "THERE IS A BOMB " 
    	+ krogdorBomb.attachedMethod 
    	+ " TO " 
    	+ krogdorBomb.location 
    	+ "!!!";
    }
    
    public static void main(String[] args) 
    {
    	Krogdor krogdor = new Krogdor();
    	System.out.println(krogdor.getPlea());
    }
}
