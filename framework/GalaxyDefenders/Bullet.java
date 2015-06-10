package GalaxyDefenders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


/**
 * The Bullet class gives functionality to the bullets that are shot out by the
 * sprite. Bullet extends the Actor class. The bullets use actual images that we
 * found online and are not drawn by the program.
 *
 * @author Ben Liang
 * @version May 25, 2015
 * @author Period: 4
 * @author Assignment: GridworldFinal
 *
 * @author Sources: None
 */
public class Bullet extends Actor
{

    private Timer t;

    public GalaxyDefendersWorld gdw;


    /**
     * Constructor for the Bullet Class
     * 
     * @param gdw
     *            GalaxyDefendersWorld
     */
    public Bullet( GalaxyDefendersWorld gdw )
    {
        this.gdw = gdw;
    }


    /**
     * Moves if it can move. If the bullet goes off the screen, it is removed
     * from the grid. If it makes contact with an alien, the alien and bullet
     * are both removed from the grid. The bullet moves by a grid every 10th of
     * a second, using the timer.
     */
    public void act()
    {
        ActionListener taskPerformer = new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent evt )
            {
                Grid<Actor> gr = getGrid();
                Location loc = getLocation();
                Location next = loc.getAdjacentLocation( getDirection() );
                if ( canMove() )
                {
                    move();
                }
                else if ( loc.getRow() == 0 )
                {
                    gr.get( loc ).removeSelfFromGrid();
                    return;
                }
                else if ( gr.get( next ) != null )
                {
                    gr.get( next ).removeSelfFromGrid();
                    gr.get( loc ).removeSelfFromGrid();
                    gdw.getList().remove();
                    gdw.setMessage( "Enemies Left: " + gdw.getList().size() );
                    return;
                }

            }
        };
        t = new Timer( 1 * 100, taskPerformer );
        t.start();

    }


    /**
     * 
     * Moves the bullet forward.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if ( gr == null )
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation( getDirection() );
        if ( gr.isValid( next ) )
            moveTo( next );
        else
            removeSelfFromGrid();

    }


    /**
     * Tests whether the bullet can move forward into a location that is empty
     * or contains an alien.
     * 
     * @return true if this bullet can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if ( gr == null )
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation( getDirection() );
        if ( !gr.isValid( next ) )
            return false;
        Actor neighbor = gr.get( next );
        return ( neighbor == null );
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }

}
