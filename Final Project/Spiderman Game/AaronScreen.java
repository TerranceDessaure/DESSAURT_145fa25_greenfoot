import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AaronScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AaronScreen extends World
{

    private int timer = 0;   // counts frames
    private boolean shown = false; // track if text has been added
    
    private Spiderman spiderman;
    private GameManager manager;

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
