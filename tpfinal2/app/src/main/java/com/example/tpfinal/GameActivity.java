package com.example.tpfinal;


import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Stack;

public class GameActivity extends AppCompatActivity {

    // Déclarations des éléments de l'interface utilisateur
    LinearLayout parent, conteneurChiffreEntête,conteneurAscendant, conteneurAscendantGauche, conteneurAscendantDroite,
            conteneurDescendant, conteneurDescendantGauche, conteneurDescedantDroite;
    GridLayout deckCartes;

    Chronometer chronometer;
    EcouteurCarte ecouteurCarte;
    EcouteurDeck ecouteurDeck;
    Button menu;
    TextView nbCartesRestantes, textscore, carteSelectionner;
    Integer[] idDeckHaut = {R.id.deckCardHautGauche, R.id.deckCardHautDroite};
    Integer[] idDeckBas = {R.id.deckCarteBasGauche, R.id.deckCarteBasDroite};

    // Déclarations des objets pour gérer le jeu
    Partie partie;
    PileCarte pileCarte;
    Score score;
    int scoreAjoute;
    String chiffreSelectionne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initialisation des éléments de l'interface utilisateur
        parent = findViewById(R.id.MainLayout);
        conteneurChiffreEntête = findViewById(R.id.conteneurChiffresEnTete);
        conteneurAscendant = findViewById(R.id.conteneurAscendant);
        conteneurAscendantGauche = findViewById(R.id.conteneurAscendantGauche);
        conteneurAscendantDroite = findViewById(R.id.conteneurAscendantDroite);
        conteneurDescendant = findViewById(R.id.conteneurDescendant);
        conteneurDescendantGauche = findViewById(R.id.conteneurDescendantGauche);
        conteneurDescedantDroite = findViewById(R.id.conteneurDescendantDroite);
        deckCartes = findViewById(R.id.deckCards);
        chronometer = findViewById(R.id.simpleChronometer);
        menu = findViewById(R.id.buttonMenu);
        nbCartesRestantes = findViewById(R.id.nbCarteRestante);
        textscore = findViewById(R.id.score);

        // Initialisation des écouteurs et des valeurs de jeu
        ecouteurCarte = new EcouteurCarte();
        ecouteurDeck = new EcouteurDeck();
        chronometer.start();
        chiffreSelectionne = "0";
        score = new Score(0);
        scoreAjoute = 0;
        pileCarte = new PileCarte();
        pileCarte.melangerCartes();
        partie = new Partie();

        // Mise à jour des éléments d'affichage
        nbCartesRestantes.setText(String.valueOf(pileCarte.tailleListeCartes()));
        textscore.setText(String.valueOf(score.getScore()));

        // Définition des écouteurs pour le menu, les cartes et les conteneurs de deck
        menu.setOnClickListener(ecouteurCarte);

        // Remplissage des cartes du jeu avec des valeurs depuis la pile de cartes

        for (int i = 0; i < deckCartes.getChildCount(); i++) {
            TextView carte = (TextView) deckCartes.getChildAt(i);
            int carteNb = pileCarte.retirerCarte();
            changeCardColor(carte,carteNb);
            // Définir le texte de la carte avec la valeur de la carte tirée de la pile
            carte.setText(String.valueOf(pileCarte.tirerCarte()));

            // Définir un écouteur pour le toucher de la carte (déplacement)
            carte.setOnTouchListener(ecouteurCarte);

            // Définir un écouteur pour le glisser-déposer de la carte
            carte.setOnDragListener(ecouteurCarte);
        }

        // Configuration des conteneurs pour le deck ascendant
        for (int i = 0; i < conteneurAscendant.getChildCount(); i++) {
            LinearLayout conteneur = (LinearLayout) conteneurAscendant.getChildAt(i);

            // Définir un écouteur pour le glisser-déposer des cartes dans le deck ascendant
            conteneur.setOnDragListener(ecouteurDeck);
        }

