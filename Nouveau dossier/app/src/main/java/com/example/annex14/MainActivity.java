package com.example.annex14;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        Ecouteur ec = new Ecouteur();

        for (int i =0; i < parent.getChildCount(); i++ ){
            //nous devons convertir le parent en linear layout
            LinearLayout colonne = (LinearLayout) parent.getChildAt(i);
            colonne.setOnDragListener(ec);
            colonne.getChildAt(0).setOnTouchListener(ec);
        }
    }


    private class Ecouteur implements View.OnDragListener, View.OnTouchListener{

        Drawable normal = getResources().getDrawable(R.drawable.background_contenant, null);
        Drawable select = getResources().getDrawable(R.drawable.background_contenant_selectionne, null);
        View jeton = null;



        @Override
        public boolean onDrag(View source, DragEvent event) {

            switch (event.getAction()){
                //on prefere cela lorsque c'est un constente on l'appel toujours sur le main
                case DragEvent.ACTION_DRAG_ENTERED:

                    source.setBackground(select);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                case DragEvent.ACTION_DRAG_ENDED:
                        source.setBackground(normal);
                    break;

                case DragEvent.ACTION_DROP:
                     //chercher le jeton d'origine
                     jeton = (View) event.getLocalState();
                     // on va enlever le jeton de sa colonne d'origine
                     LinearLayout parentOriginel =  (LinearLayout) jeton.getParent();
                     parentOriginel.removeView(jeton);
                     //remettre le jeton dans sa nouvelle colonne
                     LinearLayout  parentActuel = (LinearLayout) source;
                     parentActuel.addView(jeton);
                     // remettre le jeton visible
                    jeton.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;

            }


            //dois etre retour true
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