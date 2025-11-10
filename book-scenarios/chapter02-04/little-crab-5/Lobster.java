import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A lobster. Lobsters live on the beach. They like to eat crabs. (Well, in our game
 * they do...)
 * 
 * The lobster walks around randomly. If it runs into a crab it eats it.
 * 
 * @author _______@email.uscb.edu
 * @version 145fa25 ICE for 10 Nov 2025
 */
public class Lobster extends Actor
{
    /* FIELDS / INSTANCE VARIABLES / CONSTANTS */
    
    // NOTE #1: an `enum` (short for "enumeration") is named as such because you ENUMERATE or "list"
    //          only the valid values (An "enumeration," as a noun, would mean a list of things -- 
    //          it doesn't refer to "numerals" necessarily... However, in Java, every enum constant has
    //          an implicit zero-based ORDINAL value that reflects the order in which it is declared.
    //      
    // NOTE #2: Here we have defined our enum, `State` as a private field of each Lobster object. 
    //          However, if you needed an enum to belong to the overall state or status of the 
    //          game itself, it might make sense to declare the enum separately (similar to a 
    //          "helper class"), like what Dr. Canada did for the Greenfoot version of "Pong"
    //          (see https://github.com/doctorcanada/pong-greenfoot/)
    private enum State { 
        CHASE,  // ordinal 0 
        PATROL, // ordinal 1
        WANDER  // ordinal 2
    } // end enum State 

    // 2D array used to model 3x3 transition matrix (rows=current state, cols=next state)
    // row 0=CHASE, row 1=PATROL, row 2=WANDER
    // Follows a "Steady Guard" feel/vibe, adapted as follows:
    // From CHASE : stay in CHASE 0.60,    change to PATROL 0.20,  or change to WANDER 0.20
    // From PATROL: change to CHASE 0.25,  stay in PATROL   0.60,  or change to WANDER 0.15
    // From WANDER: change to CHASE 0.30,  change to PATROL 0.40,  or stay in WANDER 0.30
    //
    // NOTE #1: The keyword `final` is normally used for declaring a CONSTANT, but here it 
    //          means that the identifier `LobsterStateTransitionMatrix`provides a 
    //          "constant reference" to the array shown below; while the reference cannot
    //          be changed to a different array, one could still technically modify the 
    //          individual array elements (here, the transition probabilities) 
    // NOTE #2: The keyword `static` here means that the same transition matrix belongs to
    //          ALL Lobster instances (and not to any specific Lobster object). 
    // NOTE #3: For your projects, you will only be permitted to use `static` when declaring CONSTANTS
    //          (using `static final`, specifically) 
    private static final double[][] LobsterStateTransitionMatrix = { {0.60, 0.20, 0.20}, 
                                                                     {0.25, 0.60, 0.15}, 
                                                                     {0.30, 0.40, 0.30} };

    // Fields to support logic of preventing "jittering" too quickly between states
    // NOTE: Again, `static final` is common for declaring CONSTANTS, but do NOT use `static` for
    //       variable fields (e.g., instance variables) or else your grade will be SIGNIFICANTLY PENALIZED
    private static final int DWELL_FRAMES = 45; // wait this many act() calls before possible state change
    private int countOfFramesInState;

    // Movement speed
    // NOTE: Again, `static final` is common for declaring CONSTANTS, but do NOT use `static` for
    //       variable fields (e.g., instance variables) or else your grade will be SIGNIFICANTLY PENALIZED    
    private static final int SPEED = 3; // was originally 5, but might be too fast to be fair
                                        // Side note: Change crab walking speed to 4 (instead of 5)
                                        //            for better "balance" between easy and challenging

    // When in `PATROL` state, go straight for a given number of frames, then flip 180 degrees
    // NOTE: Again, `static final` is common for declaring CONSTANTS, but do NOT use `static` for
    //       variable fields (e.g., instance variables) or else your grade will be SIGNIFICANTLY PENALIZED  
    private static final int PATROL_FRAMES_EACH_DIRECTION = 30; // adjust as needed
    private int patrolFrameCount;
    private int patrolHeading;

    // Current state of this Lobster (State.CHASE, State.PATROL, or State.WANDER)
    private State currentState;

    // Reference to the Crab player-character
    private Crab targetCrab;

    /* CONSTRUCTOR(S) */
    /** 
     * One simple "no-arg" constructor for initializing Lobster's fields
     */
    public Lobster() {
        // Start in a known mode
        currentState = State.WANDER;

        // Reset all runtime counters/bookkeeping
        countOfFramesInState = 0;
        patrolFrameCount = 0;

        // We'll lock this angle on first PATROL frame so the Lobster's current rotation is maintained
        patrolHeading = 0;

        // Target is discovered duirng act()/getReferenceToTargetCrab()
        targetCrab = null;

        // For gameplay variety, set this Lobster's direction to a random int 
        setRotation(Greenfoot.getRandomNumber(360));
    }
    
