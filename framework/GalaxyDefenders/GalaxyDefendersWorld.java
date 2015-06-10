package GalaxyDefenders;

import info.gridworld.actor.Actor;
import java.util.LinkedList;
import info.gridworld.actor.ActorWorld;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


/**
 * The GalaxyDefendersWorld class extends ActorWorld. This class contains helper
 * methods for the functionality of the game. This includes overriding the
 * keyPressed method from Gridworld, a contains method to determine if a grid is
 * occupied by an actor, and a get method for the linkedlist containing the
 * aliens.
 *
 * @author Ben Liang, Andy Shen, Pavan Gollapalli
 * @version May 25, 2015
 * @author Period: 4
 * @author Assignment: GridworldFinal
 *
 * @author Sources:
 */
public class GalaxyDefendersWorld extends ActorWorld
{
    private Sprite s;

    private Bullet b;

    private PoweredBullet p;

    private int count = 1;

    public LinkedList<Alien> list;


    /**
     * Constructor for the GalaxyDefendersWorld class. Establishes a sprite,
     * bullet, poweredBullet, and LinkedList of Aliens.
     * 
     * @param s
     *            Sprite
     */
    public GalaxyDefendersWorld( Sprite s )
    {
        this.s = s;
        b = new Bullet( this );
        p = new PoweredBullet( this );
        list = new LinkedList<Alien>();

    }


    /**
     * Controls the movement and the shooting abilities of the sprite based on
     * the keys that are inputed by the player. For example, left and right
     * arrow keys for movement and the space bar for shooting.
     */
    public boolean keyPressed( String description, Location loc )
    {
        if ( description.equals( "LEFT" ) )
        {
            s.moveTo( new Location( s.getLocation().getRow(), s.getLocation()
                .getCol() - 1 ) );
            return true;
        }
        else if ( description.equals( "RIGHT" ) )
        {
            s.moveTo( new Location( s.getLocation().getRow(), s.getLocation()
                .getCol() + 1 ) );
            return true;
        }
        else if ( description.equals( "SPACE" ) )
        {

            if ( count == 5 && !contains( b ) )
            {
                this.add( new Location( s.getLocation().getRow() - 2,
                    s.getLocation().getCol() ), p );
                p.act();
                count = 1;
            }
            else if ( !contains( p ) && !contains( b ) )
            {
                this.add( new Location( s.getLocation().getRow() - 2,
                    s.getLocation().getCol() ), b );
                b.act();
                count++;
            }

            return true;
        }
        else if ( description.equals( "UP" ) || description.equals( "DOWN" ) )
        {
            return true;
        }
        else if (description.equals( "ENTER" ))
        {
            for (Alien a: list)
            {
                a.act();
            }
        }
        show();
        return false;
    }


    /**
     * 
     * Checks to see if a location contains a specific actor.
     * 
     * @param a
     *            Actor
     * @return true if the location contains the specific actor
     */
    public boolean contains( Actor a )
    {
        for ( int x = 0; x < 15; x++ )
        {
            for ( int y = 0; y < 25; y++ )
            {
                Grid<Actor> gr = getGrid();
                if ( gr.get( new Location( x, y ) ) == a )
                {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 
     * Helper method for the LinkedList of Aliens.
     * 
     * @return list LinkedList of aliens
     */
    public LinkedList<Alien> getList()
    {
        return list;
    }
}
