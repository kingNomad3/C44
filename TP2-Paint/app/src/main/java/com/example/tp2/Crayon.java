package com.example.tp2;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
public class Crayon extends Path {

    float epaisseurTrait;
    int currentCouleur;
    Paint formCrayon;
    int couleur;

    Path p;

    public Crayon(float epaisseurTrait, int currentCouleur, Path p) {
        this.epaisseurTrait = epaisseurTrait;
        this.currentCouleur = currentCouleur;
//        this.formCrayon = formCrayon;
        this.p = p;


    }



    public float getEpaisseurTrait() {
        return epaisseurTrait;
    }

    public int getCurrentCouleur() {
        return currentCouleur;
    }

    

    public Path getP() {
        return p;
    }
}
