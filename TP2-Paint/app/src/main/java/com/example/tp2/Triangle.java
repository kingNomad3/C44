package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle extends BoiteOutil {
    private Path path; // The path to draw the triangle
    private float startX, startY; // Starting coordinates for drawing the triangle

    public Triangle(float epaisseurTrait, int currentCouleur, Paint p) {
        super(epaisseurTrait, currentCouleur, p);
        path = new Path();
    }

    @Override
    public void onTouchDown(float x, float y) {
        startX = x;
        startY = y;
        path.moveTo(x, y);
    }

    @Override
    public void onTouchMove(float x, float y) {
//        https://kylewbanks.com/blog/drawing-triangles-rhombuses-and-other-shapes-on-android-canvas
        path.reset(); // Effacez le chemin précédent

        // Calculez les sommets du triangle en fonction de la position de la souris
        float moitieLongueurCote = Math.abs(x - startX) / 2; // Moitié de la longueur du côté
        float hauteur = (float) (moitieLongueurCote * Math.sqrt(3)); // Calculez la hauteur du triangle équilatéral
        float x1, y1, x2, y2, x3, y3;

        if (x < startX) {
            x1 = startX - moitieLongueurCote;
            y1 = startY + hauteur / 2;
            x2 = startX + moitieLongueurCote;
            y2 = startY + hauteur / 2;
            x3 = startX;
            y3 = startY - hauteur / 2; //
        } else {
            x1 = startX - moitieLongueurCote;
            y1 = startY - hauteur / 2;
            x2 = startX + moitieLongueurCote;
            y2 = startY - hauteur / 2;
            x3 = startX;
            y3 = startY + hauteur / 2;
        }

        // Définissez le chemin pour le triangle
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.close(); // Fermez le chemin pour compléter le triangle
    }

    @Override
    public void draw(Canvas canvas) {
        // Draw the triangle on the canvas using the specified Paint object
        canvas.drawPath(path, getP());
    }

    public Path getPath() {
        return path;
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }
}


