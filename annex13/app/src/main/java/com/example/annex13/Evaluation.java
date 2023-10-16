package com.example.annex13;

import androidx.appcompat.app.AppCompatActivity;

public class Evaluation {

    private String nom;
    private String microbrasserie;
    private byte evaluation;


    public Evaluation(String nom, String microbrasserie, byte evaluation) throws Exception {
        if (this.nom.trim().length()<= 0)
            throw new Exception("ne peut pas etre vide");
        this.nom = nom;
        this.microbrasserie = microbrasserie;
        this.evaluation = evaluation;
    }

    public String getNom() {
        return nom;
    }

    public String getMicrobrasserie() {
        return microbrasserie;
    }

    public byte getEvaluation() {
        return evaluation;
    }


}