package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    TextView questions,bleu,rouge,noir,jaune,blanc;
    LinearLayout conteneurG;
    LinearLayout conteneurC;
    LinearLayout conteneurD;
    Button confirmer;
    Ecouteur ec;
    SingletonDatabase gest;
    private static SingletonDatabase instance;
    Vector choix ;

    HashMap<Integer,String> cleCouleur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions = findViewById(R.id.texteQuestion);
        bleu = findViewById(R.id.bleu);
        rouge = findViewById(R.id.rouge);
        noir = findViewById(R.id.noir);
        jaune = findViewById(R.id.jaune);
        blanc = findViewById(R.id.blanc);
        conteneurG = findViewById(R.id.conteneurG);
        conteneurC = findViewById(R.id.conteneurC);
        conteneurD = findViewById(R.id.conteneurD);
        confirmer = findViewById(R.id.boutonConfirmer);

        ec = new Ecouteur();

        questions.setOnClickListener(ec);
        bleu.setOnTouchListener(ec);
        rouge.setOnTouchListener(ec);
        noir.setOnTouchListener(ec);
        jaune.setOnTouchListener(ec);
        blanc.setOnTouchListener(ec);
        conteneurG.setOnDragListener(ec);
        conteneurC.setOnDragListener(ec);
        conteneurD.setOnDragListener(ec);



         cleCouleur = new HashMap<Integer, String>();
         cleCouleur.put(-329737,"blanc");
         cleCouleur.put(-13022805,"bleu");
         cleCouleur.put(-849912,"rouge");
         cleCouleur.put(-16448251,"noir");
         cleCouleur.put(-19712,"jaune");

    }
    @Override
    protected void onStart() {
        super.onStart();

//        choix =   gest.retournePays();
        gest = SingletonDatabase.getInstance(getApplicationContext());
        instance = SingletonDatabase.getInstance(getApplicationContext());

        gest.ouvrireConnectionBd();

    }



    private class Ecouteur implements View.OnClickListener,View.OnDragListener, View.OnTouchListener {




        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Do nothing
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    // Optional: change the style of the target view to indicate that it is an active drop target.
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    // Optional: revert any styling applied in ACTION_DRAG_ENTERED.
                    return true;
                case DragEvent.ACTION_DROP:
                    if (v instanceof LinearLayout) {
                        LinearLayout parentActuel = (LinearLayout) v;
                        View draggedView = (View) event.getLocalState();
                        int color = ((ColorDrawable) draggedView.getBackground()).getColor();
                        String colorName = cleCouleur.get(color);
                        if (colorName != null) {
                            parentActuel.setBackgroundColor(color);
                        }
                        draggedView.setVisibility(View.VISIBLE);  // Reset visibility of the dragged view
                    }
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    // Optional: revert any styling applied during the drag.
                    return true;
                default:
                    break;
            }
            return false;
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(null, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE); // you can also use View.GONE
                return true;
            }
            return false;
        }


        @Override
        public void onClick(View v) {

//            questions.setText("Dessiner le dreapeay de la" + gest.retournePays());

//            String reponseDonnee =  gest.bonPays();
            if(v == confirmer){
                if (gest.bonPays("bleu", "blanc", "rouge", "France")){

                    questions.setText("BRAVO");
                } else if (gest.bonPays("rouge", "blanc", "rouge", "Pérou")) {
                    questions.setText("BRAVO");
                } else if (gest.bonPays("bleu", "jaune", "rouge", "Roumanie")) {
                    questions.setText("BRAVO");
                } else if (gest.bonPays("noir", "jaune", "rouge", "Belgique")) {
                    questions.setText("BRAVO");
                }else{
                    questions.setText("DSL");
                }


            }

        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        gest.fermerConnection();
    }
}