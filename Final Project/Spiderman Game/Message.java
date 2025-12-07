import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Message displays temporary text on the screen.
 * It disappears after a set number of frames.
 * 
 * @author Terrance Dessaure 
 * @version 12.6.25
 */
public class Message extends Actor
{
    //Fields
    private int life;

    // Constructor
    /**
     * Creates a new message with the given text and duration.
     *
     * @param text the text to display
     * @param durationFrames how many frames the message lasts
     */
    public Message(String text, int durationFrames) 
    {
        setImage(new GreenfootImage(text, 24, Color.YELLOW, null));
        life = durationFrames;
    }// end two arg constructor

    // Methods
    /**
     * Runs each frame. Counts down lifetime and removes the message when time is up.
     */
    public void act()
    {
        life--;
        if (life <= 0) 
        {
            getWorld().removeObject(this);
        }// end if
    }// end act method
}// end Message Class
