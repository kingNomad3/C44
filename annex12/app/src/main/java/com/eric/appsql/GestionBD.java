package com.eric.appsql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

// est un singleton
public class GestionBD extends SQLiteOpenHelper {

    //instance unique de la classe Singleton GestionBD
    private static GestionBD instance;
    private SQLiteDatabase database;


    // méthode de base pour un Singleton
    public static GestionBD getInstance(Context contexte) {
        if (instance == null)
            //si lìnstance est null alors je creer mon instence unique
            instance = new GestionBD(contexte);
        return instance;
    }

    //pour limiter les risques que quelqu'un creer un autre objet de singleton
    //s'il y a en a deux le singleton n'a plus sa raison d'etre
    private GestionBD(Context context) {
        super(context, "db", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
     // pour creer les tables et si necessaire ajouter les enregistrements/tuples initiaux
     //est appeler automatiquement une seule fois. soit lors de l'estalation de l'app sur le téléphone
    //si je fais une erreur de frappe dans le nom de la bd je dois delete le app dans l'emulateur et corriger la faute
        // si je  corrige la faute directement sa ne va pas marcher. Ou changer la version
        db.execSQL("CREATE TABLE inventeur(_id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT,origine TEXT,invention TEXT,annee INTEGER);");


        ajouter(new Inventeur("Laszlo Biro", "Hongrie","Stylo à bille", 1938),db);
        ajouter(new Inventeur("Benjamin Franklin", "Etats-Unis","Paratonnerre", 1752),db);
        ajouter(new Inventeur("Mary Anderson", "Etats-Unis","Essuie-glace", 1903),db);
        ajouter(new Inventeur("Grace Hopper", "Etats-Unis","Compilateur", 1952),db);
        ajouter(new Inventeur("Benoit Rouquayrot", "France","Scaphandre", 1864),db);

    }

    public void ajouter(Inventeur i , SQLiteDatabase db){

        ContentValues cv = new ContentValues();
        cv.put("nom",i.getNom());
        cv.put("origine",i.getOrigine());
        cv.put("invention",i.getInvention());
        cv.put("annee", i.getAnnee());

        db.insert("inventeur",null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS inventeur");
        onCreate(db);
    }

    public void ouvrireConnectionBd(){
        //tjrs mieux de faire cwritetable meme si on ne veut pas ecrire
        database = this.getWritableDatabase();

    }

    public void  fermerConnection(){
        database.close();
    }

    public Vector<String> retournerInventions(){
        Vector<String> v = new Vector<>();
        Cursor resultat = database.rawQuery("SELECT invention FROM inventeur",null);

        while (resultat.moveToNext()){
            //c'est a 0 car l,ensemble de resultat est slm une colonne sir on avait fait SELEct * il aurai fallu metre plus de paramettre selon la table
            v.add(resultat.getString(0));
        }


        return v;
    }

    public boolean aBonneReponse(String nomInventeur, String invention){

        //toujours faire un tableau meme s'il y a un seul arguement
        //l'ordre est important
        String[] args = {nomInventeur,invention};

        //SELECT * FROM inventeur WHERE nom = nomInventeur AND invention = invention",
        //? pour des variable
        Cursor c = database.rawQuery("SELECT * FROM inventeur WHERE nom = ? AND invention = ?",args);

        //si retourne vrai on a trouver un tuple ou le nom et l'invention sont dans la la liste
        return c.moveToFirst();
    }

}
