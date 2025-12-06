import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (Terrance Dessaure) 
 * @version (11/10/25)
 */
public class Background extends BaseWorld
{
    /* FIELDS */

    private int frameCounter; // instializes a counter variable
    private int frameInterval;// initializes a varible to count the frames
    // per second
    private int gameFrameCounter;
    private int gemInterval; // initializes a interval that counts the frames
    // to add the gems
    private int heartInterval;// count frames for hearts
    private int spiritInterval;
    private int blessingInterval;

    private int time;
    private int spiritCounter;

    public Background() 
    {
        this(new Spiderman(), new GameManager()); 
    }

    public Background(Spiderman spiderman, GameManager manager) 
    {
        super(800, 600, 1, spiderman, manager);
    }

    public void act()
    {
        frameCounter = frameCounter + 1;
        countFrames();
        sendSpirit();
        checkHealth();
        showHUD();
        manager.setCurrentTime(1);
        //showText("" + myGameManager.getCurrentTime(), 150, 80);
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

        if (gemInterval == 10)
        {
            sendGems();
            gemInterval = 0; // resets the gem interval to 0
            // so it count to 15 again and place 
            // another gem
        }// end if

        if(blessingInterval == 5)
        {
            sendBlessings();
            blessingInterval = 0;
        }// end if

    }// end countFrames method 

    /**
     * Send a Blessing to the world in a random place
     */
    private void sendBlessings()
    {
        String[] types = { "doubleDamage", "speedBoost", "rapidFire"};
        String chosen = types[Greenfoot.getRandomNumber(types.length)];

        int x = Greenfoot.getRandomNumber(700);
        int y = Greenfoot.getRandomNumber(500);

        addObject(new Blessings(chosen), x, y);

    }// end sendBlessings method

    /**
     * Sends Gems to the world.
     */
    private void sendGems()
    {
        addObject (new Gems(), Greenfoot.getRandomNumber(700), Greenfoot.getRandomNumber(500));    
    }// end sendGems method

    /**
     * If heath does not equal 100 hundred then a heart will pop up every 5 seconds in
     * a random spot on the screen.
     */
    private void checkHealth()
    {
        if (manager.getHealth() < 100 && heartInterval >= 10)
        {

            addObject (new Heart(), Greenfoot.getRandomNumber(700), Greenfoot.getRandomNumber(500));
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
        manager.takeHealth(damage);
        if  (manager.isGameOver())
        {
            Greenfoot.setWorld(new GameOver());
            Greenfoot.stop();
           
        }// end if
    }// end takeHealth method

    /**
     * Adds 1 Gem to gem counter
     */
    public void addGem()
    {

        if (manager.hasWonLevel1())
        {
            showWinMessage();
            Greenfoot.setWorld(new LoadingScreen1(getSpiderman(), getManager()));
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
        showText("Game Over - You Win!", 400, 300);
    }// end showWinMesage method

    /**
     * This method will adds an Evil Spirit to the world in a ramdom place. We use the numbers 580 and 380
     * so that they do not spawn to close to the edges of the screen.
     */
    private void addSpirit()
    {
        EvilSpirit evilspirit = new EvilSpirit();
        addObject( evilspirit, Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(550));
    }// end addSpirit method

}// end class Background
