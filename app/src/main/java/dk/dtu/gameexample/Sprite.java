package dk.dtu.gameexample;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

public class Sprite {

    private int x; // sprite coordinate x
    private int y; // sprite coordinate y
    private int xSpeed = 10 ; // sprite x speed
    private int ySpeed = 0 ; // sprite y speed

    private GameView gameView;  // reference to GameView
    private Bitmap bitmap;         // sprite Bitmap


    public Sprite(GameView gameView, Bitmap bitmap,int x, int y) {
        this.gameView=gameView;
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;

    }

    // boundaries collision for a single bitmap
    private void update() {
        // boundaries collision for east / west
        if (x > gameView.getWidth() - bitmap.getWidth() - xSpeed) {
            xSpeed = -xSpeed;
        }
        if (x + xSpeed< 0) {
            xSpeed = 15;
        }
        x = x + xSpeed;

        // boundaries collision for north /south
        if (y > gameView.getHeight() - bitmap.getHeight() - ySpeed) {
            ySpeed = -ySpeed;
        }
        if (y + ySpeed< 0) {
            ySpeed = 15;
        }
        y = y + ySpeed;
    }

    public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bitmap, x , y, null);
    }
}
