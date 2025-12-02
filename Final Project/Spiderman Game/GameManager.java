import greenfoot.*; 
/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager
{
    // instance variables - replace the example below with your own
    private int frameCounter;
    private int timeCounter;
    private int gemCounter;
    private int spiritCounter;
    private int health;
    
    /**
     * Constructor for objects of class GameManager
     */
    public GameManager()
    {
        frameCounter = 0;
        timeCounter = 0;
        gemCounter = 0;
        spiritCounter = 0;
        health = 100;
    }// end no-arg GameManager constructor
    
    public GameManager ( int currentTimeFromPreviousScene )
    {
        timeCounter = currentTimeFromPreviousScene;
    }// end overloaded Gamemanager constructor
    
    /**
     * A public "getter" (accessor) method to enable access to the 
     * private `timeCounter`  instance variable
     * 
     * @return the current value of the timeCounter variable
     */
    public int getCurrentTime()
    {
        return timeCounter;
    }
    
    public void setCurrentTime (int timeIncrement)
    {
        frameCounter++;
        if(frameCounter == 60)
        {
            timeCounter += timeIncrement;
            frameCounter = 0;
        }

    }// end method setCurrentTime
    
    
    
    /*Health*/
    public void takeHealth(int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            health = 0;
        }
    }
    
    public void addHealth(int healing)
    {
        health = Math.min(100, health + healing);
    }
    
    public int getHealth()
    {
        return health;
    }
    
    /* Gem Management */
    
    public void addGem(int amount)
    {
        gemCounter += amount;
    }
    
    public int getGemCounter()
    {
        return gemCounter;
    }
    
    /* Spirit Management */
    
    public void addSpiritCounter(int amount)
    {
        spiritCounter += amount;
    }
    
    public void subtractSpiritCounter(int amount)
    {
        spiritCounter -= amount;
    }
    
    public int getSpiritCounter()
    {
        return spiritCounter;
    }
    
    /* Time management */
    
    public void incrementTime()
    {
        timeCounter++;
    }
    
    public int getTime()
    {
        return timeCounter;
    }
    
    /* Win/Lose conditions */
    
    public boolean isGameOver()
    {
        return health <= 0;
    }
    
    public boolean hasWonLevel1()
    {
        return gemCounter >= 2;
    }
}
