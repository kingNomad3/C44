package com.example.revisionexamfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Vector;


public class EquipeHockeyBd extends SQLiteOpenHelper {

    private static EquipeHockeyBd instance;
    private SQLiteDatabase database;

    public static EquipeHockeyBd getInstance(Context contexte) {
        if (instance == null)
            //si lìnstance est null alors je creer mon instence unique
            instance = new EquipeHockeyBd(contexte);
        return instance;
    }

    public EquipeHockeyBd(Context context) {
        super(context, "db", null, 1);

    }

    public void ajouter(Equipe i , SQLiteDatabase db){

        ContentValues cv = new ContentValues();
        cv.put("nom",i.getNom());
        cv.put("division",i.getDivision());
        cv.put("arena",i.getArena());
        cv.put("capacite", i.getCapacite());

        db.insert("EquipesLHJMQ",null, cv);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE EquipesLHJMQ(_id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT,division TEXT, arena TEXT, capacite INTEGER);");

        ajouter(new Equipe("Tigres de Victoriavillle","Est", "Colisée Desjardins",1900),db);
        ajouter(new Equipe("Cataractes de Shawinigan","Est", "Centre Gervais Auto",4000),db);
        ajouter(new Equipe("Olympiques de Gatineau","Ouest", "Centre Slush Puppies",4200),db);
        ajouter(new Equipe("Foreurs de Val d’Or","Ouest", "Centre Agnico Eagle",2600),db);
        ajouter(new Equipe("Armada de Blainville","Ouest", "Centre Rousseau",3000),db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS EquipesLHJMQ");
        onCreate(db);
    }

    public void ouvrireConnectionBd(){
        //tjrs mieux de faire cwritetable meme si on ne veut pas ecrire
        database = this.getWritableDatabase();
    }

    public void  fermerConnection(){
        database.close();
    }

    public int getNombreEquipesParDivision(String division) {
        String[] args = {division};
        Cursor resultat = database.rawQuery("SELECT COUNT(*) FROM EquipesLHJMQ WHERE division=?", args);
        resultat.moveToFirst();
        int count = resultat.getInt(0);
        resultat.close();
        return count;
    }

    public int getCapaciteParArena(String arena) {
        int capacity = 0;
        String[] args = {arena};
        Cursor resultat = database.rawQuery("SELECT capacite FROM EquipesLHJMQ WHERE arena=?", args);

        if (resultat.moveToFirst()) {
            capacity = resultat.getInt(0);
        }

        resultat.close();
        return capacity;
    } 
    public Vector<String> retournerNomsArenas() {
        Vector<String> v = new Vector<>();
        Cursor resultat = database.rawQuery("SELECT DISTINCT arena FROM EquipesLHJMQ", null);
        while (resultat.moveToNext()) {
            v.add(resultat.getString(0));
        }
        return v;
    }

    public String getDivisionParArena(String arena) {
        String[] args = {arena};
        Cursor resultat = database.rawQuery("SELECT division FROM EquipesLHJMQ WHERE arena=?", args);
        if (resultat.moveToFirst()) {
            String division = resultat.getString(0);
            resultat.close();
            return division;
        }
        return null;
    }


    public String getNomEquipeParArena(String arena) {
        Cursor resultat = database.rawQuery("SELECT nom FROM EquipesLHJMQ WHERE arena LIKE ?", new String[]{arena});
        if (resultat.moveToFirst()) {
            String nom = resultat.getString(0);
            resultat.close();
            return nom;
        }
        return null;
    }
}
