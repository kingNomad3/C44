package com.example.tp2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

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
    ImageView redo;
    ImageView potPeinture;
    ImageView triangle;
    ImageView pipette;
    ImageView efface;
    ImageView save;

    //ecouteurs
    Ecouteursurf ecSurf = new Ecouteursurf();
    EcouteurBouton ecBouton  = new EcouteurBouton();
    EcouteurCouleur ecCouleur = new EcouteurCouleur();


    //tableau pour passer toutes les formes
    ArrayList<BoiteOutil> listeCrayon = new ArrayList<>();
    //tableau temporaire pour le redo
    ArrayList<BoiteOutil>  listeRedo = new ArrayList<>();

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
        redo = findViewById(R.id.redo);
        save = findViewById(R.id.save);

        //permet de traverse tout les couleurs disponible
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
        redo.setOnClickListener(ecBouton);
        undo.setOnClickListener(ecBouton);
        rectangle.setOnClickListener(ecBouton);
        cercle.setOnClickListener(ecBouton);
        efface.setOnClickListener(ecBouton);
        save.setOnClickListener(ecBouton);
    }

    private class Ecouteursurf implements View.OnTouchListener {
        @Override
        public boolean onTouch(View source, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    switch (outilChoisie) {
                        case CRAYON:
                            // Si l'outil choisi est le CRAYON, créez un nouveau Crayon et ajoutez-le à la liste
                            Crayon crayon = new Crayon(epaisseurTrait, currentCouleur);
                            crayon.onTouchDown(x, y);
                            listeCrayon.add(crayon);
                            listeRedo.clear();
                            break;
                        case RECTANGLE:
                            // Si l'outil choisi est le RECTANGLE, créez un nouveau Rectangle et ajoutez-le à la liste
                            Rectangle rectangle = new Rectangle(epaisseurTrait, currentCouleur, paint);
                            rectangle.onTouchDown(x, y);
                            listeCrayon.add(rectangle);
                            listeRedo.clear();
                            break;
                        case CERCLE:
                            // Si l'outil choisi est le CERCLE, créez un nouveau Cercle et ajoutez-le à la liste
                            Cercle cercle = new Cercle(epaisseurTrait, currentCouleur, paint, 0);
                            cercle.onTouchDown(x, y);
                            listeCrayon.add(cercle);
                            break;
                        case TRIANGLE:
                            // Si l'outil choisi est le TRIANGLE, créez un nouveau Triangle et ajoutez-le à la liste
                            Triangle triangle = new Triangle(epaisseurTrait, currentCouleur, paint);
                            triangle.onTouchDown(x, y);
                            listeCrayon.add(triangle);
                            listeRedo.clear();
                            break;
                        case EFFACE:
                            // Si l'outil choisi est l'EFFACE, créez un nouvel Efface et ajoutez-le à la liste
                            Efface efface = new Efface(epaisseurTrait, currentCouleur, new Path());
                            efface.onTouchDown(x, y);
                            listeCrayon.add(efface);
                            listeRedo.clear();
                            break;

                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!listeCrayon.isEmpty()) {
                        // Si la liste d'outils n'est pas vide, obtenez le dernier outil de la liste et gérez son mouvement
                        BoiteOutil outil = listeCrayon.get(listeCrayon.size() - 1);
                        outil.onTouchMove(x, y);
                    }
                    break;
            }
            // Demande à la vue de se redessiner après l'action
            source.invalidate();
            return true;
        }
    }


    //https://stackoverflow.com/questions/36624756/how-to-save-bitmap-to-android-gallery
    //https://medium.com/@atifsayings/get-save-bitmap-from-any-ui-android-studio-kotlin-cd9ea422eb7c
    // Fonction pour sauvegarder une image bitmap dans la galerie, prend plusieurs secondes avant d'apparetre pourquoi?
    private void sauvegardeImage(Bitmap bitmap) {
        // Générer un nom de fichier unique basé sur la date et l'heure
        String nomFichier = "dessin_" + new Date().getTime() + ".png";

        // Répertoire de stockage pour les images dans le répertoire "Images" public de l'appareil
        File repertoire = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MonApplicationDeDessin");

        boolean reussite = true;
        if (!repertoire.exists()) {
            // Créer le répertoire s'il n'existe pas
            reussite = repertoire.mkdirs();
        }

        if (reussite) {
            // Chemin complet du fichier image
            File fichierImage = new File(repertoire, nomFichier);

            //le try catch semble etre obligatoire
            try {
                // Créer un flux de sortie pour écrire l'image dans le fichier
                OutputStream fluxSortie = new FileOutputStream(fichierImage);

                // Compresser l'image au format PNG avec une qualité de 100
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fluxSortie);

                // Vider le flux et le fermer
                fluxSortie.flush();
                fluxSortie.close();

                // Ajouter l'image à la galerie pour qu'elle puisse être visualisée dans l'application Galerie
                MediaStore.Images.Media.insertImage(getContentResolver(), fichierImage.getAbsolutePath(), fichierImage.getName(), null);

                // Afficher un message de succès
                Toast.makeText(this, "L'image a été sauvegardée", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                // En cas d'erreur lors de l'enregistrement de l'image, afficher un message d'erreur
                Toast.makeText(this, "Erreur lors de la sauvegarde de l'image", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // Fonction pour obtenir une image bitmap à partir d'une vue
//    https://www.geeksforgeeks.org/how-to-create-bitmap-from-view-in-android/
    public static Bitmap getBitMapFromView(View view) {
        // Créer un bitmap avec la même taille que la vue
        Bitmap bitmapResultat = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapResultat);
        Drawable arrierePlanDrawable = view.getBackground();

        if (arrierePlanDrawable != null) {
            arrierePlanDrawable.draw(canvas);
        }
        view.draw(canvas);
        return bitmapResultat;
    }

    private class EcouteurBouton implements View.OnClickListener {
        @Override
        public void onClick(View source) {
            switch (source.getId()) {
                case R.id.crayon:
                    // Lorsque le bouton "crayon" est cliqué, choisissez l'outil "CRAYON"
                    outilChoisie = TypeOutil.CRAYON;
                    break;
                case R.id.rectangle:
                    // Lorsque le bouton "rectangle" est cliqué, choisissez l'outil "RECTANGLE"
                    outilChoisie = TypeOutil.RECTANGLE;
                    break;
                case R.id.cercle:
                    // Lorsque le bouton "cercle" est cliqué, choisissez l'outil "CERCLE"
                    outilChoisie = TypeOutil.CERCLE;
                    break;
                case R.id.triangle:
                    // Lorsque le bouton "triangle" est cliqué, choisissez l'outil "TRIANGLE"
                    outilChoisie = TypeOutil.TRIANGLE;
                    break;
                case R.id.largeur_trait:
                    // Afficher un dialogue pour personnaliser la taille du crayon lorsque le bouton "largeur_trait" est cliqué
                    showCrayonTaille();
                    break;
                case R.id.couleurwheel:
                    // Code pour gérer la sélection de couleur lorsque le bouton "couleurwheel" est cliqué
                    new EcouteurCouleur();
                    break;
                case R.id.potPeinture:
                    // Changer la couleur de fond lorsque le bouton "potPeinture" est cliqué
                    surf.setBackgroundColor(currentCouleur);
                    break;
                case R.id.undo:
                    // Annuler la dernière action lorsque le bouton "undo" est cliqué
                    if (!listeCrayon.isEmpty()) {
                        // Supprimez le dernier élément de listeCrayon
                        BoiteOutil elementSupprime = listeCrayon.remove(listeCrayon.size() - 1);
                        // Ajoutez l'élément supprimé à la liste redo
                        listeRedo.add(elementSupprime);
                    }
                    break;
                case R.id.redo:
                    // Refaire la dernière action lorsque le bouton "redo" est cliqué
                    if (!listeRedo.isEmpty()) {
                        // Supprimez le dernier élément de redo
                        BoiteOutil elementRefait = listeRedo.remove(listeRedo.size() - 1);
                        // Ajoutez l'élément refait à la liste listeCrayon
                        listeCrayon.add(elementRefait);
                    }
                    break;
                case R.id.pipette:
                    // Activer l'outil "pipette" lorsque le bouton "pipette" est cliqué
                    outilChoisie = TypeOutil.CRAYON;
                    break;
                case R.id.efface:
                    // Activer l'outil "Efface" lorsque le bouton "efface" est cliqué
                    outilChoisie = TypeOutil.EFFACE;
                    break;
                case R.id.save:
                    // Convertir la surface de dessin (surf) en une image Bitmap
                    Bitmap image = getBitMapFromView(surf);
                    // Enregistrer l'image Bitmap dans la galerie
                    sauvegardeImage(image);
                    break;
            }
            // Demande à la vue de se redessiner après l'action
            surf.invalidate();
        }
    }

//    Bien que le cadre du cours nous demande d'utiliser ie source == crayon, ceci fait beacoup de if else, alors j'ai opter pour des cases
//    private class EcouteurBouton implements View.OnClickListener {
//
//        @Override
//        public void onClick(View source) {
//            if (source == crayon) {
//                // Gérer le clic sur le bouton "crayon" ici
//                outilChoisie = TypeOutil.CRAYON;
//            } else if (source == rectangle) {
//                // Gérer le clic sur le bouton "rectangle" ici
//                outilChoisie = TypeOutil.RECTANGLE;
//            } else if (source == cercle) {
//                // Gérer le clic sur le bouton "cercle" ici
//                outilChoisie = TypeOutil.CERCLE;
//            } else if (source == triangle) {
//                // Gérer le clic sur le bouton "triangle" ici
//                outilChoisie = TypeOutil.TRIANGLE;
//            } else if (source == largeur_trait) {
//                // Afficher le dialogue pour personnaliser la taille du crayon
//                showCrayonTaille();
//            } else if (source == couleurwheel) {
//                // Code pour gérer la sélection de couleur
//                new EcouteurCouleur();
//            } else if (source == potPeinture) {
//                // change la couleur de fond
//                surf.setBackgroundColor(currentCouleur);
//            } else if (source == undo) {
//                if (!listeCrayon.isEmpty()) {
//                    listeCrayon.remove(listeCrayon.size() - 1);
//                }
//            } else if (source == pipette) {
//                // Activer la pipette lorsque le bouton "pipette" est cliqué
//                System.out.println("bipmap");
//                outilChoisie = TypeOutil.CRAYON;
//            } else if (source == efface) {
//                // Activer l'outil "Efface" lorsque le bouton "effaceButton" est cliqué
//                outilChoisie = TypeOutil.EFFACE;
//            }
//
//            surf.invalidate();
//        }
//    }
    private class EcouteurCouleur implements View.OnClickListener {
        @Override
        public void onClick(View source) {

            Button butonCouleur = (Button) source;
            Drawable couleurId = butonCouleur.getBackground();

            ColorDrawable temp = (ColorDrawable) couleurId;
            currentCouleur = temp.getColor();

        }
    }

    //https://stackoverflow.com/questions/13675822/android-alertdialog-builder
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
            }
        });
        builder.setNegativeButton("Annuler", null);
        builder.show();
    }


// pas ete capable
//    private void showCrayonTaille() {
//        Intent intent = new Intent(MainActivity.this, DialogueActivity.class);
//        intent.putExtra("epaisseurTrait", epaisseurTrait);
//        startActivityForResult(intent, 1);
//    }

    class SurfaceDessin extends View {
        public Bitmap getBitmapImage() {
            //non fonctionnel
            this.buildDrawingCache();
            Bitmap bitmapImage = Bitmap.createBitmap(this.getDrawingCache());
            this.destroyDrawingCache();

            return bitmapImage;
        }
        public SurfaceDessin(Context context) {
            super(context);
        }
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
                    currentCouleur = getBitmapImage().getPixel((int) getX(),(int) getY());
                }
            }
        }
    }
}

