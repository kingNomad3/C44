package com.example.projetadressage;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Collection;
import java.util.Set;
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
        Vector<String> v = new Vector<>(s);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,v);
        spinnerCapitale.setAdapter(adapter);

        Collection<String> c = h.values();
        Vector<String> v2 = new Vector<>(c);
        ArrayAdapter adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,v2);
        spinnerEtat.setAdapter(adapter2);


        Ecouteur ec = new Ecouteur();
        bouton.setOnClickListener(ec);


    }
    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            try {
               Inscrit i = new Inscrit(champPrenom.getText().toString(),
                                       champNom.getText().toString(),
                                       champAdresse.getText().toString(),
                                       (String) spinnerCapitale.getSelectedItem(),
                                       (String) spinnerEtat.getSelectedItem(),
                                        champZip.getText().toString());


               builder.setMessage("vous etes ecrit")
                       .setTitle("bravo");

            }catch (AdresseException ae){
                builder.setMessage(ae.getMessage())
                        .setTitle("problem");
            }
            AlertDialog dialog = builder.create();
            dialog.show();


        }
    }
}