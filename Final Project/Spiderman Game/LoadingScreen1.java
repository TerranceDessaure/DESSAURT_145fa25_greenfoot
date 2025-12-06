import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The LoadingScreen 1 class it a transistional screen used between levels 1 
 * and 2. It displays information about one of the four symbols that was assigned
 * and is being incorparted into the game. 
 * 
 * @author Terrance Dessaure
 * @version 12.6.25
 */
public class LoadingScreen1 extends BaseWorld
{
    //fields
    private int timer;
    private boolean shown;
    
    // The player character and GameManager to track and hold progression values
    private Spiderman spiderman;
    private GameManager manager;
    
    // Array of `lore` messages to be randomly displayed on screen
    private String[] loreMessages = 
        {
            "The mandala represents unity and balance.",
            "Gems are inspired by the Breastplate of Aaron.",
            "Hearts restore vitality and symbolize resilience.",
            "Spirits embody challenges that test your strength.",
            "Blessings are gifts from the Loa, granting power."
        };// end array

    // Constructor    
    /**
     * Constructs the LoadingScreen1 world with the given Spiderman and GameManager.
     * Removes the score display, adds a loading circle, and shows a random message from the
     * array.
     * 
     * @param spiderman the player character
     * @param manager   the game manager tracking score, health, and progression
     */
    public LoadingScreen1(Spiderman spiderman, GameManager manager)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, spiderman, manager); 
        this.spiderman = spiderman;
        
        // instantiate variables from field
        int timer = 0;
        boolean shown = false;
        
        removeObjects(getObjects(Score.class));// removes the score from the loading screen

        LoadingCircle loadingcircle = new LoadingCircle();
        addObject(loadingcircle, 780, 580);
        
        showRandomMessage();
        
    }// end LoadingScreen Contrutors
    
    // Methods
    /**
     * Called every frame. Updates the timer, shows the "Press Enter" prompt
     * after 5 seconds, allow the user to cycle through  lore messages with Space,
     * and transitions to the next world when Enter is pressed
     */
    public void act()
    {
        timer++;  
        if (timer >= 300 && !shown) 
        {
            addObject(new PressEnter(), getWidth()/2, 580); // bottom middle
            shown = true; // ensure it only adds once
        }//end if
        
        if (Greenfoot.isKeyDown("space")) 
        {
            removeObjects(getObjects(LorePopup.class));
            showRandomMessage();
            Greenfoot.delay(10); // prevent rapid skipping
        }// end if
        
        if (shown && Greenfoot.isKeyDown("enter")) 
        {
            Greenfoot.setWorld(new SecondLevel(getSpiderman(), getManager())); // replace with your next world class
        }// end if
    }// end act method
    
    /**
     * Displays the random lore message at the center of the screen
     */
    private void showRandomMessage() 
    {
        int index = Greenfoot.getRandomNumber(loreMessages.length);
        String message = loreMessages[index];
        addObject(new LorePopup(message), getWidth()/2, getHeight()/2);
    }// end showRandom method
}// end Loading screen1 Class
