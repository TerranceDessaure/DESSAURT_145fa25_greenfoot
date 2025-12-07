import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the Title Screen. Hit enter to move to the next world.
 * 
 * @author Terrance Dessaure 
 * @version 12.4.25
 */
public class StartScreen extends World
{
    // Fields
    
    // Contructor 
    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
         
        showText("Press enter to Start", getWidth()/2, 550);
        showText("Use WASD to move, Mouse to aim, Click to shoot", getWidth()/2, 580);
    }// end StartScreen constructor
    
    /**
     * Calls every frame. If the enter button is pressed, the Title screen
     * will move to the first loading screen.
     */
    public void act() 
    {
        if (Greenfoot.isKeyDown("enter")) 
        {
            Greenfoot.setWorld(new Loading()); // switch to your main game world
        }// end if 
    }// end act method
}//end class
