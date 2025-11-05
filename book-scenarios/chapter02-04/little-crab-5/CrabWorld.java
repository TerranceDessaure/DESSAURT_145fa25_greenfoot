import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

/**
 * The CrabWorld is the place where crabs and other creatures live. 
 * It creates the initial population.
 */
public class CrabWorld extends World
{
    /**
     * Create the crab world (the beach). Our world has a size 
     * of 560x560 cells, where every cell is just 1 pixel.
     */
    public CrabWorld() 
    {
        super(560, 560, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        Crab crab = new Crab();
        addObject(crab, 231, 203);

        Lobster lobster = new Lobster();
        addObject(lobster, 334, 65);
        Lobster lobster2 = new Lobster();
        addObject(lobster2, 481, 481);
        Lobster lobster3 = new Lobster();
        addObject(lobster3, 79, 270);

        // place 10 worms at randomly-gernerated X- and Y-coordinates
        // All loops must have these 3 components in order to work properly 
        // (and not  result in an "infinite loop")
        // 1) Initalization of a counter variable (a.k.a. your "control variable")
        // 2) Loop-continuation condtion (if false, then we exit the loop)
        // 3) Updating the counter variable (usually by incrementing or decrementing)

        // Coding a `while` loop
        /*
        int wormIndex = 0; // initiaize counter

        while ( wormIndex < 10 ) {
        addObject(new Worm(),Greenfoot.getRandomNumber(511) + 25,
        Greenfoot.getRandomNumber(511) + 25);
        wormIndex = wormIndex + 1; // increments counter by 1
        }// end while
        // execution resumes here after exiting the loop
         */
        // Helpful analogy: We can think of a `while` loop as being like an if-statement that repeats
        //       is evaluated at the start of an iteration. However, note that the SCOPE 
        //       of the counter variable, wormIndex, is LIMITED to the `for` loop itself 
        //       *if* the counter variable is DECLARED INSIDE THE FOR-LOOP's HEADER

        // Refactoring the `while` loop as a `for` loop
        /*
        for ( int wormIndex = 0; wormIndex < 10; wormIndex++ ) {

            addObject(new Worm(),Greenfoot.getRandomNumber(511) + 25,
                Greenfoot.getRandomNumber(511) + 25);
        }// end for
        */
       
       // Refactoring as a `do...while` loop
       int wormIndex = 0;// the scope of this variable is from the declaration to the end of the prepare method
       
       do {
           addObject(new Worm(),Greenfoot.getRandomNumber(511) + 25,
                                Greenfoot.getRandomNumber(511) + 25);
           wormIndex = wormIndex +1;
       } while (wormIndex < 10); //in a do while loop, the loop continuaion condition
                                 // is evaluated at the END of the current iteration, not at the start
         // ... each type of loop, in general? 
        /*
         * 1) if you know EXACTLY how many repetitions are needed, code a `for` loop
         * 2) if you're unsure how many repetitions are needed AND want to allow for the 
         *    possibility that your loop might NOT, code a `while` loop
         * 3) if you're unsure how many repetitions are needed AND you need to allow for 
         *    the possibility that your loop Once, code a `do...while`
         *    loop
         */                          
         // SOME FINAL NOTES:
        // 1) Be mindful of the ..> of the loop variable (that is, where in a method the 
        //    variable can be accessed) -- scope depends on ...!
        // 2) Any `while`, `for`, or `do...while` loop can be re-expressed as either of the 
        //    other types of loops with ..., but most programmers will
        //    follow the ... above regarding when to code each type of loop
 
    }// end method prepare
}// end class CrabWorld
