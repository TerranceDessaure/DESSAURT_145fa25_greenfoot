import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingScreen2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingScreen2 extends BaseWorld
{

    private int timer = 0;   // counts frames
    private boolean shown = false; // track if text has been added
    
    private Spiderman spiderman;
    private GameManager manager;
    private String[] loreMessages = 
    {
    "The Loa are guardians of balance, guiding mortals through trials unseen.",
    "Each Loa embodies a force of nature",
    "Blessings from the Loa are gifts of strength, but they must be earned.",
    "The Loa whisper wisdom in dreams, shaping destiny with subtle hands.",
    "To honor the Loa is to walk in harmony with the living world.",
    "Spirits of the Loa weave fate, binding heroes to paths of growth.",
   
    };

    /**
     * Constructor for objects of class StoryScreen2.
     * 
     */
    public LoadingScreen2(Spiderman spiderman, GameManager manager)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, spiderman, manager); 
        this.spiderman = spiderman;

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
            Greenfoot.setWorld(new LastLevel(getSpiderman(), getManager())); // replace with your next world class
        }
    }
    
    private void showRandomMessage() 
    {
        int index = Greenfoot.getRandomNumber(loreMessages.length);
        String message = loreMessages[index];
        addObject(new LorePopup(message), getWidth()/2, getHeight()/2);
    }

}
