package com.example.annex2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Button boutonValider;
    Button envoyezButton;

    EditText champEmail;
    EditText champNomCompte;
    EditText champTransfert;
    EditText champDestinataire;
    TextView textSolde;
    Ecouteur ec;

    Vector<String> listeNomsCompte;
    double solde;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialiser les composantes
        boutonValider = findViewById(R.id.validerButtom);
        champNomCompte = findViewById(R.id.DeInput);
        textSolde = findViewById(R.id.texteSolde);

        envoyezButton = findViewById(R.id.envoyezButton);
        champTransfert = findViewById(R.id.champTransfert);
        champEmail = findViewById(R.id.champEmail);

        listeNomsCompte = new Vector<String>();
        listeNomsCompte.add("CHEQUE");
        listeNomsCompte.add("EPARGNE");
        listeNomsCompte.add("EPARGNE PLUS");

        solde = 500;

        //1er etape
        ec =  new Ecouteur();
        //2e etape
        boutonValider.setOnClickListener(ec);
        envoyezButton.setOnClickListener(ec);

        //3
//        envoyezButton.setEnabled(false);



    }

    private class Ecouteur implements View.OnClickListener {

    @Override
    public void onClick(View source) {


        if (source == boutonValider) {
            String nomCompte = champNomCompte.getText().toString();
            nomCompte = nomCompte.trim(); //enleve les espaces
            nomCompte = nomCompte.toUpperCase();
            if (listeNomsCompte.contains((nomCompte))) {
                textSolde.setText(String.valueOf(solde));

            } else {
                textSolde.setText("pas un nom valide");
            }
        }else {
            String couriel = champEmail.getText().toString();
            String transfert = champTransfert.getText().toString();
            double transfertdouble = Double.parseDouble(transfert);

            if (couriel.trim().length() >0) {
                if(transfertdouble <=solde){
                    solde = solde - transfertdouble;
                    textSolde.setText(String.valueOf(solde));
//                    envoyezButton.setEnabled(true);
                }else {
                    champTransfert.setText(null);
                    champTransfert.setHint("pas assez de fond");
                }

            }
            else {
                champDestinataire.setHint("entrez un courriel valide");
            }

        }








    }



}


}