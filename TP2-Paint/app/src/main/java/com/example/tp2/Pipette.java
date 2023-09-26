package com.example.tp2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Pipette extends BoiteOutil {

    public Pipette(float epaisseurTrait, int currentCouleur, Paint p) {
        super(epaisseurTrait, currentCouleur, p);
    }

    // Modify the existing method to work with the View
    public Bitmap getBitmapImage(View view) {
        view.buildDrawingCache();
        Bitmap bitmapImage = Bitmap.createBitmap(view.getDrawingCache());
        view.destroyDrawingCache();

        return bitmapImage;
    }

    @Override
    public void onTouchDown(float x, float y) {
        // Implement this method as needed for your pipette functionality
    }

    @Override
    public void onTouchMove(float x, float y) {
        // Implement this method as needed for your pipette functionality
    }

    @Override
    public void draw(Canvas canvas) {
        // Implement this method as needed for your pipette functionality
    }


}