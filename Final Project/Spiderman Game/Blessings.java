import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class Blessings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Blessings extends Actor
{
    private int lifeTime = 600;
    /**
     * Act - do whatever the Blessings wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        lifeTime--;
        if (lifeTime <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}
