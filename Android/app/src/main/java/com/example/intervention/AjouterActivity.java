package com.example.intervention;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.intervention.Model.DAO.InterventionManager;
import com.example.intervention.Model.Entity.Intervention;
import com.example.intervention.NetworkService.AsyncJSONData;

public class AjouterActivity extends AppCompatActivity {

    private Button madd;
    private InterventionManager interventionmanager;
    private EditText mNom, mPrenom, mAdresse, mMarqueChaudiere, mModeleChaudiere,
            mNumeroSerie, mDescription, mTempsPasse;
    private DatePicker mDateMES, mDateIntervention;
    private Integer moisMES, moisIntervention, jourMES, jourIntervention;

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
        mDateMES = (DatePicker) findViewById(R.id.dateMiseService);
        mDateIntervention = (DatePicker) findViewById(R.id.dateIntervention);
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
                newIntervention.setUser_id(AsyncJSONData.idCompteConnecte);
                newIntervention.setNomClient(mNom.getText().toString());
                newIntervention.setPrenomClient(mPrenom.getText().toString());
                newIntervention.setAdresseClient(mAdresse.getText().toString());
                newIntervention.setMarqueChaudiere(mMarqueChaudiere.getText().toString());
                newIntervention.setModeleChaudiere(mModeleChaudiere.getText().toString());
                moisMES = Integer.valueOf(mDateMES.getMonth()+1);
                moisIntervention = Integer.valueOf(mDateIntervention.getMonth()+1);
                jourMES = mDateMES.getDayOfMonth();
                jourIntervention = mDateIntervention.getDayOfMonth();
                if (moisMES < 10) {
                    moisMES = Integer.valueOf("0" + String.valueOf(moisMES));
                }
                if (moisIntervention < 10){
                    moisIntervention = Integer.valueOf("0" + String.valueOf(moisIntervention));
                }
                if (jourMES < 10){
                    jourMES = Integer.valueOf("0" + String.valueOf(jourMES));
                }
                if (moisIntervention < 10){
                    jourIntervention = Integer.valueOf("0" + String.valueOf(jourIntervention));
                }
                String dateMiseEnService = mDateMES.getYear() + "-" + moisMES + "-" + jourMES;
                newIntervention.setDateMiseEnService(dateMiseEnService);
                String dateIntervention = mDateIntervention.getYear() + "-" + moisIntervention + "-" + jourIntervention;
                newIntervention.setDateIntervention(dateIntervention);
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