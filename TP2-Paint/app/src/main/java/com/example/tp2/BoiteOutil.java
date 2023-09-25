package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public abstract class BoiteOutil {
    private float epaisseurTrait;
    private int currentCouleur;
    private Paint p;



    public BoiteOutil(float epaisseurTrait, int currentCouleur, Paint p) {
        this.epaisseurTrait = epaisseurTrait;
        this.currentCouleur = currentCouleur;
        this.p = p;

    }

    public abstract void onTouchDown(float x, float y);
    public abstract void onTouchMove(float x, float y);
    public abstract void draw(Canvas canvas);

    public float getEpaisseurTrait() {
        return epaisseurTrait;
    }

    public int getCurrentCouleur() {
        return currentCouleur;
    }

    public Paint getP()  {
        return p;
    }

}