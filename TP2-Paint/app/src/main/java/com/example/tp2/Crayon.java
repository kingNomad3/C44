package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Crayon extends BoiteOutil {

    private final Path path;
    private Paint paint;

    public Crayon(float epaisseurTrait, int currentCouleur) {
        super(epaisseurTrait, currentCouleur, null); // Passez null pour le paramètre Path
        paint = new Paint();
        paint.setColor(currentCouleur);
        paint.setStrokeWidth(epaisseurTrait);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        this.path = new Path();
    }

    @Override
    public void onTouchDown(float x, float y) {
        // Lorsque l'utilisateur touche l'écran, déplacez le chemin vers les coordonnées du toucher
        path.moveTo(x, y);
    }

    @Override
    public void onTouchMove(float x, float y) {
        // Lorsque l'utilisateur déplace son doigt, ajoutez une ligne au chemin pour dessiner
        path.lineTo(x, y);
    }

    @Override
    public void draw(Canvas canvas) {
        // Dessinez le chemin sur le canevas en utilisant l'objet Paint spécifié
        canvas.drawPath(path, paint);
    }

    public Paint getPaint() {
        return paint;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public boolean isCrayon() {
        return true;
    }
}


