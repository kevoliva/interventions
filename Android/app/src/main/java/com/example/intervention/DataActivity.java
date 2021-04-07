package com.example.intervention;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intervention.Model.DAO.InterventionManager;
import com.example.intervention.Model.Entity.Intervention;
import com.example.intervention.NetworkService.AsyncJSONData;
import com.example.intervention.NetworkService.AsyncPostJSONData;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity implements com.example.intervention.DataAdapter.InterventionListAdapter.ItemClickListener{

    private Button mbuttonAddData, mbuttonSendInterventions;
    private ArrayList<Intervention> interventionList, interventionListNonEnvoyees;
    com.example.intervention.DataAdapter.InterventionListAdapter adapter;
    private InterventionManager interventionmanager;
    private int compteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        // On créer un InterventionManager pour pouvoir récupérer la liste des interventions
        interventionmanager = new InterventionManager(this);

        // on se connecte à la base de données
        interventionmanager.open();

        // On récupère la liste des interventions contenues dans la table Intervention
        interventionList = interventionmanager.getInterventions();

        // on ferme la connexion
        interventionmanager.close();

        //on lie les elements d'interface aux objets correspondants
        mbuttonAddData = (Button) findViewById(R.id.buttonAddData);
        mbuttonSendInterventions = (Button) findViewById(R.id.buttonSendInterventions);

        mbuttonAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAjouterInterventions();
            }
        });

        // On parametre notre vue Recycler
        RecyclerView recyclerView = findViewById(R.id.MyrvData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // On lie maintenant les données à notre adapteur
        adapter = new com.example.intervention.DataAdapter.InterventionListAdapter(this, interventionList);

        // On ajoute la gestion des evenements sur le clic
        adapter.setClickListener(this);

        // Puis on lie notre adapteur au RecyclerView
        recyclerView.setAdapter(adapter);

        // On ajoute un trait de séparation entre les éléments (pour faciliter la lecture et le clic
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),1);
        recyclerView.addItemDecoration(dividerItemDecoration);

        mbuttonSendInterventions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compteur = 0;
                interventionmanager.open();

                for(int i = 0 ; i < interventionList.size(); i++) {

                    if (interventionList.get(i).isEstEnvoye() == 0) {
                        AsyncPostJSONData task = new AsyncPostJSONData(interventionList.get(i).getMap());
                        task.execute("http://10.0.2.2:8000/api/intervention");

                        interventionList.get(i).setEstEnvoye(1);
                        interventionmanager.updateIntervention(interventionList.get(i));
                        compteur ++;
                    }
                }

                interventionList.clear();
                interventionList.addAll(interventionmanager.getInterventions());
                adapter.notifyDataSetChanged();

                interventionmanager.close();

                if (compteur > 0){
                    Toast.makeText(getApplicationContext(), "Les interventions ont bien été envoyées", Toast.LENGTH_SHORT).show();
                    compteur = 0;
                }
                else{
                    Toast.makeText(getApplicationContext(), "Aucune nouvelle intervention à ajouter", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //On vide la liste
        interventionList.clear();

        interventionmanager.open();

        //Puis on réajoute tout le contenu de la liste de la base
        interventionList.addAll(interventionmanager.getInterventions());


        interventionmanager.close();

        // indique a l'adapter que les données ont été mise à jour,
        // et que le contenu de recyclerView doit être réaffiché.
        adapter.notifyDataSetChanged();
    }

    public void openAjouterInterventions() {
        Intent ajouterActivity = new Intent(DataActivity.this, AjouterActivity.class);
        startActivity(ajouterActivity);
    }

    @Override
    public void onItemClick(View view, int position) {

        interventionmanager.open();
        interventionmanager.removeintervention(interventionmanager.getIntervention(adapter.getItem(position).getId()));

        interventionList.clear();

        //Puis on réajoute tout le contenu de la liste de la base
        interventionList.addAll(interventionmanager.getInterventions());

        interventionmanager.close();

        adapter.notifyDataSetChanged();

        //On utilise Toast (https://developer.android.com/reference/android/widget/Toast) et sa méthode makeText pour afficher le texte correspondant à l'élément cliqué.
        // Toast.makeText(this, "Vous avez cliqué sur " + adapter.getItem(position).getNomClient() + " " + adapter.getItem(position).getPrenomClient()  + " à la ligne " + position, Toast.LENGTH_SHORT).show();
        // ou tout autre traitement de notre choix sur le clic
    }
}