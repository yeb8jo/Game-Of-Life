package yeb8jo.gameoflife.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import yeb8jo.gameoflife.detector.AliveGestureDetector;
import yeb8jo.gameoflife.detector.DeadGestureDetector;
import yeb8jo.gameoflife.model.GameModel;


/**
 * Created by yeb8jo on 2016/05/12.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private static final String TAG = GameView.class.getSimpleName();

    private Context mContext;
    private SurfaceHolder surfaceHolder;

    private AliveGestureDetector aliveGestureDetector;
    private DeadGestureDetector deadGestureDetector;

    private GameModel model;
    private Paint strokePaint;
    private Paint fillPaint;

    public GameView(Context context) {
        super(context);
        this.mContext = context;

        surfaceHolder = getHolder();
        if (surfaceHolder != null) {
            surfaceHolder.addCallback(this);
        }
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        aliveGestureDetector.onTouch(motionEvent);
        return true;
    }

    public void setup(GameModel model) {
        this.model = model;

        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.LTGRAY);

        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.BLACK);

        aliveGestureDetector = new AliveGestureDetector(aliveGestureListener);
        deadGestureDetector = new DeadGestureDetector(deadGestureListener);

        setOnTouchListener(this);
        setZOrderOnTop(true);
        setZOrderMediaOverlay(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    public void loop(final int INTERVAL) {
        // timer
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, INTERVAL);
                model.next();
                try {
                    present();
                } catch (NullPointerException npe) {
                    // do nothing.
                }
            }
        };
        handler.postDelayed(runnable, INTERVAL);
    }

    private void present() throws NullPointerException {
        Canvas canvas = surfaceHolder.lockCanvas();
        drawing(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void drawing(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        int size = GameModel.CELL_SIZE;
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getColumns(); j++) {
                int left = i * size;
                int top = j * size;
                int right = left + size;
                int bottom = top + size;
                if(model.isAlive(i, j)) {
                    canvas.drawRect(left, top, right, bottom, fillPaint);
                }

                canvas.drawRect(left, top, right, bottom, strokePaint);
            }
        }
    }

    private AliveGestureDetector.AliveGestureListener aliveGestureListener = new AliveGestureDetector.AliveGestureListener() {
        @Override
        public void onAliveGesture(AliveGestureDetector detector) {
            super.onAliveGesture(detector);
        }

        @Override
        public void onAliveGestureBegin(AliveGestureDetector detector) {
            super.onAliveGestureBegin(detector);
            model.makeAlive(detector.getRow(), detector.getColumn());
        }

        @Override
        public void onAliveGestureEnd(AliveGestureDetector detector) {
            super.onAliveGestureEnd(detector);
        }
    };

    private DeadGestureDetector.DeadGestureListener deadGestureListener = new DeadGestureDetector.DeadGestureListener() {
        @Override
        public void onDeadGesture(DeadGestureDetector detector) {
            super.onDeadGesture(detector);
        }

        @Override
        public void onDeadGestureBegin(DeadGestureDetector detector) {
            super.onDeadGestureBegin(detector);
        }

        @Override
        public void onDeadGestureEnd(DeadGestureDetector detector) {
            super.onDeadGestureEnd(detector);
        }
    };

}
