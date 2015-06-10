package GalaxyDefenders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


/**
 * The Alien Class extends the Bug class. This class describes the movement of
 * the aliens and determines if they can move and in which direction. A timer is
 * also used to determine the speed at which the aliens move.
 *
 * @author Ben Liang, Andy Shen, Pavan Gollapalli
 * @version May 25, 2015
 * @author Period: 4
 * @author Assignment: GridworldFinal
 *
 * @author Sources:
 */
public class Alien extends Bug
{

    private Timer t;

    private int dir = 0;


    /**
     * The aliens move from side to side and move closer towards the sprite
     * after every 1.5 seconds.
     */
    public void act()
    {
        ActionListener taskPerformer = new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent evt )
            {
                if ( dir == 0 )
                {
                    move( 90 );
                }
                if ( dir == 1 || dir == 4 )
                {
                    move( 180 );
                }
                if ( dir > 1 && dir < 4 )
                {
                    move( 270 );
                }
                if ( dir == 5 )
                {
                    move( 90 );
                    dir = -1;
                }
                dir++;

            }
        };
        t = new Timer( 1 * 1500, taskPerformer );
        t.start();
    }


    /**
     * 
     * Moves the aliens one grid, depending on the direction.
     * 
     * @param dir
     *            direction
     */
    public void move( int dir )
    {
        Grid<Actor> gr = getGrid();
        if ( gr == null )
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation( getDirection() + dir );
        if ( gr.isValid( next ) && gr.get( next ) == null )
            moveTo( next );

    }


    /**
     * 
     * Stops the timer, which stops the movement of the aliens.
     */
    public void stop()
    {
        this.t.stop();
    }

}
