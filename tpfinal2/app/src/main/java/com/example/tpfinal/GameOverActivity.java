package com.example.tpfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    Button rejouer; // Bouton pour rejouer
    TextView scoreFinal; // TextView pour afficher le score final
    Ecouteur ec; // Écouteur pour gérer les clics sur le bouton
    DatabaseHelper gest; // Base de données SQLite pour gérer les scores
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Initialisation des éléments graphiques
        rejouer = findViewById(R.id.buttonRejouer);
        scoreFinal = findViewById(R.id.textViewScoreFinal);
        ec = new Ecouteur();
        gest = DatabaseHelper.getInstance(this);

        // Récupération du score passé depuis l'activité précédente stocker dans la bd
        String scoreEnString = getIntent().getExtras().getString("SCORE");
        Score score = new Score(Integer.parseInt(scoreEnString));

        // Ajout du score à la base de données
        gest.ajouterScore(score, gest.getWritableDatabase());

        // Affichage du score final
        scoreFinal.setText("Votre score : " + scoreEnString);

        // Ecouteur du clic sur le bouton "Rejouer"
        rejouer.setOnClickListener(ec);
    }

    protected void onStop(){
        super.onStop();
        gest.fermerConnection();
    }
    // Classe interne pour gérer les clics sur le bouton "Rejouer"
    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonRejouer: {
                    // Redirection vers l'activité de jeu (Mainactivity) pour rejouer
                    Intent i = new Intent(GameOverActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        }
    }
}
