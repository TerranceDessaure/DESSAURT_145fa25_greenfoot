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
        addObject(new Worm(),Greenfoot.getRandomNumber(511) + 25,
                             Greenfoot.getRandomNumber(511) + 25);
        
    }// end method prepare
}// end class CrabWorld
