import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StoryScreen3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StoryScreen3 extends World
{
    private int timer = 0;   // counts frames
    private boolean shown = false; // track if text has been added
    /**
     * Constructor for objects of class StoryScreen2.
     * 
     */
    public StoryScreen3()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        LoadingCircle loadingcircle = new LoadingCircle();
        addObject(loadingcircle, 780, 580);
    }
    
    public void act()
    {
         timer++;  // increase every frame
        
        // Greenfoot runs ~60 frames per second, so 5 seconds ≈ 300 frames
        if (timer >= 300 && !shown) 
        {
            addObject(new PressEnter(), getWidth()/2, 580); // bottom middle
            shown = true; // ensure it only adds once
        }
        
        // If Enter is pressed after text is shown, go to next world
        if (shown && Greenfoot.isKeyDown("enter")) 
        {
            Greenfoot.setWorld(new Background()); // replace with your next world class
        }
    }
}
