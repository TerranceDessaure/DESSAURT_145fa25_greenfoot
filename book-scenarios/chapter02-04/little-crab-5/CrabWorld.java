import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

/**
 * The CrabWorld is the place where crabs and other creatures live. 
 * It creates the initial population.
 * 
 * @author _username_@email.uscb.edu
 * @version ICE for Fri 7 Nov 2025
 */
public class CrabWorld extends World
{
    // As a Java programming convention: always organize "non-application" classes 
    // (e.g., classes from which objects are instantiated) in this order: 
    // FIELD(S), followed by any CONSTRUCTOR(S), then followed by any METHOD(S)
    
    /* FIELD(S) */ 
    private Crab myCrab;          // instance variable for the player-character object (now has class-wide SCOPE!)
    private Lobster[] lobsters;   // declare 1-D array of Lobster object references
    //private int[] lobsterXcoords; // declare 1-D array of ints 
    //private int[] lobsterYcoords; // declare 1-D array of ints
    
    // NOTE: At the start of today's ICE (Friday 11/7), make sure your line numbers are visible, 
    //       and then we'll begin by scrolling down to line 44 below (inside the constructor)
    
    // TODO: We could keep the 1-D arrays we created for storing the X- and Y- coordinates
    //       of each lobster, but let's instead "consolidate" these two 1-D arrays into a 
    //       single 2-D array called `lobsterCoords`
    
    private int[][] lobsterCoords; // declare 2-D array of ints - rows represent lobster (row indexes 0,1, and 2)
                                   // while columns represent the the X- and Y- coordinates of each lobster 
                                   // (the X-coordinate is at column index 0, while the Y-coordinate is at column )
    
   
    /* CONSTRUCTOR(S) */
    /**
     * Create the crab world (the beach). Our world has a size 
     * of 560x560 cells, where every cell is just 1 pixel.
     */
    public CrabWorld() 
    {
        super(560, 560, 1); // remember, you can't make a pepperoni pizza without
                            // making a cheese pizza first! 
        
        lobsters = new Lobster[3];   // instantiate "empty" 1-D array capable of holding 
                                     // 3 Lobster object references (default value for each element = null) 
        //lobsterXcoords = new int[3]; // instantiate "empty" 1-D array of 3 (three) ints 
                                     // (default element value = 0)
        //lobsterYcoords = new int[3]; // instantiate "empty" 1-D array of 3 (three) ints 
                                     // (default element value = 0)
       
        // TODO: Refactor to instantiate a 2-D array of ints instead of instantiating 
        //       two separate 1-D arrays
        
        lobsterCoords = new int[3][2];// instantiate "empty" 2-D array of ints, with 3 rows, and 2 columns in each row
                                       // (as above, default element value is 0)
        
       
        // You could store values in the array, one element at a time...
        /*
        lobsterXcoords[0] = 334;
        lobsterYcoords[0] = 65;
        
        lobsterXcoords[1] = 481;
        lobsterYcoords[1] = 481;
        
        lobsterXcoords[2] = 79;
        lobsterYcoords[2] = 270;
        */
       
        // ...OR you could use ARRAY INITIALIZERS for more compact/readable syntax
        // (in this case, we wouldn't even need to instantiate the empty 1-D arrays above,
        //  so you can comment out the statements that use `new int[3]` on the RHS
        /*
        lobsterXcoords = new int[] { 334, 481, 79 };
        lobsterYcoords = new int[] { 65, 481, 270 };
        */
       
        /*
         * HOWEVER, for BEST PRACTICE (clarity, compactness, readability, and other reasons
         * to be discussed later in the course): we can use array initializers
         * to populate the arrays in the SAME statements used to declare the 
         * original array variables (in the "FIELDS" section at the top of the class):
         * 
         * Note that the `new` keyword isn't needed in this situation!
         * 
         * private int[] lobsterXcoords = {334, 481, 79};
         * private int[] lobsterYcoords = {65, 481, 270);
         * 
         * Just be aware that if you prefer to use the CONSTRUCTOR for initializing
         * your 1-D arrays, you will need to use the `new int[]` syntax shown above.
         */
        
        
        // TODO: Refactor the above to store the same data in the 2-D array, `lobsterCoords`
        //       remember that the row indices (i.e., the "outer" array elements) represent the lobsters
        //       while the column indices (for the elements of the "inner" arrays) represent the X- and Y- 
        //       coordinates, respectively, of a lobster at the given row index
        
        lobsterCoords[0][0] = 334; // i.e., lobster at row index 0 has X-coordinate 334 
        lobsterCoords[0][1] = 65;  // i.e., lobster at row index 0 has Y-coordinate 65
        
        lobsterCoords[1][0] = 481; // i.e., lobster at row index 1 has X-coordinate 481
        lobsterCoords[1][1] = 481; // i.e., lobster at row index 1 has Y-coordinate 481
       
        lobsterCoords[2][0] = 79;  // i.e., lobster at row index 2 has X-coordinate 79
        lobsterCoords[2][1] = 270; // i.e., lobster at row index 2 has Y-coordinate 270
        
       
        /*
         * Can you refactor again, this time using ARRAY INITIALIZERS
         * for lobsterXcoords and lobsterYcoords?
         */
        /*
        lobsterCoords = ...;
        */
       
        /*
         * Can you refactor again, this time using a NESTED set of ARRAY INITIALIZERS
         * for initializing the values of the 2-D array, `lobsterCoords`?
         */
        /*
        lobsterCoords = ...;
        */
       
        /*
         * do everything else needed to populate the world for the 
         * start of the game
         */
        prepare();
    }
    
