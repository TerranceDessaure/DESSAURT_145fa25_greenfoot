import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AaronScreen2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingScreen1 extends BaseWorld
{

    private int timer = 0;   // counts frames
    private boolean shown = false; // track if text has been added

    private Spiderman spiderman;
    private GameManager manager;

    private String[] loreMessages = 
        {
            "The mandala represents unity and balance.",
            "Gems are inspired by the Breastplate of Aaron.",
            "Hearts restore vitality and symbolize resilience.",
            "Spirits embody challenges that test your strength.",
            "Blessings are gifts from the Loa, granting power."
        };

    /**
     * Constructor for objects of class StoryScreen2.
     * 
     */
    public LoadingScreen1(Spiderman spiderman, GameManager manager)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, spiderman, manager); 
        this.spiderman = spiderman;
        
        removeObjects(getObjects(Score.class));

        LoadingCircle loadingcircle = new LoadingCircle();
        addObject(loadingcircle, 780, 580);
        
        showRandomMessage();
    }

    public void act()
    {
        timer++;  // increase every frame

        // Greenfoot runs ~60 frames per second, so 5 seconds ≈ 300 frames
        if (timer >= 300 && !shown) 
        {
            addObject(new PressEnter(), getWidth()/2, 580); // bottom middle
            shown = true; // ensure it only adds once
        }
        
        if (Greenfoot.isKeyDown("space")) 
        {
            removeObjects(getObjects(LorePopup.class));
            showRandomMessage();
            Greenfoot.delay(10); // prevent rapid skipping
        }
        
        // If Enter is pressed after text is shown, go to next world
        if (shown && Greenfoot.isKeyDown("enter")) 
        {
            Greenfoot.setWorld(new SecondLevel(getSpiderman(), getManager())); // replace with your next world class
        }
    }

    private void showRandomMessage() 
    {
        int index = Greenfoot.getRandomNumber(loreMessages.length);
        String message = loreMessages[index];
        addObject(new LorePopup(message), getWidth()/2, getHeight()/2);
    }

}
