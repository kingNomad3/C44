package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Path;

public abstract class BoiteOutil {
    protected float epaisseurTrait;
    protected int currentCouleur;
    protected Path p;


    public BoiteOutil(float epaisseurTrait, int currentCouleur, Path p) {
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

    public Path getP() {
        return p;
    }
    public abstract boolean isCrayon();

}