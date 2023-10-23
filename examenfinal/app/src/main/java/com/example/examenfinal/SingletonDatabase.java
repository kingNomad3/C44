package com.example.examenfinal;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SingletonDatabase extends SQLiteOpenHelper {

    private static SingletonDatabase instance;
    public SQLiteDatabase database;


    public static SingletonDatabase getInstance(Context context){
        if (instance == null){
            instance = new SingletonDatabase(context);
        }
        return instance;
    }

    public SingletonDatabase(Context context) {
        super(context, "app", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

       sqLiteDatabase.execSQL("create table drapeau ( _id INTEGER PRIMARY KEY AUTOINCREMENT, couleurG TEXT, couleurC TEXT, couleurD TEXT, pays TEXT);");
       insererDrapeau(sqLiteDatabase, new Drapeau("bleu", "blanc", "rouge", "France"));
        insererDrapeau(sqLiteDatabase, new Drapeau("rouge", "blanc", "rouge", "Pérou"));
        insererDrapeau(sqLiteDatabase, new Drapeau("bleu", "jaune", "rouge", "Roumanie"));
        insererDrapeau(sqLiteDatabase, new Drapeau("noir", "jaune", "rouge", "Belgique"));
    }

    public void insererDrapeau (SQLiteDatabase sqLiteDatabase, Drapeau d )
    {
        ContentValues cv = new ContentValues();
        cv.put("couleurG", d.getCouleurG());
        cv.put("couleurC", d.getCouleurC());
        cv.put("couleurD", d.getCouleurD());
        cv.put("pays", d.getPays());
        sqLiteDatabase.insert("drapeau", null, cv );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
	 sqLiteDatabase.execSQL("drop table drapeau");
        onCreate(sqLiteDatabase);
    }

    public void ouvrireConnectionBd(){
        database = this.getWritableDatabase();
    }

    public void  fermerConnection(){
        database.close();
    }

    public String retournePays(String pays){
        String[] args = {pays};
        Cursor resultat = database.rawQuery("SELECT drapeau FROM drapeau WHERE pay=? ",args);

        while (resultat.moveToNext()){
            String bonPays = resultat.getString(0);
            resultat.close();
            return bonPays;

        }
        return null;
    }

    public boolean bonPays(String couleurG, String couleurC, String couleurD,String pays){

        String[] args = {couleurG,couleurC,couleurD,pays};
        Cursor c = database.rawQuery("SELECT * FROM drapeau WHERE couleurG=? AND couleurC=? AND couleurD=? AND pays=? ", args);


        return c.moveToFirst();
    }











}
