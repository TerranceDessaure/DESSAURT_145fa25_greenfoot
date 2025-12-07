import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StoryScreen2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StoryScreen2 extends World
{
    // Fields
    private int timer;
    private boolean shown;
    
    // Constructor
    /**
     * Constructor for objects of class StoryScreen2.
     * Initializes the timer and shown flag, and adds a loading circle animation.
     * 
     */
    public StoryScreen2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        // intialize fields
        this.timer = 0;
        this.shown = false; 
        
        LoadingCircle loadingcircle = new LoadingCircle();
        addObject(loadingcircle, 780, 580);
    }// end StoryScreen constructor
    
    //Methods
    /**
     * Called every frame. Updates the timer, shows the "Press Enter" prompt
     * after 5 seconds, and transitions to StoryScreen3 when Enter is pressed.
     */
    public void act()
    {
        timer++;
        if (timer >= 300 && !shown) 
        {
            addObject(new PressEnter(), getWidth()/2, 580);
            shown = true; 
        }// end if
        
        if (shown && Greenfoot.isKeyDown("enter")) 
        {
            Greenfoot.setWorld(new StoryScreen3()); // replace with your next world class
        }// end if
    }// end act method
}// end StoryScreen2 class
