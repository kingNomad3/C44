package com.example.tpfinal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MenuAlert extends Dialog {
    Button RetourJeu, QuitterJeu;
    Chronometer chrono;
    public MenuAlert(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_dialogue);
        RetourJeu = findViewById(R.id.RetourJeu);
        QuitterJeu = findViewById(R.id.quitterJeu);

        Ecouteur ec = new Ecouteur();

        RetourJeu.setOnClickListener(ec);
        QuitterJeu.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {
        // Gestion du clic sur un élément de l'interface
        @Override
        public void onClick(View v) {
            Button temp = (Button) v;
            String textButton = temp.getText().toString();


            if(textButton.equals("Continuer la partie")){
                ///faire restart le chrono
                dismiss();

            } else if (v ==QuitterJeu) {
                Intent i = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(i);
           }
        }
    }
}


