package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    ImageView avionView;
    ImageView hotelView;
    Button totalBouton;
    TextView textAvion;
    TextView textHotel;
    TextView textTotal;

    DecimalFormat df = new DecimalFormat("0.00$");

    Ecouteur ec = new Ecouteur();





    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avionView = findViewById(R.id.avionView);
        hotelView = findViewById(R.id.hotelView);
        totalBouton = findViewById(R.id.totalBouton);
        textAvion = findViewById(R.id.textAvion);
        textHotel = findViewById(R.id.textHotel);
        textTotal = findViewById(R.id.textTotal);

        avionView.setOnClickListener(ec);
        hotelView.setOnClickListener(ec);
        totalBouton.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {

        int compteurH, compteurC;
        Commande c = new Commande();

        @Override
        public void onClick(View v) {


            if ( v == avionView){

                compteurH++;
                textAvion.setText(String.valueOf(compteurH));


            } else if (v == hotelView) {
                compteurC++;
                textHotel.setText(String.valueOf(compteurH));

            }
            if (v == totalBouton){
                c.ajouterProduit( new BilletAvion(compteurH));
                c.ajouterProduit(new HebergementHotel(compteurC));
                double grTotal = c.grandTotal();
                textTotal.setText(new DecimalFormat("0.00$").format(grTotal));

            }

        }


    }

}