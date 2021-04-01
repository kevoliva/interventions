package com.example.intervention;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.intervention.Model.DAO.InterventionManager;
import com.example.intervention.Model.Entity.Intervention;

public class AjouterActivity extends AppCompatActivity {

    private Button madd;
    private InterventionManager interventionmanager;
    private EditText mNom, mPrenom, mAdresse, mMarqueChaudiere, mModeleChaudiere,
            mDateMES, mDateIntervention, mNumeroSerie, mDescription, mTempsPasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        madd = (Button) findViewById(R.id.enregistrerIntervention);
        mNom = (EditText) findViewById(R.id.nomClient);
        mPrenom = (EditText) findViewById(R.id.prenomClient);
        mAdresse = (EditText) findViewById(R.id.adresseClient);
        mMarqueChaudiere = (EditText) findViewById(R.id.marqueChaudiere);
        mModeleChaudiere = (EditText) findViewById(R.id.modeleChaudiere);
        mDateMES = (EditText) findViewById(R.id.dateMiseService);
        mDateIntervention = (EditText) findViewById(R.id.dateIntervention);
        mNumeroSerie = (EditText) findViewById(R.id.numeroSerie);
        mDescription = (EditText) findViewById(R.id.descriptionIntervention);
        mTempsPasse = (EditText) findViewById(R.id.tempsPasse);

        interventionmanager = new InterventionManager(this);

        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ouvre l'acces a la base de données
                interventionmanager.open();

                //Crée un nouveau contact et peuple l'objet avec les valeurs des champs de l'activité
                Intervention newIntervention = new Intervention(0,0,"","","","","","","","","", 0);
                newIntervention.setNomClient(mNom.getText().toString());
                newIntervention.setPrenomClient(mPrenom.getText().toString());
                newIntervention.setAdresseClient(mAdresse.getText().toString());
                newIntervention.setMarqueChaudiere(mMarqueChaudiere.getText().toString());
                newIntervention.setModeleChaudiere(mModeleChaudiere.getText().toString());
                newIntervention.setDateMiseEnService(mDateMES.getText().toString());
                newIntervention.setDateIntervention(mDateIntervention.getText().toString());
                newIntervention.setNumeroSerie(mNumeroSerie.getText().toString());
                newIntervention.setDescription(mDescription.getText().toString());
                newIntervention.setTempsPasse(Integer.parseInt(mTempsPasse.getText().toString()));


                //Ajoute le contact à la base de données
                interventionmanager.addIntervention(newIntervention);

                // Ferme l'acces a la base
                interventionmanager.close();

                // Ferme l'activité une fois l'ajout terminé.
                AjouterActivity.this.finish();
            }
        });
    }
}