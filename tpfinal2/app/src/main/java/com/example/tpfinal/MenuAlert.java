package com.example.tpfinal;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuAlert extends AppCompatActivity {

    Button menu;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = findViewById(R.id.buttonMenu);

        Ecouteur ec = new Ecouteur();

        menu.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener {

        // Gestion du clic sur un élément de l'interface
        @Override
        public void onClick(View v) {

          if (v == menu){
//              showMenuAlert();
          }

        }



//        private void showMenuAlert() {
////            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setTitle("Menu");
//
//
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            builder.setNegativeButton("Annuler", null);
//            builder.show();
//        }
    }
}
