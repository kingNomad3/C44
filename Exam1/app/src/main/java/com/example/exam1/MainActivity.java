package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button avionView;
    Button hotelView;
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

        avionView = findViewById(R.id.avionView);
        hotelView = findViewById(R.id.hotelView);
        totalBouton = findViewById(R.id.totalBouton);
        textAvion = findViewById(R.id.textAvion);
        textHotel = findViewById(R.id.textHotel);
        textTotal = findViewById(R.id.textTotal);

        avionView.setOnClickListener(ec);
        hotelView.setOnClickListener(ec);
        totalBouton.setOnClickListener(ec);


        setContentView(R.layout.activity_main);
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String totalAvion = textAvion.getText().toString();
            String totalhotel = textHotel.getText().toString();
            String grandTotal = textTotal.getText().toString();

            if ( v == avionView){
                textAvion.setText(String.valueOf(totalAvion));

            } else if (v == hotelView) {
                textHotel.setText(String.valueOf(totalhotel));

            }
            if (v == totalBouton){
//                grandTotal.setText(df.format(String.valueOf(grandTotal)));
            }

        }


    }

}