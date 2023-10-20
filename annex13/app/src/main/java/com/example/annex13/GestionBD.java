package com.example.annex13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

public class GestionBD extends SQLiteOpenHelper {
    //instance unique de la classe Singleton GestionBD
    private static GestionBD instance;
    private SQLiteDatabase database;

    //pour limiter les risques que quelqu'un creer un autre objet de singleton
    //s'il y a en a deux le singleton n'a plus sa raison d'etre
    private GestionBD(Context context) {
        super(context, "biere", null, 1);

    }


    public static GestionBD getInstance(Context contexte) {
        if (instance == null)
            //si lìnstance est null alors je creer mon instence unique
            instance = new GestionBD(contexte);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE biere(_id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT,microbrasserie TEXT,nbEtoiles REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void ajouterBiere(Evaluation i){
        ContentValues cv = new ContentValues();
        cv.put("nom", i.getNom());
        cv.put("nbEtoiles",i.getEvaluation());
        cv.put("microbrasserie", i.getMicrobrasserie());

        database.insert("biere", null, cv);
    }
    public void ouvrireConnectionBd(){
        //tjrs mieux de faire cwritetable meme si on ne veut pas ecrire
        database = this.getWritableDatabase();

    }
    public void  fermerConnection(){
        database.close();
    }

    public Vector<String> retournerMeilleur(){
        Vector<String> v = new Vector<>();
        Cursor resultat = database.rawQuery("SELECT nom,microbrasserie, nbEtoiles  FROM biere ORDER BY nbEtoiles DESC LIMIT 3 ",null);
//        resultat = query("evaluation", new String[]{"nom"}, null,null,null,null,"evaluation DESC",3);

        //move to next va retourner faut si il y a rien apres, ne va jamais retourner null
        while (resultat.moveToNext()){
            //c'est a 0 car l,ensemble de resultat est slm une colonne sir on avait fait SELEct * il aurai fallu metre plus de paramettre selon la table
            v.add(resultat.getString(0));
        }


        return v;
    }


}
