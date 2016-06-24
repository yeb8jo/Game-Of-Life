package yeb8jo.gameoflife.detector;

import android.view.MotionEvent;

import yeb8jo.gameoflife.model.GameModel;

/**
 * Created by yeb8jo on 2016/05/13.
 */
public class AliveGestureDetector {
    private AliveGestureListener mListener;
    private int row;
    private int column;

    public AliveGestureDetector(AliveGestureListener listener) {
        mListener = listener;
    }

    public void onTouch(MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN :
                setRow((int) motionEvent.getX(0));
                setColumn((int) motionEvent.getY(0));
                mListener.onAliveGestureBegin(this);
                break;

            case MotionEvent.ACTION_MOVE :
                mListener.onAliveGesture(this);
                break;

            case MotionEvent.ACTION_UP :
                mListener.onAliveGestureEnd(this);
                break;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if(row < 0) {
            this.row = -1;
            return;
        }

        row /= GameModel.CELL_SIZE;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        if (column < 0) {
            this.column = -1;
            return;
        }

        column /= GameModel.CELL_SIZE;
        this.column = column;
    }

    public static class AliveGestureListener {
        public void onAliveGesture(AliveGestureDetector detector)
        {
        }

        public void onAliveGestureBegin(AliveGestureDetector detector)
        {
        }

        public void onAliveGestureEnd(AliveGestureDetector detector)
        {
        }
    }

}
