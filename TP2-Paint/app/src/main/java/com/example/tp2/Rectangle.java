package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class Rectangle extends BoiteOutil {

    private Paint paint;
    private RectF rect; // Utilisation de RectF pour représenter le rectangle

    public Rectangle(float epaisseurTrait, int currentCouleur, Path p) {
        super(epaisseurTrait, currentCouleur, p);
        paint = new Paint();
        paint.setColor(currentCouleur);
        paint.setStrokeWidth(epaisseurTrait);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        this.rect = new RectF();
    }

    @Override
    public void onTouchDown(float x, float y) {
        // Lorsque l'utilisateur touche l'écran, définissez les coordonnées de départ du rectangle
        rect.left = x;
        rect.top = y;
        rect.right = x;
        rect.bottom = y;
    }

    @Override
    public void onTouchMove(float x, float y) {
        // Lorsque l'utilisateur déplace son doigt, mettez à jour les coordonnées du rectangle
        rect.right = x;
        rect.bottom = y;
    }

    @Override
    public void draw(Canvas canvas) {
        // Dessinez le rectangle sur le canevas en utilisant l'objet Paint spécifié
        canvas.drawRect(rect, paint);
    }

    public Paint getPaint() {
        return paint;
    }

    public RectF getRect() {
        return rect;
    }

    public boolean isCrayon() {
        return false;
    }
}

