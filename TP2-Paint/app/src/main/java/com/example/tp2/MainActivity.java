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

    enum ToolType {
        CRAYON,
        RECTANGLE
    }

    private ToolType outilChoisie = ToolType.CRAYON; // Default crayon

    ConstraintLayout zoneDessin;

    SurfaceDessin surf;

    LinearLayout scrollOption;
    LinearLayout couleurwheel;
    Button  buttonColorRed;

    ImageView largeur_trait;
    ImageView crayon;
    ImageView rectangle;
    ImageView undo;
    ImageView peintureBackground;

    Ecouteursurf ecSurf = new Ecouteursurf();
    EcouteurBouton ecBouton  = new EcouteurBouton();

    Path p;
    private HashMap<Integer, Integer> primaryColorsMap = new HashMap<>();
    ArrayList<BoiteOutil> listeCrayon = new ArrayList<>();

    private ArrayList<Rectangle> listeRectangles = new ArrayList<>();

    float epaisseurTrait =15 ;
    Paint c;

    private Crayon crayonTool;
    private Rectangle rectangleTool;


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
        crayon = findViewById(R.id.crayon);

        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1)); // Fill parent
        surf.setBackgroundColor(ContextCompat.getColor(this, backgroundColor));
        zoneDessin.addView(surf);


        primaryColorsMap.put(R.id.buttonColorRed, Color.BLUE);
//        primaryColorsMap.put(R.id.buttonColorBlue, Color.BLUE);
//        primaryColorsMap.put(R.id.buttonColorGreen, Color.GREEN);


        surf.setOnTouchListener(ecSurf);
        largeur_trait.setOnClickListener(ecBouton);
        crayon.setOnClickListener(ecBouton);
        peintureBackground.setOnClickListener(ecBouton);
        couleurwheel.setOnClickListener(ecBouton);
        undo.setOnClickListener(ecBouton);
        rectangle.setOnClickListener(ecBouton);
//        p = new Path();

        Crayon crayonTool = new Crayon(epaisseurTrait, Color.BLUE);
        listeCrayon.add(crayonTool);

        Rectangle rectangleTool = new Rectangle(epaisseurTrait, Color.RED, new Path());
        listeCrayon.add(rectangleTool);


    }


    private class Ecouteursurf implements View.OnTouchListener {
        @Override
        public boolean onTouch(View source, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            Log.d("TouchDebug", "X: " + x + " Y: " + y);

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (outilChoisie == ToolType.CRAYON) {
                    // creation d'un nouvelle objet Crayon
                    Crayon crayon = new Crayon(epaisseurTrait, Color.BLUE);
                    crayon.onTouchDown(x, y);
                    listeCrayon.add(crayon);
                    source.invalidate();
                } else if (outilChoisie == ToolType.RECTANGLE) {
                    // creation d'un nouvelle objet Rectangle
                    Rectangle rectangle = new Rectangle(epaisseurTrait, Color.RED, new Path());
                    rectangle.onTouchDown(x, y);
                    listeCrayon.add(rectangle);
                    source.invalidate();
                }
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (outilChoisie == ToolType.CRAYON) {
                    Crayon crayon = (Crayon) listeCrayon.get(listeCrayon.size() - 1);
                    crayon.onTouchMove(x, y);
                } else if (outilChoisie == ToolType.RECTANGLE) {
                    Rectangle rectangle = (Rectangle) listeCrayon.get(listeCrayon.size() - 1);
                    rectangle.onTouchMove(x, y);
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
                outilChoisie = ToolType.CRAYON;
            } else if (source == rectangle) {
                // Gérer le clic sur le bouton "rectangle" ici
                outilChoisie = ToolType.RECTANGLE;
            } else if (source == largeur_trait) {
                // Afficher le dialogue pour personnaliser le crayon
                showCrayonTaille();
            } else if (source == couleurwheel) {
                // Gérer le clic sur le bouton "couleur" ici
                // showCouleurDialog(); // Afficher le dialogue pour choisir la couleur
            } else if (source == peintureBackground) {
                showColorDialog((ImageView) source);
            } else if (source == undo) {
                if (outilChoisie == ToolType.CRAYON && !listeCrayon.isEmpty()) {
                    listeCrayon.remove(listeCrayon.size() - 1);
                } else if (outilChoisie == ToolType.RECTANGLE && !listeCrayon.isEmpty()) {
                    listeCrayon.remove(listeCrayon.size() - 1);;
                }
            }
//            surf.invalidate();
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

            // Draw crayons and rectangles
            for (BoiteOutil tool : listeCrayon) {
                c.setColor(tool.getCurrentCouleur());
                c.setStrokeWidth(tool.getEpaisseurTrait());

                if (tool.isCrayon()) {
                    // crayon
                    Crayon crayon = (Crayon) tool;
                    canvas.drawPath(crayon.getPath(), crayon.getPaint());
                } else {
                    // rectangle
                    Rectangle rectangle = (Rectangle) tool;
                    canvas.drawRect(rectangle.getRect(), rectangle.getPaint());
                }
            }
        }
    }

}

