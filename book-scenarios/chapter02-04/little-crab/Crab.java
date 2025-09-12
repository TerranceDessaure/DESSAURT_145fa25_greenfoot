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

        // attempt to move the crab forward by 5 pixels
        move(5);
    } // end method act
} // end class Crab

