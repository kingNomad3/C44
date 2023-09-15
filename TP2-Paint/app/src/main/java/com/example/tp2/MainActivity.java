package com.example.tp2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    ConstraintLayout zoneDessin;

    SurfaceDessin surf;

    LinearLayout scrollOption;
    LinearLayout couleurwheel;
    Button  buttonColorRed;

    ImageView largeur_trait;
    ImageView crayon;
    ImageView rectangle,
    ImageView undo;
    ImageView peintureBackground;

    Ecouteursurf ecSurf = new Ecouteursurf();
    EcouteurBouton ecBouton  = new EcouteurBouton();

    Path p;
    private HashMap<Integer, Integer> primaryColorsMap = new HashMap<>();
    private ArrayList<Crayon> listeCrayon = new ArrayList<>();

    float epaisseurTrait =15 ;
    Paint c;



    int backgroundColor = R.color.teal_200; // cbackground color par defaut
    //changement du background color

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zoneDessin = findViewById(R.id.zoneDessin);
        largeur_trait = findViewById(R.id.largeur_trait);
        peintureBackground = findViewById(R.id.peintureBackground);
        couleurwheel = findViewById(R.id.couleurwheel);
        undo = findViewById(R.id.undo);
        rectangle = findViewById(R.id.rectangle);

        surf =  new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        surf.setBackgroundColor(ContextCompat.getColor(this, backgroundColor)); //variable change back ground color


        primaryColorsMap.put(R.id.buttonColorRed, Color.BLUE);
//        primaryColorsMap.put(R.id.buttonColorBlue, Color.BLUE);
//        primaryColorsMap.put(R.id.buttonColorGreen, Color.GREEN);


        zoneDessin.addView(surf);


        surf.setOnTouchListener(ecSurf);
        largeur_trait.setOnClickListener(ecBouton);
//        crayon.setOnClickListener(ecBouton);
        peintureBackground.setOnClickListener(ecBouton);
        couleurwheel.setOnClickListener(ecBouton);
        undo.setOnClickListener(ecBouton);
        rectangle.setOnClickListener(ecBouton);
//        p = new Path();


    }


    private class Ecouteursurf implements View.OnTouchListener {
        @Override
        public boolean onTouch(View source, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                p = new Crayon(epaisseurTrait,Color.BLUE,p); // Créez un nouveau Path pour chaque nouveau tracé
//                p = new Rectangle(epaisseurTrait,Color.BLUE,p);
                p.moveTo(x, y);
                listeCrayon.add((Crayon) p); // Ajoutez le nouveau Path à la liste
                source.invalidate();
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                p.lineTo(x, y);
                source.invalidate();
            }

            return true;
        }
    }

    private void DrawCrayon() {
        p = new Crayon(epaisseurTrait,Color.BLUE,p); // Créez un nouveau Path pour chaque nouveau tracé

    }

//    public void undo() {
//        // check whether the List is empty or not
//        // if empty, the remove method will return an error
//        if (paths.size() != 0) {
//            paths.remove(paths.size() - 1);
//            invalidate();
//        }
//    }



    private class EcouteurBouton implements View.OnClickListener {
        @Override
        public void onClick(View source) {
            if (source == largeur_trait) {
                // Gérer le clic sur le bouton "crayon" ici
                showCrayonTaille(); // Afficher le dialogue pour personnaliser le crayon
            } else if (source == couleurwheel) {
                // Gérer le clic sur le bouton "couleur" ici
//                showCouleurDialog(); // Afficher le dialogue pour choisir la couleur

            } else if (source == peintureBackground) {

                showColorDialog((ImageView) source);
            } else if (source == undo){
                if (listeCrayon.size() != 0) {
                    listeCrayon.remove(listeCrayon.size() - 1);
//                    invalidate();
                }

            }
        }


            private void showCrayonTaille() {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Épaisseur du trait");
                final SeekBar seekBar = new SeekBar(MainActivity.this);
                seekBar.setMax(100); // Réglez la plage d'épaisseur souhaitée
                seekBar.setProgress((int) epaisseurTrait); // Définissez la valeur actuelle
                builder.setView(seekBar);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        epaisseurTrait = seekBar.getProgress(); // Mettez à jour l'épaisseur souhaitée
        //                // Mettez à jour l'épaisseur du trait dans la vue SurfaceDessin
//                        surf.setEpaisseurTrait(epaisseurTrait);
                    }

                });
                builder.setNegativeButton("Annuler", null);
                builder.show();

        }

    }
            private void showColorDialog(final ImageView colorButton) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose a Color");

                // liste des couleur
                final String[] colorNames = {"Blue", "Red", "Green"};

                builder.setItems(colorNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedColor = primaryColorsMap.get(colorButton.getId());
                        peintureBackground.setBackgroundColor(selectedColor);
                    }
                });

                builder.setNegativeButton("Cancel", null);
                builder.show();
            }


        private class SurfaceDessin extends View {

            public SurfaceDessin(Context context) {
                super(context);
                c = new Paint(Paint.ANTI_ALIAS_FLAG);
                c.setStyle(Paint.Style.STROKE);

            }


            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);


                for (Crayon cr: listeCrayon) {

                    c.setColor(cr.currentCouleur);
                    c.setStrokeWidth(cr.epaisseurTrait);
//                    canvas.drawPath(cr, c);
                    canvas.drawRect(cr,c);
                }




            }

        }
    }