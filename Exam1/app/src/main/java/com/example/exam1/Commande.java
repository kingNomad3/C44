package com.example.exam1;

import java.util.Vector;

public class Commande {

    private Vector<Produit> listeCommande;

    public Commande() {
        listeCommande = new Vector();
    }

    public void ajouterProduit(Produit p) {
        listeCommande.add(p);
    }

    public double total() {
        double total = 0;
        // compléter : total de la commande







        return total;
    }

    public double taxes() {
        double taxes = 0;

        double taxeTvq = total() * (5/100);
        double taceTps = total() * (9.975/100);

        taxes = taxeTvq + taceTps;
        // compléter : montant des taxes sur le total de la commande

        // tps sur le montant avant taxes ( 5% )

        //tvq sur le montant avant taxes ( 9.975% )

        // taxes total = tps + tvq


        return taxes;
    }

    public double grandTotal() {

        double grTotal = 0;

        grTotal = taxes() + total();

        // compléter


        return grTotal;


    }
}

