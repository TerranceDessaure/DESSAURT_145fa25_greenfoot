import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The StoryScreen2 class represents a transitional story screen in the game.
 * It shows the loading ciricle animation and, after short delay,
 * prompts  the player to press Enter to continue to the next story screen.
 * 
 * @author Terrance Dessaure 
 * @version 12.6.25
 */
public class StoryScreen3 extends World
{
    // Fields
    private int timer;
    private boolean shown;
    
    // Constructor
    /**
     * Constructor for objects of class StoryScreen2.
     *  Initializes the timer and shown flag, and adds a loading circle animation.
     */
    public StoryScreen3()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        //initialized fields
        this.timer = 0; 
        this.shown = false;
        
        LoadingCircle loadingcircle = new LoadingCircle();
        addObject(loadingcircle, 780, 580);
    }// end StoryScreen3 constructor
    
    //Methods
    /**
     * Constructs the StoryScreen2 world.
     * Initializes the timer and shown flag, and adds a loading circle animation.
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
            Greenfoot.setWorld(new Background());// transition to start of the game
        }// end if
    }// end act method
}// end StoryScreen3 class
