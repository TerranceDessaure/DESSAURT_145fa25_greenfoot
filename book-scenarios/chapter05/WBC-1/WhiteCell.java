import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a white blood cell. This kind of cell has the job to catch 
 * bacteria and remove them from the blood.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class WhiteCell extends Actor
{
    
    /**
     * Act: move up and down when cursor keys are pressed.
     */
    public void act() 
    {
        checkKeyPress();
        checkCollision();
    }
    
    /**
     * Check whether a keyboard key has been pressed and react if it has.
     */
    private void checkKeyPress()
    {
        if (Greenfoot.isKeyDown("up")) 
        {
            setLocation(getX(), getY()-4);
        }
        
        if (Greenfoot.isKeyDown("down")) 
        {
            setLocation(getX(), getY()+4);
        }
    }
    
    /*
     * Checks whether the WhiteCell is touching the Bacteria
     */
    private void checkCollision()
    {
        if (isTouching(Bacteria.class))
        {
            Greenfoot.playSound("slurp.wav");// Plays the sound from the slurp.wav file
            removeTouching(Bacteria.class);// Removes any bacteria touching the WhiteCell object
        }// end if
        
        if (isTouching(Bacteria.class))
        {
            Greenfoot.playSound("game-over.wav");
            Greenfoot.stop();
        }//end if
    }// end checkCollision
}
