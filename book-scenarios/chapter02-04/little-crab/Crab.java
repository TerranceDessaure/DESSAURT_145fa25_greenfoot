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
        if (Greenfoot.isKeyDown("left"))
        {
            turn(-4);
        }// end if
        
        if(Greenfoot.isKeyDown("right"))
        {
            turn(4);
        }// end if
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
} // end class Crab
