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

    TextView questions,bleu,rouge,noir,jaune;
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
        conteneurG = findViewById(R.id.conteneurG);
        conteneurC = findViewById(R.id.conteneurC);
        conteneurD = findViewById(R.id.conteneurD);
        confirmer = findViewById(R.id.boutonConfirmer);

        ec = new Ecouteur();

        questions.setOnClickListener(ec);
        bleu.setOnClickListener(ec);
        noir.setOnClickListener(ec);
        jaune.setOnClickListener(ec);
        rouge.setOnClickListener(ec);
        conteneurG.setOnClickListener(ec);
        conteneurC.setOnClickListener(ec);
        conteneurD.setOnClickListener(ec);
        confirmer.setOnClickListener(ec);


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

            Drawable couleurBleu = bleu.getBackground();
            Drawable couleurRouge = rouge.getBackground();
            Drawable couleurnoir = noir.getBackground();
            Drawable couleurJaune = jaune.getBackground();
            Drawable couleurChoisie;


        @Override
        public boolean onDrag(View v, DragEvent event) {
           if (event.getAction() == DragEvent.ACTION_DROP){
//               couleurChoisie = v.getResources().getDrawable();

               LinearLayout parentActuel =(LinearLayout) v;
               parentActuel.setBackground(couleurChoisie);

           }


            return true;
        }
        @Override
        public boolean onTouch(View source, MotionEvent event) {

            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(source);
            source.startDragAndDrop(null,shadowBuilder,source,0);

            source.setVisibility(View.VISIBLE);

            return true;
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