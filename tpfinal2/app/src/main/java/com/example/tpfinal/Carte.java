package com.example.tpfinal;

// Définition d'une classe appelée Carte
public class Carte {

    // Variable membre privée pour stocker le numéro de la carte
    private int numeroDeCarte;

    // Constructeur pour initialiser la carte avec un numéro
    public Carte(int numeroDeCarte) {
        this.numeroDeCarte = numeroDeCarte;
    }

    // Méthode getter pour récupérer le numéro de la carte
    public int getNumeroDeCarte() {
        return numeroDeCarte;
    }

    public void setNumeroDeCarte(int numeroDeCarte) {
        this.numeroDeCarte = numeroDeCarte;
    }
}