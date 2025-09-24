 import greenfoot.*;  // (Actor, World, Greenfoot, GreenfootImage)

public class CrabWorld extends World
{
    /**
     * Create the crab world (the beach). Our world has a size 
     * of 560x560 cells, where every cell is just 1 pixel.
     */
    public CrabWorld() 
    {
        // set the size and resolution of the world
        super(560, 560, 1);
        Crab myCrab = new Crab();
        addObject( myCrab, 250, 200);
    }//end CrabWorld constructor
}// end class CrabWorld
