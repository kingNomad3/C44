package com.example.tpfinal;

import android.widget.Chronometer;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import java.util.Vector;

public class Partie {
    private Vector<String> valeurCarteEnlever; // Vecteur pour stocker les valeurs des cartes retirées du jeu
    private Vector<Integer> placeCarteEnlever; // Vecteur pour stocker les emplacements des cartes retirées
    private int dernierCarteSurLaPile; // Identifiant de la dernière carte placée sur la pile
    private String valeurDernierCarteSurLaPile; // Valeur de la dernière carte placée sur la pile
    Chronometer simpleChronometer;
    Deck deck = new Deck();
    public Partie() {
        this.valeurCarteEnlever = new Vector<>();
        this.placeCarteEnlever = new Vector<>();
        this.dernierCarteSurLaPile = 0;
        this.valeurDernierCarteSurLaPile = "";
    }
    public boolean verifierPlace(String conteneurParent, int valeurCarte, int carteCouranteValeur) {
        // Méthode qui permet de vérifier si la carte que le joueur veut placer est possible ou non
        if (conteneurParent.equals("ascendant"))
            return (valeurCarte > carteCouranteValeur || carteCouranteValeur - valeurCarte == 10);
        else if (conteneurParent.equals("descendant"))
            return (valeurCarte < carteCouranteValeur || valeurCarte - carteCouranteValeur == 10);
        else
            return false;
    }
    public int calculScore(Score score, int valeurCarte, int carteCouranteValeur, long timeDifference, int cartesRestantes) {
        // Calculer un bonus de score inversement proportionnel à timeDifference
        //le 100 permet de me donne un nombre de point plus significative
        timeDifference = timeDifference/1000;// convertie miliseconds en seconds
        int bonusScore = (int) Math.round(100/ Math.log(timeDifference + 1.0));  //+ 1 au cas ou il aurait une division par 0

        // Calculer le bonus basé sur le nombre de cartes restantes
        // L'inverse du nombre de cartes restantes : moins il y a de cartes, plus le bonus est élevé
        int bonusCartesRestantes = (deck.getNbCartetotal() - cartesRestantes) * 2;

        // Calculer le score basé sur la proximité de la carte jouée à la carte courante
        int UpdateScore = Math.abs(valeurCarte - carteCouranteValeur) + bonusScore + bonusCartesRestantes;

        // Mettre à jour le score total
        int scoreCourant = score.getScore();
        score.setScore(UpdateScore + scoreCourant);

        return UpdateScore + scoreCourant;
    }
    public boolean isVide(GridLayout deckCartes) {
        // Méthode permettant de vérifier si le joueur n'a plus de cartes
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
