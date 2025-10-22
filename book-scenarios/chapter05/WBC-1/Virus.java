import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Virus is another object floating in the bloodstream.
 * 
 * @author Terrance Dessaure 
 * @version 10/22/2025
 */
public class Virus extends Actor
{
    /**
     * The Virus object moves at 4 frames per act and turns counter clockwise.
     */
    public void act()
    {
        setLocation(getX()-4, getY()); // Moves the Virus object 4 frames per act
        turn(-1); // Turns the Bacteria object counter clockwise
    }// end act
}
