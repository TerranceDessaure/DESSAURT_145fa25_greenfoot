import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RedCell here.
 * 
 * @author (Terrance Dessaure) 
 * @version (10/23/2025)
 */

public class RedCell extends Actor
{
    private int speed;
    /**
     * Constructor. Setting the speed for RedCell
     */
    public RedCell()
    {
        speed = Greenfoot.getRandomNumber(2)+1;
        setRotation(Greenfoot.getRandomNumber(360));
    }// end RedCell

    /**
     * Act - do whatever the RedCell wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX()-4, getY()); // Moves the RedCell object 4 frames per act
        turn(-1);
    }//end act
}
