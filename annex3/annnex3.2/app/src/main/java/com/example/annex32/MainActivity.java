package com.example.annex32;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout parent;
    TextView passView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        passView = findViewById(R.id.passView);

//        1 etape
        Ecouteur ec = new Ecouteur();
//        2 ieme
        int count = parent.getChildCount();

        for (int i = 0; i < count; i++) {
            if (passView.length()<4) {
                View child = parent.getChildAt(i);
                if (child instanceof Button) {
                    child.setOnClickListener(ec);
                }
            }
        }
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View source) {
            if (source instanceof TextView) {
                String buttonText = ((TextView) source).getText().toString();
                passView.append(buttonText); // Ajouter le texte au champ de texte

                // Comparer le code avec le bon code
                String bonCode = "1234"; // Remplacez par votre bon code
                if (passView.getText().toString().equals(bonCode)) {

                } else {
                }
            }
        }
    }

}