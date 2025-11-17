import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (Terrance Dessaure) 
 * @version (a version number or a date)
 */
public class Background extends World
{

    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(750, 750, 1); 
        
        prepare();
    }// end Background constructor
    private void prepare()
    {
        Spiderman spiderman = new Spiderman();
        addObject(spiderman, 286, 224);
    }// end prepare method
    
}// end class Background
