package com.example.examen2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

public class Carre extends com.eric.ex2formes.Polygone {

    private Paint paint;
    private RectF rect;
    public Carre(int couleur, int largeur, Point depart, int nbSommets) {
        super(couleur, largeur, depart, nbSommets);
        this.paint = paint;
        this.rect = rect;
    }

    @Override
    public void dessinerPath(Canvas c) {
        rect.left = getDepart().x;
        rect.top = getDepart().y;
        rect.right = getDepart().x;
        rect.bottom =getDepart().y;
        c.drawRect(rect, paint);
    }

    public Paint getPaint() {
        return paint;
    }

    public RectF getRect() {
        return rect;
    }
}
