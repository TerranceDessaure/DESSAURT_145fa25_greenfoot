import greenfoot.*;

/**
 * This class defines a crab. Crabs live on the beach.
 */
public class Crab extends Actor
{
    /* INSTANCE VARIABLES (FIELDS) */
    private GreenfootImage image1;// image1 can be accessed anywhere in the class
    private GreenfootImage image2;// image2 can be accessed anywhere in the class
    
    /* CONSTRUCTORS */
    /**
     * Initialize a newly instantiated crab object with
     * its two possible images; then set the initial image to
     * one of those images.
     */
    public Crab()
    {
       image1 = new GreenfootImage("crab.png");
       image2 = new GreenfootImage("crab2.png");
       setImage( image1 ); // the crabs initial image will be set to image1
    }// end Crab constructor
    /* METHODS */

    /**
     * Act - do whatever the crab wants to do. This method is called
     * whenever the 'Act' or 'Run' button gets pressed. 
     */
    public void act()
    {    
        checkKeypress();
        move(5);
        lookForWorm();
        switchImage();
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
            Greenfoot.playSound("slurp.wav");
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

    /**
     * Chesks to see if keys are pressed to allow the crab
     * to turn left or right
     */
    public void checkKeypress()
    {
        if (Greenfoot.isKeyDown("left"))
        {
            turn(4);
        }// end if

        if(Greenfoot.isKeyDown("right"))
        {
            turn(4);
        }// end if 
    }// end method checkKeypress
    
    /**
     * Alternates the crab's image between image1 and image2
     */
    public void switchImage()
    {
        if(getImage() == image1 )
        {
            setImage( image2 );
        }
        else // otherwise.. if the current image is image2...
        {
            setImage( image1 );
        }// end if/else
    }// end method switchImage
} // end class Crab
