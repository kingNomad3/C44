package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Efface extends BoiteOutil {
    private int backgroundColor;
    private Path path;
    public Efface(float epaisseurTrait, int backgroundColor, Path path) {
        super(epaisseurTrait, backgroundColor, new Paint());
        this.backgroundColor = backgroundColor;
        this.path = path;
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
        Paint paint = getP();
        paint.setColor(backgroundColor);
        canvas.drawPath(path, paint);
    }
    public Path getPath() {
        return path;
    }

}