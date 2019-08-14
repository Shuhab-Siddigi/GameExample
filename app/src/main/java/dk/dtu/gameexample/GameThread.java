package dk.dtu.gameexample;

import android.graphics.Canvas;
import android.view.View;

public class GameThread extends Thread {

    private GameView view;
    private boolean running = false;


    public GameThread(GameView view){

        this.view = view;
    }


    public void setRunning(boolean run){
        running = run;
    }

    public void run(){
        while (running){
            Canvas canvas = null;
            try {
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.onDraw(canvas);
                }
            }finally {
                if(canvas != null){
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }




}
