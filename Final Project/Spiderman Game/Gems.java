import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * These gems represent the gems of the Breast Plate of Aaron.
 * They can stay still, move, or disappear based on state transitions
 * 
 * @author Terrance Dessaure 
 * @version 12.6.25
 */
public class Gems extends Actor
{
    //Fields
    private GameManager manager;

    // these are the posible states for a gem
    public enum GemState 
    {
        STATIC,
        MOVING,
        DISAPPEARING
    }// end enum

    // this is the transition matrix i used for transition 
    double[][] transitionMatrix = 
        {
            {0.6, 0.3, 0.1},// From STATIC
            {0.2, 0.6,0.2}, // From MOVING
            {0.0, 0.0, 1.0} // From DISAPPEARING       
        };

    private GemState currentState = GemState.STATIC;
    private int framesInState;
    private static final int DWELL_FRAMES = 60;

    // constuctor
    /**
     * Creates a new gem starting in the STATIC state.
     */
    public Gems()
    {
        //initialize fields
        this.framesInState = 0;
    }//end Gems method

    /**
     * Chooses the next state based on probabilities.
     *
     * @param current the current state
     * @return the next state
     */
    private GemState nextState(GemState current) 
    {
        double rand = Math.random();
        double cumulative = 0.0;
        int row = current.ordinal();

        for (int col = 0; col < transitionMatrix[row].length; col++) 
        {
            cumulative += transitionMatrix[row][col];
            if (rand <= cumulative) 
            {
                return GemState.values()[col];
            }// end if
        }// end for
        return current;
    }// end GemState nextState(GemState current) method

    /**
     * Runs each frame: updates state, moves if needed,
     * disappears when required, and checks collisions.
     */
    public void act()
    {
        if (getWorld() == null) 
        {
            return; //  check if gem is already removed
        }

        framesInState++;
        if (framesInState >= DWELL_FRAMES)
        {
            currentState = nextState(currentState);
            framesInState = 0;
        }// end if

        switch (currentState) 
        {
            case STATIC:
                // do nothing
                break;
            case MOVING:
                move(2);
                if (isAtEdge()) 
                {
                    turn(180);
                }// end if
                break;
            case DISAPPEARING:
                getWorld().removeObject(this);
                return; // stop after removal
        }// end switch

        checkCollision();
    }

    /**
     * Moves faster if enough gems have been collected.
     */
    private void gemProgression()
    {
        if (manager != null && manager.getGemCounter() >=  3)
        {
            move(5);
            if (isAtEdge())
            {
                turn(180);
            }// end if
        }// end if
    }// end gemProgression method

    /**
     * Checks if Spiderman collected the gem.
     * If so, adds to gem count, score, shows a message,
     * and removes the gem from the world.
     */
    private void checkCollision()
    {
        if (isTouching(Spiderman.class)) 
        {
            Spiderman spiderman = (Spiderman) getOneIntersectingObject(Spiderman.class);
            BaseWorld world = (BaseWorld) getWorld();

            world.getManager().addGem(1); 
            world.addScore(100);
            world.addObject(new Message("+1", 180), 100, 40);
            world.removeObject(this);

            spiderman.sayQuip();
        }
    }// end checkCollision
}// end Gems class
