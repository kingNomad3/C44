package com.example.tpfinal;

import java.util.Collections;
import java.util.Vector;

public class PileCarte {
    private Vector<Carte> listeCartes; // Vecteur pour stocker les cartes de la pile

    public PileCarte() {
        // On crée les 98 cartes dans le jeu
        listeCartes = new Vector<Carte>();
        for (int i = 1; i < 97; i++)
            listeCartes.add(new Carte(i));
    }

    public void melangerCartes() {
        // Méthode permettant de mélanger les cartes dans le jeu
        Vector listeCartesMelangees = listeCartes;
        Collections.shuffle(listeCartesMelangees);
        this.listeCartes = listeCartesMelangees;
    }

    public int tirerCarte() {

        // Méthode permettant de tirer une carte (la dernière sur la pile de cartes) après que cette pile soit mélangée
        // J'ai utilisé listeCartes.size()-1 comme alternative à pop, car j'utilise un vecteur et non un Stack
        return listeCartes.remove(listeCartes.size()-1).getChiffre();
    }

    public int tailleListeCartes() {
        return listeCartes.size();
    }

    public Vector<Carte> getListeCartes() {
        return listeCartes;
    }

    public void setListeCartes(Vector<Carte> listeCartes) {
        this.listeCartes = listeCartes;
    }
}
