package com.example.tpfinal;

import android.app.Activity;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import java.util.Vector;

public class Partie {

    // Classe servant de modèle à la vue
    // permet de verifier le court du jeu et ses fonctionnalites

    private Vector<String> carteEnleverValeur; // Vecteur pour stocker les valeurs des cartes retirées du jeu
    private Vector<Integer> carteEnleverEmplacement; // Vecteur pour stocker les emplacements des cartes retirées
    private int dernierCarteSurlapile; // Identifiant de la dernière carte placée sur la pile
    private String dernierCarteSurlapileValeur; // Valeur de la dernière carte placée sur la pile

    public Partie() {
        this.carteEnleverValeur = new Vector<>();
        this.carteEnleverEmplacement = new Vector<>();
        this.dernierCarteSurlapile = 0;
        this.dernierCarteSurlapileValeur = "";
    }

    public boolean verifierPlacement(String conteneurParent, int valeurCarte, int carteCouranteValeur) {

        // Méthode permettant de vérifier si la carte que le joueur veut placer est possible ou non
        // Si oui, retourne true, sinon, retourne false

        if (conteneurParent.equals("ascendant"))
            return (valeurCarte > carteCouranteValeur || carteCouranteValeur - valeurCarte == 10);
        else if (conteneurParent.equals("descendant"))
            return (valeurCarte < carteCouranteValeur || valeurCarte - carteCouranteValeur == 10);
        else
            return false;
    }

    public int calculerScore(Score score, int valeurCarte, int carteCouranteValeur) {

        // Méthode permettant de calculer le score du joueur dans son action
        // Plus la différence entre la carte mise et la carte courante est grande, plus le score sera élevé
        int scoreAjoute = (int) (Math.abs(valeurCarte - carteCouranteValeur) * 2.5);
        int scoreCourant = score.getScore();
        if (scoreAjoute < 0)
            scoreAjoute = 0;
        score.setScore(scoreAjoute += scoreCourant);
        return scoreAjoute + scoreCourant;
    }

    public boolean deckVide(GridLayout deckCartes) {

        // Méthode permettant de vérifier si le joueur n'a plus de cartes dans son deck
        boolean deckVide = true;

        for (int i = 0; i < 8; i++)
            if (!((String) ((TextView) deckCartes.getChildAt(i)).getText()).contentEquals("")) {
                deckVide = false;
                break;
            }
        return deckVide;
    }

    public Vector<String> getCarteEnleverValeur() {
        return carteEnleverValeur;
    }

    public void setCarteEnleverValeur(Vector<String> carteEnleverValeur) {
        this.carteEnleverValeur = carteEnleverValeur;
    }

    public Vector<Integer> getCarteEnleverEmplacement() {
        return carteEnleverEmplacement;
    }

    public void setCarteEnleverEmplacement(Vector<Integer> carteEnleverEmplacement) {
        this.carteEnleverEmplacement = carteEnleverEmplacement;
    }

    public int getDernierCarteSurlapile() {
        return dernierCarteSurlapile;
    }

    public void setDernierCarteSurlapile(int dernierCarteSurlapile) {
        this.dernierCarteSurlapile = dernierCarteSurlapile;
    }

    public String getDernierCarteSurlapileValeur() {
        return dernierCarteSurlapileValeur;
    }

    public void setDernierCarteSurlapileValeur(String dernierCarteSurlapileValeur) {
        this.dernierCarteSurlapileValeur = dernierCarteSurlapileValeur;
    }
}
