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
    private int coolDown;
    private int actCounter;
    private int seconds;
    
    //movement instance variables
    private int stepCounter;
    private int sideLength = 50;
    
    /**
     * Act - do whatever the EvilSpirit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(2);
        countSeconds();
        fireSpeed();
        fireRate();
        turnAtEdge();
        boxMovement();
        checkHit();
        checkHealth();
    }// end act method
    
    private void countSeconds()
    {
        actCounter++;
        if (actCounter == 60)
        {
            seconds++;
            actCounter = 0;
        }
    }
    
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
    
     public void randomMovement()
    {
        if ( Greenfoot.getRandomNumber(100) < 1 )
        {
            turn(45);
        } // end if
    } // end method randomTurn

    
    private void fire()
    {
        if (coolDown == 0)
        {
            
            enemyProjectile projectile = new enemyProjectile();
            getWorld().addObject (projectile, getX(), getY());
            move(5);
            
            coolDown = 60;
        }
    }

    
     private void fireSpeed()
    {
        if (coolDown > 0)
        {
            coolDown--;
        }//end if
    }
    
    private void fireRate()
    {
        if (seconds == 5) // fire every five seconds
        {
            fire();
            seconds = 0; // resest counter
        }
    }
    
    private void boxMovement()
    {
        move(2);
        stepCounter += 2;
        
        if (stepCounter >= sideLength)
        {
            turn(90);
            stepCounter = 0;
        }
    }
}
