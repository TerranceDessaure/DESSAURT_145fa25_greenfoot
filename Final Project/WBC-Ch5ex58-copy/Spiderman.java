import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spiderman here.
 * 
 * @author Terrance Dessaire 
 * @version (a version number or a date)
 */
public class Spiderman extends Actor
{
    /**
     * Act - do whatever the Spiderman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkKeypress();
    }
    public void checkKeypress()
    {
        // Get Current position
        int x = getX();
        int y = getY();
        
        if(Greenfoot.isKeyDown("left"))
        {
            setLocation(x - 5,y);
        
        }// end if 
        
        if(Greenfoot.isKeyDown("right"))
        {
            setLocation(x + 5, y);
        }// end if
        
        if (Greenfoot.isKeyDown("up"))
        {
            setLocation(x, y - 5);
        }// end if
        
        if (Greenfoot.isKeyDown("down"))
        {
            setLocation(x, y + 5);
        }// end if
    } // end checkKeypress Method
    }// end Spiderman class
    
