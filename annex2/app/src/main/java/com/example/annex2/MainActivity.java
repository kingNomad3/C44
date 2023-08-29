package com.example.annex2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Button boutonValider;
    Button envoyezButton;

    EditText champEmail;
    Spinner spinnerCompte;
    EditText champTransfert;
    TextView textSolde;
    Ecouteur ec;

    Vector<String> listeNomsCompte;
    double solde;

    compte compteChosie;
    Hashtable<String, compte> lesComptes;

    compte compteEp, compteCh, compteComte;
    AlertDialog.Builder builder;


    DecimalFormat df = new DecimalFormat("0.00$");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         builder = new AlertDialog.Builder(this);

        //initialiser les composantes

        spinnerCompte = findViewById(R.id.spinnerCompte);
        textSolde = findViewById(R.id.texteSolde);

        envoyezButton = findViewById(R.id.envoyezButton);
        champTransfert = findViewById(R.id.champTransfert);
        champEmail = findViewById(R.id.champEmail);

        lesComptes = new Hashtable<>();
        lesComptes.put("CHEQUE", new compte("CHEQUE",400));
        lesComptes.put("EPARGNE", new compte("EPARGNE",200));
        lesComptes.put("EPARGNE PLUS", new compte("EPARGNE PLUS",400));


        listeNomsCompte = new Vector<String>();
        listeNomsCompte.add("CHEQUE");
        listeNomsCompte.add("EPARGNE");
        listeNomsCompte.add("EPARGNE PLUS");

        solde = 500;


        Set<String> ensembleCles = lesComptes.keySet();
        listeNomsCompte.addAll(ensembleCles);


        //initialiser le spinnersimple_simple_list_item_1
        ArrayAdapter adapteur = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,listeNomsCompte);
        spinnerCompte.setAdapter(adapteur);


        //1er etape
        ec =  new Ecouteur();
        //2e etape

        envoyezButton.setOnClickListener(ec);

        //3
//        envoyezButton.setEnabled(false);
        spinnerCompte.setOnItemSelectedListener(ec);



    }

    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @Override
    public void onClick(View source) {


            String couriel = champEmail.getText().toString();
            String transfert = champTransfert.getText().toString();
            double transfertdouble = Double.parseDouble(transfert);

        if (couriel.trim().length() > 0) {
//            courriel.matches("[.\\w]@[.\\w]+"))
            if (couriel.contains("@")) {
                if (transfertdouble <= solde) {
                    solde = solde - transfertdouble;
                    textSolde.setText(String.valueOf(solde));
                    // Transfer logic here...
                } else {
                    champTransfert.setText(null);
                    champTransfert.setHint("Pas assez de fonds");
                }
            } else {
                champEmail.setText(null);
                builder.setMessage("doit contenir @")
                        .setTitle("erreur");

                AlertDialog dialog = builder.create();
                dialog.show();
                champTransfert.setText("0");
            }
        } else {
            champEmail.setHint("Entrez un courriel");
        }



    }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            String nomCompte = (String) spinnerCompte.getItemAtPosition(position);
//            autre facon
            TextView temp = (TextView) view;
            String nomCompte = temp.getText().toString();


            compteChosie = lesComptes.get(nomCompte);
//            afficher le solde du compte choisie
            solde = compteChosie.getSolde();
            textSolde.setText(df.format(solde));
    }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}