        // Configuration des conteneurs pour le deck descendant
        for (int i = 0; i < conteneurDescendant.getChildCount(); i++) {
            LinearLayout conteneur = (LinearLayout) conteneurDescendant.getChildAt(i);

            // Définir un écouteur pour le glisser-déposer des cartes dans le deck descendant
            conteneur.setOnDragListener(ecouteurDeck);
        }
    }


    // Classe EcouteurCarte : Gère les interactions avec les cartes du jeu

    private class EcouteurCarte implements View.OnClickListener, View.OnDragListener, View.OnTouchListener {

        // Gestion du clic sur un élément de l'interface
        @Override
        public void onClick(View v) {
            // Si l'élément cliqué est le bouton "menu", démarre l'activité MainActivity
//            if (v.equals(menu))
//                startActivity(new Intent(GameActivity.this, MenuActivity.class));

        }


        // Gestion des événements de glisser-déposer sur un élément de l'interface
        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                // Lorsque le glisser-déposer commence, si le texte de la carte est égal à chiffreSelectionne, efface le texte de la carte
                if (((TextView) v).getText() == chiffreSelectionne)
                    ((TextView) v).setText("");
                //rajouter   java.lang.NumberFormatException: For input string: "" (GameActivity.java:168)
            } else if (event.getAction() == DragEvent.ACTION_DRAG_ENDED || event.getAction() == DragEvent.ACTION_DROP) {
                // Lorsque le glisser-déposer se termine, si chiffreSelectionne n'est pas null, met le texte de la carte sélectionnée à chiffreSelectionne
                if (chiffreSelectionne != null) {
                    carteSelectionner.setText(chiffreSelectionne);
                    chiffreSelectionne = null;
                }
            }
            return true;
        }

        // Gestion des événements tactiles sur un élément de l'interface
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

            // Démarrage du glisser-déposer lorsque l'élément est touché
            if (Build.VERSION.SDK_INT <= 24)
                v.startDrag(null, shadowBuilder, v, 0);
            else
                v.startDragAndDrop(null, shadowBuilder, v, 0);

            // Mémorisation de la carte sélectionnée et de sa valeur
            carteSelectionner = (TextView) v;
            chiffreSelectionne = (String) carteSelectionner.getText();
            return true;
        }
    }

    // Classe EcouteurDeck : Gère les événements de glisser-déposer sur les zones de deck
    private class EcouteurDeck implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                LinearLayout conteneur = (LinearLayout) v;
                boolean placementEffectue = false;
                int valeurCarte = Integer.parseInt(chiffreSelectionne);
                int carteCouranteValeur;
                TextView carteConteneur = null;
                TextView endroitDeposeCarte;

                // Vérifie le type de deck dans lequel la carte est déposée (ascendant ou descendant)
                if (v.getId() == conteneurAscendantGauche.getId() || v.getId() == conteneurDescendantGauche.getId())
                    carteConteneur = (TextView) conteneur.getChildAt(1);
                else if (v.getId() == conteneurAscendantDroite.getId() || v.getId() == conteneurDescedantDroite.getId())
                    carteConteneur = (TextView) conteneur.getChildAt(1);
                carteCouranteValeur = Integer.parseInt((String) carteConteneur.getText());

                // Vérifie si le placement de la carte est valide dans le deck actuel (ascendant ou descendant)
                if ((((LinearLayout) conteneur.getParent()).getId() == conteneurAscendant.getId())) {
                    if (partie.verifierPlacement("ascendant", valeurCarte, carteCouranteValeur))
                        placementEffectue = true;
                } else if ((((LinearLayout) conteneur.getParent()).getId() == conteneurDescendant.getId())) {
                    if (partie.verifierPlacement("descendant", valeurCarte, carteCouranteValeur))
                        placementEffectue = true;
                }

                if (placementEffectue) {
                    // Met à jour les informations sur la dernière carte placée
                    partie.setDernierCarteSurlapile(carteConteneur.getId());
                    partie.setDernierCarteSurlapileValeur((String) carteConteneur.getText());
                    endroitDeposeCarte = carteConteneur;
                    partie.getCarteEnleverValeur().add(chiffreSelectionne);
                    partie.getCarteEnleverEmplacement().add(carteSelectionner.getId());
                    nbCartesRestantes.setText(String.valueOf(pileCarte.tailleListeCartes() -  partie.getCarteEnleverValeur().size()));

                    // Calcule le score en fonction des cartes placées
                    partie.calculerScore(score, valeurCarte, carteCouranteValeur);
                    textscore.setText(String.valueOf(score.getScore()));

                    if (partie.getCarteEnleverValeur().size() == 2) {
                        if (pileCarte.tailleListeCartes() > 0) {
                            for (int i = 0; i < 2; i++) {
                                TextView nouvelleCarte = findViewById(partie.getCarteEnleverEmplacement().remove(partie.getCarteEnleverEmplacement().size() - 1));
                                partie.getCarteEnleverValeur().remove(partie.getCarteEnleverValeur().size() - 1);

                                int nouvelleValeurCarte = pileCarte.tirerCarte();
                                nouvelleCarte.setText(String.valueOf(nouvelleValeurCarte));
                            }
                        }
                    }
                } else {
                    endroitDeposeCarte = carteSelectionner;
                }
                endroitDeposeCarte.setText(chiffreSelectionne);

                if (placementEffectue) {
                    // Vérifie la fin de la partie ou si le deck de cartes est vide
                    if (verifierFindePartie() || pileCarte.tailleListeCartes() == 0 && partie.deckVide(deckCartes)) {
                        finish();
                        Intent i = new Intent(GameActivity.this,GameOverActivity.class);
                        i.putExtra("SCORE", textscore.getText());
                        startActivity(i);
                    }
                }
                carteSelectionner = null;
                chiffreSelectionne = null;
            }
            return true;
        }
    }

    // Méthode pour changer la couleur de la carte en fonction de sa valeur
    private void changeCardColor(TextView cardTextView, int cardValue) {
        if (cardValue == 98 || cardValue == 0) {
            cardTextView.setBackgroundResource(R.drawable.card_in_play);
        } else if (cardValue < 21) {
            cardTextView.setBackgroundResource(R.drawable.card_container_0_20);
        } else if (cardValue > 20 && cardValue < 41) {
            cardTextView.setBackgroundResource(R.drawable.card_container_21_40);
        } else if (cardValue > 40 && cardValue < 61) {
            cardTextView.setBackgroundResource(R.drawable.card_container_41_60);
        } else if (cardValue > 60 && cardValue < 80) {
            cardTextView.setBackgroundResource(R.drawable.card_container_61_80);
        } else {
            cardTextView.setBackgroundResource(R.drawable.card_container_81_plus);
        }
    }

    // Méthode pour vérifier la fin de la partie
    public boolean verifierFindePartie() {
        int valeurCartePile; // Valeur de la carte dans la pile
        int valeurCarteMain; // Valeur de la carte en main
        String valeurCarteMainString; // Valeur de la carte en main sous forme de chaîne

        // Vérifie les piles de cartes du haut (ascendant)
        for (Integer id : idDeckHaut) {
            valeurCartePile = Integer.parseInt((String) (((TextView) findViewById(id)).getText()));

            for (int i = 0; i < 8; i++) {
                valeurCarteMainString = (String) ((TextView) deckCartes.getChildAt(i)).getText();
                if (!valeurCarteMainString.contentEquals("")) {
                    valeurCarteMain = Integer.parseInt(valeurCarteMainString);
                    // Vérifie si la carte en main est plus grande que la carte dans la pile
                    // ou si elles ont une différence de 10
                    if (valeurCarteMain > valeurCartePile || valeurCartePile - valeurCarteMain == 10) {
                        return false; // La partie n'est pas terminée
                    }
                }
            }
        }

        // Vérifie les piles de cartes du bas (descendant)
        for (Integer id : idDeckBas) {
            valeurCartePile = Integer.parseInt((String) (((TextView) findViewById(id)).getText()));

            for (int i = 0; i < 8; i++) {
                valeurCarteMainString = (String) ((TextView) deckCartes.getChildAt(i)).getText();
                if (!valeurCarteMainString.contentEquals("")) {
                    valeurCarteMain = Integer.parseInt(valeurCarteMainString);
                    // Vérifie si la carte en main est plus petite que la carte dans la pile
                    // ou si elles ont une différence de 10
                    if (valeurCarteMain < valeurCartePile || valeurCarteMain - valeurCartePile == 10) {
                        return false; // La partie n'est pas terminée
                    }
                }
            }
        }

        return true; // La partie est terminée
    }
}

