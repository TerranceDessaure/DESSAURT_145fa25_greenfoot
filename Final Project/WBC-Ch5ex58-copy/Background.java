import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (Terrance Dessaure) 
 * @version (a version number or a date)
 */
public class Background extends World
{

    private int frameCounter; // instializes a counter variable
    private int frameInterval; // initializes a varible to count the frames
                               // per second
    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 750x750 cells with a cell size of 1x1 pixels.
        super(750, 750, 1); 
        
        prepare();
    }// end Background constructor
    public void act()
    {
      countFrames();
    } // end act method
    
    private void countFrames()
    {
        frameCounter = frameCounter + 1;
        
        if(frameCounter == 60)
        {
            frameInterval = frameInterval +1;
            frameCounter = 0;
        }
        
        if (frameInterval == 15)
        {
            addObject (new Gems(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(750));
            frameInterval = 0;
        }
    }// end countFrames method 
    
    private void prepare()
    {
        Spiderman spiderman = new Spiderman();
        addObject(spiderman, 286, 224);
    }// end prepare method
    
}// end class Background