    /* METHODS */
    public void act()
    {
        // get a reference to the Crab player-character (or "re-find" if needed)
        targetCrab = getReferenceToTargetCrab();

        // Given the current state, determine the flow of control accordingly
        // (could be refactored to go in its own method)
        if (currentState == State.CHASE) 
        {
            if (targetCrab != null) 
            {
                turnTowards(targetCrab.getX(), targetCrab.getY());
            } // end inner if
            
            move(SPEED);
            turnAtEdge();
        } 
        else if (currentState == State.PATROL) 
        {
            // on the very first frame in PATROL, lock a heading
            if (countOfFramesInState == 0 && patrolFrameCount == 0) 
            {
                patrolHeading = getRotation();
            } // end inner if
            
            setRotation(patrolHeading);
            move(SPEED);
            patrolFrameCount++;

            // flip direction after given # of frames or if we collide with the world edge 
            if (patrolFrameCount >= PATROL_FRAMES_EACH_DIRECTION || isAtEdge()) 
            {
                patrolHeading = (patrolHeading + 180) % 360; // `+ 180` flips direction, then `% 360`
                                                             // ensures patrolHeading is still 0 to 360
                setRotation(patrolHeading);
                patrolFrameCount = 0;
            } // end inner if
        } 
        else 
        { 
            // WANDER state (follows original Lobster logic)
            turnAtEdge();
            randomTurn();
            move(SPEED);
        } // end multi-way if/else

        // Eat crab if touching (from original Lobster logic)
        lookForCrab();

        // For timing state change (NOTE: this could go in its own method)
        countOfFramesInState++;
        if (countOfFramesInState >= DWELL_FRAMES) // ensure no state change until "dwell time" has passed
        {
            // call method to randomly sample possible new state from the 
            // current row in transition matrix
            State possibleNextState = randomlySamplePossibleNextState(); 
            
            if (possibleNextState != currentState) 
            {
                // update the current state and reset counter
                currentState = possibleNextState;
                countOfFramesInState = 0;
                
                /* log debug message to console (for testing only; comment this before "release") */
                // System.out.println(this + " has changed state to " + currentState);
                
                // reset patrol bookkeeping when we enter PATROL
                if (currentState == State.PATROL) 
                {
                    patrolFrameCount = 0;
                    patrolHeading = getRotation();
                } // end INNERMOST if
            } 
            else 
            {
                // stayed in same state; and reset counter
                countOfFramesInState = 0;
            } // end INNER if/else
        } // end OUTER if
    } // end method act


    /**
     * Returns the updated State of this Lobster to the calling method
     */
    private State randomlySamplePossibleNextState()
    {
        // Map current state to row index (there are more efficient 
        // ways of doing this that I might introduce later if time permits)
        int currentStateRowIndex;
        
        if (currentState == State.CHASE) 
        { 
            currentStateRowIndex = 0;
        }
        else if (currentState == State.PATROL) 
        {
            currentStateRowIndex = 1;
        }
        else 
        { 
            currentStateRowIndex = 2; // WANDER
        } // end multi-way if/else

        // probability of transitioning to CHASE (for the current matrix row)
        double probChase = LobsterStateTransitionMatrix[currentStateRowIndex][0]; 
        // probability of transitioning to PATROL (for the current matrix row)
        double probPatrol = LobsterStateTransitionMatrix[currentStateRowIndex][1]; 
        // probability of transitioning to WANDER (for the current matrix row)
        double probWander = LobsterStateTransitionMatrix[currentStateRowIndex][2]; 

        // produce a random number in the range [0.0,1.0)
        // (i.e., 0.0 inclusive, 1.0 exclusive)
        // NOTE: could also use Java's Math.random() method or other approaches
        double randomValue = Greenfoot.getRandomNumber(100) / 100.0;

        // perform a "cumulative check" to see if the random double value 
        // falls within the probability range for a given state
        // NOTE: When coding a multi-way if/else statement, always code
        // the most specific condition (or "narrowest range" of possible values)
        // first, and then code in order of most specific to least specific
        if (randomValue < probChase) 
        {
            return State.CHASE; // exits method, returns (possibly new) State to method caller
        } 
        else if (randomValue < probChase + probPatrol) 
        {
            return State.PATROL; // exits method, returns (possibly new) State to method caller
        } 
        else 
        {
            return State.WANDER; // exits method, returns (possibly new) State to method caller
        } // end multi-way if/else
    } // end method sampleNextState

    /**
     * Returns a reference to the Crab object 
     */
    public Crab getReferenceToTargetCrab()
    {
        if (targetCrab == null) 
        {
            java.util.List listOfPossibleCrabsOnScreen = getWorld().getObjects(Crab.class);
            
            if (!listOfPossibleCrabsOnScreen.isEmpty()) 
            {
                targetCrab = (Crab) listOfPossibleCrabsOnScreen.get(0);
            }
        } // end if
        
        return targetCrab;
    } // end method 
    
    /**
     * look for a crab; if the lobster is intersecting a crab object,
     * then we will "eat" (remove) that crab object. If not,
     * then do nothing.
     */
    public void lookForCrab()
    {
        if ( isTouching( Crab.class ) )
        {
            removeTouching( Crab.class );
            Greenfoot.playSound("au.wav");
            Greenfoot.stop(); // stop the game
        } // end if
    } // end method lookForCrab

    /** 
     * Make the lobster turn by up to 45 degrees, at random,
     * either left or right, ten percent of the time
     */
    public void randomTurn()
    {
        if ( Greenfoot.getRandomNumber(100) < 10 )
        {
            turn( Greenfoot.getRandomNumber( 91 ) - 45 );
        } // end if
    } // end method randomTurn
    
    /**
     * if the lobster intersects with the boundary of
     * the world, make it turn enough so that it can
     * keep moving
     */
    public void turnAtEdge()
    {
        if ( isAtEdge() )
        {
            turn(17); // turn to the right by 17 degrees
        } // end if
    } // end method turnAtEdge
} // end class Lobster


/* 
 * "Housekeeping" suggestion:
 * Please DELETE any older, unused code that you have 
 * commented-out; if you really need to refer to older code,
 * you can always review your commit history on Bitbucket
 */