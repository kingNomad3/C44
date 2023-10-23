package com.example.annex13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;



public class AjouterActivity extends AppCompatActivity {

    RatingBar ratingBar;
    double ratingEnDouble;
    EditText nombiere;
    EditText nomMicrobrasserie;
    Button ajouter;
    String nombiereTemps;
    String nomMicrobrasserieTemps;
    GestionBD bd;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        ajouter = findViewById(R.id.enregistrer);
        ratingBar = findViewById(R.id.ratingBar);

        nombiere = findViewById(R.id.nombiere);
        nomMicrobrasserie = findViewById(R.id.microbrasserie);


        ec = new Ecouteur();

        ajouter.setOnClickListener(ec);
        ratingBar.setRating(5);


    }

    @Override
    protected void onStart() {
        super.onStart();
        bd = GestionBD.getInstance(getApplicationContext());
        bd.ouvrireConnectionBd();

    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            ratingEnDouble = ratingBar.getRating();
            System.out.println(ratingEnDouble);
            nombiereTemps = nombiere.getText().toString();
            nomMicrobrasserieTemps = nomMicrobrasserie.getText().toString();

            if (nombiereTemps.isEmpty() || nomMicrobrasserieTemps.isEmpty()) {
                Toast.makeText(AjouterActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    Evaluation eval = new Evaluation(nombiereTemps, nomMicrobrasserieTemps, ratingEnDouble);
                    bd.ajouterBiere(eval);
                } catch (Exception e) {
                    Toast.makeText(AjouterActivity.this, "Error adding evaluation!", Toast.LENGTH_SHORT).show();
                }
                bd.fermerConnection();
                finish();
            }
        }
    }
}