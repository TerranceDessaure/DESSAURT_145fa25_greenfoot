import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents an enemy in the game.
 * It can move around the world, sometimes turning randomly,
 * and will attack Spiderman if he gets close. The enemy can also fire projectiles at Spirderman
 * when in range. It keeps track of health and removes itself from the world when defeated.
 * 
 * @author Terrance Dessaure
 * @version (12/02/25)
 */
public class EvilSpirit extends Actor 
{
    // Fields
    private int health;      
    private int coolDown;         // projectile cooldown
    private int actCounter;       
    private int seconds;         

    private int stepCounter;      
    private int sideLength; 

    private boolean attacking;
    private int attackTimer;
    private int maxChaseTime;

    private Spiderman spiderman;

    /**
     * EnemyState is an enumeration (enum) that list the possible
     * behaviors an enemy can have in the game. Each value represents
     * a different type of action
     * 
     *  IDLE - the enemy stands still and does nothing.
     *  PATROL - the enemy moves around om a set pr random pattern.
     *  ATTACK - the enemy activity follows and tries to catch Spiderman.
     *  
     *  Using an enum makes the code easier to read and manage because
     *  the enemy's state is alwats one of these defined values.
     */
    private enum EnemyState
    {
        IDLE,
        PATROL,
        ATTACK
    }// end private enum

    double[][] transitionMatrix = {
            {0.6, 0.3, 0.1},
            {0.2, 0.6, 0.2},
            {0.1, 0.2, 0.7}
        };

    private EnemyState currentState;
    private int FramesInState;
    private static final int DWELL_FRAMES = 45;

    private static final int PATROL_FRAMES_EACH_DIRECTION = 30;
    private int patrolFrameCount;
    private int patrolHeading;

    // Constructor
    /**
     * Creates a new EvilSpirit enemy.
     * When the object is made, it is given a random starting
     * patrol direction ( an angle between 0 and 359 degrees). 
     * This makes each EvilSpirit begin facing a different way
     * so their movement feels less predictable.
     */
    public EvilSpirit()
    {
        // intialize fields
        this.health = 3;
        this.sideLength = 50;
        this.attacking = false;
        this.attackTimer = 0;
        this.maxChaseTime = 180;

        currentState = EnemyState.IDLE;
        FramesInState = 0;
        patrolHeading = Greenfoot.getRandomNumber(360);
    } // end no-arg constructor

    /**
     * Called each frame to control the Evil Spirit's behavior.
     * Handles timing, firing, movement, collisions, and state changes.
     */
    public void act() 
    {
        countSeconds();
        fireSpeed();
        if (seconds >= 5) {
            fireAtSpiderman();
            seconds = 0;
        }// end if
        turnAtEdge();
        checkHit();   
        if (getWorld() == null) 
        {
            return; // if the actor has already been removed stop the method
            // I added this prevent getting an Illegal pointer exception
        }

        checkCollision(); 
        if (getWorld() == null)
        {
            return; 
        }//end if

        FramesInState++;
        if(FramesInState >= DWELL_FRAMES)
        {
            currentState = nextState(currentState);
            FramesInState = 0;
        } // end if

        switch(currentState)
        {
            case IDLE:
                break;
            case PATROL:
                patrol();
                break;
            case ATTACK:
                attackSpiderman();
                break;

        }// end switch   
    } // end act method

    /**
     * Chooses the enemy's next state (IDLE, PATROL, or ATTACK) using probabilities
     * from the transition matrix. A random number determines which state is selected.
     *
     * @param current the enemy's current state
     * @return the next state chosen
     */
    private EnemyState nextState(EnemyState current)
    {
        double rand = Math.random();
        double cumulative = 0.0;
        int row = current.ordinal();

        for (int col = 0; col < transitionMatrix[row].length; col++)
        {
            cumulative += transitionMatrix[row][col];
            if (rand <= cumulative)
            {
                return EnemyState.values()[col];
            }// end if
        }// end for
        return current;
    }// end EnemyState nextState(EnemyState current) method

    /**
     * Moves the enemy back and forth to simulate a patrol.
     * The enemy walks forward each frame, then turns around after a set number of frames.
     */
    private void patrol()
    {
        move(2);
        patrolFrameCount++;
        if (patrolFrameCount >= PATROL_FRAMES_EACH_DIRECTION)
        {
            turn(180);
            patrolFrameCount = 0;
        }// end if
    }// end patrol method

    /**
     * Makes the enemy attack Spiderman by turning toward his position
     * and moving forward each frame.
     */
    private void attackSpiderman()
    {
        BaseWorld bw = (BaseWorld)getWorld();
        Spiderman spiderman = bw.getSpiderman();
        if (spiderman != null && spiderman.getWorld() != null)
        {
            turnTowards(spiderman.getX(), spiderman.getY());
            move(3);
        }// end if 
    }// end attackSpiderman method

    //Timers
    /**
     * Tracks time in seconds based on act cycles.
     * Increases actCounter each frame and resets after 60 to measure one second.
     */
    private void countSeconds() 
    {
        actCounter++;
        if (actCounter == 60)
        {
            seconds++;
            actCounter = 0;
        }// end if
    }// end countSeconds method

