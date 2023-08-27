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

    // CustomData class definition
    private class CustomData {
        private int imageResourceId;
        private String arenaInput;
        private String coachName;

        public CustomData(int imageResourceId, String arenaName, String coachName) {
            this.imageResourceId = imageResourceId;
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

    List<CustomData> dataList;
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
        atlantaImg = findViewById(R.id.AtlantaImg);
        chicagoImg = findViewById(R.id.ChicagoImg);
        washingtonImg = findViewById(R.id.washingtonImg);



        dataList = new ArrayList<>();
        dataList.add(new CustomData(R.drawable.atlantaImg, "Atlanta Arena", "John Doe"));
        dataList.add(new CustomData(R.drawable.chicagoImg, "Chicago Arena", "Jane Smith"));
        dataList.add(new CustomData(R.drawable.washingtonImg, "Washington Arena", "Michael Johnson"));

        ec = new Ecouteur(); // Initialize the listener
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