package com.example.projetadressage;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Vector;

import bla.HashtableAssociation;


public class MainActivity extends AppCompatActivity {

    EditText champPrenom, champNom, champAdresse, champZip;
    Spinner spinnerCapitale, spinnerEtat;

    Button bouton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champPrenom = findViewById(R.id.champPrenom);
        champNom= findViewById(R.id.champNom);
        champAdresse = findViewById(R.id.champAdresse);
        champZip = findViewById(R.id.champZip);

        spinnerCapitale = findViewById(R.id.spinnerCapitale);
        spinnerEtat = findViewById(R.id.spinnerEtat);

        bouton = findViewById(R.id.boutonInscrire);

        // remplir les spinner à l'aide de la Hashtable

        HashtableAssociation h = new HashtableAssociation();
        Set<String> s = h.keySet();
        Vector<String> v = new Vector<>()


    }
    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {


        }
    }
}