import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Message here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Message extends Actor
{
    private int life;
    
    public Message(String text, int durationFrames) 
    {
        setImage(new GreenfootImage(text, 24, Color.YELLOW, null));
        life = durationFrames;
    }

    /**
     * Act - do whatever the Message wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        life--;
        if (life <= 0) 
        {
            getWorld().removeObject(this);
        }

    }
}
