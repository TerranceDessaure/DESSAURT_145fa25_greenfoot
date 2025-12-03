import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartScreen extends World
{

    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
         
        showText("SPIDERMAN VS SPIRITS", getWidth()/2, 150);
        showText("Press enter to Start", getWidth()/2, 250);
        showText("Use WASD to move, Mouse to aim, Click to shoot", getWidth()/2, 300);

    }
    
      public void act() {
        if (Greenfoot.isKeyDown("enter")) 
        {
            Greenfoot.setWorld(new Background()); // switch to your main game world
        }
    }

    
}
