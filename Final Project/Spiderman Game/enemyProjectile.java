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
        if (getWorld() == null) return;
        move(2);
        checkCollision();
        
        if (getWorld() != null)
        {
            checkEdge();
        }// end if
    }// end act

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

    private void checkCollision()
    {
        if (isTouching(Spiderman.class))
        { 
                World currentWorld = getWorld();
                if (currentWorld instanceof Background) 
                {
                    ((Background)currentWorld).takeHealth(10);
                } 
                else if (currentWorld instanceof SecondLevel) 
                {
                    ((SecondLevel)currentWorld).takeHealth(10);
                }
                currentWorld.removeObject(this);
                return;
        }// end checkTouching method

    }
}
