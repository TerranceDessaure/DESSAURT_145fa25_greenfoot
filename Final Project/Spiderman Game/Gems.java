import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * These gems represent the gems of the Breast Plate of Aaron.
 * 
 * @author Terrance Dessaure 
 * @version (11/17/2025)
 */
public class Gems extends Actor
{
    //instance variables
    private GreenfootImage[] images = new GreenfootImage[6];
    private int frameCount;
    private int imageIndex;
    private SpriteSheet spriteSheet;
    // constuctor
    public Gems()
    {
         // instantiate the SpriteSheet object
        spriteSheet = new SpriteSheet(); // call defalt constructor
        
        images[0] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("gemsSpriteSheet.png"), 
                                                             0, 0,
                                                             15, 23, 
                                                             15, 23));
        images[1] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("gemsSpriteSheet.png"), 
                                                             15, 0,
                                                             32, 23, 
                                                             17, 23));
        images[2] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("gemsSpriteSheet.png"), 
                                                             33, 0,
                                                             49, 23, 
                                                             16, 23));
        images[3] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("gemsSpriteSheet.png"), 
                                                             50, 0,
                                                             65, 23, 
                                                             15, 23));
        images[4] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("gemsSpriteSheet.png"), 
                                                             66, 0,
                                                             80, 23, 
                                                             14, 23));
        images[5] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("gemsSpriteSheet.png"), 
                                                             81, 0,
                                                             93, 23, 
                                                             12, 23));
    }//end Gems method
    
    /**
     * Act - do whatever the Gems wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        frameCount++;
            if (frameCount == 4)
            {
                updateImage();
                frameCount = 0;
            }
            
         if (isTouching(Spiderman.class)) 
         
         {
             World currentWorld = getWorld();
             GameManager manager = null;
            if (currentWorld instanceof Background)
            {
                Background world = (Background)getWorld();
                manager = world.getManager();
                manager.addGem(1);
                world.addGem();
            } 
            else if(currentWorld instanceof SecondLevel)
            {
                SecondLevel world = (SecondLevel)currentWorld;
                manager = world.getManager();
                manager.addGem(1);
                world.addGem(); 
            }

            
            getWorld().removeObject(this); // remove the gem itself

        }// end act method

    }
    
    public void updateImage()
    {
        setImage ( images[imageIndex] );
        
        // ++imageIndex pre-incremnt imageIndex
        // then we check to see if the incremented value of imageIndex
        // is greater that the highest available index in the
        // images array
        if (++imageIndex > (images.length - 1))
        {
            imageIndex = 0;
        }// end if
    }// end method updateImage
    
    
}
