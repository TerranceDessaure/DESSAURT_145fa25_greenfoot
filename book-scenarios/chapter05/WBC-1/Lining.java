import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Lining are objects at the edge of the vein.
 * 
 * @author Terrance Dessaure
 * @version 0.2
 */
public class Lining extends Actor
{
    /**
     * Act - Moves the Lining object left until it hits 0 and then disappers.
     */
    public void act() 
    {
        setLocation(getX()-2, getY()); // Makes the Lining objects move
                                       // continuously left
        if (getX() == 0) 
        {
            getWorld().removeObject(this); // Once the Lining Object to moves 
                                           // moves to the left position of 
                                           // 0 it is removed from the screen
        }
    }// end act
}
