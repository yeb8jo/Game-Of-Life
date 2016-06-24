package yeb8jo.gameoflife.model;

import junit.framework.TestCase;

/**
 * Created by yeb8jo on 2016/05/12.
 */
public class GameModelTest extends TestCase {

    GameModel instance;

    public void setUp() throws Exception {
        super.setUp();
        instance = new GameModel(3, 3);
    }

    public void test_init() throws Exception {
        assertEquals(3, instance.getRows());
        assertEquals(3, instance.getColumns());
    }

    public void test_is_alive() throws Exception {
        assertFalse(instance.isAlive(0, 0));

        assertFalse(instance.isAlive(-1, 0));
        assertFalse(instance.isAlive(0, -1));
        assertFalse(instance.isAlive(3, 0));
        assertFalse(instance.isAlive(0, 3));
    }

    public void test_make_alive() throws Exception {
        instance.makeAlive(0, 0);
        assertTrue(instance.isAlive(0, 0));

        instance.makeAlive(1, 1);
        assertTrue(instance.isAlive(1, 1));

        instance.makeAlive(2, 2);
        assertTrue(instance.isAlive(2, 2));

        instance.makeAlive(-1, 0);
        instance.makeAlive(0, -1);
        instance.makeAlive(3, 0);
        instance.makeAlive(0, 3);
    }

    public void test_make_dead() throws Exception {
        instance.makeAlive(0, 0);
        instance.makeDead(0, 0);
        assertFalse(instance.isAlive(0, 0));

        instance.makeDead(-1, 0);
        instance.makeDead(0, -1);
        instance.makeDead(3, 0);
        instance.makeDead(0, 3);
    }

    public void test_live_cell() throws Exception {
        instance.makeAlive(1, 1);
        assertFalse(instance.willLive(1, 1));

        instance.makeAlive(0, 0);
        assertFalse(instance.willLive(1, 1));

        instance.makeAlive(0, 1);
        assertTrue(instance.willLive(1, 1));

        instance.makeAlive(0, 2);
        assertTrue(instance.willLive(1, 1));

        instance.makeAlive(2, 2);
        assertFalse(instance.willLive(1, 1));

        instance.next();
        assertFalse(instance.isAlive(1, 1));

        assertTrue(instance.isAlive(0, 0));
        assertTrue(instance.isAlive(0, 1));
        assertTrue(instance.isAlive(0, 2));
        assertTrue(instance.isAlive(1, 0));
        assertFalse(instance.isAlive(1, 1));
        assertFalse(instance.isAlive(1, 2));
        assertFalse(instance.isAlive(2, 0));
        assertFalse(instance.isAlive(2, 1));
        assertFalse(instance.isAlive(2, 2));
    }

    public void test_dead_cell() throws Exception {
        assertFalse(instance.willLive(1, 1));

        instance.makeAlive(0, 0);
        assertFalse(instance.willLive(1, 1));

        instance.makeAlive(0, 1);
        assertFalse(instance.willLive(1, 1));

        instance.makeAlive(0, 2);
        assertTrue(instance.willLive(1, 1));

        instance.makeAlive(2, 2);
        assertFalse(instance.willLive(1, 1));

        instance.next();
        assertFalse(instance.isAlive(1, 1));

        assertFalse(instance.isAlive(0, 0));
        assertTrue(instance.isAlive(0, 1));
        assertFalse(instance.isAlive(0, 2));
        assertFalse(instance.isAlive(1, 0));
        assertFalse(instance.isAlive(1, 1));
        assertTrue(instance.isAlive(1, 2));
        assertFalse(instance.isAlive(2, 0));
        assertFalse(instance.isAlive(2, 1));
        assertFalse(instance.isAlive(2, 2));
    }



}