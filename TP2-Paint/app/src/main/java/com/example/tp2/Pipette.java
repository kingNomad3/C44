package com.example.tp2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

public class Pipette extends BoiteOutil{

    public Pipette(float epaisseurTrait, int currentCouleur, Paint p) {
        super(epaisseurTrait, currentCouleur, p);
    }

    public Bitmap getBitmapImage(int x, int y) {

        this.buildDrawingCache();
        Bitmap bitmapImage = Bitmap.createBitmap(this.getDrawingCache());
        this.destroyDrawingCache();



        return bitmapImage;
    }

    private void destroyDrawingCache() {
    }

    private void buildDrawingCache() {
    }

    private Bitmap getDrawingCache() {
    }

    @Override
    public void onTouchDown(float x, float y) {

    }

    @Override
    public void onTouchMove(float x, float y) {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
