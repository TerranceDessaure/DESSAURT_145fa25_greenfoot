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
    
    private int gemCounter;
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
    
    /**
     * If heath does not equal 100 hundred then a heart will pop up every 5 seconds in
     * a random spot on the screen.
     */
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
     * If the current Health is less than or equal to zero the game will stop.
     */
    public void takeHealth(int damage)
    {
        health = health - damage;
        if  (health <= 0)
        {
            Greenfoot.stop();
        }// end if
    }// end takeHealth method
    
    /**
     * Adds 1 Gem to gem counter
     */
    public void addGems(int gem)
    {
        gemCounter = gemCounter + gem;
        if (gemCounter == 5)
        {
            showWinMessage();
            Greenfoot.stop();
        }
    }// end addGems method
    
    /**
     * Show winning end of game message 
     */
    private void showWinMessage()
    {
        showText("Game Over - You Win!", 390, 150);
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
    
   
    private void prepare()
    {
        Spiderman spiderman = new Spiderman();
        addObject(spiderman, 286, 224);
        
        EvilSpirit evilspirit = new EvilSpirit();
        addObject( evilspirit, Greenfoot.getRandomNumber(550), Greenfoot.getRandomNumber(550));
        
        EvilSpirit evilspirit2 = new EvilSpirit();
        addObject( evilspirit2, Greenfoot.getRandomNumber(550), Greenfoot.getRandomNumber(350));
        
        EvilSpirit evilspirit3 = new EvilSpirit();
        addObject( evilspirit3, Greenfoot.getRandomNumber(550), Greenfoot.getRandomNumber(350));
    }// end prepare method
    
}// end class Background
