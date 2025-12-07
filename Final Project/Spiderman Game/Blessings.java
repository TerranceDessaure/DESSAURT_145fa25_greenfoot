import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Blessing class represents Special power-up items that appear in the game
 * Each blessing give Spiderman a temporary ablility when collected, such as 
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
    // Fields
    
    private int lifeTime;
    private String type;

    //Constructor
    /**
     * Creates a new Blessing object with a specific type.
     * The type determins what kind of blessing eddect this object gives
     * (for example: "doubleDamage", "speedBoost", or "rapidFire").
     * 
     * @param type the kind of blessing this object represents
     */
    public Blessings(String type)
    {
        // intialize fields
        lifeTime = 600;
        type = type;
        setImageForType(type);
    }// end Blessings construtor

    //Methods
    /**
     * Runs each frame: counts down lifttime and checks for collisions.
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
     * Checks if this blessing is touching Spiderman.
     * If collected:
     * - Chooses a random blessing type.
     * - Applies the effect to Spiderman.
     * - Optionally shows a quip.
     * - Adds to the score.
     * - Removes the blessing from the world.
     */

    private void checkCollision() 
    {
        if (isTouching(Spiderman.class)) 
        {
            Spiderman spiderman = (Spiderman)getOneIntersectingObject(Spiderman.class);

            String[] types = { "doubleDamage", "speedBoost", "rapidFire"};
            String chosen = types[Greenfoot.getRandomNumber(types.length)];

            spiderman.applyBlessing(chosen);

            spiderman.sayQuip();
            
            GameManager manager = ((BaseWorld)getWorld()).getManager();
            manager.addScore(25);

            getWorld().removeObject(this);
        }//end if
    }// end check Collision

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
                GreenfootImage doubleImage = new GreenfootImage("double.png");
                doubleImage.scale(48, 48); 
                setImage(doubleImage);
                break;
            case "speedBoost":
                GreenfootImage speedImage = new GreenfootImage("speed.png");
                speedImage.scale(48, 48); 
                setImage(speedImage);
                break;

            case "rapidFire":
                GreenfootImage rapidFireImage = new GreenfootImage("rapid.png");
                rapidFireImage.scale(48, 48); 
                setImage(rapidFireImage);
                break;
        }// end switch
    }// end setImageForType(String type) method
}// end class
