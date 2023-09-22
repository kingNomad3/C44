package com.example.annex8;

public class NegativeMontantException extends Exception {

    private double valeurProb;

    public NegativeMontantException(double valeurProb){

        super("Le montant doit etre plus grand que 0");
        this.valeurProb = valeurProb;


    }

    public double getValeurProb() {
        return valeurProb;
    }
}
