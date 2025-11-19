import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spiderman here.
 * 
 * @author Terrance Dessaire 
 * @version (a version number or a date)
 */
public class Spiderman extends Actor
{
    private int cooldown;
    int mouseX;
    int mouseY;
    /**
     * Act - do whatever the Spiderman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkKeypress();
        aim();
        if (cooldown > 0)
        {
            cooldown--;
        }//end if
    }// end act metho

    public void checkKeypress()
    {
        // Get Current position
        int x = getX();
        int y = getY();

        if(Greenfoot.isKeyDown("a"))
        {
            setLocation(x - 5,y);

        }// end if 

        if(Greenfoot.isKeyDown("d"))
        {
            setLocation(x + 5, y);
        }// end if

        if (Greenfoot.isKeyDown("w"))
        {
            setLocation(x, y - 5);
        }// end if

        if (Greenfoot.isKeyDown("s"))
        {
            setLocation(x, y + 5);
        }// end if

        if(Greenfoot.mouseClicked(null))
        {
            fire();
            turnTowards(mouseX, mouseY);
        }// end if

    } // end checkKeypress Method

    private void fire()
    {
        if (cooldown == 0)
        {  
            webProjectiles web = new webProjectiles();
            web.setRotation(getRotation());
            getWorld().addObject (web, getX(), getY());

            cooldown = 30;
        }// end if 
    }// end fire method

    private void aim()
    {
        if(Greenfoot.getMouseInfo() != null) // if the mouse is in the screen
                                             // execute the if statement
        {
            mouseX = Greenfoot.getMouseInfo().getX();
            mouseY = Greenfoot.getMouseInfo().getY();

        }//end if
    }// end aim method

}// end Spiderman class

