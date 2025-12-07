import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The LoadingScreen 1 class it a transistional screen used between levels 1 
 * and 2. It displays information about one of the four symbols that was assigned
 * and is being incorparted into the game. 
 * 
 * @author Terrance Dessaure
 * @version 12.6.25
 */
public class LoadingScreen2 extends BaseWorld
{
    // Fields
    private int timer;
    private boolean shown;
    
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
    
    //Constructor
    /**
     * Constructs the LoadingScreen2 world with the given Spiderman and GameManager.
     * Removes the score display, adds a loading circle, and shows a random message from the
     * array.
     * 
     * @param spiderman the player character
     * @param manager   the game manager tracking score, health, and progression
     */
    public LoadingScreen2(Spiderman spiderman, GameManager manager)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, spiderman, manager); 
        this.spiderman = spiderman;
        
        // instantiate variables from field
        int time = 0;
        boolean shown = false;

        LoadingCircle loadingcircle = new LoadingCircle();
        addObject(loadingcircle, 780, 580);
        
        showRandomMessage();
    }// end LoadingScreen1 contrutor
    
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
            addObject(new PressEnter(), getWidth()/2, 580); 
            shown = true; 
        }// end if 
        
        if (Greenfoot.isKeyDown("space")) 
        {
            removeObjects(getObjects(LorePopup.class));
            showRandomMessage();
            Greenfoot.delay(10); 
        }// end if
        
        // If Enter is pressed after text is shown, go to next world
        if (shown && Greenfoot.isKeyDown("enter")) 
        {
            Greenfoot.setWorld(new LastLevel(getSpiderman(), getManager())); // replace with your next world class
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
    }// end showRandomMessage method

}// end LoadingScreen2

