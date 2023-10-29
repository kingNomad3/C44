package com.example.tpfinal;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Integer> listeCartes; // Pile pour stocker les cartes de la pile
    private int nbCartetotal = 97;
    public Deck() {
        // On crée les 98 cartes dans le jeu
        listeCartes = new Stack<Integer>();
        for (int i = 0; i < nbCartetotal; i++)
            listeCartes.push(new Integer(i)); // Utilisez push pour ajouter des cartes à la pile
    }
    public void melangerCartes() {
        // Méthode permettant de mélanger les cartes dans le jeu
        //https://www.geeksforgeeks.org/collections-shuffle-method-in-java-with-examples/
        Collections.shuffle(listeCartes);
    }
    public int tirerCarte() {
        // Méthode permettant de tirer une carte (la dernière sur la pile de cartes) après que cette pile soit mélangée
        if (!listeCartes.isEmpty()) {
            return listeCartes.pop(); // Utilisez pop pour retirer et retourner la carte du dessus de la pile
        } else {
            // Gérez le cas où la pile est vide
            return -1;
        }
    }

    public int retirerCarte()
    {
        return listeCartes.pop();
    }
    public int tailleListeCartes() {
        return listeCartes.size();
    }

    public int getNbCartetotal() {
        return nbCartetotal;
    }

    public void setNbCartetotal(int nbCartetotal) {
        this.nbCartetotal = nbCartetotal;
    }
}

//Ceci est une note personnelle afin de comaprer avec stack
//public class PileCarte {
//    private Vector<Carte> listeCartes; // Vecteur pour stocker les cartes de la pile
//
//    public PileCarte() {
//        // On crée les 98 cartes dans le jeu
//        listeCartes = new Vector<Carte>();
//        for (int i = 1; i < 97; i++)
//            listeCartes.add(new Carte(i));
//    }
//
//    public void melangerCartes() {
//        // Méthode permettant de mélanger les cartes dans le jeu
//        Vector listeCartesMelangees = listeCartes;
//        Collections.shuffle(listeCartesMelangees);
//        this.listeCartes = listeCartesMelangees;
//    }
//
//    public int tirerCarte() {
//        // Méthode permettant de tirer une carte (la dernière sur la pile de cartes) après que cette pile soit mélangée
//        // J'ai utilisé listeCartes.size()-1 comme alternative à pop, car j'utilise un vecteur et non un Stack
//        return listeCartes.remove(listeCartes.size()-1).getNumeroDeCarte();
//    }
//
//    public int tailleListeCartes() {
//        return listeCartes.size();
//    }
//
//    public Vector<Carte> getListeCartes() {
//        return listeCartes;
//    }
//
//    public void setListeCartes(Vector<Carte> listeCartes) {
//        this.listeCartes = listeCartes;
//    }
//}
