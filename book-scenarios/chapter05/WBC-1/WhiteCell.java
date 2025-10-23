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
    private int score;
    /**
     * Constructor.Set the score WhiteCell.
     */
    public WhiteCell()
    {
        score = 0;
    }// end WhiteCell
    
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
        }// end if
        
        if (Greenfoot.isKeyDown("down")) 
        {
            setLocation(getX(), getY()+4);
        }// end if
        
        if (Greenfoot.isKeyDown("left"))
        {
            setLocation(getX()-4, getY());
        }// end if 
        
        if (Greenfoot.isKeyDown("right"))
        {
            setLocation(getX()+4, getY());
        }// end if
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
            score = +20;
        }// end if
        
        if (isTouching(Virus.class))
        {
            Greenfoot.playSound("game-over.wav");
            Greenfoot.stop();
        }//end if
        
    }// end checkCollision
}
