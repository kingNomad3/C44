package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    // CustomData classe
    private class EquipeBasket {
         ImageView imageResourceId;
//         set image ressource
        //mieux de separer dan sune autre classe
         String arenaInput;
         String coachName;

        public CustomData(ImageView  imageResourceId, String arenaName, String coachName) {
            this.imageResourceId =  imageResourceId;
            this.arenaInput = arenaName;
            this.coachName = coachName;
        }
    }

    Button atlantaButton;
    Button chicagoButton;
    Button washingtonButton;
    TextView arenaView;
    TextView nomInput;
    Ecouteur ec;
    ImageView atlantaImg;
    ImageView washingtonImg;
    ImageView chicagoImg;

    List<EquipeBasket> dataList;
    int currentIndex = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atlantaButton = findViewById(R.id.atlantaButton);
        chicagoButton = findViewById(R.id.chicagoButton);
        washingtonButton = findViewById(R.id.washingtonButton);
        arenaView = findViewById(R.id.arenaView);
        nomInput = findViewById(R.id.nomEntrainer);
        atlantaImg = (ImageView) findViewById(R.id.AtlantaImg);
        chicagoImg = (ImageView) findViewById(R.id.ChicagoImg);
        washingtonImg = (ImageView) findViewById(R.id.washingtonImg);



        dataList = new ArrayList<>();
//le drawable doit etre l<adresse de l<image tel quel pas le id

        dataList.add(new EquipeBasket(R.drawable.atlanta, "Atlanta Arena", "John Doe"));
        dataList.add(new EquipeBasket(chicagoImg, "Chicago Arena", "Jane Smith"));
        dataList.add(new EquipeBasket(washingtonImg, "Washington Arena", "Michael Johnson"));

        ec = new Ecouteur();
        atlantaButton.setOnClickListener(ec);
        chicagoButton.setOnClickListener(ec);
        washingtonButton.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View source) {
            int clickedIndex = -1;

            if (source == atlantaButton) {
                clickedIndex = 0;
            } else if (source == chicagoButton) {
                clickedIndex = 1;
            } else if (source == washingtonButton) {
                clickedIndex = 2;
            }

            if (clickedIndex != -1) {
                CustomData clickedData = dataList.get(clickedIndex);
                arenaView.setText(clickedData.arenaInput);
                nomInput.setText(clickedData.coachName);
            } else {

            }
        }
    }
}