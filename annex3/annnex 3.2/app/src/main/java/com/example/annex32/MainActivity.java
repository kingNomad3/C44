package com.example.annex32;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout parent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);

//        1 etape
        Ecouteur ec = new Ecouteur();
//        2 ieme
//


    }

    private class Ecouteur implements View.OnClickListener{


        @Override
        public void onClick(View source) {

            for (int i = 0; i<=9; i++){
                if (source == parent.)

            }



        }
    }

}