import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * LorePopup displays a shore lore message in a semi-transparent box.
 * It disapperars when the player presses Enter or Space
 * 
 * @author Terrance Dessaure 
 * @version 12.6.25
 */
public class LorePopup extends Actor
{
    //Fields
    private String message;

    // Constructor
    /**
     * Creates a LorePopup with the give message.
     * 
     * @param message the text to display
     */
    public LorePopup(String message) 
    {
        this.message = message;
        updateImage();
    }// end one-arg constructor

    /**
     * Updates the popup image with the current message
     */
    private void updateImage() 
    {
        GreenfootImage img = new GreenfootImage(400, 100);
        img.setColor(new Color(0, 0, 0, 180)); // semi-transparent background
        img.fillRect(0, 0, 250, 100);
        img.setColor(Color.WHITE);
        img.drawString(message, 10, 50);
        setImage(img);
    }// end updateImage method
    
    /**
     * Runs each frame. Removes the popup if Enter or Space is pressed.
     */
    public void act() 
      {
        if (Greenfoot.isKeyDown("enter") || Greenfoot.isKeyDown("space")) 
        {
            getWorld().removeObject(this);
        }// end if
    }// end act method
}// end LorePopup class

