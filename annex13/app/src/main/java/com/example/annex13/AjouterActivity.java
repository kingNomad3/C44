package com.example.annex13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RatingBar;

public class AjouterActivity extends AppCompatActivity {

    RatingBar ratingBar;
    double ratingEnDouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
    }

    private class Ecouteur implements AdapterView.OnClickListener{


        @Override
        public void onClick(View v) {
            if (v == ratingBar) {
                ratingEnDouble = ratingBar.getRating();

            }
        }
    }
}