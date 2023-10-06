package com.eric.appsql;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.time.Instant;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    ListView liste;
    TextView textReponse;
    GestionBD gest ;
    Vector choix ;

    private static GestionBD instance;
    Ecouteur ec = new Ecouteur();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        choix =   gest.retournerInventions()
        gest = GestionBD.getInstance(getApplicationContext());
        instance = GestionBD.getInstance(getApplicationContext());
//        choix = instance.retournerInventions();
        liste = findViewById(R.id.liste);
        textReponse = findViewById(R.id.textReponse);



        //ouvrire la connection avant d'appeler la methode
        gest.ouvrireConnectionBd();

        //remplir le listeView
        ArrayAdapter adapteur = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,gest.retournerInventions() );

        liste.setAdapter(adapteur);

        //il ne faut pas fermer la connection avant le on create
//        gest.fermerConnection();
        liste.setOnItemClickListener(ec);


    }

    @Override
    protected void onStop(){
        super.onStop();
        gest.fermerConnection();
    }

    private class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View itemClique,int position, long id) {

        String reponseDonnee =  gest.retournerInventions().get(position);

        if (gest.aBonneReponse(reponseDonnee, "Mary Andersin")){
            textReponse.setText("bonne Reponse");
            itemClique.setBackgroundColor(Color.GREEN);
        }else{
            textReponse.setText("Mauvaise reponse");
            itemClique.setBackgroundColor(Color.RED);
            liste.getChildAt(2).setBackgroundColor(Color.GREEN);
        }
        liste.setOnItemClickListener(null);

        }


    }








}