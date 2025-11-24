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
    private int gemInterval; // initializes a interval that counts the frames
                             // to add the gems
    private int heartInterval;// count frames for hearts
    private Spiderman spiderman;
    
    private int health = 100;
    
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
      checkHealth();
      showText("Health:" + health, 60, 20);; 
    } // end act method
    
    private void countFrames()
    {
        
        if(frameCounter == 60)
        {
            gemInterval = gemInterval + 1;
            heartInterval = heartInterval + 1;
            frameCounter = 0;
        }//end if
        
        if (gemInterval == 15)
        {
            addObject (new Gems(), Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400));
            gemInterval = 0; // resets the gem interval to 0
                             // so it count to 15 again and place 
                             // another gem
        }// end if
    }// end countFrames method 
    
    private void checkHealth()
    {
        if (health != 100)
        {
            if (heartInterval == 5)
            {
                addObject (new heart(), Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400));
                heartInterval = 0;
            }// end if
        }// end if
    }// end checkHealth Method
    
    /**
     * takeHealth will subtract whatever amout of damage is neccisary
     * after hitting an ememy or enemy projectile.
     * If the current Health is less than or equal to zero the game will stop
     */
    public void takeHealth(int damage)
    {
        health = health - damage;
        showHealth();
        if  (health <= 0)
        {
            Greenfoot.stop();
        }// end if
    }// end takeHealth method
    
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
    
    private void prepare()
    {
        Spiderman spiderman = new Spiderman();
        addObject(spiderman, 286, 224);
        
        EvilSpirit evilspirit = new EvilSpirit();
        addObject( evilspirit, Greenfoot.getRandomNumber(700), Greenfoot.getRandomNumber(700));
        
        EvilSpirit evilspirit2 = new EvilSpirit();
        addObject( evilspirit2, Greenfoot.getRandomNumber(700), Greenfoot.getRandomNumber(700));
        
        EvilSpirit evilspirit3 = new EvilSpirit();
        addObject( evilspirit3, Greenfoot.getRandomNumber(700), Greenfoot.getRandomNumber(700));
    }// end prepare method
    
}// end class Background
