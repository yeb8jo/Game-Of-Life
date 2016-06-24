package yeb8jo.gameoflife.activities;

import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.reflect.Method;
import java.util.Random;

import yeb8jo.gameoflife.R;
import yeb8jo.gameoflife.model.GameModel;
import yeb8jo.gameoflife.view.GameView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout container;
    private GameModel model;
    private GameView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        containerInit();
        modelInitWithAliveCells(1000); //
        viewInitWithInterval(100); // with interval value
    }

    private void containerInit() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        container = (FrameLayout) findViewById(R.id.container);
    }

    private void modelInitWithAliveCells(int aliveCells) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        int version = Build.VERSION.SDK_INT;
        if(version >= Build.VERSION_CODES.HONEYCOMB){
            display.getSize(size);
        } else if(version < Build.VERSION_CODES.HONEYCOMB) {
            overrideGetSize(display, size);
        }

        int rows = size.x / GameModel.CELL_SIZE;
        int columns = size.y / GameModel.CELL_SIZE;

        model = new GameModel( rows, columns );
        Random random = new Random();
        for (int i = 0; i < aliveCells; i++) {
            model.makeAlive(random.nextInt(rows), random.nextInt(columns));
        }
    }

    /**
     * @param display
     * @param outSize
     */
    private void overrideGetSize(Display display, Point outSize) {
        try {
            // test for new method to trigger exception
            Class pointClass = Class.forName("android.graphics.Point");
            Method newGetSize = Display.class.getMethod("getSize", new Class[]{ pointClass });

            // no exception, so new method is available, just use it
            newGetSize.invoke(display, outSize);
        } catch(Exception e) {
            // new method is not available, use the old ones
            outSize.x = display.getWidth();
            outSize.y = display.getHeight();
        }
    }


    private void viewInitWithInterval(int interval) {
        view = new GameView(this);
        container.addView(view);
        view.setup(model);
        view.loop(interval);
    }

}
