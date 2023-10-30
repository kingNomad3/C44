package com.example.tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tpfinal.GameActivity;
import com.example.tpfinal.R;

public class MainActivity extends AppCompatActivity {

    // Les variables
    TextView highestScore;
    Button commencerPartie;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        commencerPartie = findViewById(R.id.playButton);
        highestScore = findViewById(R.id.highestScore);
        ec = new Ecouteur();

        // Récupération du plus grand score enregistré
        highestScore.setText("Le plus grand score enregistré : " + String.valueOf(DatabaseHelper.getInstance(this).classementScores()));

        commencerPartie.setOnClickListener(ec);
    }

    // Classe interne pour gérer les clics sur le bouton "Commencer Partie"
    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // Si le joeur sera redirigé vers l'activité de jeu (GameActivity)
            if (v == commencerPartie) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
            }
        }
    }
}
