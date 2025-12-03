import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Blessings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Blessings extends Actor
{
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
        }

        checkCollision();
    }

    private void checkCollision() {
     if (isTouching(Spiderman.class)) {
            Spiderman spiderman = (Spiderman)getOneIntersectingObject(Spiderman.class);

            // Choose a random blessing type
            String[] types = { "doubleDamage", "speedBoost", "rapidFire"};
            String chosen = types[Greenfoot.getRandomNumber(types.length)];

            // Apply the blessing effect to Spiderman
            spiderman.applyBlessing(type);

            // Optional: trigger a quip
            spiderman.sayQuip();

            // Remove blessing from the world
            getWorld().removeObject(this);
        }
    }

    public Blessings(String type)
    {
        this.type = type;
        setImageForType(type);
    }

    private void setImageForType(String type)
    {
        switch(type) 
        {
            case "doubleDamage":
                setImage("powerup1.png"); // red flame
                break;
            case "speedBoost":
                setImage("powerup2.png"); // blue lightning
                break;
            case "rapidFire":
                setImage("powerup3.png"); // green web shooter
                break;
        }
    }
}
