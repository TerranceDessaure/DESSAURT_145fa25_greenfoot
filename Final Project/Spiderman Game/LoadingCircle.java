import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingCirle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingCircle extends Actor
{
    private GreenfootImage[] images = new GreenfootImage[8];
    
    private int frameCount;
    private int imageIndex;
    
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
    }
    
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
