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

    public void ajouterScore(Score s, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("score", s.getScore());
        db.insert ("scores", null, cv);
    }
    // Méthode pour obtenir le score le plus élevé dans la base de données
    public int classementScores(){
        ensureDatabaseIsOpen();
        int Resultat = 0;
        Cursor c = database.rawQuery("SELECT MAX(score) FROM scores",null); // on cherche celui qui le plus grand score
        c.moveToFirst();
        Resultat = c.getInt(0);
        c.close();
        return Resultat;
    }
    //verifie si la database est vraiment ouverte
    private void ensureDatabaseIsOpen() {
        if (database == null || !database.isOpen()) {
            database = this.getWritableDatabase();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création de la table "scores" lors de la première création de la base de données
        db.execSQL("create table scores ( _id INTEGER PRIMARY KEY AUTOINCREMENT, score INTEGER UNIQUE NOT NULL);");
        ajouterScore(new Score(0), db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void ouvrirConnection(){
        database = this.getWritableDatabase();
    }
    public void fermerConnection(){
        database.close();
    }
}
