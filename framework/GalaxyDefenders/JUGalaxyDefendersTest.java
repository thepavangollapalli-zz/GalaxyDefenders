package GalaxyDefenders;

import info.gridworld.grid.Location;

import org.junit.*;

import static org.junit.Assert.*;


/**
 * GalaxyDefenders tests: Sprite Alien Bullet PoweredBullet GalaxyDefendersWorld
 *
 * @author Ben Liang, Andy Shen, Pavan Gollapalli
 * @version May 25, 2015
 * @author Period: 4
 * @author Assignment: GridworldFinal
 *
 * @author Sources: JM Chapter 19 - SafeTrade (JUSafeTradeTest.java)
 */
public class JUGalaxyDefendersTest
{
    // testing Sprite
    /**
     * Sprite tests SpriteConstructor: constructs Sprite and compares names
     */
    @Test
    public void SpriteConstructor()
    {
        Sprite s = new Sprite( "player" );
        String check = s.currName();
        assertTrue( "<<<Invalid Sprite Constructor>>>", check.equals( "player" ) );
    }


    // Testing Alien
    /**
     * Alien tests AlienAct: compares location returned to expected location
     * while accounting for timer AlienMove: compares location returned to
     * expected location
     */
    @Test
    public void AlienAct()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( new Sprite( "test" ) );
        Alien a = new Alien();
        w.add( new Location( 0, 4 ), a );
        a.act();
        try
        {
            Thread.sleep( 2000 );
        }
        catch ( InterruptedException ex )
        {
            Thread.currentThread().interrupt();
        }
        assertEquals( "Alien.act() implemented incorrectly", a.getLocation()
            .getCol(), 5 );
    }


    @Test
    public void AlienMove()
    {
        GalaxyDefendersWorld world = new GalaxyDefendersWorld( new Sprite( "test" ) );
        Alien a = new Alien();
        world.add( new Location( 0, 5 ), a );
        a.move( 90 );
        assertEquals( "Alien.move() implemented incorrectly", a.getLocation()
            .getCol(), 6 );
    }


    // Testing Bullet
    /**
     * Bullet tests BulletConstructor: constructs Bullet and checks for presence
     * of linkedList bulletAct: compares returned Location to expected
     * bulletCanMove: instantiates at edge of screen and compares canMove to
     * expected output
     */
    @Test
    public void BulletConstructor()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( new Sprite( "test" ) );
        w.getList().add( new Alien() );
        Bullet b = new Bullet( w );
        assertNotNull( "<<<Invalid Bullet Constructor>>>", b.gdw );
    }


    @Test
    public void bulletAct()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( new Sprite( "test" ) );
        Bullet b = new Bullet( w );
        w.add( new Location( 1, 5 ), b );
        b.act();
        try
        {
            Thread.sleep( 2000 );
        }
        catch ( InterruptedException ex )
        {
            Thread.currentThread().interrupt();
        }
        assertFalse( "Bullet act() implemented incorrectly", w.contains( b ) );
    }


    public void bulletMove()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( new Sprite( "test" ) );
        Bullet b = new Bullet( w );
        w.add( new Location( 5, 5 ), b );
        b.move();
        assertEquals( "Invalid implementation of Bullet.move()",
            b.getLocation().getRow() == 4 );
    }


    public void bulletCanMove()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( new Sprite( "test" ) );
        Bullet b = new Bullet( w );
        w.add( new Location( 0, 5 ), b );
        assertFalse( "Bullet.canMove() should be false", b.canMove() );
    }


    // Testing PoweredBullet
    /**
     * PoweredBullet tests PoweredBulletConstructor: constructs PoweredBullet
     * and checks for presence of linkedList PoweredBulletAct: compares returned
     * Location to expected value
     *
     */
    @Test
    public void PoweredBulletConstructor()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( new Sprite( "test" ) );
        w.getList().add( new Alien() );
        PoweredBullet b = new PoweredBullet( w );
        assertNotNull( "<<<Invalid PoweredBullet Constructor>>>", b.gdw );
    }


    @Test
    public void PoweredBulletAct()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( new Sprite( "test" ) );
        PoweredBullet b = new PoweredBullet( w );
        w.add( new Location( 3, 5 ), b );
        w.add( new Location( 2, 5 ), new Alien() );
        b.act();
        try
        {
            Thread.sleep( 5000 );
        }
        catch ( InterruptedException ex )
        {
            Thread.currentThread().interrupt();
        }
        assertTrue( "PoweredBullet act() implemented incorrectly",
            w.contains( b ) );
    }

    // GalaxyDefendersWorld test
    /**
     * GalaxyDefendersWorld tests GalaxyDefendersWorldConstructor: checks
     * validity of included LinkedList GalaxyDefendersWorldKeyPressed: compares
     * returned Location to expected value GalaxyDefendersWorldContains:
     * compares output to expected output
     */
    Sprite s = new Sprite( "test" );


    @Test
    public void GalaxyDefendersWorldConstructor()
    {
        GalaxyDefendersWorld gdw = new GalaxyDefendersWorld( s );
        gdw.getList().add( new Alien() );
        assertNotNull( "<<<Invalid GalaxyDefendersWorld constructor>>>",
            gdw.getList() );
    }


    @Test
    public void GalaxyDefendersWorldKeyPressed()
    {
        GalaxyDefendersWorld gdw = new GalaxyDefendersWorld( s );
        gdw.add( new Location( 0, 0 ), s );
        gdw.keyPressed( "RIGHT", new Location( 0, 0 ) );
        assertEquals( "GalaxyDefendersWorld keyPressed implemented incorrectly",
            s.getLocation().getCol(),
            1 );
    }


    @Test
    public void GalaxyDefendersWorldContains()
    {
        GalaxyDefendersWorld gdw = new GalaxyDefendersWorld( s );
        gdw.add( new Location( 0, 0 ), s );
        assertTrue( "GalaxyDefendersWorld contains implemented incorrectly",
            gdw.contains( s ) );
    }


    // Actions

    // Adding characters
    /**
     * Test of actions addAlien: adds alien and tests presence addSprite: adds
     * sprite and checks presence addBullet: adds bullet and checks presence
     */
    @Test
    public void addAlien()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( s );
        Alien a = new Alien();
        w.add( a );
        assertTrue( "Alien not added to screen", w.contains( a ) );
    }


    @Test
    public void addSprite()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( s );
        w.add( s );
        assertTrue( "Sprite not added to screen", w.contains( s ) );
    }


    @Test
    public void addBullet()
    {
        GalaxyDefendersWorld w = new GalaxyDefendersWorld( s );
        Bullet b = new Bullet( w );
        w.add( b );
        assertTrue( "Bullet not added to screen", w.contains( b ) );
    }
}