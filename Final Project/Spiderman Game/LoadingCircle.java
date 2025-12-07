import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Loading Circle is just an animation used to make transition screens look
 * like they are loading
 * 
 * @author Terrance Dessaure
 * @version 12.6.25
 */
public class LoadingCircle extends Actor
{
    // Fields
    private GreenfootImage[] images = new GreenfootImage[8];
    
    private int frameCount;
    private int imageIndex;
    
    // Constructor
    public LoadingCircle()
    {
        images[0] = new GreenfootImage("circle0.png");
        images[1] = new GreenfootImage("circle1.png");
        images[2] = new GreenfootImage("circle2.png");
        images[3] = new GreenfootImage("circle3.png");
        images[4] = new GreenfootImage("circle4.png");
        images[5] = new GreenfootImage("circle5.png");
        images[6] = new GreenfootImage("circle6.png");
        images[7] = new GreenfootImage("circle7.png");
    }// end LoadingCircle constructor
    
    /**
     * Runs each frame and cycles through the Loading Circle animation
     */
    public void act()
    {
         frameCount++;
        if (frameCount == 7)
        {
            updateImage();
            frameCount = 0;
        }// end if
    }// end act method
    
    /**
     * Updates the heart's image to the next frame.
     * Loops back to the first frame  after the last one.
     */
    public void updateImage()
    {
        setImage(images[imageIndex]);
        
        if ( ++imageIndex > (images.length - 1) )
        {
            imageIndex = 0;
        }// end if
    }// end updateImage method
}// end LoadCircle class
