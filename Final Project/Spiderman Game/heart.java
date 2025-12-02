import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * If the heart is touched by Spiderman it will give him health.
 * Theses hearts should only spawn if Spiderman has taken damage.
 * 
 * @author Terrance Dessaure 
 * @version (11/18/2025)
 */
public class heart extends Actor
{
    /**
     * Act - do whatever the heart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (isTouching(Spiderman.class)) 
        {
            World currentWorld = getWorld();
            GameManager manager = null;

        if (currentWorld instanceof Background) 
        {
            Background world = (Background)currentWorld;
            manager = world.getManager();
            manager.addHealth(20); // heal player
        }    
        else if (currentWorld instanceof SecondLevel) 
        {
            SecondLevel world = (SecondLevel)currentWorld;
            manager = world.getManager();
            manager.addHealth(20);
        }

        getWorld().removeObject(this);
    }
    }
}
