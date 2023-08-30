package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity {

    Button atlantaButton;
    Button chicagoButton;
    Button washingtonButton;
    TextView arenaView;
    TextView nomInput;
    ImageView teamImage;  // Add ImageView for the team image

    List<EquipeBasket> dataList;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atlantaButton = findViewById(R.id.atlantaButton);
        chicagoButton = findViewById(R.id.chicagoButton);
        washingtonButton = findViewById(R.id.washingtonButton);
        arenaView = findViewById(R.id.arenaView);
        nomInput = findViewById(R.id.nomEntrainer);
        teamImage = findViewById(R.id.teamImage);  // Initialize the ImageView

        dataList = new ArrayList<>();

        dataList.add(new EquipeBasket(R.drawable.atlanta, "Atlanta Arena", "John Doe"));
        dataList.add(new EquipeBasket(R.drawable.chicago, "Chicago Arena", "Jane Smith"));
        dataList.add(new EquipeBasket(R.drawable.washington, "Washington Arena", "Michael Johnson"));

        updateView(currentIndex);

        // Common OnClickListener for all buttons
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == atlantaButton) {
                    currentIndex = 0;
                } else if (v == chicagoButton) {
                    currentIndex = 1;
                } else if (v == washingtonButton) {
                    currentIndex = 2;
                }
                updateView(currentIndex);
            }
        };

        atlantaButton.setOnClickListener(buttonClickListener);
        chicagoButton.setOnClickListener(buttonClickListener);
        washingtonButton.setOnClickListener(buttonClickListener);
    }

    private void updateView(int index) {
        EquipeBasket equipe = dataList.get(index);
        arenaView.setText(equipe.getArenaInput());
        nomInput.setText(equipe.getCoachName());
        teamImage.setImageResource(equipe.getImageResourceId());  // Update the team image
    }
}
