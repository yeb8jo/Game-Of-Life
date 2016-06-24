package yeb8jo.gameoflife.detector;

import android.view.MotionEvent;

import junit.framework.TestCase;

/**
 * Created by yeb8jo on 2016/05/13.
 */
public class AliveGestureDetectorTest extends TestCase {
    MotionEvent motionEvent;

    AliveGestureDetector instance;
    AliveGestureDetector.AliveGestureListener listener;

    public void setUp() throws Exception {
        super.setUp();

        listener = new AliveGestureDetector.AliveGestureListener();
        instance = new AliveGestureDetector(listener);
    }

    public void test_get_positive_point() throws Exception {
        motionEvent = setPoint(5, 15);
        instance.onTouch(motionEvent);
        assertEquals(0, instance.getRow());
        assertEquals(1, instance.getColumn());

        motionEvent = setPoint(15, 5);
        instance.onTouch(motionEvent);
        assertEquals(1, instance.getRow());
        assertEquals(0, instance.getColumn());

        motionEvent = setPoint(5, 5);
        instance.onTouch(motionEvent);
        assertEquals(0, instance.getRow());
        assertEquals(0, instance.getColumn());

        motionEvent = setPoint(15, 15);
        instance.onTouch(motionEvent);
        assertEquals(1, instance.getRow());
        assertEquals(1, instance.getColumn());

        motionEvent = setPoint(100, 100);
        instance.onTouch(motionEvent);
        assertEquals(10, instance.getRow());
        assertEquals(10, instance.getColumn());

        motionEvent = setPoint(99, 99);
        instance.onTouch(motionEvent);
        assertEquals(9, instance.getRow());
        assertEquals(9, instance.getColumn());

        motionEvent = setPoint(101, 101);
        instance.onTouch(motionEvent);
        assertEquals(10, instance.getRow());
        assertEquals(10, instance.getColumn());
    }

    public void test_get_negative_point() throws Exception {
        motionEvent = setPoint(-1, 0);
        instance.onTouch(motionEvent);
        assertEquals(-1, instance.getRow());
        assertEquals(0, instance.getColumn());

        motionEvent = setPoint(0, -1);
        instance.onTouch(motionEvent);
        assertEquals(0, instance.getRow());
        assertEquals(-1, instance.getColumn());

        motionEvent = setPoint(-11, -11);
        instance.onTouch(motionEvent);
        assertEquals(-1, instance.getRow());
        assertEquals(-1, instance.getColumn());
    }

    /**
     * to set motionEvent with coordination
     *
     * @param x :
     * @param y :
     * @return : motionEvent
     */
    private MotionEvent setPoint(int x, int y) {
        return MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, x, y, 0);
    }
}