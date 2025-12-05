import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BaseWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BaseWorld extends World
{   protected Spiderman spiderman;
    protected GameManager manager;
    
    private int spiritCounter;
    
    

    public BaseWorld(int width, int height, int cellSize, Spiderman spiderman, GameManager manager) {
        super(800, 600, 1);
        this.spiderman = spiderman;
        this.manager = manager;

        // Add Spiderman to the world at a default position
        addObject(spiderman, 300, 200);
    }
    
    
    public void subtractSpiritCounter(int erase) 
    {
        spiritCounter -= erase;
    }
    

    public Spiderman getSpiderman() 
    {
        return spiderman;
    }

    public GameManager getManager() 
    {
        return manager;
    }
    
    public void showHUD() 
    {
        showText("Health: " + manager.getHealth(), 60, 20);
        showText("Gems: " + manager.getGemCounter(), 48, 40);
        showText("Time: " + manager.getTime(), 45, 60);
    }


}