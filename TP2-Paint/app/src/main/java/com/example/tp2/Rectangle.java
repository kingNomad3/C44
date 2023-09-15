package com.example.tp2;

import android.graphics.Paint;
import android.graphics.Path;

public class Rectangle {
    float epaisseurTrait;
    int currentCouleur;
    Paint formCrayon;
    int couleur;

    Path p;

    public Rectangle(float epaisseurTrait, int currentCouleur, Path p) {
        this.epaisseurTrait = epaisseurTrait;
        this.currentCouleur = currentCouleur;

        this.p = p;


    }
}
