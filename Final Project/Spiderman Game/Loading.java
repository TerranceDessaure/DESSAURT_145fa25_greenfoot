import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Loading here.
 * 
 * @author Terrance Dessaure 
 * @version 12.6.25
 */
public class Loading extends World {
    private int timer = 0;   
    private boolean shown = false; 

    public Loading() 
    {    
        super(800, 600, 1); 
        LoadingCircle loadingcircle = new LoadingCircle();
        addObject(loadingcircle, 780, 580);
    }
    
    public void act() 
    {
        timer++; 
        
        
        if (timer >= 300 && !shown) 
        {
            addObject(new PressEnter(), getWidth()/2, 580);
            shown = true; 
        }// end if

        
        if (shown && Greenfoot.isKeyDown("enter")) 
        {
            Greenfoot.setWorld(new StoryScreen2());
        }// end if
    }// end act
}// end class

    
