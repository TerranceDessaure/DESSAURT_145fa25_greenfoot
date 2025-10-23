import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bacteria fload along in the bloodstream. They are bad. Best to destroy
 * them if you can.
 * 
 * @author Terrance Dessaure
 * @version 10/22/2025
 */
public class Bacteria extends Actor
{
    private int speed;
    /**
     * Constructor.Set the speed for Bacteria.
     */
    public Bacteria()
    {
        speed = Greenfoot.getRandomNumber(3)+1;
    }

    /**
     * Float along the bloodstream, slowly rotating.
     */
    public void act() 
    {
        setLocation(getX()-speed, getY());// Subtracts speed variable from getX valueSSSSSSSSSSSSSSSSS
        turn(1);
        
        if (getX() == 0) 
        {
            getWorld().removeObject(this);
        }
    }
}
