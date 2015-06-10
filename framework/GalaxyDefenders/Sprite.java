package GalaxyDefenders;

import info.gridworld.actor.Actor;


/**
 * 
 * The Sprite class extends the Actor class. This class is called by the
 * GalaxyDefenders class and is used to create the images.
 *
 * @author Ben Liang, Andy Shen, Pavan Gollapalli
 * @version May 25, 2015
 * @author Period: 4
 * @author Assignment: GridworldFinal
 *
 * @author Sources:
 */
public class Sprite extends Actor
{
    private String name;


    /**
     * Constructor for the Sprite class
     * 
     * @param check
     *            Name of Sprite
     */
    public Sprite( String check )
    {
        name = check;
    }


    /**
     * Returns the name of the Sprite.
     * 
     * @return Name of Sprite
     */
    public String currName()
    {
        return name;
    }
}
