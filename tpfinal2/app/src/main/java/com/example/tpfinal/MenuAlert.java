package com.example.tpfinal;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuAlert extends Dialog {

    Button RetourJeu, QuitterJeu;

    public MenuAlert(@NonNull Context context) {
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
                System.out.println("test");
            }


            if (v == QuitterJeu){
//              showMenuAlert();

          } else if (v ==QuitterJeu) {
              dismiss();

          }

        }

        private void showMenuAlert() {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);


        }
    }
}
