package com.example.revisionexamfinal;

public class Equipe {

    private String nom;
    private String division;
    private String arena;
    private int capacite;

    public Equipe(String nom, String division, String arena, int capacite) {
        this.nom = nom;
        this.division = division;
        this.arena = arena;
        this.capacite = capacite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
