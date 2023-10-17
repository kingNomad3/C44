package com.example.tpfinal;

// Définition d'une classe appelée Carte
public class Carte {

    // Variable membre privée pour stocker le "chiffre" (numéro) de la carte
    private int chiffre;

    // Constructeur pour initialiser la carte avec un "chiffre" (numéro)
    public Carte(int chiffre) {
        this.chiffre = chiffre;
    }

    // Méthode getter pour récupérer le "chiffre" (numéro) de la carte
    public int getChiffre() {
        return chiffre;
    }

    // Méthode setter pour définir le "chiffre" (numéro) de la carte
    public void setChiffre(int chiffre) {
        this.chiffre = chiffre;
    }
}