package com.example.tpfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Stack;

public class GameActivity extends AppCompatActivity {
    private TextView cardsLeft;
    private TextView timeContainer;
    private String selectedCardValueString;
    private TextView selectedCardTextView;
    private Stack<String> removedCardsValueStr = new Stack<>();
    private Stack<Integer> removedCardsLocation = new Stack<>();
    private Deck deck = new Deck();
    private Thread thread = null;
    private long startTime;
    private long timeSinceStartMs;
    private int minutes;
    private int seconds;
    private String minutesStr = null;
    private String secondsStr = null;
    private LinearLayout descendingCards = null;
    private LinearLayout ascendingCards = null;
    private ImageView replayButton = null;
    private TextView scoreContainer = null;
    private Integer lastCardOnPileBeforePlayContainerId = null;
    private String lastCardOnPileBeforePlayValue = null;
    private long timeAlreadyPlayed;
    private int score = 0;

    Ecouteur ec = new Ecouteur();

    //https://stackoverflow.com/questions/5387371/how-to-convert-minutes-to-hours-and-minutes-hhmm-in-java
    //https://stackoverflow.com/questions/5188295/how-to-change-a-textview-every-second-in-android
    private String timeInMsToString(long timeInMs) {
        minutes = (int) timeSinceStartMs / 60000;
        seconds = (int) (timeSinceStartMs % 60000) / 1000;
        // update TextView here!
        secondsStr = String.valueOf(seconds);
        minutesStr = String.valueOf(minutes);
        if (minutes < 10) {
            minutesStr = "0" + minutesStr;
        }
        if (seconds < 10) {
            secondsStr = "0" + secondsStr;
        }
        return minutesStr + ":" + secondsStr;
    }


    private void changeCardColor(TextView cardTextView, int cardValue) {
        if (cardValue == 98 || cardValue == 0) {
            cardTextView.setBackgroundResource(R.drawable.card_in_play);
        } else if (cardValue < 21) {
            cardTextView.setBackgroundResource(R.drawable.card_container_0_20);
        } else if (cardValue > 20 && cardValue < 41) {
            cardTextView.setBackgroundResource(R.drawable.card_container_21_40);
        } else if (cardValue > 40 && cardValue < 61) {
            cardTextView.setBackgroundResource(R.drawable.card_container_41_60);
        } else if (cardValue > 60 && cardValue < 80) {
            cardTextView.setBackgroundResource(R.drawable.card_container_61_80);
        } else {
            cardTextView.setBackgroundResource(R.drawable.card_container_81_plus);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        timeAlreadyPlayed = 0;
        startTime = new java.util.Date().getTime();
        // timeContainer = findViewById(R.id.Temps);
        descendingCards = findViewById(R.id.descendingCards);
        ascendingCards = findViewById(R.id.ascendingCards);
        scoreContainer = findViewById(R.id.scoreContainer);
        replayButton = findViewById(R.id.replayButton);
        cardsLeft = findViewById(R.id.cardsLeftCount);


//        replayButton.setOnClickListener((View.OnClickListener) ec);
    }


    private class Ecouteur implements View.OnDragListener, View.OnTouchListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            switch (event.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:
                    if (((TextView) v).getText() == selectedCardValueString) {
//                        v.setBackgroundResource(R.color.background_color);
                        ((TextView) v).setText("");
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                case DragEvent.ACTION_DROP:
                    if (selectedCardValueString != null) {
                        selectedCardTextView.setText(selectedCardValueString);
                        changeCardColor(selectedCardTextView, Integer.parseInt(selectedCardValueString));
                        selectedCardValueString = null;
                        break;
                    }
                    break;
                default:
                    break;

            }
            return true;
        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(null, shadowBuilder, v, 0);
            selectedCardTextView = (TextView) v;
            selectedCardValueString = (String) selectedCardTextView.getText();

            return true;
        }
    }
}