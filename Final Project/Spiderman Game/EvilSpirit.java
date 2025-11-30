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
    
    // chasing instance variables
    private boolean chasing = false;
    private int chaseTimer = 0;
    private int maxChaseTime = 180;
    
    /**
     * Act - do whatever the EvilSpirit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        countSeconds();
        fireSpeed();
        if(seconds >= 5)
        {
            fireAtSpiderman();
            seconds = 0;
        }
        turnAtEdge();
        // boxMovement();
        checkHit();
        checkHealth();

        if (chasing)
        {
            chaseSpiderman();
        }
        else
        {
            roam();
        }
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
            if (getWorld() != null)
            {
                Background background = (Background)getWorld();
                background.subtractSpiritCounter(1);
                getWorld().removeObject(this); 
            }
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
       if (seconds >= 5)
       {
           fireAtSpiderman();
           seconds = 0;
       }
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
            turn(Greenfoot.getRandomNumber(360));
            stepCounter = 0;
        }
    }
    
    private void fireAtSpiderman() 
    {
        
        if (getWorld() == null) 
        {
            return; // safety check
        }

        Background bg = (Background)getWorld();
        Spiderman spiderman = bg.getSpiderman(); // make sure Background has a getter

        if (spiderman != null && spiderman.getWorld() != null && coolDown == 0) 
        {
            if (isInRange(spiderman,150))
            {
                enemyProjectile projectile = new enemyProjectile();
                getWorld().addObject(projectile, getX(), getY());

                // Calculate angle to Spiderman
                int dx = spiderman.getX() - getX();
                int dy = spiderman.getY() - getY();
                double angle = Math.atan2(dy, dx); // radians

                // Convert to degrees and set rotation
                int rotation = (int)Math.toDegrees(angle);
                projectile.setRotation(rotation);

                coolDown = 60; // wait 1 second before next shot
            }
        }
    }
    
    private boolean isInRange(Spiderman spiderman, int range)
    {
        int dx = spiderman.getX() - getX();
        int dy = spiderman.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= range;
    }
    
    
    private void chaseSpiderman()
    {
        if (getWorld() == null)
        {
            return;
        }
        Background bg = (Background)getWorld();
        Spiderman spiderman = bg.getSpiderman();
        if (spiderman != null && spiderman.getWorld () != null)
        {
            turnTowards(spiderman.getX(), spiderman.getY());
            move(2);
            
            chaseTimer++;
            if (chaseTimer >= maxChaseTime)
            {
                chasing = false;
                chaseTimer = 0;
            }
        }
        else
        {
            chasing = false; 
        }
    }
    
    private void roam()
    {
        move(2);
        if (Greenfoot.getRandomNumber(100) < 2)
        {
            turn(Greenfoot.getRandomNumber(90) - 45);
        }
        
        if (getWorld() == null)
        {
            return;
        }
        Background bg = (Background)getWorld();
        Spiderman spiderman = bg.getSpiderman();
        
        if (spiderman != null && spiderman.getWorld() != null)
        {
            int dx = spiderman.getX() - getX();
            int dy = spiderman.getY() - getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            if (distance < 150)
            {
                chasing = true;
                chaseTimer = 0;
            }
        }
    }
}
