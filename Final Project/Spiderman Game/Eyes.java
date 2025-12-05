import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eyes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Eyes extends Actor
{
    private GreenfootImage[] images = new GreenfootImage[4];
    
    private int frameCount;
    private int imageIndex;
    
    public Eyes()
    {
        images[0] = new GreenfootImage("eyes0.png");
        images[1] = new GreenfootImage("eyes1.png");
        images[2] = new GreenfootImage("eyes2.png");
        images[3] = new GreenfootImage("eyes3.png");
    
    }
    
    /**
     * Act - do whatever the Eyes wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        frameCount++;
        if (frameCount == 7)
        {
            updateImage();
            frameCount = 0;
        }

    }
    
    public void updateImage()
    {
        setImage(images[imageIndex]);
        
        if ( ++imageIndex > (images.length - 1) )
        {
            imageIndex = 0;
        }
    }
}
