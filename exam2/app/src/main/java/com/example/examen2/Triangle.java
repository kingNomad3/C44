package com.example.examen2;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;

public class Triangle extends com.eric.ex2formes.Polygone {
    private Path path;
    private float startX, startY;

    public Triangle(int couleur, int largeur, Point depart, int nbSommets) {
        super(couleur, largeur, depart, nbSommets);
        path = new Path();
//        new Point(Integer.parseInt(positionX.getText().toString()),Integer.parseInt(positionY.getText().toString()))
    }

    @Override
    public void dessinerPath(Canvas c) {

    }

//    @Override
//    public void dessinerPath(Canvas c) {
//        path.reset(); // Effacez le chemin précédent
//        depa
//
//        // Calculez les sommets du triangle en fonction de la position de la souris
//        float moitieLongueurCote = Math.abs(x - startX) / 2; // Moitié de la longueur du côté
//        float hauteur = (float) (moitieLongueurCote * Math.sqrt(3)); // Calculez la hauteur du triangle équilatéral
//        float x1, y1, x2, y2, x3, y3;
//
//        if (x < startX) {
//            x1 = startX - moitieLongueurCote;
//            y1 = startY + hauteur / 2;
//            x2 = startX + moitieLongueurCote;
//            y2 = startY + hauteur / 2;
//            x3 = startX;
//            y3 = startY - hauteur / 2; //
//        } else {
//            x1 = startX - moitieLongueurCote;
//            y1 = startY - hauteur / 2;
//            x2 = startX + moitieLongueurCote;
//            y2 = startY - hauteur / 2;
//            x3 = startX;
//            y3 = startY + hauteur / 2;
//        }
//
//        // Définis le chemin pour le triangle
//        path.moveTo(x1, y1);
//        path.lineTo(x2, y2);
//        path.lineTo(x3, y3);
//        path.close(); // Ferme l
//    }
}
