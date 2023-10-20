package com.example.annex13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Vector;

public class MeilleurActivity extends AppCompatActivity {
    private static GestionBD instance;
    Button back;
    GestionBD bd ;
    Ecouteur ec = new Ecouteur();
    TextView premier;
    TextView deuxieme;
    TextView troisieme;
    ListView listeBiere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meilleur);

        back = findViewById(R.id.backButon);

        listeBiere = findViewById(R.id.listeBiere);


//        premier = findViewById(R.id.premier);
//        deuxieme = findViewById(R.id.deuxieme);
//        troisieme = findViewById(R.id.troisieme);

        back.setOnClickListener(ec);

    }

    @Override
    protected void onStart() {
        super.onStart();
        bd = GestionBD.getInstance(getApplicationContext());
        bd.ouvrireConnectionBd();

        Vector<String> temps = new Vector<String>();
        temps =  bd.retournerMeilleur();

        ArrayAdapter adapteur = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,bd.retournerMeilleur() );

        //avec cette maniere il faut 3 deja dans la bd
//        premier.setText(temps.elementAt(0));
//        deuxieme.setText(temps.elementAt(1));
//        troisieme.setText(temps.elementAt(2));

        //boucler sur retournerMeilleur
//        for (int i = 0; i< retournerMeilleur.getsize(); i++ ){
//            if (listeBiere.getChildAt(i) instanceof TextView) {
//                TextView tempListe = (TextView) listeBiere.getChildAt(i);
//                tempListe.setText(temps.elementAt(i));
//            }
//
//        }



        listeBiere.setAdapter(adapteur);

    }


    private class Ecouteur implements  AdapterView.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == back){
                Intent i = new Intent(MeilleurActivity.this,MainActivity.class);
                startActivity(i);
            }
        }
    }
}