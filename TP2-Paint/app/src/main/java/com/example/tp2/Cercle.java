package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Cercle extends BoiteOutil {
    private float rayon;
    private float centerX;
    private float centerY;



    public Cercle(float epaisseurTrait, int currentCouleur, Paint p, float rayon) {
        super(epaisseurTrait, currentCouleur, new Paint());
        this.rayon = rayon;
        this.centerX = 0; // Définir les coordonnées initiales à (0, 0)
        this.centerY = 0;

        getP().setColor(currentCouleur);
        getP().setStrokeWidth(epaisseurTrait);
        getP().setStyle(Paint.Style.STROKE);
        getP().setAntiAlias(true);
    }

    @Override
    public void onTouchDown(float x, float y) {
        // Lorsque l'utilisateur touche l'écran, définir le centre du cercle aux coordonnées (x, y)
        centerX = x;
        centerY = y;
    }

    @Override
    public void onTouchMove(float x, float y) {
        // Calculer le nouveau rayon en fonction de la distance entre le point de touche initial et le point de touche actuel
        rayon = (float) Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
    }

    @Override
    public void draw(Canvas canvas) {
        // Dessiner le cercle sur le canvas en utilisant l'objet Paint spécifié
        canvas.drawCircle(centerX, centerY, rayon, getP());
    }

    public float getRayon() {
        return rayon;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }


}

