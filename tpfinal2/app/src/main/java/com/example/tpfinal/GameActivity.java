package com.example.tpfinal;


import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    // Déclarations des éléments de l'interface utilisateur
    LinearLayout parent, conteneurChiffreEntête, conteneurHaut, conteneurHautGauche, conteneurHautDroite,
            conteneurBas, conteneurBasGauche, conteneurBasDroite;
    GridLayout deckGrid;
    Chronometer chronometer;
    EcouteurCarte ecouteurCarte;
    EcouteurDeck ecouteurDeck;
    ImageView replayButton;
    TextView nbCartesRestantes, textScore, carteSelectionner, menu;
    Integer[] idDeckHaut = {R.id.deckCardHautGauche, R.id.deckCardHautDroite};
    Integer[] idDeckBas = {R.id.deckCarteBasGauche, R.id.deckCarteBasDroite};

    // Déclarations des objets pour gérer le jeu
    Partie partie;
    Deck deck;
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
        conteneurHaut = findViewById(R.id.conteneurAscendant);
        conteneurHautGauche = findViewById(R.id.conteneurAscendantGauche);
        conteneurHautDroite = findViewById(R.id.conteneurAscendantDroite);
        conteneurBas = findViewById(R.id.conteneurDescendant);
        conteneurBasGauche = findViewById(R.id.conteneurDescendantGauche);
        conteneurBasDroite = findViewById(R.id.conteneurDescendantDroite);
        deckGrid = findViewById(R.id.deckCards);
        chronometer = findViewById(R.id.simpleChronometer);
        menu = findViewById(R.id.buttonMenu);
        nbCartesRestantes = findViewById(R.id.nbCarteRestante);
        textScore = findViewById(R.id.score);
        replayButton = findViewById(R.id.Replay);

        // Initialisation des écouteurs et des valeurs de jeu
        ecouteurCarte = new EcouteurCarte();
        ecouteurDeck = new EcouteurDeck();
        chronometer.start();
        chiffreSelectionne = "0";
        score = new Score(0);
        scoreAjoute = 0;
        deck = new Deck();
        deck.melangerCartes();
        partie = new Partie();

        // Mise à jour des éléments d'affichage
        nbCartesRestantes.setText(String.valueOf(deck.tailleListeCartes()));
        textScore.setText(String.valueOf(score.getScore()));

        // Définition des écouteurs pour le menu, les cartes et les conteneurs de deck
        menu.setOnClickListener(ecouteurCarte);

        // Remplissage des cartes du jeu avec des valeurs depuis la pile de cartes
        for (int i = 0; i < deckGrid.getChildCount(); i++) {
            TextView carte = (TextView) deckGrid.getChildAt(i);
            int carteNb = deck.retirerCarte();
            ChangementCouleur(carte,carteNb);
            // Définir le texte de la carte avec la valeur de la carte tirée de la pile
            carte.setText(String.valueOf(deck.tirerCarte()));
            // Définir un écouteur pour le toucher de la carte (déplacement)
            carte.setOnTouchListener(ecouteurCarte);
            // Définir un écouteur pour le glisser-déposer de la carte
            carte.setOnDragListener(ecouteurCarte);
        }
        // Configuration des conteneurs pour le deck ascendant
        for (int i = 0; i < conteneurHaut.getChildCount(); i++) {
            LinearLayout conteneur = (LinearLayout) conteneurHaut.getChildAt(i);

            // Définir un écouteur pour le glisser-déposer des cartes dans le deck ascendant
            conteneur.setOnDragListener(ecouteurDeck);
        }
        // Configuration des conteneurs pour le deck descendant
        for (int i = 0; i < conteneurBas.getChildCount(); i++) {
            LinearLayout conteneur = (LinearLayout) conteneurBas.getChildAt(i);
            // Définir un écouteur pour le glisser-déposer des cartes dans le deck descendant
            conteneur.setOnDragListener(ecouteurDeck);
        }
    }

    // Classe EcouteurCarte : Gère les interactions avec les cartes du jeu
    private class EcouteurCarte implements View.OnClickListener, View.OnDragListener, View.OnTouchListener {
        // Gestion du clic sur un élément de l'interface
        @Override
        public void onClick(View v) {
             //Si l'élément cliqué est le bouton "menu", démarre l'activité MainActivity
            if (v.equals(menu)){
                MenuAlert ma = new MenuAlert(GameActivity.this);
                ma.show();
            }
        }
        // Gestion des événements de glisser-déposer sur un élément de l'interface
        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {

                if (((TextView) v).getText() == chiffreSelectionne)
                    ((TextView) v).setText("");
                //rajouter   java.lang.NumberFormatException: For input string: "" (GameActivity.java:168) lorsqu une carte vide est utilise
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
            if (Build.VERSION.SDK_INT <= 24) {
                v.startDrag(null, shadowBuilder, v, 0);
            }else {
                v.startDragAndDrop(null, shadowBuilder, v, 0);
            }
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
                TextView carteConteneur = null;
                TextView endroitDeposeCarte;

                if (v.getId() == conteneurHautGauche.getId() || v.getId() == conteneurBasGauche.getId() ||
                        v.getId() == conteneurHautDroite.getId() || v.getId() == conteneurBasDroite.getId()) {
                    carteConteneur = (TextView) conteneur.getChildAt(1);
                }
                //empeche le code de crash, si une carte vide est placer sur une autre carte
                try {
                    int valeurCarte = Integer.parseInt(chiffreSelectionne);
                    int carteCouranteValeur = Integer.parseInt((String) carteConteneur.getText());
                    ChangementCouleur(carteConteneur, valeurCarte);

                    if ((((LinearLayout) conteneur.getParent()).getId() == conteneurHaut.getId())) {
                        if (partie.verifierPlacement("ascendant", valeurCarte, carteCouranteValeur))
                            placementEffectue = true;
                    } else if ((((LinearLayout) conteneur.getParent()).getId() == conteneurBas.getId())) {
                        if (partie.verifierPlacement("descendant", valeurCarte, carteCouranteValeur))
                            placementEffectue = true;
                    }

                    if (placementEffectue) {
                        partie.setDernierCarteSurlapile(carteConteneur.getId());
                        partie.setDernierCarteSurlapileValeur((String) carteConteneur.getText());
                        endroitDeposeCarte = carteConteneur;
                        partie.getCarteEnleverValeur().add(chiffreSelectionne);
                        partie.getCarteEnleverEmplacement().add(carteSelectionner.getId());
                        nbCartesRestantes.setText(String.valueOf(deck.tailleListeCartes() - partie.getCarteEnleverValeur().size()));

                        partie.calculerScore(score, valeurCarte, carteCouranteValeur);
                        textScore.setText(String.valueOf(score.getScore()));

                        if (partie.getCarteEnleverValeur().size() == 2) {
                            if (deck.tailleListeCartes() > 0) {
                                for (int i = 0; i < 2; i++) {
                                    TextView nouvelleCarte = findViewById(partie.getCarteEnleverEmplacement().remove(partie.getCarteEnleverEmplacement().size() - 1));
                                    partie.getCarteEnleverValeur().remove(partie.getCarteEnleverValeur().size() - 1);

                                    int nouvelleValeurCarte = deck.tirerCarte();
                                    nouvelleCarte.setText(String.valueOf(nouvelleValeurCarte));
                                    ChangementCouleur(nouvelleCarte, nouvelleValeurCarte);
                                }
                            }
                        }

                    } else {
                        endroitDeposeCarte = carteSelectionner;
                    }
                    endroitDeposeCarte.setText(chiffreSelectionne);

                    if (placementEffectue) {
                        if (verifierFindePartie() || deck.tailleListeCartes() == 0 && partie.deckVide(deckGrid)) {
                            finish();
                            Intent i = new Intent(GameActivity.this, GameOverActivity.class);
                            i.putExtra("SCORE", textScore.getText());
                            startActivity(i);
                        }
                    }
                    carteSelectionner = null;
                    chiffreSelectionne = null;

                } catch (NumberFormatException e) {
                    System.out.println("Exception");
                }
            }
            return true;
        }
    }

    // Méthode pour changer la couleur de la carte en fonction de sa valeur
    private void ChangementCouleur(TextView cardTextView, int cardValue) {
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
                valeurCarteMainString = (String) ((TextView) deckGrid.getChildAt(i)).getText();
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
                valeurCarteMainString = (String) ((TextView) deckGrid.getChildAt(i)).getText();
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

