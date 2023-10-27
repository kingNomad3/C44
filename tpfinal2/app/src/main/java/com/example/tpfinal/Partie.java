package com.example.tpfinal;

import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import java.util.Vector;

public class Partie {
    // permet de verifier le court du jeu et ses fonctionnalites
    private Vector<String> valeurCarteEnlever; // Vecteur pour stocker les valeurs des cartes retirées du jeu
    private Vector<Integer> placeCarteEnlever; // Vecteur pour stocker les emplacements des cartes retirées
    private int dernierCarteSurLaPile; // Identifiant de la dernière carte placée sur la pile
    private String valeurDernierCarteSurLaPile; // Valeur de la dernière carte placée sur la pile
    Chronometer simpleChronometer;
    public Partie() {
        this.valeurCarteEnlever = new Vector<>();
        this.placeCarteEnlever = new Vector<>();
        this.dernierCarteSurLaPile = 0;
        this.valeurDernierCarteSurLaPile = "";

    }
    public boolean verifierPlace(String conteneurParent, int valeurCarte, int carteCouranteValeur) {
        // Méthode permettant de vérifier si la carte que le joueur veut placer est possible ou non
        // Si oui, retourne true, sinon, retourne false
        if (conteneurParent.equals("ascendant"))
            return (valeurCarte > carteCouranteValeur || carteCouranteValeur - valeurCarte == 10);
        else if (conteneurParent.equals("descendant"))
            return (valeurCarte < carteCouranteValeur || valeurCarte - carteCouranteValeur == 10);
        else
            return false;
    }
    public int calculScore(Score score, int valeurCarte, int carteCouranteValeur, long simpleChronometer) {
        // Stop and read the Chronometer to get the elapsed time.
        simpleChronometer.stop();
        long elapsedTime = SystemClock.elapsedRealtime() - simpleChronometer.getBase();

        // Calculate speedFactor based on elapsed time.
        double maxTime = 10000.0;  // 10 seconds for maximum time
        double speedFactor = 2.0 - (elapsedTime / maxTime);
        if (speedFactor < 1.0) speedFactor = 1.0;  // setting a lower limit

        // Calculate score based on difference between played card and current card.
        int UpdateScore = (int) (Math.abs(valeurCarte - carteCouranteValeur) * 2.5 * speedFactor);  // Modified to include speedFactor

        int scoreCourant = score.getScore();
        if (UpdateScore < 0)
            UpdateScore = 0;
        score.setScore(UpdateScore + scoreCourant);

        // Reset the Chronometer for the next turn.
        simpleChronometer.setBase(SystemClock.elapsedRealtime());

        return UpdateScore + scoreCourant;
    }
    public boolean isVide(GridLayout deckCartes) {
        // Méthode permettant de vérifier si le joueur n'a plus de cartes dans son deck
        boolean isVide = true;

        for (int i = 0; i < 8; i++)
            if (!((String) ((TextView) deckCartes.getChildAt(i)).getText()).contentEquals("")) {
                isVide = false;
                break;
            }
        return isVide;
    }
    public Vector<String> getValeurCarteEnlever() {
        return valeurCarteEnlever;
    }
    public Vector<Integer> getPlaceCarteEnlever() {
        return placeCarteEnlever;
    }
    public void setDernierCarteSurLaPile(int dernierCarteSurLaPile) {
        this.dernierCarteSurLaPile = dernierCarteSurLaPile;
    }
    public void setValeurDernierCarteSurLaPile(String valeurDernierCarteSurLaPile) {
        this.valeurDernierCarteSurLaPile = valeurDernierCarteSurLaPile;
    }
}
