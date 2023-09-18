package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Path;

public class Triangle extends BoiteOutil{

    public Triangle(float epaisseurTrait, int currentCouleur, Path p) {
        super(epaisseurTrait, currentCouleur, p);
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

    @Override
    public boolean isCrayon() {
        return false;
    }
}
