package yeb8jo.gameoflife.detector;

import android.view.MotionEvent;

/**
 * Created by yeb8jo on 2016/05/13.
 */
public class DeadGestureDetector {
    private DeadGestureListener mListener;
    private int row;
    private int column;

    public DeadGestureDetector(DeadGestureListener listener) {
        mListener = listener;
    }

    public void onTouch(MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN :
                mListener.onDeadGestureBegin(this);
                break;

            case MotionEvent.ACTION_MOVE :
                mListener.onDeadGesture(this);
                break;

            case MotionEvent.ACTION_UP :
                mListener.onDeadGestureEnd(this);
                break;
        }
    }

    public static class DeadGestureListener {
        public void onDeadGesture(DeadGestureDetector detector)
        {
        }

        public void onDeadGestureBegin(DeadGestureDetector detector)
        {
        }

        public void onDeadGestureEnd(DeadGestureDetector detector)
        {
        }
    }

}
