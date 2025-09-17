import greenfoot.*;

/**
 * This class defines a crab. Crabs live on the beach.
 */
public class Crab extends Actor
{
    /**
     * Act - do whatever the crab wants to do. This method is called
     * whenever the 'Act' or 'Run' button gets pressed. 
     */
    public void act()
    {    
        // if the crab intersects with the boundary of the world,
        // make it turn enough so that it can keep moving
        if (isAtEdge())
        {
            turn(17);// turn to the right by 17 degrees

        } // end if

        // make the crab turn by 5 degrees,at random, 
        // ten oercent if the time
        if(Greenfoot.getRandomNumber(100) < 10) 
        {
            turn(Greenfoot.getRandomNumber(91) - 45 );
        }// end if

        // attempt to move the crab forward by 5 pixels
        move(5);

        lookForWorm();
    } // end method act

    /**
     * Check whether we hae stumlbled upon a worm.
     * If we have, eat it. If not, do nothing.
     */
    public void lookForWorm()
    {
        // If the crab sees a worm, it eats it
        if( isTouching(Worm.class))
        {
            removeTouching(Worm.class);
        }// end if
    }// end method lookForWorm
} // end class Crab

