package com.example.tp2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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


public class MainActivity extends AppCompatActivity {

//     le enum me permet d'invoquer mes constructeurs
//    https://developer.android.com/reference/kotlin/java/lang/Enum
//    https://www.geeksforgeeks.org/enum-in-java/
    enum TypeOutil {
        CRAYON,
        RECTANGLE,
        CERCLE,
        TRIANGLE,
        EFFACE,

    }

    // permet de choisir le type de crayon dans ma boite d'outil
    private TypeOutil outilChoisie = TypeOutil.CRAYON; // par defaut crayon

    //surface de dessin
    ConstraintLayout zoneDessin;
    SurfaceDessin surf;
    LinearLayout couleurwheel;


    //Image view
    ImageView largeur_trait;
    ImageView crayon;
    ImageView rectangle;
    ImageView cercle;
    ImageView undo;
    ImageView potPeinture;
    ImageView triangle;
    ImageView pipette;
    ImageView efface;

    //ecouteurs
    Ecouteursurf ecSurf = new Ecouteursurf();
    EcouteurBouton ecBouton  = new EcouteurBouton();
    EcouteurCouleur ecCouleur = new EcouteurCouleur();


    //tableau pour passer toutes les formes
    ArrayList<BoiteOutil> listeCrayon = new ArrayList<>();
    //tableau temporaire pour le redo
    ArrayList<BoiteOutil> redo = new ArrayList<>();

    //par defaut
    float epaisseurTrait =15; // 15 par defaut
    int currentCouleur = R.color.black; // par default<
    int backgroundColor = R.color.white; // cbackground color par defaut

    Paint paint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //assignement des id
        zoneDessin = findViewById(R.id.zoneDessin);
        largeur_trait = findViewById(R.id.largeur_trait);
        potPeinture = findViewById(R.id.potPeinture);
        couleurwheel = findViewById(R.id.couleurwheel);
        undo = findViewById(R.id.undo);
        pipette = findViewById(R.id.pipette);
        rectangle = findViewById(R.id.rectangle);
        crayon = findViewById(R.id.crayon);
        cercle = findViewById(R.id.cercle);
        triangle = findViewById(R.id.triangle);
        efface = findViewById(R.id.efface);



            for (int i = 0; i < couleurwheel.getChildCount(); i++) {
                View childView = couleurwheel.getChildAt(i);
                if (childView instanceof Button) {
                    Button button = (Button) childView;
                    button.setOnClickListener(ecCouleur);
                }
            }



        //surface de dessin
        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1)); // Fill parent
        surf.setBackgroundColor(ContextCompat.getColor(this, backgroundColor));
        zoneDessin.addView(surf);



        //association des ecouteurs
        surf.setOnTouchListener(ecSurf);
        largeur_trait.setOnClickListener(ecBouton);
        crayon.setOnClickListener(ecBouton);
        triangle.setOnClickListener(ecBouton);
        pipette.setOnClickListener(ecBouton);
        potPeinture.setOnClickListener(ecBouton);
        couleurwheel.setOnClickListener(ecBouton);
//        redo tableau pour les variavbles temp?
        undo.setOnClickListener(ecBouton);
        rectangle.setOnClickListener(ecBouton);
        cercle.setOnClickListener(ecBouton);
        efface.setOnClickListener(ecBouton);

