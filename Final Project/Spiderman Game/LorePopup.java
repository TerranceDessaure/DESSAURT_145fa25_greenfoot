import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LorPopup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LorePopup extends Actor
{
    private String message;

    public LorePopup(String message) 
    {
        this.message = message;
        updateImage();
    }

    private void updateImage() 
    {
        GreenfootImage img = new GreenfootImage(400, 100);
        img.setColor(new Color(0, 0, 0, 180)); // semi-transparent background
        img.fillRect(0, 0, 250, 100);
        img.setColor(Color.WHITE);
        img.drawString(message, 10, 50);
        setImage(img);
    }
    
    public void act() 
      {
        if (Greenfoot.isKeyDown("enter") || Greenfoot.isKeyDown("space")) 
        {
            getWorld().removeObject(this);
        }
    }
}

