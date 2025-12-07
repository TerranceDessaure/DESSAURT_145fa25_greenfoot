import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BaseWorld is the foundation for all the game levels.
 * 
 * @author Terrance Dessaure
 * @version 12.6.25
 */
public class AaronScreen extends World
{
    /*Fields*/
    private int timer;   
    private boolean shown;
    
    private Spiderman spiderman;
    private GameManager manager;
    
    /*Constructor*/
    /**
     * Constructor for objects of class StoryScreen2.
     * 
     */
    public AaronScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        this.spiderman = spiderman;
        this.manager = manager;
       
        timer = 0;
        shown = false;
        
        LoadingCircle loadingcircle = new LoadingCircle();
        addObject(loadingcircle, 780, 580);
    }// end AaronScreen constructor
    
    public void act()
    {
        if (Greenfoot.isKeyDown("enter")) 
        {
            Greenfoot.setWorld(new SecondLevel(spiderman,manager));
        }// end if
    }// end act method
    
}// end class
