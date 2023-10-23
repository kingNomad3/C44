package com.example.annex14;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class
MainActivity extends AppCompatActivity {
    LinearLayout parent;
    LinearLayout colonne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        Ecouteur ec = new Ecouteur();

        for (int i =0; i < parent.getChildCount(); i++ ){
            //nous devons convertir le parent en linear layout
            colonne = (LinearLayout) parent.getChildAt(i);
            colonne.setOnDragListener(ec);
            colonne.getChildAt(0).setOnTouchListener(ec);
        }
    }


    private class Ecouteur implements View.OnDragListener, View.OnTouchListener{

        Drawable normal = getResources().getDrawable(R.drawable.background_contenant, null);
        Drawable select = getResources().getDrawable(R.drawable.background_contenant_selectionne, null);
        Drawable selectTriangle = getResources().getDrawable(R.drawable.triangle_selectionner,null);
        Drawable normalTriangle = getResources().getDrawable(R.drawable.triangle,null);
        View jeton = null;



        @Override
        public boolean onDrag(View source, DragEvent event) {
            switch (event.getAction()) {
                // on préfère cela lorsque c'est une constante, on l'appelle toujours sur le main
                case DragEvent.ACTION_DRAG_ENTERED:
                    //sois un ou l'autre pas obliger de changer dans l'interface la drawable
//                        source.setBackground(selectTriangle);
                        source.setBackground(select);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                case DragEvent.ACTION_DRAG_ENDED:
//                        source.setBackground(normalTriangle);
                        source.setBackground(normal);
                    break;
//                      source.setBackground(normal);

//                case DragEvent.ACTION_DRAG_ENTERED:
//                    if (source == colonne.getChildAt(3) && source == colonne.getChildAt(1)) {
//                        source.setBackground(select);
//                    } else {
//                        source.setBackground(selectTriangle);
//                    }
////                      source.setBackground(select);
//                    break;
//                case DragEvent.ACTION_DRAG_EXITED:
//                case DragEvent.ACTION_DRAG_ENDED:
//                    if (source == colonne.getChildAt(3) && source == colonne.getChildAt(1)) {
//                     source.setBackground(normal);
//                    } else {
//
//                     source.setBackground(normalTriangle);
//                    }
//                    break;
////                      source.setBackground(normal);


                case DragEvent.ACTION_DROP:
                    // chercher le jeton d'origine
                    jeton = (View) event.getLocalState();
                    // on va enlever le jeton de sa colonne d'origine
                    LinearLayout parentOriginel = (LinearLayout) jeton.getParent();
                    parentOriginel.removeView(jeton);
                    // remettre le jeton dans sa nouvelle colonne
                    LinearLayout parentActuel = (LinearLayout) source;
                    parentActuel.addView(jeton);
                    // remettre le jeton visible
                    jeton.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }

            // doit être retourné true
            return true;
        }

        @Override
        public boolean onTouch(View source, MotionEvent event) {

            //ca creer une ombre de notre jeton qui est la source
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(source);
            source.startDragAndDrop(null,shadowBuilder,source,0);

            //rendre le jeton invisible tant qu'il n;est pas rendu a destination
            source.setVisibility(View.INVISIBLE);


            //dois etre retour true
            return true;
        }
    }


}