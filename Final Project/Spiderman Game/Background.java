import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (Terrance Dessaure) 
 * @version (11/10/25)
 */
public class Background extends World
{
    // instance variables
    private int frameCounter; // instializes a counter variable
    private int frameInterval;// initializes a varible to count the frames
    // per second
    private int gameFrameCounter;
    private int gemInterval; // initializes a interval that counts the frames
    // to add the gems
    private int heartInterval;// count frames for hearts
    private int spiritInterval;

    private Spiderman spiderman;

    private int health = 100;

    private int gemCounter;
    private int spiritCounter;
    
    private int blessingInterval;
    
    private int time;
    
    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 750x750 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        spiderman = new Spiderman();
        prepare();
    }// end Background constructor

    public void act()
    {
        frameCounter = frameCounter + 1;
        countFrames();
        sendSpirit();
        checkHealth();
        time();
        showTime();
        showText("Health:" + health, 60, 20);
        showText("Gems Collected: " + gemCounter, 95, 40);
    } // end act method

    /**
     * This method transforms the frames into seconds so that we can count
     * and know when objects should appear on the screen. We Know that every act is 60 frames.
     * If every act is 60 frames we want each act to be one second.
     */
    private void countFrames()
    {

        if(frameCounter == 60)
        {
            gemInterval = gemInterval + 1;
            heartInterval = heartInterval + 1;
            spiritInterval++;
            time++;
            blessingInterval++;
            frameCounter = 0;
        }//end if

        if (gemInterval == 15)
        {
            sendGems();
            gemInterval = 0; // resets the gem interval to 0
            // so it count to 15 again and place 
            // another gem
        }// end if
        
        if(blessingInterval == 30)
        {
            sendBlessings();
            blessingInterval = 0;
        }// end if

    }// end countFrames method 
    
    /**
     * Displays time played on the screen
     */
    private void showTime()
    {
        showText("Time Passed:" + time, 81, 60);
    }// end showTime method
    
    public void time()
    {
        gameFrameCounter++;
        if(gameFrameCounter == 60)
        {
            time++;
            frameCounter = 0;
        }// end if
        
    }// end time method
    
    /**
     * Send a Blessing to the world in a random place
     */
    private void sendBlessings()
    {
        addObject (new Blessings(), Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400));
    }// end sendBlessings method

    /**
     * Sends Gems to the world.
     */
    private void sendGems()
    {
        addObject (new Gems(), Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400));    
    }// end sendGems method

    /**
     * If heath does not equal 100 hundred then a heart will pop up every 5 seconds in
     * a random spot on the screen.
     */
    private void checkHealth()
    {
        if (health < 100 && heartInterval >= 5)
        {
           
            addObject (new heart(), Greenfoot.getRandomNumber(580), Greenfoot.getRandomNumber(380));
            heartInterval = 0;
           
        }// end if
    }// end checkHealth Method

    /**
     * Sends the Spirits to the world.
     */
    private void sendSpirit()
    {
        if (getObjects(EvilSpirit.class).size() < 3 && spiritInterval >= 5)
        {
            addSpirit();
            spiritInterval = 0;
        }// end if
    }// end sendSpirit method

    /**
     * takeHealth will subtract whatever amout of damage is neccisary
     * after hitting an ememy or enemy projectile.
     * If the current Health is less than or equal to zero the game will stop.
     */
    public void takeHealth(int damage)
    {
        health = health - damage;
        if  (health <= 0)
        {
            showText("Game Over: You Lose", 300,200);
            Greenfoot.stop();
        }// end if
    }// end takeHealth method

    /**
     * Adds 1 Gem to gem counter
     */
    public void addGems(int gem)
    {
        gemCounter = gemCounter + gem;
        if (gemCounter == 2)
        {
            showWinMessage();
            Greenfoot.setWorld(new SecondLevel(spiderman,health, gemCounter,time));
        }
    }// end addGems method
    
    /**
     * This method will allow the Spirit Counter to earase one count.
     */
    public void subtractSpiritCounter(int erase)
    {
        spiritCounter = spiritCounter - erase;
    }// end subtractSpiritCounter method

    /**
     * Show winning end of game message 
     */
    private void showWinMessage()
    {
        showText("Game Over - You Win!", 300, 200);
    }// end showWinMesage method

    /**
     * addHealth will increase Spidermans health to then given amount.
     */
    public void addHealth(int healing)
    {
        health = health + healing;
    }// end addHealth method

    /**
     * Show current health on the screen.
     */
    private void showHealth()
    {
        showText("Health:" + health, 60, 20);
    }// end showHealth method

    /**
     * This method will adds an Evil Spirit to the world in a ramdom place. We use the numbers 580 and 380
     * so that they do not spawn to close to the edges of the screen.
     */
    private void addSpirit()
    {
        EvilSpirit evilspirit = new EvilSpirit();
        addObject( evilspirit, Greenfoot.getRandomNumber(580), Greenfoot.getRandomNumber(380));
    }// end addSpirit method
    
    private void prepare()
    {
        spiderman = new Spiderman();
        addObject(spiderman, 286, 224);
    }// end prepare method
    
    public Spiderman getSpiderman()
    {
        return spiderman;
    }

}// end class Background
