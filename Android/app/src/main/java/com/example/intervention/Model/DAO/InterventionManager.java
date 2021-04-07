package com.example.intervention.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.intervention.Model.Entity.Intervention;
import com.example.intervention.Model.TheSQLiteDB;
import com.example.intervention.NetworkService.AsyncJSONData;

import java.util.ArrayList;

public class InterventionManager {

    private static final String TABLE_NAME = "interventions";
    public static final String KEY_ID_INTERVENTION="id";
    public static final String KEY_USER_ID_INTERVENTION="user_id";
    public static final String KEY_NOM_CLIENT_INTERVENTION="nomClient";
    public static final String KEY_PRENOM_CLIENT_INTERVENTION="prenomClient";
    public static final String KEY_ADRESSE_CLIENT_INTERVENTION="adresseClient";
    public static final String KEY_MARQUE_CHAUDIERE_INTERVENTION="marqueChaudiere";
    public static final String KEY_MODELE_CHAUDIERE_INTERVENTION="modeleChaudiere";
    public static final String KEY_DATE_MISE_EN_SERVICE_INTERVENTION="dateMiseEnService";
    public static final String KEY_DATE_INTERVENTION_INTERVENTION="dateIntervention";
    public static final String KEY_NUMERO_SERIE_INTERVENTION="numeroSerie";
    public static final String KEY_DESCRIPTION_INTERVENTION="description";
    public static final String KEY_TEMPS_PASSE_INTERVENTION="tempsPasse";
    public static final String KEY_EST_ENVOYE_INTERVENTION="estEnvoye";
    public static final String CREATE_TABLE_INTERVENTION = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_INTERVENTION+" INTEGER primary key," +
            " "+KEY_USER_ID_INTERVENTION+" INTEGER," +
            " "+KEY_NOM_CLIENT_INTERVENTION+" TEXT," +
            " "+KEY_PRENOM_CLIENT_INTERVENTION+" TEXT," +
            " "+KEY_ADRESSE_CLIENT_INTERVENTION+" TEXT," +
            " "+KEY_MARQUE_CHAUDIERE_INTERVENTION+" TEXT," +
            " "+KEY_MODELE_CHAUDIERE_INTERVENTION+" TEXT," +
            " "+KEY_DATE_MISE_EN_SERVICE_INTERVENTION+" STRING," +
            " "+KEY_DATE_INTERVENTION_INTERVENTION+" STRING," +
            " "+KEY_NUMERO_SERIE_INTERVENTION+" TEXT," +
            " "+KEY_DESCRIPTION_INTERVENTION+" TEXT," +
            " "+KEY_TEMPS_PASSE_INTERVENTION+" INTEGER," +
            " "+KEY_EST_ENVOYE_INTERVENTION+" INTEGER" +
            ");";
    private TheSQLiteDB maBase; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public InterventionManager(Context context)
    {
        maBase = TheSQLiteDB.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBase.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addIntervention(Intervention intervention) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID_INTERVENTION, intervention.getUser_id());
        values.put(KEY_NOM_CLIENT_INTERVENTION, intervention.getNomClient());
        values.put(KEY_PRENOM_CLIENT_INTERVENTION, intervention.getPrenomClient());
        values.put(KEY_ADRESSE_CLIENT_INTERVENTION, intervention.getAdresseClient());
        values.put(KEY_MARQUE_CHAUDIERE_INTERVENTION, intervention.getMarqueChaudiere());
        values.put(KEY_MODELE_CHAUDIERE_INTERVENTION, intervention.getModeleChaudiere());
        values.put(KEY_DATE_MISE_EN_SERVICE_INTERVENTION, intervention.getDateMiseEnService());
        values.put(KEY_DATE_INTERVENTION_INTERVENTION, intervention.getDateIntervention());
        values.put(KEY_NUMERO_SERIE_INTERVENTION, intervention.getNumeroSerie());
        values.put(KEY_DESCRIPTION_INTERVENTION, intervention.getDescription());
        values.put(KEY_TEMPS_PASSE_INTERVENTION, intervention.getTempsPasse());
        values.put(KEY_EST_ENVOYE_INTERVENTION, intervention.isEstEnvoye());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int updateIntervention(Intervention intervention) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        //values.put(KEY_NOM_CLIENT_INTERVENTION, intervention.getNomClient());
        values.put(KEY_EST_ENVOYE_INTERVENTION, intervention.isEstEnvoye());


