import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a white blood cell. This kind of cell has the job to catch 
 * bacteria and remove them from the blood.
 * 
 * @author Terrance Dessaure
 * @version 10/23/2025
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
    }// end act

    /**
     * Check whether a keyboard key has been pressed and react if it has.
     */
    private void checkKeyPress()
    {
        if (Greenfoot.isKeyDown("up")) 
        {
            setLocation(getX(), getY()-10);
        }// end if

        if (Greenfoot.isKeyDown("down")) 
        {
            setLocation(getX(), getY()+15);
        }// end if

        if (Greenfoot.isKeyDown("left"))
        {
            setLocation(getX()-6, getY());
        }// end if 

        if (Greenfoot.isKeyDown("right"))
        {
            setLocation(getX()+7, getY());
        }// end if
    } // end checkKeyPress

    /**
     *  Checks whether the WhiteCell is touching the Bacteria
     */
    private void checkCollision()
    {
        if (isTouching(Bacteria.class)) 
        {
            Greenfoot.playSound("slurp.wav");
            removeTouching(Bacteria.class);
            Bloodstream bloodstream = (Bloodstream)getWorld();
            bloodstream.addScore(20);
        }//end if
        
        if (isTouching(Virus.class)) 
        {
            removeTouching(Virus.class);
            Bloodstream bloodstream = (Bloodstream)getWorld();
            bloodstream.addScore(-100);            
        }//end if
    }// end checkCollision method
}// end WhiteCell Actor
