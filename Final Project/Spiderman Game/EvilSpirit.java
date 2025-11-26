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
        move(5);
        turnAtEdge();
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
            Background background = (Background)getWorld();
            background.subtractSpiritCounter(1);
            getWorld().removeObject(this); 
        }
    }// end checkHealth method
    
    private void checkSpiritNumber()
    {
        Background background = (Background)getWorld();
        background.subtractSpiritCounter(1);
    }
    
    private void turnAtEdge()
    {
        if ( isAtEdge() )
        {
            setRotation(getRotation() + 180);
            
        } // end if
    } // end method turnAtEdge
    
     public void randomTurn()
    {
        if ( Greenfoot.getRandomNumber(100) < 10 )
        {
            turn( Greenfoot.getRandomNumber( 91 ) - 45 );
        } // end if
    } // end method randomTurn
}
