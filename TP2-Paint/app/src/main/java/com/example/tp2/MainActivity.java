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
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ConstraintLayout zoneDessin;

    SurfaceDessin surf;

    LinearLayout scrollOption;
    LinearLayout couleur;

    Button  buttonColorRed;

    ImageView largeur_trait;
    ImageView peintureBackground;

    Ecouteursurf ecSurf = new Ecouteursurf();
    EcouteurBouton ecBouton  = new EcouteurBouton();

    Path p;
    private HashMap<Integer, Integer> primaryColorsMap = new HashMap<>();
    private List<Path> listePaths = new ArrayList<>();

    private float epaisseurTrait = 15;

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
        couleur = findViewById(R.id.couleur);

        surf =  new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        surf.setBackgroundColor(ContextCompat.getColor(this, backgroundColor)); //variable change back ground color


        primaryColorsMap.put(R.id.buttonColorRed, Color.BLUE);
//        primaryColorsMap.put(R.id.buttonColorBlue, Color.BLUE);
//        primaryColorsMap.put(R.id.buttonColorGreen, Color.GREEN);


        zoneDessin.addView(surf);


        surf.setOnTouchListener(ecSurf);
        largeur_trait.setOnClickListener(ecBouton);
        peintureBackground.setOnClickListener(ecBouton);
        couleur.setOnClickListener(ecBouton);
        p = new Path();


    }

    private class Ecouteursurf implements View.OnTouchListener {
        @Override
        public boolean onTouch(View source, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                p = new Path(); // Créez un nouveau Path pour chaque nouveau tracé
                p.moveTo(x, y);
                listePaths.add(p); // Ajoutez le nouveau Path à la liste
                source.invalidate();
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                p.lineTo(x, y);
                source.invalidate();
            }

            return true;
        }
    }

    private class EcouteurBouton implements View.OnClickListener {
        @Override
        public void onClick(View source) {
            if (source == largeur_trait) {
                // Gérer le clic sur le bouton "crayon" ici
                showCrayonDialog(); // Afficher le dialogue pour personnaliser le crayon
            } else if (source == couleur) {
                // Gérer le clic sur le bouton "couleur" ici
//                showCouleurDialog(); // Afficher le dialogue pour choisir la couleur
            } else if (source == peintureBackground) {

                showColorDialog((ImageView) source);
            }
        }


            private void showCrayonDialog() {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Épaisseur du trait");
                final SeekBar seekBar = new SeekBar(MainActivity.this);
                seekBar.setMax(20); // Réglez la plage d'épaisseur souhaitée
                seekBar.setProgress((int) epaisseurTrait); // Définissez la valeur actuelle
                builder.setView(seekBar);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        epaisseurTrait = seekBar.getProgress(); // Mettez à jour l'épaisseur souhaitée
        //                // Mettez à jour l'épaisseur du trait dans la vue SurfaceDessin
                        surf.setEpaisseurTrait(epaisseurTrait);
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
            Paint c;
            public SurfaceDessin(Context context) {
                super(context);
                c = new Paint(Paint.ANTI_ALIAS_FLAG);
                c.setStyle(Paint.Style.STROKE);

                c.setColor(Color.RED);
                c.setStrokeWidth(epaisseurTrait);

            }


            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                for (Path path : listePaths) {
                    canvas.drawPath(path, c);
                }
            }

            public void setEpaisseurTrait(float epaisseur) {
                epaisseurTrait = epaisseur;
                c.setStrokeWidth(epaisseurTrait);
                invalidate(); // Forcer le redessin avec la nouvelle épaisseur
            }

        }
    }