import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * EvilSpirit enemy that can roam, chase Spiderman, and fire projectiles.
 * Loses health when hit by web projectiles.
 * 
 * @author Terrance Dessaure
 * @version (12/02/25)
 */
public class EvilSpirit extends Actor {
    private int health = 3;       // each spirit starts with 3 HP
    private int coolDown;         // projectile cooldown
    private int actCounter;       // frame counter
    private int seconds;          // second counter

    private int stepCounter;      // movement counter
    private int sideLength = 50;  // box movement length

    private boolean chasing = false;
    private int chaseTimer = 0;
    private int maxChaseTime = 180;

    private Spiderman spiderman;

    public void act() {
        countSeconds();
        fireSpeed();
        if (seconds >= 5) {
            fireAtSpiderman();
            seconds = 0;
        }
        turnAtEdge();
        checkHit();   
        if (getWorld() == null) return;
        
        checkCollision(); 
        if (getWorld() == null) return;

        if (chasing) {
            chaseSpiderman();
        } else {
            roam();
        }
    }

    // --- Timers ---
    private void countSeconds() {
        actCounter++;
        if (actCounter == 60) {
            seconds++;
            actCounter = 0;
        }
    }

    // --- Projectile collision ---
    private void checkHit() {
        if (isTouching(webProjectiles.class)) {
            health--; // reduce spirit’s health
            removeTouching(webProjectiles.class);

            if (health <= 0) {
                World currentWorld = getWorld();
                if (currentWorld instanceof Background) {
                    ((Background)currentWorld).subtractSpiritCounter(1);
                } else if (currentWorld instanceof SecondLevel) {
                    ((SecondLevel)currentWorld).subtractSpiritCounter(1);
                }
                getWorld().removeObject(this); // remove spirit itself
            }
        }
    }

    // --- Collision with Spiderman ---
    private void checkCollision() {
        if (isTouching(Spiderman.class)) {
            World currentWorld = getWorld();
            if (currentWorld instanceof Background) {
                ((Background)currentWorld).takeHealth(10);
            } else if (currentWorld instanceof SecondLevel) {
                ((SecondLevel)currentWorld).takeHealth(10);
            }
            getWorld().removeObject(this);
            return;
        }
    }

    // --- Movement ---
    private void turnAtEdge() {
        if (isAtEdge()) {
            setRotation(getRotation() + 180);
        }
    }

    private void roam() {
        move(2);
        if (Greenfoot.getRandomNumber(100) < 2) {
            turn(Greenfoot.getRandomNumber(90) - 45);
        }

        if (getWorld() == null) return;

        BaseWorld bw = (BaseWorld)getWorld();
        Spiderman spiderman = bw.getSpiderman();

        if (spiderman != null && spiderman.getWorld() != null) {
            int dx = spiderman.getX() - getX();
            int dy = spiderman.getY() - getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < 150) {
                chasing = true;
                chaseTimer = 0;
            }
        }
    }

    private void chaseSpiderman() {
        if (getWorld() == null) return;

        if (spiderman != null && spiderman.getWorld() != null) {
            turnTowards(spiderman.getX(), spiderman.getY());
            move(2);

            chaseTimer++;
            if (chaseTimer >= maxChaseTime) {
                chasing = false;
                chaseTimer = 0;
            }
        } else {
            chasing = false;
        }
    }

    // --- Firing ---
    private void fireSpeed() {
        if (seconds >= 5) {
            fireAtSpiderman();
            seconds = 0;
        }
    }

    private void fireAtSpiderman() {
        if (getWorld() == null) return;

        BaseWorld world = (BaseWorld)getWorld();
        Spiderman spiderman = world.getSpiderman();

        if (spiderman != null && spiderman.getWorld() != null && coolDown == 0) {
            if (isInRange(spiderman, 150)) {
                enemyProjectile projectile = new enemyProjectile();
                getWorld().addObject(projectile, getX(), getY());

                int dx = spiderman.getX() - getX();
                int dy = spiderman.getY() - getY();
                double angle = Math.atan2(dy, dx);

                int rotation = (int)Math.toDegrees(angle);
                projectile.setRotation(rotation);

                coolDown = 60; // wait 1 second before next shot
            }
        }
    }

    private boolean isInRange(Spiderman spiderman, int range) {
        int dx = spiderman.getX() - getX();
        int dy = spiderman.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= range;
    }

    // --- Getter ---
    public Spiderman getSpiderman() {
        return spiderman;
    }
}