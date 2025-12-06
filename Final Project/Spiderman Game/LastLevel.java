import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *The LastLevel class is the final stage of the game.
 *It manages timed spawing of gems, blessings, heard, and spirits,
 *tracks player and health and progression through the GameManagers,
 *and determines win/lose conditions
 * 
 * @author Terrance Dessaure
 * @version 11/29/2025
 */
public class LastLevel extends BaseWorld
{
    //Fields
    private Spiderman spiderman;
    private GameManager myGameManager;

    private int frameCounter;
    private int gemInterval;
    private int heartInterval;
    private int spiritInterval;
    private int blessingInterval;

    //Constructors
    /**
     * Constructs the LastLevel world with a given Spiderman and GameManager.
     * This allows the player to keep the same state and score across worlds.
     * 
     * @param spiderman the player character
     * @param myGameManager the game manager tracking score, health, and progress
     */
    public LastLevel(Spiderman spiderman, GameManager myGameManager)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, spiderman, myGameManager); 
        this.spiderman = spiderman;
        this.myGameManager = myGameManager;
        
        addObject(spiderman, 400, 300); 

    }// end LastLevel Constructor
    
    //Methods
    /**
     * Called every frame. Updates counters, spawns objects at intervals,
     * checks health, and displays HUD information.
     */
    public void act()
    {
        frameCounter++;
        countFrames();
        sendSpirit();
        checkHealth();
        showHUD();
    }// end act method

    /**
     * Converts frames into second and triggers timed events.
     * Every 60 frames = 1 second.
     * - Gems spawn every 5 seconds.
     * -Blassing spawn every 10 seconds.
     */
    private void countFrames()
    {
        if (frameCounter == 60)
        {
            gemInterval++;
            heartInterval++;
            spiritInterval++;
            blessingInterval++;

            myGameManager.incrementTime();
            frameCounter = 0;
        }// end if

        if (gemInterval == 5)
        {
            sendGems();
            gemInterval = 0;
        }// end if

        if (blessingInterval == 10)
        {
            sendBlessings();
            blessingInterval = 0;
        }// end if
    }// end countFrames method

    /**
     * Spawns a random blessing in the world
     * Blassoing grant temporay abilities to Spiderman.
     */
    private void sendBlessings() 
    {
        String[] types = { "doubleDamage", "speedBoost", "rapidFire"};
        String chosen = types[Greenfoot.getRandomNumber(types.length)];

        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());

        addObject(new Blessings(chosen), x, y);

    }// end sendBlessing method

    /**
     * Spawns a gem in a random location.
     * Gems contribute to score and progression
     */
    private void sendGems() 
    {
        addObject(new Gems(), Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400));
    }// end sendGems method
    
    /**
     * Spawn a heart if health is below 100.
     * Hears restore health and appear every 5 seconds.
     */
    private void checkHealth() 
    {
        if (myGameManager.getHealth() < 100 && heartInterval >= 5) 
        {
            addObject(new Heart(), Greenfoot.getRandomNumber(580), Greenfoot.getRandomNumber(380));
            heartInterval = 0;
        }// end if
    }// end checkHealth method

    /**
     * Spawns an EvilSpirit if fewer than 5 exist and 5 seconds have passed.
     */
    private void sendSpirit() 
    {
        if (getObjects(EvilSpirit.class).size() < 5 && spiritInterval >= 5) 
        {
            EvilSpirit evilspirit = new EvilSpirit();
            addObject(evilspirit, Greenfoot.getRandomNumber(580), Greenfoot.getRandomNumber(380));
            spiritInterval = 0;
        }// end if
    }// end sendSpirit method
    
    /**
     * Adds a gem to the manager's counter.
     * If the player has won Level 3, transitions to the win screen.
     */
    public void addGem() 
    {
        if (manager.hasWonLevel3()) 
        {
            Greenfoot.setWorld(new GameOverWin());
            Greenfoot.stop();
        }// end if
    }// end addGem method

    /**
     * Reduces player health by the given damage amount.
     * Ends the game if health reaches zero.
     * 
     * @param damage the amount of health to subtract
     */
    public void takeHealth(int damage)
    {
        myGameManager.takeHealth(damage);
        if (myGameManager.isGameOver()) 
        {
            Greenfoot.setWorld(new GameOver());
            Greenfoot.stop();
        }// end if
    }// end takeHealth method

    /**
     * Returns the GameManager for this level.
     * 
     * @return the player character
     */
    public GameManager getManager()
    {
        return myGameManager;
    }// end getter

    /**
     * Returns the Spiderma player character.
     * 
     * @return the player character
     */
    public Spiderman getSpiderman()
    {
        return spiderman;
    }// end getter
}// end LastLevel class
