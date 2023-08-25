package com.example.exercices3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    ImageView bidon;
    ImageView verre;
    ImageView bouteille;

    TextView texteqte;

    int qte;



    ProgressBar progressBar;

    Ecouteur ec;

    int objectifUpdate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bouteille = findViewById(R.id.boutielle);
        bidon = findViewById(R.id.bidon);
        verre = findViewById(R.id.verre);
        progressBar = findViewById(R.id.progressBar);
        texteqte = findViewById(R.id.texteqte);

        ec = new Ecouteur();
        bidon.setOnClickListener(ec);
        verre.setOnClickListener(ec);
        bouteille.setOnClickListener(ec);

        objectifUpdate = 200;


        qte = 0;


    }



    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View source) {

            if (source == bouteille) {

                qte += 350;

            } else if ( source == verre ) {

                qte+=150;
            } else if (source == bidon) {
                qte+=1500;
            }
            
            texteqte.setText(qte + "ml");
            progressBar.setProgress(qte);

            if (qte >= 2000){
                Toast toast=Toast.makeText(MainActivity.this,"objectif reussi",Toast.LENGTH_SHORT);
                toast.show();


                Snackbar mySnackbar = Snackbar.make(source, "Objectif Reussie", BaseTransientBottomBar.LENGTH_LONG);
            }




        }
    }
}