package com.example.examenfinal;

public class Drapeau {
    private String couleurG;
    private String couleurC;
    private String couleurD;
    private String pays;

    public Drapeau(String couleurG, String couleurM, String couleurD, String pays) {
        this.couleurG = couleurG;
        this.couleurC = couleurM;
        this.couleurD = couleurD;
        this.pays = pays;
    }

    public String getCouleurG() {
        return couleurG;
    }

    public String getCouleurC() {
        return couleurC;
    }

    public String getCouleurD() {
        return couleurD;
    }

    public String getPays() {
        return pays;
    }
}