//        couleurRouge.setOnClickListener(ecCouleur);
//        couleurBleu.setOnClickListener(ecCouleur);
        //triangle je comprends pas les calcules
        //couuleurs a faire
        //changement de fond d,ecran voir le document du prof utliser bitmap
        //effacer aucune idee


    }


    private class Ecouteursurf implements View.OnTouchListener {
        @SuppressLint("ResourceAsColor")
        @Override
        public boolean onTouch(View source, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            Log.d("TouchDebug", "X: " + x + " Y: " + y);

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (outilChoisie == TypeOutil.CRAYON) {
                    Crayon crayon = new Crayon(epaisseurTrait, currentCouleur);
                    crayon.onTouchDown(x, y);
                    listeCrayon.add(crayon);
                } else if (outilChoisie == TypeOutil.RECTANGLE) {
                    Rectangle rectangle = new Rectangle(epaisseurTrait, currentCouleur, paint);
                    rectangle.onTouchDown(x, y);
                    listeCrayon.add(rectangle);
                } else if (outilChoisie == TypeOutil.CERCLE) {
                    Cercle cercle = new Cercle(epaisseurTrait, currentCouleur, paint, 0);
                    cercle.onTouchDown(x, y);
                    listeCrayon.add(cercle);
                } else if (outilChoisie == TypeOutil.TRIANGLE) {
                    Triangle triangle = new Triangle(epaisseurTrait, currentCouleur, paint);
                    triangle.onTouchDown(x, y);
                    listeCrayon.add(triangle);
                } else if (outilChoisie == TypeOutil.EFFACE) {
//                    currentCouleur = backgroundColor;
                    Efface efface = new Efface(epaisseurTrait, currentCouleur, new Path());
                    efface.onTouchDown(x, y);
                    listeCrayon.add(efface);
//                    System.out.println("efface");
                }
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (!listeCrayon.isEmpty()) {
                    BoiteOutil outil = listeCrayon.get(listeCrayon.size() - 1);
                    outil.onTouchMove(x, y);
                }
            }

            source.invalidate();
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
            } else if (source == triangle) {
                // Gérer le clic sur le bouton "triangle" ici
                outilChoisie = TypeOutil.TRIANGLE;
            } else if (source == largeur_trait) {
                // Afficher le dialogue pour personnaliser la taille du crayon
                showCrayonTaille();
            } else if (source == couleurwheel) {
                // Code pour gérer la sélection de couleur
                new EcouteurCouleur();
            } else if (source == potPeinture) {
                // change la couleur de fond
                surf.setBackgroundColor(currentCouleur);
            } else if (source == undo) {
                if (!listeCrayon.isEmpty()) {
                    listeCrayon.remove(listeCrayon.size() - 1);
                }
            } else if (source == pipette) {
                // Activer la pipette lorsque le bouton "pipette" est cliqué
                System.out.println("bipmap");
                outilChoisie = TypeOutil.CRAYON;
            } else if (source == efface) {
                // Activer l'outil "Efface" lorsque le bouton "effaceButton" est cliqué
                outilChoisie = TypeOutil.EFFACE;
            }

            surf.invalidate();
        }
    }

    private class EcouteurCouleur implements View.OnClickListener {

        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View source) {

            Button butonCouleur = (Button) source;
            Drawable couleurId = butonCouleur.getBackground();

            ColorDrawable temp = (ColorDrawable) couleurId;
            currentCouleur = temp.getColor();

            System.out.println("Couleur changée : " + currentCouleur);
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
    private class SurfaceDessin extends View {
        public Bitmap getBitmapImage() {

            this.buildDrawingCache();
            Bitmap bitmapImage = Bitmap.createBitmap(this.getDrawingCache());
            this.destroyDrawingCache();

            return bitmapImage;
        }

        public SurfaceDessin(Context context) {
            super(context);
        }

        @SuppressLint("ResourceAsColor")
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Dessiner les crayons, rectangles, cercles et triangles
            for (BoiteOutil outil : listeCrayon) {
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(outil.getCurrentCouleur());
                paint.setStrokeWidth(outil.getEpaisseurTrait());

                if (outil instanceof Crayon) {
                    // Crayon
                    Crayon crayon = (Crayon) outil;
                    canvas.drawPath(crayon.getPath(), paint);
                } else if (outil instanceof Rectangle) {
                    // Rectangle
                    Rectangle rectangle = (Rectangle) outil;
                    canvas.drawRect(rectangle.getRect(), paint);
                } else if (outil instanceof Cercle) {
                    // Cercle
                    Cercle cercle = (Cercle) outil;
                    canvas.drawCircle(cercle.getCenterX(), cercle.getCenterY(), cercle.getRayon(), paint);
                } else if (outil instanceof Triangle) {
                    // Triangle
                    Triangle triangle = (Triangle) outil;
                    canvas.drawPath(triangle.getPath(), paint);
                } else if (outil instanceof Efface) {
                    // Recuperer la surface en ColorDrawable
                    ColorDrawable cd = (ColorDrawable)surf.getBackground();
                    paint.setColor(cd.getColor());
                    Efface efface = (Efface) outil;
                    canvas.drawPath(efface.getPath(),paint);
                } else {
                    Log.d("PipetteDebug", "Current Color: " + Integer.toHexString(currentCouleur));

                    System.out.println("bipmap");
                    currentCouleur = getBitmapImage().getPixel((int) getX(),(int) getY());
                }
            }
        }
    }
}

