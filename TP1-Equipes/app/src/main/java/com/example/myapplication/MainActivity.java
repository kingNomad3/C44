package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //creation des variables
    Button atlantaButton;
    Button chicagoButton;
    Button washingtonButton;
    TextView arenaView;
    TextView nomInput;
    ImageView teamImage;

    //better to use Hashmap dans mon cas?
    HashMap<String, EquipeBasket> team;
    //List<EquipeBasket> dataList;
    //String[] teamNames = {"Atlanta", "Chicago", "Washington"};
    //int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialisation de vlaeur
        atlantaButton = findViewById(R.id.atlantaButton);
        chicagoButton = findViewById(R.id.chicagoButton);
        washingtonButton = findViewById(R.id.washingtonButton);
        arenaView = findViewById(R.id.arenaView);
        nomInput = findViewById(R.id.nomEntrainer);
        teamImage = findViewById(R.id.defaultImg);

        //creation/initialisation de mon ecouteur
        Ecouteur ec = new Ecouteur();

        //creation de mon Hashmap
        team = new HashMap<>();

        //le premier string est la cle pour chaque equipe
        //creation de mon objet team depuis la class EquipeBasket
        team.put("Atlanta", new EquipeBasket(R.drawable.atlanta, "Gateway Center Arena", "Tanisha Wright"));
        team.put("Chicago", new EquipeBasket(R.drawable.chicago, "Wintrust Arena", "Emre Vatansever"));
        team.put("Washington", new EquipeBasket(R.drawable.washington, "Entertainment & Sports Arena", "Eric Thibault"));

        //Set mes tags avec mes clee
        //https://developer.android.com/reference/android/view/View#getTag()
        //http://www.java2s.com/example/java-api/android/widget/textview/gettag-0-0.html
        atlantaButton.setTag("Atlanta");
        chicagoButton.setTag("Chicago");
        washingtonButton.setTag("Washington");

        //associsation de mes bouttons avec mon Ecouteur
        atlantaButton.setOnClickListener(ec);
        chicagoButton.setOnClickListener(ec);
        washingtonButton.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View source) {
            //get tag me permet de prendre l'objet Team que j'ai creer
            String teamName = (String) source.getTag();
            //appel de ma fonction qui va change l'affichage
            updateView(teamName);
        }
    }

    //fonction qui me permet d'update mes valeur qui proviennent de mon hashStable
    private void updateView(String teamName) {
        EquipeBasket equipe = team.get(teamName);
        arenaView.setText(equipe.getArenaInput());
        nomInput.setText(equipe.getCoachName());
        teamImage.setImageResource(equipe.getImageResourceId());
    }
}


//private class Ecouteur implements View.OnClickListener {
//
//    @Override
//    public void onClick(View v) {
//        if (v == atlantaButton) {
//            currentIndex = 0;
//        } else if (v == chicagoButton) {
//            currentIndex = 1;
//        } else if (v == washingtonButton) {
//            currentIndex = 2;
//        }
//        updateView(teamNames[currentIndex]);
//    }
//
//
//}

//        dataList.add(new EquipeBasket(R.drawable.atlanta, "Atlanta Arena", "John Doe"));
//        dataList.add(new EquipeBasket(R.drawable.chicago, "Chicago Arena", "Jane Smith"));
//        dataList.add(new EquipeBasket(R.drawable.washington, "Washington Arena", "Michael
//        View.OnClickListener buttonClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v == atlantaButton) {
//                    currentIndex = 0;
//                } else if (v == chicagoButton) {
//                    currentIndex = 1;
//                } else if (v == washingtonButton) {
//                    currentIndex = 2;
//                }
//                updateView(teamNames[currentIndex]);
//            }
//        };
//