package dk.dtu.gameexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {

    // Create Log tag for messagese
    private static final String TAG = "GameView";

    private Bitmap bitmap;
    private SurfaceHolder surfaceHolder;
    private GameThread gameThread;

    // Creating Arraylist of sprites
    private List<Sprite> sprites = new ArrayList<Sprite>();


    public GameView(Context context) {
        super(context);
        gameThread = new GameThread(this);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameThread.setRunning(false);
                while (retry) {
                    try {
                        gameThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameThread.setRunning(true);
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });


        //

        sprites.add(createSprite(R.drawable.dude2, 10, 0));
        sprites.add(createSprite(R.drawable.dude3, 10, 100));
        sprites.add(createSprite(R.drawable.dude3, 10, 200));
        sprites.add(createSprite(R.drawable.dude3, 10, 300));
        sprites.add(createSprite(R.drawable.dude3, 10, 400));
        sprites.add(createSprite(R.drawable.dude3, 10, 500));
        sprites.add(createSprite(R.drawable.dude3, 10, 600));
        sprites.add(createSprite(R.drawable.dude3, 10, 700));
        sprites.add(createSprite(R.drawable.dude3, 10, 800));
        sprites.add(createSprite(R.drawable.dude3, 10, 900));
        sprites.add(createSprite(R.drawable.dude3, 10, 1000));
        sprites.add(createSprite(R.drawable.dude3, 10, 1100));


    }

    private Sprite createSprite(int resource, int xPosition, int yPosition) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resource);
        return new Sprite(this, bitmap, xPosition, yPosition);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
            // Creating a Loop trough all the sprites Elements in the Arraylist
            for (Sprite sprite : sprites) {
                sprite.onDraw(canvas);
                Log.d("Sprite : ", "Index = " + sprites.indexOf(sprite));
            }
        }
    }


}


