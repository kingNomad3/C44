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
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

//     le enum me permet d'invoquer mes constructeurs
//    https://developer.android.com/reference/kotlin/java/lang/Enum
//    https://www.geeksforgeeks.org/enum-in-java/
    enum TypeOutil {
        CRAYON,
        RECTANGLE,
        CERCLE
    }

    // permet de choisir le type de crayon dans ma boite d'outil
    private TypeOutil outilChoisie = TypeOutil.CRAYON; // par defaut crayon

    ConstraintLayout zoneDessin;
    SurfaceDessin surf;

    LinearLayout scrollOption;
    LinearLayout couleurwheel;
    Button  couleurRouge;
    //Image view
    ImageView largeur_trait;
    ImageView crayon;
    ImageView rectangle;
    ImageView cercle;
    ImageView undo;
    ImageView peintureBackground;

    //ecouteurs
    Ecouteursurf ecSurf = new Ecouteursurf();
    EcouteurBouton ecBouton  = new EcouteurBouton();
    //ecoteurs pour les couleurs
    EcouteurCouleur ecCouleur = new EcouteurCouleur();

    //not working
    private HashMap<Integer, Integer> primaryColorsMap = new HashMap<>();

    //tableau pour passer toutes les formes
    ArrayList<BoiteOutil> listeCrayon = new ArrayList<>();

    //controle l'epaiosseur des formes
    float epaisseurTrait =15; // 15 par defaut
    int currentCouleur = R.color.black; // par default

    //paint
    Paint c;
    Paint paint;


    int backgroundColor = R.color.teal_200; // cbackground color par defaut

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //assignement des id
        zoneDessin = findViewById(R.id.zoneDessin);
        largeur_trait = findViewById(R.id.largeur_trait);
        peintureBackground = findViewById(R.id.peintureBackground);
        couleurwheel = findViewById(R.id.couleurwheel);
        undo = findViewById(R.id.undo);
        rectangle = findViewById(R.id.rectangle);
        crayon = findViewById(R.id.crayon);
        cercle = findViewById(R.id.cercle);
        couleurRouge = findViewById(R.id.buttonColorRed);

        //surface de dessin
        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1)); // Fill parent
        surf.setBackgroundColor(ContextCompat.getColor(this, backgroundColor));
        zoneDessin.addView(surf);

        //pour les couleurs
        primaryColorsMap.put(R.id.buttonColorRed, Color.BLUE);
//        primaryColorsMap.put(R.id.buttonColorBlue, Color.BLUE);
//        primaryColorsMap.put(R.id.buttonColorGreen, Color.GREEN);

        //association des ecouteurs
        surf.setOnTouchListener(ecSurf);
        largeur_trait.setOnClickListener(ecBouton);
        crayon.setOnClickListener(ecBouton);
        peintureBackground.setOnClickListener(ecBouton);
        couleurwheel.setOnClickListener(ecBouton);
//        redo tableau pour les variavbles temp?
        undo.setOnClickListener(ecBouton);
        rectangle.setOnClickListener(ecBouton);
        cercle.setOnClickListener(ecBouton);
        couleurRouge.setOnClickListener(ecCouleur);
        //triangle je comprends pas les calcules
        //couuleurs a faire
        //changement de fond d,ecran voir le document du prof utliser bitmap
        //effacer aucune idee


    }