    //Projectile collision
    /**
     * This method checks if the spirit touches a web projectile. If it does,
     * the spirit loses health and the projectile is remoes. When health is 0 or less,
     * the spide is removed form the world and the counter goes down.
     */
    private void checkHit() 
    {
        if (isTouching(webProjectiles.class)) 
        {
            health--; // reduce spirit’s health
            removeTouching(webProjectiles.class);

            if (health <= 0) 
            {
                World currentWorld = getWorld(); 
                if (currentWorld != null) 
                { 
                    BaseWorld world = (BaseWorld) currentWorld;
                    world.subtractSpiritCounter(1);
                    if (!world.getObjects(Score.class).isEmpty()) 
                    {
                        world.getManager().addScore(25);
                    }// end if
                    world.removeObject(this);
                }// end if
            }// end if
        }// end if
    }// end check hit method

    //Collision with Spiderman
    /**
     * This method checks if the spirit touches Spiderman. If it does,
     * the world will take awat health (25 in the Backgound, and 50 in SecondLevel).
     * After that the spirit is removed from the world.
     */
    private void checkCollision() 
    {
        if (isTouching(Spiderman.class)) 
        {
            World currentWorld = getWorld();
            if (currentWorld instanceof Background) 
            {
                ((Background)currentWorld).takeHealth(15);
            } // end if 
            if (currentWorld instanceof SecondLevel) 
            {
                ((SecondLevel)currentWorld).takeHealth(25);
            }// end if
            else if (currentWorld instanceof LastLevel) 
            {
                ((LastLevel)currentWorld).takeHealth(35);
            }// end if
            GameManager manager = ((BaseWorld)getWorld()).getManager();
            manager.addScore(-100);

            getWorld().removeObject(this);
            return;
        }// end if
    }// end checkCollsion method

    //Movement
    /**
     * This method makes the actor turn around when it reaches ther edge
     * of the world. It checks if the actor is at the edge and then rotates
     * it by 180 degrees.
     */
    private void turnAtEdge() 
    {
        if (isAtEdge())
        {
            setRotation(getRotation() + 180);
        }// end if
    }// end turnAtEdge method

    /**
     * This method makes the actor move around the world. It moves forward 
     * and sometmes turns randomly. If Spiderman is close (less than 150),
     * the ator will start attacking him and reset the attack timer.
     */
    private void roam() 
    {
        move(2);
        if (Greenfoot.getRandomNumber(100) < 2) 
        {
            turn(Greenfoot.getRandomNumber(90) - 45);
        }// end if

        if (getWorld() == null) 
        {
            return;
        }

        BaseWorld bw = (BaseWorld)getWorld();
        Spiderman spiderman = bw.getSpiderman();

        if (spiderman != null && spiderman.getWorld() != null) 
        {
            int dx = spiderman.getX() - getX();
            int dy = spiderman.getY() - getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < 150) 
            {
                attacking = true;
                attackTimer = 0;
            }// end if
        }// end if
    }// end roam method

    // --- Firing ---
    /**
     * This method controls how often the actor fires at Spiderman.
     * When the seconds counter is 5 or more, it will call fireAtSpiderman() 
     * and then reset the counter back to 0.
     */
    private void fireSpeed() 
    {
        if (seconds >= 5) 
        {
            fireAtSpiderman();
            seconds = 0;
        }// end if
    }// end fireSpeed method

    /**
     * This method makes the enemy shoot a projectile at Spiderman. It first check if
     * the world and Spiderman exits and if the cooldown is 0.
     * If Spiderman is close (within 150),
     * it creates a new projectile, points it toward Spiderman, and adds it
     * to the world. Then the cooldown is set so it waits before firing agian.
     */
    private void fireAtSpiderman() {
        if (getWorld() == null) return;

        BaseWorld world = (BaseWorld)getWorld();
        Spiderman spiderman = world.getSpiderman();

        if (spiderman != null && spiderman.getWorld() != null && coolDown == 0) 
        {
            if (isInRange(spiderman, 150)) 
            {
                enemyProjectile projectile = new enemyProjectile();
                getWorld().addObject(projectile, getX(), getY());

                int dx = spiderman.getX() - getX();
                int dy = spiderman.getY() - getY();
                double angle = Math.atan2(dy, dx);

                int rotation = (int)Math.toDegrees(angle);
                projectile.setRotation(rotation);

                coolDown = 60; // wait 1 second before next shot
            }// end if
        }// end if
    }// end fireAtSpiderman method

    /**
     * This method check if Spiderman is cloes enough to the actor.
     * It finds the distance between the actor and Spiderman and 
     * and returns true if the distanc is less than or equal to the given range.
     */
    private boolean isInRange(Spiderman spiderman, int range) 
    {
        int dx = spiderman.getX() - getX();
        int dy = spiderman.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= range;
    }// end isInRange method

    //Getter
    /**
     * This method gives back the Spiderman object from the world. It just returns
     * the current Spiderman.
     * 
     * @return the current Spiderman object
     */

    public Spiderman getSpiderman() 
    {
        return spiderman;
    }// end getter
}// end class