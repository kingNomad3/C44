package com.example.tp2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class DialogueActivity extends AppCompatActivity {
    Button okButton;
    SeekBar largeurTrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);

        okButton = findViewById(R.id.okButton);
        largeurTrait = findViewById(R.id.largeurTrait);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCrayonTaille();
            }
        });
    }

    private void showCrayonTaille() {

        final SeekBar seekBar = new SeekBar(DialogueActivity.this);
        seekBar.setMax(100);
        seekBar.setProgress(largeurTrait.getProgress());


    }
}