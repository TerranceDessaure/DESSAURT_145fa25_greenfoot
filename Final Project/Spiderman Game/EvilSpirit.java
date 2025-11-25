import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EvilSpirit here.
 * 
 * @author Terrance Dessaure 
 * @version (11/20/25)
 */
public class EvilSpirit extends Actor
{
    private int health = 3;
    /**
     * Act - do whatever the EvilSpirit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkHit();
        checkHealth();
    }// end act method
    
    private void checkHit()
    {
        if (isTouching(webProjectiles.class))
        {
            health--;
            removeTouching(webProjectiles.class);
        }// end if
    }// end chechHit mehtod
    
    private void checkHealth()
    {
        if (health <= 0)
        {
            getWorld().removeObject(this); 
        }
    }// end checkHealth method
}
