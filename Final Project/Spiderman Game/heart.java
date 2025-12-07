import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * If the heart is touched by Spiderman it will give him health.
 * Theses hearts should only spawn if Spiderman has taken damage.
 * 
 * @author Terrance Dessaure 
 * @version (11/18/2025)
 */
public class Heart extends Actor
{
    // Fields 
    private GreenfootImage[] images = new GreenfootImage[8];

    private int frameCount;
    private int imageIndex;

    //constructor
    public Heart()
    {
        // populate the images array with references 
        // to GreenfootImage objects

        images[0] = new GreenfootImage("heart0.png");
        images[1] = new GreenfootImage("heart1.png");
        images[2] = new GreenfootImage("heart2.png");
        images[3] = new GreenfootImage("heart3.png");
        images[4] = new GreenfootImage("heart4.png");
        images[5] = new GreenfootImage("heart5.png");
        images[6] = new GreenfootImage("heart6.png");
        images[7] = new GreenfootImage("heart7.png");
    }

    // Methods
    /**
     *Creates heart and loads its animation frames.
     */
    public void act()
    {
        frameCount++;
        if (frameCount == 7)
        {
            updateImage();
            frameCount = 0;
        }// end if

        if (isTouching(Spiderman.class)) 
        {
            World currentWorld = getWorld();
            GameManager manager = null;

            if (currentWorld instanceof Background) 
            {
                Background world = (Background)currentWorld;
                manager = world.getManager();
                manager.addHealth(20); // heal player
            }    
            else if (currentWorld instanceof SecondLevel) 
            {
                SecondLevel world = (SecondLevel)currentWorld;
                manager = world.getManager();
                manager.addHealth(20);
            }// end if

            getWorld().removeObject(this);
        }// end if
    }// end act method

    /**
     * Cycle through animation frames
     */
    public void updateImage()
    {
        setImage(images[imageIndex]);

        if ( ++imageIndex > (images.length - 1) )
        {
            imageIndex = 0;
        }// end if
    }// end updateImage method
}// end Heart class
