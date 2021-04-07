package com.example.intervention;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.intervention.NetworkService.AsyncJSONData;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private Button mbuttonConnexion;
    private EditText mIdentifiant, mMotDePasse;
    public static String identifiant, motDePasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbuttonConnexion = (Button) findViewById(R.id.logiin);
        mIdentifiant = (EditText) findViewById(R.id.usrusr);
        mMotDePasse = (EditText) findViewById(R.id.passwrd);


         /*mbuttonConnexion.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
              openInterventions();
          }
         });*/

        mbuttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // récupère les champs entrés
                identifiant = mIdentifiant.getText().toString();
                motDePasse = mMotDePasse.getText().toString();

                // récupère l'utilisateur connecté
                AsyncJSONData task = new AsyncJSONData(MainActivity.this);
                task.execute("http://10.0.2.2:8000/api/user");

                if (!AsyncJSONData.idCompteConnecte.equals(-1)){
                    openInterventions();
                }
            }
        });
    }

    public void openInterventions() {
        Intent dataActivity = new Intent(MainActivity.this, DataActivity.class);
        startActivity(dataActivity);
    }

}