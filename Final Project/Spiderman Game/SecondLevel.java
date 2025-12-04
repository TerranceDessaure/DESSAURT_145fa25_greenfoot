import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SecondLevel here.
 * 
 * @author Terrance 
 * @version 11/29/2025
 */
public class SecondLevel extends BaseWorld
{
    private Spiderman spiderman;
    private GameManager myGameManager;

    private int frameCounter;
    private int gemInterval;
    private int heartInterval;
    private int spiritInterval;
    private int blessingInterval;

    /**
     * Constructor for objects of class SecondLevel.
     * 
     */
    public SecondLevel(Spiderman spiderman, GameManager myGameManager)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 1000, 1, spiderman, myGameManager); 
        this.spiderman = spiderman;
        this.myGameManager = myGameManager;

        addObject(spiderman, 300, 200); 

        // showText("Time:" + myGameManager.getCurrentTime(), 150, 80);
    }

    public void act()
    {
        frameCounter++;
        countFrames();
        sendSpirit();
        checkHealth();
        showHUD();
    }

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
        }

        if (gemInterval == 5)
        {
            sendGems();
            gemInterval = 0;
        }

        if (blessingInterval == 10)
        {
            sendBlessings();
            blessingInterval = 0;
        }

    }

    private void sendBlessings() 
    {
        String[] types = { "doubleDamage", "speedBoost", "rapidFire"};
        String chosen = types[Greenfoot.getRandomNumber(types.length)];

        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());

        addObject(new Blessings(chosen), x, y);

    }
    

    private void sendGems() 
    {
        addObject(new Gems(), Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400));
    }

    private void checkHealth() 
    {
        if (myGameManager.getHealth() < 100 && heartInterval >= 5) 
        {
            addObject(new heart(), Greenfoot.getRandomNumber(580), Greenfoot.getRandomNumber(380));
            heartInterval = 0;
        }
    }

    private void sendSpirit() 
    {

        if (getObjects(EvilSpirit.class).size() < 3 && spiritInterval >= 5) 
        {
            EvilSpirit evilspirit = new EvilSpirit();
            addObject(evilspirit, Greenfoot.getRandomNumber(580), Greenfoot.getRandomNumber(380));
            spiritInterval = 0;
        }
    }
    
    public void addGem()
    {
        if (manager.getGemCounter() == 12)
        {
            showText("Level 2 complete!", 300, 200);
            Greenfoot.stop();
        }
    }
    
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
            showText("Game Over: You Lose", 300,200);
            Greenfoot.stop();
        }// end if
    }// end takeHealth method
    
    public GameManager getManager()
    {
        return myGameManager;
    }
    
    public Spiderman getSpiderman()
    {
        return spiderman;
    }

}
