import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The SecondLevel class represents the second stage of the game.
 * It manages timed spawning of gems, blessings, hearts, and spirits,
 * tracks player health and progression through the GameManager,
 * and determines when to transition to the next loading screen.
 * 
 * @author Terrance 
 * @version 11/29/2025
 */
public class SecondLevel extends BaseWorld
{
    //fields
    private Spiderman spiderman; // the player character
    private GameManager myGameManager; // tracks programs

    private int frameCounter;
    private int gemInterval;
    private int heartInterval;
    private int spiritInterval;
    private int blessingInterval;

    // Constructor
    /**
     * Constructs the SecondLevel world with a given Spiderman and GameManager.
     * This allows persistence of player state and score across worlds.
     *
     * @param spiderman     the player character
     * @param myGameManager the game manager tracking score, health, and progression
     */

    public SecondLevel(Spiderman spiderman, GameManager myGameManager)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, spiderman, myGameManager); 
        this.spiderman = spiderman;
        this.myGameManager = myGameManager;

        addObject(spiderman, 300, 200); 
    }// end contructor

    /**
     * Called every frame. Updates counters, spawns obejctes at intervals
     * checks health, and shows HUD information.
     */
    public void act()
    {
        frameCounter++;
        countFrames();
        sendSpirit();
        checkHealth();
        showHUD();
    }// end act

    /**
     * Converts frames into seconds and triggers timed events.
     * Every 60 frames = 1
     * - Gems spawn every 5 seconds
     * - Bkessings spawn every 10 seconds.
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
    }// end countFrames menthod

    /**
     * Spawns a random blessing in the world.
     * Blessings grant temporary abilities to Spiderman.
     */
    private void sendBlessings() 
    {
        String[] types = { "doubleDamage", "speedBoost", "rapidFire"};
        String chosen = types[Greenfoot.getRandomNumber(types.length)];

        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());

        addObject(new Blessings(chosen), x, y);

    }// end sendBlessings method

    /**
     * Spawns a gem in a random location.
     * Gems contribute to score and progression.
     */
    private void sendGems() 
    {
        addObject(new Gems(), Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400));
    }// end sendGems

     /**
     * Spawns a heart if health is below 100.
     * Hearts restore health and appear every 5 seconds.
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
     * Spawns an EvilSpirit if fewer than 4 exist and 5 seconds have passed.
     */
    private void sendSpirit() 
    {
        if (getObjects(EvilSpirit.class).size() < 4 && spiritInterval >= 5) 
        {
            EvilSpirit evilspirit = new EvilSpirit();
            addObject(evilspirit, Greenfoot.getRandomNumber(580), Greenfoot.getRandomNumber(380));
            spiritInterval = 0;
        }// end if
    }// end sendSpirit method
    
     /**
     * Adds a gem to the manager’s counter.
     * If the player has won Level 2, transitions to the next loading screen.
     */
    public void addGem() 
    {
        if (manager.hasWonLevel2())
        {
            
            Greenfoot.setWorld(new LoadingScreen2(getSpiderman(), getManager()));
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
     * @return the game manager
     */
    public GameManager getManager()
    {
        return myGameManager;
    }// end GameManager getter

    /**
     * Returns the Spiderman player character.
     *
     * @return the player character
     */
    public Spiderman getSpiderman()
    {
        return spiderman;
    }// end GameManage
    
}// end Second LevelCkass
