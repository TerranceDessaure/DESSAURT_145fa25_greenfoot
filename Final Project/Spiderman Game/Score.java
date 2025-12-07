import greenfoot.*;

/**
 * Dispays the player's score on screen.
 * Updates each frame based on GameManager
 * 
 * @author Terrance Dessaure
 * @version 12.6.2025
 */
public class Score extends Actor 
{
    // Constructor
    /**
     * Creates a Score display starting at 0
     */
    public Score() 
    {
        updateImage(0); // start with 0 until GameManager updates
    }// end cpnstructor

    //methods
    /**
     * Updates the score display each frame.
     */
    public void act() 
    {
        GameManager manager = ((BaseWorld)getWorld()).getManager();
        setImage(new GreenfootImage("Score: " + manager.getScore(), 24, Color.WHITE, null));
    }// emd act method

    /**
     * Updates the score image with the given points.
     * 
     * @param point the score to display
     */
    private void updateImage(int points) 
    {
        setImage(new GreenfootImage("Score: " + points, 30, Color.WHITE, Color.BLACK));
    }// end updateImage
}//end Score class