    /* METHOD(S) */
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        myCrab = new Crab(); // Note that `myCrab` is now declared as an instance variable (a field), with class-wide scope
        addObject(myCrab, 231, 203);
        /*
        lobsters[0] = new Lobster();
        addObject( lobsters[0], lobsterXcoords[0], lobsterYcoords[0] );
        
        lobsters[1] = new Lobster();
        addObject( lobsters[1], lobsterXcoords[1], lobsterYcoords[1] );
        
        lobsters[2] = new Lobster();
        addObject( lobsters[2], lobsterXcoords[2], lobsterYcoords[2] );
        */
        // TODO: Refactor the statements above to use the `lobsterCoords` 2-D array
        
        lobsters[0] = new Lobster();
        addObject( lobsters[0], lobsterCoords[0][0], lobsterCoords[0][1] );
        
        lobsters[1] = new Lobster();
        addObject( lobsters[1], lobsterCoords[1][0], lobsterCoords[1][1] );
        
        lobsters[2] = new Lobster();
        addObject( lobsters[2], lobsterCoords[2][0], lobsterCoords[2][1] );
        
       
        // TODO: Refactor the six statements above into a counter-controlled for loop
        //       (Question: what do you think is the value of `lobsters.length` below?)
        /*
        for ( int lobsterIndex = 0; lobsterIndex < lobsters.length; lobsterIndex++ )
        {
            lobsters[?] = new Lobster();
            addObject( lobsters[?], lobsterCoords[?][?], lobsterCoords[?][?] );
        } // end for
        */
       
        // place 10 worms at randomly-generated X- and Y-coordinates
        // All loops must have these 3 components in order to work properly 
        // (and not result in an "infinite loop")
        // 1) Initialization of a counter variable (a.k.a. your "control variable")
        // 2) Loop-continuation condition (if false, then we exit the loop)
        // 3) Updating the counter variable (usually by incrementing or decrementing)
        
        // Coding a `while` loop 
        /*
        int wormIndex = 0; // initialize counter
        
        while ( wormIndex < 10 ) {
            addObject( new Worm(), Greenfoot.getRandomNumber(511) + 25, 
                                   Greenfoot.getRandomNumber(511) + 25);
            wormIndex = wormIndex + 1; // increment counter by 1
        } // end while
        // execution resumes here after exiting the loop
        
        // Helpful analogy: We can think of a `while` loop as being like an if-statement that repeats
        */
       
        // NOTE: As with a `while` loop, the loop-continuation condition for a `for` loop
        //       is evaluated at the START of an iteration. However, note that the SCOPE 
        //       of the counter variable, wormIndex, is LIMITED to the `for` loop itself 
        //       *if* the counter variable is DECLARED INSIDE THE FOR-LOOP's HEADER
        
        // Refactoring the `while` loop as a `for` loop
        // NOTE: Because `wormIndex` is declared INSIDE the for-loop HEADER,
        //       the scope of `wormIndex` is limited to the `for` statement (header + body).
        //       It is NOT visible after the loop's closing brace (see ** below)
        for ( int wormIndex = 0; wormIndex < 10; wormIndex++ ) {
            addObject( new Worm(), Greenfoot.getRandomNumber(511) + 25, 
                                   Greenfoot.getRandomNumber(511) + 25);
        } // end for
        
        // System.out.println(wormIndex); // <-- **For example, uncommenting this would cause a syntax error
        
        // Refactoring as a `do...while` loop
        /*
        int wormIndex = 0; // the scope of this variable is from the declaration to the end of the method
        
        do {
            addObject( new Worm(), Greenfoot.getRandomNumber(511) + 25, 
                                   Greenfoot.getRandomNumber(511) + 25);
            wormIndex = wormIndex + 1; // increment counter by 1    
        } while (wormIndex < 10); // in a do...while loop, the loop continuation condition
                                  // is evaluated at the END of the current iteration, not at the start
        */
       
        // When to code each type of loop, in general? 
        /*
         * 1) if you know EXACTLY how many repetitions are needed, code a `for` loop
         * 2) if you're unsure how many repetitions are needed AND want to allow for the 
         *    possibility that your loop might NOT execute even ONCE, code a `while` loop
         * 3) if you're unsure how many repetitions are needed AND you need to allow for 
         *    the possibility that your loop MUST execute at least ONCE, code a `do-while`
         *    loop
         */
              
        // SOME FINAL NOTES on LOOPS:
        // 1) Be mindful of the SCOPE of the loop variable (that is, where in a method the 
        //    variable can be accessed) -- scope depends on where the variable is declared!
        // 2) Any `while`, `for`, or `do...while` loop can be re-expressed as either of the 
        //    other types of loops with logical, but most programmers will
        //    follow one of the 3 options above regarding when to code each type of loop     
    } // end method prepare
    
} // end class CrabWorld