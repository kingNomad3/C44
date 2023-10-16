package com.example.annex13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Evaluation;
    Button MeilleurBiere;

    Ecouteur ec = new Ecouteur();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Evaluation = findViewById(R.id.Evaluation);
        MeilleurBiere = findViewById(R.id.meilleurBiere);

        Evaluation.setOnClickListener(ec);
        MeilleurBiere.setOnClickListener(ec);

    }



    private class Ecouteur implements AdapterView.OnClickListener{


        @Override
        public void onClick(View v) {
            if (v == Evaluation){
                setContentView(R.layout.activity_ajouter);
            }else if (v == MeilleurBiere){
                setContentView(R.layout.activity_meilleur);
            }
        }
    }
}