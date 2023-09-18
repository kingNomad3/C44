package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Path;



public class Cercle extends BoiteOutil{
    float rayon,x,y;
    Cercle cerc;

    public Cercle(float epaisseurTrait, int currentCouleur, Path p, float rayon, Cercle cerc, float x, float y) {
        super(epaisseurTrait, currentCouleur, p);
        this.rayon = rayon;
        this.cerc = cerc;
        this.x = x;
        this.y = y;
    }


    @Override
    public void onTouchDown(float x, float y) {
        cerc.x = x;
        cerc.y = y;

    }

    @Override
    public void onTouchMove(float x, float y) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public boolean isCrayon() {
        return false;
    }

    public float getRayon() {
        return rayon;
    }
}
