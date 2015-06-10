package GalaxyDefenders;

import info.gridworld.actor.Actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


/**
 * The PoweredBullet class extends the Bullet class and acts as a power-up that
 * clears the entire column of aliens.
 *
 * @author Ben Liang, Andy Shen, Pavan Gollapalli
 * @version May 25, 2015
 * @author Period: 4
 * @author Assignment: GridworldFinal
 *
 * @author Sources: 
 */
public class PoweredBullet extends Bullet
{
    public GalaxyDefendersWorld gdw;

    private Timer t;


    /**
     * Constructor for the PoweredBullet
     * 
     * @param gdw
     *            GalaxyDefendersWorld
     */
    public PoweredBullet( GalaxyDefendersWorld gdw )
    {
        super( gdw );
        this.gdw = gdw;
    }


    /**
     * Moves if it can move. If the bullet goes off the screen, it is removed
     * from the grid. If it makes contact with an alien, the bullet removes the
     * alien, but continues on the same path until it goes off the screen. The
     * bullet moves by a grid every 10th of a second, using the timer.
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
                    gdw.getList().remove();
                    gdw.setMessage( "Enemies Left: " + gdw.getList().size() );
                    act();
                }

            }
        };
        t = new Timer( 1 * 100, taskPerformer );
        t.start();

    }
}
