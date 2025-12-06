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
    private int score;
    

    
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
    
    /**
     * Updates the in-game time counter based on frame progression.
     * 
     * This method increment the `frameCounter` each time it is called.
     * When the counter reaches 60 (60 is chosen because Greenfoot is running at 60 frames per second)
     * it increases the `timeCounter` by a the `timeIncrement` and resets the `frameCounter` back to zero.
     * 
     * @param timeIncrement the amount of time (in seconds) to add to `timeCounter` once 60 frames have passed.
     */
    public void setCurrentTime (int timeIncrement)
    {
        frameCounter++;
        if(frameCounter == 60)
        {
            timeCounter += timeIncrement;
            frameCounter = 0;
        }// end if 

    }// end method setCurrentTime
    
    /*Health*/
    /**
     * Reduces the current health of this actor by the specified famage amount.
     * 
     * If health falls below zero, it is clamped to zero to prevent negative health values. This method
     * does not remove the actor from the world, but it update the health field.
     * 
     * 
     *@param damage the amoint of health to subtract
     */
    public void takeHealth(int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            health = 0;
        }// end if
    }// end takeHealth method
    
    public void addHealth(int healing)
    {
        health = Math.min(100, health + healing);
    }// end addHealth method
    
    public int getHealth()
    {
        return health;
    }// end getHealth method
    
    /* Gem Management */
    /**
     * Increases the player's gem count by the given amount.
     * 
     * This method adds the give `amount` to  the `gemCounter`.
     * that tracks the total number of gems collected. 
     * 
     * @param amount that number of gems to add to the counter
     */
    public void addGem(int amount)
    {
        gemCounter += amount;
    }// end addGem method
    
    /**
     * Gets the current number of gems collected.
     * 
     * This method returns (or `gets`) the value of `gemCounters`, which tracks
     * the total gems accumulated by the player. (plain getter method)
     * 
     * @return the current gem count
     */
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
        return gemCounter >= 4;
    }
    
     public boolean hasWonLevel2()
    {
        return gemCounter >= 8;
    }
    
    public boolean hasWonLevel3()
    {
        return gemCounter >= 12;
    }
    
    public void addScore(int points) 
    {
        score += points;
    }

    public int getScore() 
    {
        return score;
    }


}