        String where = KEY_ID_INTERVENTION+" = ?";
        String[] whereArgs = {intervention.getId()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int removeintervention(Intervention intervention) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_INTERVENTION+" = ?";
        String[] whereArgs = {intervention.getId()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Intervention getIntervention(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Intervention a=new Intervention(0,0,"","","","","","","","","", 0);

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_INTERVENTION+"="+id, null);

        if (c.moveToFirst()) {
            a.setId(c.getInt(c.getColumnIndex(KEY_ID_INTERVENTION)));
            a.setUser_id(c.getInt(c.getColumnIndex(KEY_USER_ID_INTERVENTION)));
            a.setNomClient(c.getString(c.getColumnIndex(KEY_NOM_CLIENT_INTERVENTION)));
            a.setPrenomClient(c.getString(c.getColumnIndex(KEY_PRENOM_CLIENT_INTERVENTION)));
            a.setAdresseClient(c.getString(c.getColumnIndex(KEY_ADRESSE_CLIENT_INTERVENTION)));
            a.setMarqueChaudiere(c.getString(c.getColumnIndex(KEY_MARQUE_CHAUDIERE_INTERVENTION)));
            a.setModeleChaudiere(c.getString(c.getColumnIndex(KEY_MODELE_CHAUDIERE_INTERVENTION)));
            a.setDateMiseEnService(c.getString(c.getColumnIndex(KEY_DATE_MISE_EN_SERVICE_INTERVENTION)));
            a.setDateIntervention(c.getString(c.getColumnIndex(KEY_DATE_INTERVENTION_INTERVENTION)));
            a.setNumeroSerie(c.getString(c.getColumnIndex(KEY_NUMERO_SERIE_INTERVENTION)));
            a.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION_INTERVENTION)));
            a.setTempsPasse(c.getInt(c.getColumnIndex(KEY_TEMPS_PASSE_INTERVENTION)));
            a.setEstEnvoye(c.getInt(c.getColumnIndex(KEY_EST_ENVOYE_INTERVENTION)));
            c.close();
        }

        return a;
    }

    public ArrayList<com.example.intervention.Model.Entity.Intervention> getInterventions() {

        ArrayList<Intervention> interventionList = new ArrayList<Intervention>();


        //récupère dans un curseur le résultat du select sur la table
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE user_id="+AsyncJSONData.idCompteConnecte, null);

        if (c.moveToFirst()) {
            //parcourt le curseur obtenu, jusqu'a la fin, et créer pour chaque enregistrement un objet Intervention
            do {
                Intervention a = new Intervention(0, 0,"","","","","","","","","", 0);
                a.setId(c.getInt(c.getColumnIndex(KEY_ID_INTERVENTION)));
                a.setUser_id(c.getInt(c.getColumnIndex(KEY_USER_ID_INTERVENTION)));
                a.setNomClient(c.getString(c.getColumnIndex(KEY_NOM_CLIENT_INTERVENTION)));
                a.setPrenomClient(c.getString(c.getColumnIndex(KEY_PRENOM_CLIENT_INTERVENTION)));
                a.setAdresseClient(c.getString(c.getColumnIndex(KEY_ADRESSE_CLIENT_INTERVENTION)));
                a.setMarqueChaudiere(c.getString(c.getColumnIndex(KEY_MARQUE_CHAUDIERE_INTERVENTION)));
                a.setModeleChaudiere(c.getString(c.getColumnIndex(KEY_MODELE_CHAUDIERE_INTERVENTION)));
                a.setDateMiseEnService(c.getString(c.getColumnIndex(KEY_DATE_MISE_EN_SERVICE_INTERVENTION)));
                a.setDateIntervention(c.getString(c.getColumnIndex(KEY_DATE_INTERVENTION_INTERVENTION)));
                a.setNumeroSerie(c.getString(c.getColumnIndex(KEY_NUMERO_SERIE_INTERVENTION)));
                a.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION_INTERVENTION)));
                a.setTempsPasse(c.getInt(c.getColumnIndex(KEY_TEMPS_PASSE_INTERVENTION)));
                a.setEstEnvoye(c.getInt(c.getColumnIndex(KEY_EST_ENVOYE_INTERVENTION)));

                // ajoute l'objet créé à la ArrayList de Intervention qui sera renvoyée.
                interventionList.add(a);
            }
            while (c.moveToNext());
        }
        c.close();

        return interventionList;
    }

} // class InterventionManager