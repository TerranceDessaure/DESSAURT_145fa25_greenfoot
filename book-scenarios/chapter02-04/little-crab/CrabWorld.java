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

        prepare();
    }//end CrabWorld constructor
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Crab crab = new Crab();
        addObject(crab,286,224);
        Lobster lobster = new Lobster();
        addObject(lobster,460,87);
        Lobster lobster2 = new Lobster();
        addObject(lobster2,61,477);
        Worm worm = new Worm();
        addObject(worm,368,174);
        Worm worm2 = new Worm();
        addObject(worm2,323,359);
        Worm worm3 = new Worm();
        addObject(worm3,236,369);
        Worm worm4 = new Worm();
        addObject(worm4,180,279);
        Worm worm5 = new Worm();
        addObject(worm5,235,154);
        Worm worm6 = new Worm();
        addObject(worm6,160,185);
        Worm worm7 = new Worm();
        addObject(worm7,135,357);
        Worm worm8 = new Worm();
        addObject(worm8,124,147);
        Worm worm9 = new Worm();
        addObject(worm9,195,71);
        Worm worm10 = new Worm();
        addObject(worm10,410,328);
        Lobster lobster3 = new Lobster();
        addObject(lobster3,496,484);
    }
}// end class CrabWorld
