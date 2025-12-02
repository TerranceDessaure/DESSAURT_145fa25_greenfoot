import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;

/**
 * Write a description of class Spiderman here.
 * 
 * @author Terrance Dessaire 
 * @version (a version number or a date)
 */
public class Spiderman extends Actor
{
    // instance variables
    private int cooldown;
    private int mouseX;
    private int mouseY;

    // animation logic
    private GreenfootImage[] images = new GreenfootImage[9];
    private int frameCount;
    private int imageIndex;

    // declare reference to a StriteSheet object
    private SpriteSheet spriteSheet;

    private boolean directionIsRight;
    //constructor
    public Spiderman()
    {
        // instantiate the SpriteSheet object
        spriteSheet = new SpriteSheet(); // call defalt constructor

        // extract the first row of animation frames
        //images[0] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
        //                                                     0, 0,
        //                                                     28, 30, 
        //                                                   28, 30));
        images[0] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
                0, 33,
                18, 65, 
                18, 32));
        images[1] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
                22,33 ,
                48, 65, 
                26, 32));
        images[2] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
                59, 33,
                83, 65, 
                24, 32));
        images[3] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
                87, 33,
                107, 65, 
                20, 32));
        images[4] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
                107, 33,
                128, 65, 
                21, 32)); 
        images[5] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
                131, 33,
                155, 65, 
                24, 32)); 
        images[6] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
                156, 33,
                178, 65, 
                22, 32));
        images[7] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
                179, 33,
                197, 65, 
                18, 32)); 
        images[8] = new GreenfootImage(spriteSheet.getSprite(new GreenfootImage("Spider-ManSpriteSheet.png"), 
                201, 33,
                228, 65, 
                27, 32)); 

        directionIsRight = true;

    }

    /**
     * Act - do whatever the Spiderman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkKeypress();
        checkCollision();
        aim();
        fireSpeed();
    }// end act method

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

    public void checkKeypress()
    {
        // Get Current position
        int x = getX();
        int y = getY();

        boolean moving = false;

        if(Greenfoot.isKeyDown("a"))
        {
            setLocation(x - 5,y);
            moving = true;
            if(directionIsRight)
            {
                for (GreenfootImage image : images)
                {
                    image.mirrorHorizontally();
                }// end end hanced for statement
                directionIsRight = false;

            }

        }// end if 
        
        if(Greenfoot.isKeyDown("d"))
        {
            setLocation(x + 5, y);
            moving = true;

            if(!directionIsRight)
            {
                for (GreenfootImage image : images)
                {
                    image.mirrorHorizontally();
                }// end end hanced for statement
                directionIsRight = true;

            }

        }// end if

        if (Greenfoot.isKeyDown("w"))
        {
            setLocation(x, y - 5);
        }// end if

        if (Greenfoot.isKeyDown("s"))
        {
            setLocation(x, y + 5);
        }// end if

        if (moving)
        {
            frameCount++;
            if (frameCount == 4)
            {
                updateImage();
                frameCount = 0;
            }
        }// end

        if(Greenfoot.mouseClicked(null))
        {
            fire();
            //turnTowards(mouseX, mouseY);
        }// end if

    } // end checkKeypress Method

    private void fireSpeed()
    {
        if (cooldown > 0)
        {
            cooldown--;
        }//end if
    }

    private void fire()
    {
        if (cooldown == 0)
        {  
            webProjectiles web = new webProjectiles();
            
            // get the position of the mouse on scren
            int targetX = Greenfoot.getMouseInfo().getX();
            int targetY = Greenfoot.getMouseInfo().getY();
            
            // Calculates the angle from Spiderman to the mouse
            int dx = targetX - getX();
            int dy = targetY - getY();
            double angle = Math.atan2(dy, dx); // radians

            // Convert to degrees and set rotation
            int rotation = (int)Math.toDegrees(angle);
            web.setRotation(rotation);

    
            getWorld().addObject (web, getX(), getY());

            cooldown = 30;
        }// end if 
    }// end fire method

    private void aim()
    {
        if(Greenfoot.getMouseInfo() != null) // if the mouse is in the screen
        // execute the if statement
        {
            mouseX = Greenfoot.getMouseInfo().getX();
            mouseY = Greenfoot.getMouseInfo().getY();

        }//end if
    }// end aim method

    private void checkCollision()
    {
        
        if (isTouching(Blessings.class))
        {
            removeTouching(Blessings.class);
        }
        
        if (isTouching(enemyProjectile.class))
        {
            removeTouching(enemyProjectile.class);
            Background background = (Background)getWorld();
            background.takeHealth(20);
        }
    }// end checkTouching method
}// end Spiderman class

