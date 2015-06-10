package GalaxyDefenders;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


/**
 * The GalaxyDefenders class runs a world that contains rows of aliens at the
 * top of the screen and a sprite at the bottom of the screen. Instructions on
 * how to play are provided.A "you win!" message will display when all of the
 * aliens are cleared or a "you lose!" message will display when the aliens
 * reach the row above the sprite.
 *
 * @author Ben Liang
 * @version May 25, 2015
 * @author Period: 4
 * @author Assignment: GridworldFinal
 *
 * @author Sources: None
 */
public class GalaxyDefenders
{

    /**
     * 
     * The main method. It adds aliens to the grid and determines whether the
     * player wins or loses based on if all of the aliens are eliminated or not.
     * 
     * @param args
     *            main
     */
    public static void main( String[] args )
    {
        Sprite s = new Sprite( "Sprite" );

        GalaxyDefendersWorld world = new GalaxyDefendersWorld( s );
        world.setMessage( "Move the sprite with the arrow keys and shoot the aliens with the spacebar.\nEvery 5th shot is a golden, powered bullet that clears the entire column. PRESS ENTER TO START." );
        world.add( new Location( 14, 12 ), s );
        Grid<Actor> gr = world.getGrid();
        for ( int row = 1; row < 4; row++ )
        {
            if ( row % 2 == 0 )
            {
                for ( int col = 2; col < 24; col = col + 2 )
                {
                    Alien a = new Alien();
                    world.add( new Location( row, col ), a );
                    world.getList().add( a );
                }
            }
            else
            {
                for ( int col = 1; col < 24; col = col + 2 )
                {
                    Alien a = new Alien();
                    world.add( new Location( row, col ), a );
                    world.getList().add( a );
                }
            }

        }

        while ( world.getList().size() != 0 )
        {
            world.show();
            if ( world.getList().size() == 0 )
            {
                world.setMessage( "You win!" );
            }
            for ( int y = 0; y < 25; y++ )
            {
                if ( gr.get( new Location( 13, y ) ) != null )
                {
                    world.setMessage( "You lose!" );
                    for ( Alien a : world.getList() )
                    {
                        a.stop();
                    }
                    return;
                }

            }

        }

        world.setMessage( "You win!" );

    }

}
