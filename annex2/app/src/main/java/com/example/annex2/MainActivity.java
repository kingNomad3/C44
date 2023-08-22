package com.example.annex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Button boutonValider;
    EditText champNomCompte;
    TextView textSolde;
    Ecouteur ec;

    Vector<String> listeNomsCompte;
    double solde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialiser les composantes
        boutonValider = findViewById(R.id.validerButtom);
        champNomCompte = findViewById(R.id.DeInput);
        textSolde = findViewById(R.id.texteSolde);

        listeNomsCompte = new Vector<String>();
        listeNomsCompte.add("CHEQUE");
        listeNomsCompte.add("EPARGNE");
        listeNomsCompte.add("EPARGNE PLUS");

        solde = 500;

        //1er etape
        ec =  new Ecouteur();
        //2e etape
        boutonValider.setOnClickListener(ec);


    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source){
           String nomCompte = champNomCompte.getText().toString();
           if (listeNomsCompte.contains(nomCompte)){
               textSolde.setText(String.valueOf(solde));
           }else {
               textSolde.setText("Pas un nom valide");
           }
        }
    }


}