import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actor
{
    private int points = 0;

    public Score() {
        updateImage();
    }

    public void addPoints(int value)
    {
        points += value;
        updateImage();
    }
    
    public void subtractPoint(int value)
    {
        points -= value;
        updateImage();
    }

    private void updateImage() 
    {
        setImage(new GreenfootImage("Score: " + points, 30, Color.WHITE, Color.BLACK));
    }

    public int getPoints() 
    {
        return points;
    }

}
