import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MyCat is your own cat. Get it to do things by writing code in its act method.
 * 
 * @author (Terrance Desaure) 
 * @version (a version number or a date)
 */
public class MyCat extends Cat
{

    private boolean isSleepy = false;
    private boolean isBored = true;
    private boolean isHungry = true;
    /**
     * Act - do whatever the MyCat wants to do.
     */
    public void act()
    {
        if (isBored){
            dance();
        }// end isBored if

        shoutHooray();
        if (isSleepy){
            sleep(1);
        }// end isSleepy if 
        
        if (isHungry){
            eat();
        }// end isHungry if

       
    }// end method act    

}// end class MyCat
