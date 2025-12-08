import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spiderman here.
 * 
 * @author Terrance Dessaire 
 * @version 12.6.25
 */
public class Spiderman extends Actor
{
    // Fields
    private int cooldown;
    private int mouseX;
    private int mouseY;

    private boolean directionIsRight;
    
    private String[] quips = { 
            "My spider-sense is tingling!",
            "Web-slinging never gets old!",
            "Another gem for the collection!",
            "Evil spirits? More like easy targets!" };

    private int quipTimer = 0;
    private String currentQuip = "";

    /* Power-Ups*/
    private boolean doubleDamage;
    private boolean speedBoost;
    private boolean rapidFire;
    private int powerUpTimer;

    //constructor
    public Spiderman()
    {
        //initialize fields
        doubleDamage = false;
        speedBoost = false;
        rapidFire = false;
        powerUpTimer = 0;
        //directionIsRight = true;
    }
    
    /**
     * Runs eahc frame: hangles input. aiming, firing, quips, and power-up timers
     */
    public void act()
    {
        checkKeypress();
        aim();
        fireSpeed();

        if (quipTimer > 0) 
        {
            quipTimer--;
            if (quipTimer == 0) 
            {
                getWorld().showText("", 200, 30); // clear after 3 seconds
            }// end if
        }// end if

        if (powerUpTimer > 0)
        {
            powerUpTimer--;
            if (powerUpTimer == 0)
            {
                clearPowerUps();
            }// end if
        }// end if
    }// end act method

    /**
     * Displays a random quip for a few seconds.
     */
    public void sayQuip() 
    {
        int index = Greenfoot.getRandomNumber(quips.length);
        currentQuip = quips[index];
        getWorld().showText(currentQuip, 400,20);
        quipTimer = 180; // 3 seconds at 60 fps
    }// end sayQuip method

    /**
     * Applies a blessing effect (double damage, speed boost, or rapid fire).
     * 
     * @param type the blessing type
     */
    public void applyBlessing(String type)
    {
        switch(type)
        {
            case "doubleDamage" :
                doubleDamage = true;
                getWorld().showText("Double Damage Activated!", 400, 580);
                break;
            case "speedBoost":
                speedBoost = true;
                getWorld().showText("Speed Boost Activated!", 400, 580);
                break;
            case "rapidFire" : 
                rapidFire = true;
                cooldown = 10;
                getWorld().showText("Rapid Fire Activated!", 400, 580);
                break;

        }// end swwith
        powerUpTimer = 300;
    }// end applyBlessing method

    /**
     * Clears all powers ups and resest cooldown.
     */
    private void clearPowerUps()
    {
        doubleDamage = false;
        speedBoost = false;
        rapidFire = false;
        cooldown = 30;
        getWorld().showText("", 300, 380); 
    }// end clearPowerUps method

    /**
     * Handles movement and firing input.
     */
    public void checkKeypress()
    {
        // Get Current position
        int x = getX();
        int y = getY();

        boolean moving = false;
        int step = speedBoost ? 8 : 5; 

        // blue and white pill = speedBoost
        if(Greenfoot.isKeyDown("a"))
        {
            setLocation(x - step,y);

        }// end if 

        if(Greenfoot.isKeyDown("d"))
        {
            setLocation(x + step, y);
        }// end if

        if (Greenfoot.isKeyDown("w"))
        {
            setLocation(x, y - step);
        }// end if

        if (Greenfoot.isKeyDown("s"))
        {
            setLocation(x, y + step);
        }// end if

        if(Greenfoot.mouseClicked(null))
        {
            fire();
        }// end if
    } // end checkKeypress Method

    /**
     * reduces cooldown each frame.
     */
    private void fireSpeed()
    {
        if (cooldown > 0)
        {
            cooldown--;
        }//end if
    }// fireSpeed method

    /**
     * Fires a web projectile toward the current mouse position.
     * 
     * When the cooldown timer is zero, this method creates a new
     * `webProjectiles` object at the actor's current location.
     * It calculates the angle between the actor and the mouse cursor,
     * set the projectile's rotation accordingly, and adds it to the world.
     * After firing, the cooldown is reset to 30 frames to prevent 
     * continuous firing.
     */
    private void fire()
    {
        if (cooldown == 0)
        {  
            int damage = doubleDamage ? 2 : 1;
            webProjectiles web = new webProjectiles(damage);

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

            if (getWorld() != null)
            {
                getWorld().addObject (web, getX(), getY());
                cooldown = rapidFire ? 10 : 30;
            }// end if
        }// end if 
    }// end fire method

    /**
     * Updates the aiming coordinates based on the current mouse position.
     * 
     * If the mouse cursor is present within the Greenfoot window, this method
     * get the X and Y coordinates and stores them in and `mouseX` and `mouseY`. 
     * These values can be used to determine the direction in which projectiles
     * should be fired.
     */
    private void aim()
    {
        if(Greenfoot.getMouseInfo() != null) // if the mouse is in the screen
        // execute the if statement
        {
            mouseX = Greenfoot.getMouseInfo().getX();
            mouseY = Greenfoot.getMouseInfo().getY();
        }//end if
    }// end aim method

    /**
     * Removes blessings if touched.
     */
    private void checkCollision()
    {
        if (isTouching(Blessings.class))
        {
            removeTouching(Blessings.class);
        }// end if
    }// end checkTouching method
}// end Spiderman class

