import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Blessing class represents Special power-up items that appear in the game
 * Each blessing give Spiderman a temporary ablility when collected, suck as 
 * double damage, faster movement, or tapid fire. Blessing only stat in the world
 * for a limit timre before disappearing on their own
 * 
 * When Spiderman touches a blessing:
 * - A random blessing type is chosen.
 * - The effect is applies to Spiderman.
 * - A quip may be added to the top of the screen.
 * - The blessing object is removed from the world
 * 
 * This class also sets the correct image for each blessing type so players
 * can know what power up they are picking up
 * 
 * @author Terrance Dessaure
 * @version 12.4.25
 */

public class Blessings extends Actor
{
    /* Instance vairiables */
    private int lifeTime = 600;
    private String type;

    /**
     * Act - do whatever the Blessings wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        lifeTime--;
        if (lifeTime <= 0)
        {
            getWorld().removeObject(this);
            return;
        }// end if
        checkCollision();
    }// end act

    /**
     * Check if this blessing object is touching Spiderman.
     * if a collision is detected:
     *  Chooses a random blessing type ( doubleDamage, speedBoost, or rapidFire)
     *  Applies the chosen blessing eddect to Spiderman
     *  Optionallt makes Spiderman say a quip.
     *  Removes the blessing object from the word so it cannot be collected again.
     *  
     *  This method does not take any parameters and does not return a value.
     *  It directly changes Spriderman's state and removes the blessing actor.
     */
    private void checkCollision() 
    {
     if (isTouching(Spiderman.class)) 
     {
            Spiderman spiderman = (Spiderman)getOneIntersectingObject(Spiderman.class);

            // Choose a random blessing type
            String[] types = { "doubleDamage", "speedBoost", "rapidFire"};
            String chosen = types[Greenfoot.getRandomNumber(types.length)];

            // Apply the blessing effect to Spiderman
            spiderman.applyBlessing(chosen);

            // Optional: trigger a quip
            spiderman.sayQuip();
            ((Score)getWorld().getObjects(Score.class).get(0)).addPoints(25);
            // Remove blessing from the world
            getWorld().removeObject(this);
    

        }
    }// end check Collision
    
    
    /**
     * Creates a new Blessing object with a specific type.
     * The type determins what kind of blessing eddect this object gives
     * (for example: "doubleDamage", "speedBoost", or "rapidFire").
     * 
     * @param type the kind of blessing this object represents
     */
    public Blessings(String type)
    {
        this.type = type;
        setImageForType(type);
    }// end Blessings method

    /**
     * Chooses the correct picture for this Blessings object based on its type.
     * Each blessing type has a different image so the player can tell them apart
     * 
     * @param type the name fo the blessing (string value) thate decides which image is displayed.
     */
    private void setImageForType(String type)
    {
        switch(type) 
        {
            case "doubleDamage":
                setImage("powerup1.png"); 
                break;
            case "speedBoost":
                setImage("powerup2.png");
                break;
            case "rapidFire":
                setImage("powerup3.png"); 
                break;
        }// end switch
    }// end setImageForType(String type) method
}// end class
