package com.example.tpfinal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAlert extends Dialog {
    Button RetourJeu, QuitterJeu;
    private GameActivity mActivity;

    public MenuAlert(GameActivity activity) {
        super(activity);
        mActivity = activity;
        setContentView(R.layout.menu_dialogue);
        RetourJeu = findViewById(R.id.RetourJeu);
        QuitterJeu = findViewById(R.id.quitterJeu);

        Ecouteur ec = new Ecouteur();

        RetourJeu.setOnClickListener(ec);
        QuitterJeu.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button temp = (Button) v;
            String textButton = temp.getText().toString();

            if (textButton.equals("Continuer la partie")) {
                //recommence le timer si le joueur retourne dans le jeu
                mActivity.startChrono();
                dismiss();
            } else if (v == QuitterJeu) {
                Intent i = new Intent(mActivity, MainActivity.class);
                mActivity.startActivity(i);

            }
        }
    }

}


