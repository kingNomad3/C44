package com.example.tpfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;
    private SQLiteDatabase database;

    public static DatabaseHelper getInstance(Context contexte) {
        if (instance == null){
            instance = new DatabaseHelper(contexte.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context contexte) {
        super(contexte, "97Cartes", null, 1);
        ouvrirConnection();
    }

    // Méthode pour ajouter un score à la base de données
    public void ajouterScore(Score s, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("score", s.getScore());
        db.insert ("scores", null, cv);
    }

    // Méthode pour obtenir le score le plus élevé dans la base de données
    public int classementScores(){
        int Resultat = 0;
        Cursor c = database.rawQuery("SELECT MAX(score) FROM scores",null); // on cherche celui qui le plus grand score
        c.moveToFirst();
        Resultat = c.getInt(0);
        c.close();
        return Resultat;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création de la table "scores" lors de la première création de la base de données
        db.execSQL("create table scores ( _id INTEGER PRIMARY KEY AUTOINCREMENT, score INTEGER UNIQUE NOT NULL);");

        // Ajout d'un score initial (0) à la table
        ajouterScore(new Score(0), db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Met à jour la base de données en recréant la table scores
        onCreate(db);
    }

    // Méthode pour ouvrir la base de données
    public void ouvrirConnection(){
        database = this.getWritableDatabase();
    }

    // Méthode pour fermer la base de données
    public void fermerConnection(){
        database.close();
    }
}
