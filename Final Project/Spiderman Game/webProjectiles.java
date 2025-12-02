import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Spiderman is able to shoot webProjectiles when the user left clicks on the mouse.
 * These webs are his tool to fighting against the EvilSpirits. If one of these webs the EvilSpirit 
 * it will be removed from the world.
 * 
 * @author Terrance Dessaure 
 * @version 11.24.25
 */
public class webProjectiles extends Actor
{
    //instance variables
    
    
    //constructor
    /**
     * Act - do whatever the web wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(5);
        checkEdge();
        
    }// end act method
    
    /**
     * This will check if the webProjectile is at the edge of the screen.
     * If it is at the edge it will remove the webProjectile.
     */
    private void checkEdge()
    {
        if(isAtEdge())
        {
            getWorld().removeObject(this);
        }// end if
    }// end checkEdge method    
    
    private void checkCollision()
    {
        
    }
   
}// end webProjectiles Class
