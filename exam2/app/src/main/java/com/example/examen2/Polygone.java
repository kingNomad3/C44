package com.eric.ex2formes;

import android.graphics.Canvas;
import android.graphics.Point;

public abstract class Polygone {

    private int couleur;
    private int largeur;
    private Point depart;
    private int nbSommets;

    public Polygone (int couleur, int largeur, Point depart, int nbSommets)
    {
        this.couleur = couleur;
        this.largeur = largeur;
        this.depart = depart;
        this.nbSommets = nbSommets;
    }


    public Point getDepart() {
        return depart;
    }

    public int getNbSommets() {
        return nbSommets;
    }



    public abstract void dessinerPath(Canvas c);



}
