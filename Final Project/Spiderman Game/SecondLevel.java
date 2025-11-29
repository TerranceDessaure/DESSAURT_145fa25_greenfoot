import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SecondLevel here.
 * 
 * @author Terrance 
 * @version 11/29/2025
 */
public class SecondLevel extends World
{
    private Spiderman spiderman;
    private int health;
    private int gemCounter;
    private int time;
    /**
     * Constructor for objects of class SecondLevel.
     * 
     */
    public SecondLevel(Spiderman spiderman, int health, int gemCounter, int time)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        this.spiderman = spiderman;
        this.health = health;
        this.gemCounter = gemCounter;
        
        addObject(spiderman, 300, 200); 
        showText("Welcome to Level 2!", 300,50);
        showText("Level 2!", 300, 50);
        showText("Health:" + health, 50, 20);
        showText("Gems: " + gemCounter, 40, 40);
        showText("Time Passed: " + time, 81, 60);


        
    }
    
    private void prepare()
    {
        spiderman = new Spiderman();
        addObject(spiderman, 286, 224);
    }
}
