import greenfoot.*;

public class Score extends Actor {

    public void act() {
        GameManager manager = ((BaseWorld)getWorld()).getManager();
        setImage(new GreenfootImage("Score: " + manager.getScore(), 24, Color.WHITE, null));
    }

    // Constructor
    public Score() {
        updateImage(0); // start with 0 until GameManager updates
    }

    // Update image using manager’s score
    private void updateImage(int points) {
        setImage(new GreenfootImage("Score: " + points, 30, Color.WHITE, Color.BLACK));
    }
}