// a changer pour des cases
    private class Ecouteursurf implements View.OnTouchListener {
        @Override
        public boolean onTouch(View source, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            Log.d("TouchDebug", "X: " + x + " Y: " + y);

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (outilChoisie == TypeOutil.CRAYON) {
                    // Créer un nouvel objet Crayon
                    Crayon crayon = new Crayon(epaisseurTrait,currentCouleur);
                    crayon.onTouchDown(x, y);
                    listeCrayon.add(crayon);
                    source.invalidate();
                } else if (outilChoisie == TypeOutil.RECTANGLE) {
                    // Créer un nouvel objet Rectangle
                    Rectangle rectangle = new Rectangle(epaisseurTrait, Color.RED,paint) ;
                    rectangle.onTouchDown(x, y);
                    listeCrayon.add(rectangle);
                    source.invalidate();
                } else if (outilChoisie == TypeOutil.CERCLE) {
                    // Créer un nouvel objet Cercle
                    Cercle cercle = new Cercle(epaisseurTrait, Color.GREEN, paint, 0);
                    cercle.onTouchDown(x, y);
                    listeCrayon.add(cercle);
                    source.invalidate();
                }
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (outilChoisie == TypeOutil.CRAYON) {
                    Crayon crayon = (Crayon) listeCrayon.get(listeCrayon.size() - 1);
                    crayon.onTouchMove(x, y);
                } else if (outilChoisie == TypeOutil.RECTANGLE) {
                    Rectangle rectangle = (Rectangle) listeCrayon.get(listeCrayon.size() - 1);
                    rectangle.onTouchMove(x, y);
                } else if (outilChoisie == TypeOutil.CERCLE) {
                    Cercle cercle = (Cercle) listeCrayon.get(listeCrayon.size() - 1);
                    cercle.onTouchMove(x, y);
                }
                source.invalidate();
            }
            return true;
        }
    }


    private class EcouteurBouton implements View.OnClickListener {
        @Override
        public void onClick(View source) {
            if (source == crayon) {
                // Gérer le clic sur le bouton "crayon" ici
                outilChoisie = TypeOutil.CRAYON;
            } else if (source == rectangle) {
                // Gérer le clic sur le bouton "rectangle" ici
                outilChoisie = TypeOutil.RECTANGLE;
            } else if (source == cercle) {
                // Gérer le clic sur le bouton "cercle" ici
                outilChoisie = TypeOutil.CERCLE;
            } else if (source == largeur_trait) {
                // Afficher le dialogue pour personnaliser la taille du crayon
                showCrayonTaille();
            } else if (source == couleurwheel) {
                // Gérer le clic sur le bouton "couleur" ici
                // showCouleurDialog(); // Afficher le dialogue pour choisir la couleur
            } else if (source == peintureBackground) {
//                showColorDialog((ImageView) source);
            } else if (source == undo) {
                if (!listeCrayon.isEmpty()) {
                    listeCrayon.remove(listeCrayon.size() - 1);
                }
            }
            // surf.invalidate();
        }
    }


    private class EcouteurCouleur implements View.OnClickListener{
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View source) {

            Button butonCouleur  = (Button) source;
            Drawable couleurId = (Drawable) butonCouleur.getBackground();

            if (couleurId instanceof ColorDrawable) {
                ColorDrawable temp  = (ColorDrawable) couleurId;
                currentCouleur = temp.getColor(); // get child at
            }

//             = butonCouleur.getBackground(); //en color drawable et ensuite couleur
//
            System.out.println("couleur change");

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
                        // Mettez à jour l'épaisseur du trait dans la vue SurfaceDessin
//                        surf.setEpaisseurTrait(epaisseurTrait);
                    }

                });
                builder.setNegativeButton("Annuler", null);
                builder.show();

        }


//            private void showColorDialog(final ImageView colorButton) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Choose a Color");
//
//                // liste des couleur
//                final String[] colorNames = {"Blue", "Red", "Green"};
//
//                builder.setItems(colorNames, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        int selectedColor = primaryColorsMap.get(colorButton.getId());
//                        peintureBackground.setBackgroundColor(selectedColor);
//                    }
//                });
//
//                builder.setNegativeButton("Cancel", null);
//                builder.show();
//            }


    private class SurfaceDessin extends View {
        public SurfaceDessin(Context context) {
            super(context);
            c = new Paint(Paint.ANTI_ALIAS_FLAG);
            c.setStyle(Paint.Style.STROKE);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Dessiner les crayons, rectangles et cercles
            for (BoiteOutil outil : listeCrayon) {
                c.setColor(outil.getCurrentCouleur());
                c.setStrokeWidth(outil.getEpaisseurTrait());

                if (outil instanceof Crayon) {
                    // Crayon
                    Crayon crayon = (Crayon) outil;
//                    pourquoi getP de ma boite a outil et paint ne marche pas
                    canvas.drawPath(crayon.getPath(), crayon.getP());
                } else if (outil instanceof Rectangle) {
                    // Rectangle
                    Rectangle rectangle = (Rectangle) outil;
                    canvas.drawRect(rectangle.getRect(), rectangle.getP());
                } else if (outil instanceof Cercle) {
                    // Cercle
                    Cercle cercle = (Cercle) outil;
                    // Dessinez le cercle en utilisant les propriétés de Cercle (rayon, x, y)
                    canvas.drawCircle(cercle.getCenterX(), cercle.getCenterY(), cercle.getRayon(), cercle.getP());
                }
            }
        }
    }

}

