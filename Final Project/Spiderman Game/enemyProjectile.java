import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class enemyProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class enemyProjectile extends Actor
{
    
    /**
     * Act - do whatever the enemyProjectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(1);
        checkEdge();
    }
    
    /**
     * This will check if the enemyProjectile is at the edge of the screen.
     * If it is at the edge it will remove the enemyProjectile.
     */
    private void checkEdge()
    {
        if(isAtEdge())
        {
            getWorld().removeObject(this);
        }// end if
    }// end checkEdge method    
   

}
