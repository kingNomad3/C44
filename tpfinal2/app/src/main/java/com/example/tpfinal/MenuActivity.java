package com.example.tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button Retour;
    Button Quitter;

   Ecouteur ec = new Ecouteur();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Retour = findViewById(R.id.RetourJeu);
        Quitter = findViewById(R.id.quitterJeu);


        Retour.setOnClickListener(ec);
        Quitter.setOnClickListener(ec);


    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // Si le joueur clique sur "Commencer Partie", il sera redirigé vers l'activité de jeu (GameActivity)
            switch (v.getId()) {
                case R.id.RetourJeu: {
                    Intent i = new Intent(MenuActivity.this, GameActivity.class);
                    startActivity(i);
                } case R.id.quitterJeu:{
                    Intent i = new Intent(MenuActivity.this,MainActivity.class);

                }
            }
        }
    }
}