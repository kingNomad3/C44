package com.example.revisionexamfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    TextView nbEquipeDivision;
    TextView capacite;
    Spinner arena;
    Button recherche;
    TextView nomEquipe;

    EquipeHockeyBd gest ;
    Vector nomArena ;

    private static EquipeHockeyBd instance;

    Ecouteur ec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Reference to the UI elements
        nbEquipeDivision = findViewById(R.id.nbEquipeDivision);
        capacite = findViewById(R.id.capacite);
        arena = findViewById(R.id.arena);
        recherche = findViewById(R.id.recherche);
        nomEquipe = findViewById(R.id.nomEquipe);

        ec = new Ecouteur();  // Initialize the listener
        recherche.setOnClickListener(ec);


    }

    @Override
    protected void onStart() {
        super.onStart();
        //copie de on create
        gest = EquipeHockeyBd.getInstance(getApplicationContext());
        gest.ouvrireConnectionBd();

        //remplir le listeView
        ArrayAdapter<String> adapteur = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gest.retournerNomsArenas());
        arena.setAdapter(adapteur);
    }

    @Override
    protected void onStop(){
        super.onStop();
        gest.fermerConnection();
    }

    private class Ecouteur implements  View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (recherche == v) {
                String selectedArena = arena.getSelectedItem().toString();
                String equipeName = gest.getNomEquipeParArena(selectedArena);
                String division = gest.getDivisionParArena(selectedArena);  // Get the division of the selected arena

                if (equipeName != null) {
                    nomEquipe.setText(equipeName);
                    int countTeamsInDivision = gest.getNombreEquipesParDivision(division);
                    int capacityForSelectedArena = gest.getCapaciteParArena(selectedArena);
                    nbEquipeDivision.setText("Nombre d'équipes évoluant dans la division " + division + ": " + countTeamsInDivision);
                    capacite.setText("Moyenne des capacites: " + capacityForSelectedArena + " sièges");

                } else {
                    nomEquipe.setText("Équipe non trouvée");
                }
            }
        }
    }
}