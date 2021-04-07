package com.example.intervention.Model.Entity;

import com.example.intervention.MainActivity;
import com.example.intervention.NetworkService.AsyncJSONData;

import java.util.HashMap;
import java.util.Map;

public class Intervention {

    private int id;
    private int user_id;
    private String nomClient;
    private String prenomClient;
    private String adresseClient;
    private String marqueChaudiere;
    private String modeleChaudiere;
    private String dateMiseEnService;
    private String dateIntervention;
    private String numeroSerie;
    private String description;
    private int tempsPasse;
    private int estEnvoye;

    public Intervention(int id, int user_id, String nomClient, String prenomClient, String adresseClient,
                        String marqueChaudiere, String modeleChaudiere, String dateMiseEnService, String dateIntervention,
                        String numeroSerie, String description, int tempsPasse) {
        this.id = id;
        this.user_id = user_id;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.adresseClient = adresseClient;
        this.marqueChaudiere = marqueChaudiere;
        this.modeleChaudiere = modeleChaudiere;
        this.dateMiseEnService = dateMiseEnService;
        this.dateIntervention = dateIntervention;
        this.numeroSerie = numeroSerie;
        this.description = description;
        this.tempsPasse = tempsPasse;
        this.estEnvoye = 0;
    }

    public Map<String, String> getMap(){
        Map<String, String> interventionMap = new HashMap<>();
        interventionMap.put("user_id", String.valueOf(AsyncJSONData.idCompteConnecte));
        interventionMap.put("nomClient", this.nomClient);
        interventionMap.put("prenomClient", this.prenomClient);
        interventionMap.put("adresseClient", this.adresseClient);
        interventionMap.put("marqueChaudiere", this.marqueChaudiere);
        interventionMap.put("modeleChaudiere", this.modeleChaudiere);
        interventionMap.put("dateMiseEnService", this.dateMiseEnService);
        interventionMap.put("dateIntervention", this.dateIntervention);
        interventionMap.put("numeroSerie", this.numeroSerie);
        interventionMap.put("description", this.description);
        interventionMap.put("tempsPasse", String.valueOf(this.tempsPasse));

        return interventionMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getMarqueChaudiere() {
        return marqueChaudiere;
    }

    public void setMarqueChaudiere(String marqueChaudiere) {
        this.marqueChaudiere = marqueChaudiere;
    }

    public String getModeleChaudiere() {
        return modeleChaudiere;
    }

    public void setModeleChaudiere(String modeleChaudiere) {
        this.modeleChaudiere = modeleChaudiere;
    }

    public String getDateMiseEnService() {
        return dateMiseEnService;
    }

    public void setDateMiseEnService(String dateMiseEnService) {
        this.dateMiseEnService = dateMiseEnService;
    }

    public String getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(String dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(int tempsPasse) {
        this.tempsPasse = tempsPasse;
    }

    public int isEstEnvoye() {
        return estEnvoye;
    }

    public void setEstEnvoye(int estEnvoye) {
        this.estEnvoye = estEnvoye;
    }
}
