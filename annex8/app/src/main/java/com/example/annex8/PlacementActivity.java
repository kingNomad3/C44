package com.example.annex8;




import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PlacementActivity extends AppCompatActivity {

    private EditText champMontant;
    private NumberPicker numberPicker;
    private TextView labelReponse;
    private Button bouton;


    Ecouteur ec  = new Ecouteur();



    public DecimalFormat d = new DecimalFormat("0.00$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        champMontant =  findViewById(R.id.champMontant);
        numberPicker = findViewById(R.id.numberPicker);
        labelReponse =  findViewById(R.id.labelReponse);
        bouton = findViewById(R.id.bouton);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int temp = value * 12;
                return "" + temp;
            }
        };


        numberPicker.setFormatter(formatter);
        
        // 3 étapes

        bouton.setOnClickListener(ec);


    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // appeler la methode calculer montant finale de la class palcement

            try {
                // cree un objet palcement
                Placement p = new Placement(Double.parseDouble(champMontant.getText().toString()), numberPicker.getValue() * 12);
                //appelr la methode
                labelReponse.setText(d.format(p.calculerMontantFinal()));
            }catch (NumberFormatException nfe){
                nfe.printStackTrace(); // pour le programmeur, affiche la pile d'appel dans la console
                creerAlertDialog("recommencer avec des chiffres");
                champMontant.setText(null); // efface le input blabla
                champMontant.setHint("ecrire ici un montant");
                champMontant.requestFocus();// va replacer le cuseur dans le champ txt

            }
            //ne peut pas entrer dans deux catch, alors si c'est une autre exception il va rentrer dans le catch ,
//            on va du plus specifique au plus general car si on met exception en premier il va toujours catch l'erreur
            catch (Exception e){
                e.printStackTrace(); // pour le programmeur, affiche la pile d'appel dans la console
                creerAlertDialog("recommencer avec des chiffres");
                champMontant.setText(null); // efface le input blabla
                champMontant.setHint("ecrire ici un montant");
                champMontant.requestFocus();// va replacer le cuseur dans le champ txt

            }
            //on peut aussi faire nos propres class d'exception, par exemple dans ce context -600 ne ferait pas de sense



        }
    }


    //pour créer une boite de dialogue simple
    public void creerAlertDialog(String message) {


        AlertDialog.Builder builder = new AlertDialog.Builder(PlacementActivity.this);

        //on peut faire ca !!
        builder.setMessage(message)
                .setTitle("Erreur");


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}








