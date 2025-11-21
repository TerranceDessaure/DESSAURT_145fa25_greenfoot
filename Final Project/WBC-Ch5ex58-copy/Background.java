import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (Terrance Dessaure) 
 * @version (11/10/25)
 */
public class Background extends World
{

    private int frameCounter; // instializes a counter variable
    private int frameInterval;// initializes a varible to count the frames
                               // per second
    private int gemInterval; // initializes a interval that counts the frames
                             // to add the gems
    private int heartInterval;// count frames for hearts
    private Spiderman spiderman;
    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 750x750 cells with a cell size of 1x1 pixels.
        super(750, 750, 1); 
        spiderman = new Spiderman();
        prepare();
    }// end Background constructor
    
    public void act()
    {
      countFrames();
      showText("Health:" + spiderman.getHealth(), 60, 20);
    } // end act method
    
    private void countFrames()
    {
        frameCounter = frameCounter + 1;
        
        if(frameCounter == 60)
        {
            gemInterval = gemInterval + 1;
            heartInterval = heartInterval + 1;
            frameCounter = 0;
        }//end if
        
        if (gemInterval == 15)
        {
            addObject (new Gems(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(750));
            gemInterval = 0; // resets the gem interval to 0
                             // so it count to 15 again and place 
                             // another gem
        }// end if
        
        if (heartInterval == 5)
        {
            addObject (new heart(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(750));
            heartInterval = 0;
        }// end if
    }// end countFrames method 
    
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
