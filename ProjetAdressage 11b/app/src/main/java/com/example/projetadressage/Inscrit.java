package com.example.projetadressage;


import java.util.Hashtable;

import bla.HashtableAssociation;


public class Inscrit {
    private String nom;
    private String prenom;
    private String adresse;
    private String capitale;
    private String etat;
    private String codeZip;

    public Inscrit(String nom, String prenom, String adresse, String capitale, String etat, String codeZip) throws  AdresseException {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codeZip = codeZip;



        // vérifier si la capitale fait partie de l'état à l'aide d'une Hashtable secrète ( classe HashtableAssociation )

        HashtableAssociation asso = new HashtableAssociation();

        //aller chercher l'etat conrrespondant a la capital  dans la hash table
        //aller chercehr dans la h table abec get
        String bonEtat = asso.get(capitale);
        //on va comparer le bon etat avwec l'etat passer avec paramettre c;est des string alors c;est avec equal

        //on va utiliser ! pour lancer l'exception on fait expres
        if (!bonEtat.equals(etat) ){
            throw new AdresseException(capitale,etat);


        }

        //on peut mettre tout les this ici car on n'a pas d'objet creer, on initialise les varaible
//        this.nom = nom;
//        this.prenom = prenom;
//        this.adresse = adresse;
//        this.codeZip = codeZip;
        //doit etre absolument etre en bas car s'il y a une exception ont les initialises pour rien
        this.capitale = capitale;
        this.etat = etat;;
    }
}
