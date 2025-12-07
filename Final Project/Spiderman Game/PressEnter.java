import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays a "Press ENTER" prompt on the screen.
 * Used to signal the player to continue.
 * 
 * @author Terrance Dessaure
 * @version 12/06/2025
 */
public class PressEnter extends Actor
{
    /**
     * Creates the "Press ENTER" image and sets it as this actor's graphic.
     */
    public PressEnter() 
    {
        GreenfootImage img = new GreenfootImage("Press ENTER", 30, Color.WHITE, Color.BLACK);
        setImage(img);
    }// end PressEnter Constructor
}// end PressEnter method
