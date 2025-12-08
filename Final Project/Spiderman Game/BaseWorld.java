import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BaseWorld is the foundation for gameworlds.
 * It manages refernces to the main player Spiderman,
 * the Gamemanager, and the HUD.
 * 
 * @author Terrance Dessaure
 * @version 12.6.25
 */
public class BaseWorld extends World
{   
    /*Fields*/
    protected Spiderman spiderman;
    protected GameManager manager;

    private int spiritCounter;

    /**
     * Creates a new BaseWorld  with default dimensions and intializes
     * the plauer and manager references
     * 
     * @param width     unused parameter (world width is fixed at 800)
     * @param height    unused parameter (world height is fixed at 600)
     * @param cellSize  unused parameter (cell size is fixed at 1)
     * @param spiderman the player character to add to the world
     * @param manager   the game manager controlling state and scoring
     */
    public BaseWorld(int width, int height, int cellSize, Spiderman spiderman, GameManager manager) 
    {
        super(800, 600, 1);
        this.spiderman = spiderman;
        this.manager = manager;

        // Add Spiderman to the world at a default position
        addObject(spiderman, 300, 200);
        Score score = new Score();
        addObject(score, 700, 30);
    }// end constructor

    /*Methods*/
    /**
     * Decreases the spirit counter by the given amount.
     *
     * @param erase number of spirits to subtract
     */
    public void subtractSpiritCounter(int erase) 
    {
        spiritCounter -= erase;
    }// end setter methdod

    /**
     * Returns the player character.
     *
     * @return the Spiderman instance
     */
    public Spiderman getSpiderman() 
    {
        return spiderman;
    }// end getter

    /**
     * Returns the game manager.
     * 
     * @ return manager
     */
    public GameManager getManager() 
    {
        return manager;
    }// end getter

    /**
     * increase gem counter by one
     */
    public void addGem() 
    {
        manager.addGem(1);
    }// end setter

    /**
     * Adds points to the player's score.
     *
     * @param points number of points to add
     */
    public void addScore(int points) 
    {
        manager.addScore(points);
    }// end setter

    /**
     * Handles health deduction when a spirit collides with Spiderman.
     * Override in subclasses to customize damage values.
     */
    public void takeHealthOnCollision() 
    {
        manager.takeHealth(15); // default damage
    }

    /**
     * Displays the HUD  with health, gem count, and time.
     */
    public void showHUD() 
    {
        showText("Health: " + manager.getHealth(), 60, 20);
        showText("Gems: " + manager.getGemCounter(), 48, 40);
        showText("Time: " + manager.getTime(), 45, 60);
    }// end method

}// end class
