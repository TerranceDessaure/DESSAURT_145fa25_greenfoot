import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lobster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lobster extends Actor
{
    /**
     * Act - do whatever the Lobster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        turnAtEdge();
        randomTurn();
        move(5);
        lookForCrab();
    }// end method act
    
    /**
     * Check whether we hae stumlbled upon a Crab.
     * If we have, eat it. If not, do nothing.
     */
    public void lookForCrab()
    {
        // If the Lobster sees a Crab, it eats it
        if( isTouching(Crab.class))
        {
            removeTouching(Crab.class);
        }// end if
    }// end method lookForWorm
    
    /**
     * make the crab turn a random number of degrees (between
     * -45 degrees and +45 degrees), 
     * about ten oercent if the time.
     */
    public void randomTurn()
    {
        if(Greenfoot.getRandomNumber(100) < 10) 
        {
            turn(Greenfoot.getRandomNumber(91) - 45 );
        }// end if
    }// end method randomTurn

    /**
     * if the crab intersects with the boundary of the world, 
     * make it turn enough so that it can keep moving
     */
    public void turnAtEdge()
    {
        if (isAtEdge())
        {
            turn(17);// turn to the right by 17 degrees
        } // end if
    }// end method turnAtEdge
}// end class Lobster
