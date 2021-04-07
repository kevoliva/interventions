package com.example.intervention.NetworkService;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intervention.MainActivity;
import com.example.intervention.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncJSONData extends AsyncTask<String, Void, JSONObject> {

    private AppCompatActivity myActivity;

    public AsyncJSONData(AppCompatActivity theActivity) {
        myActivity = theActivity;
    }
    public static Integer idCompteConnecte = -1;

    @Override
    protected JSONObject doInBackground(String... strings) {
        // Dans cette méthode nous allons donc nous connecter à l'API
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            // on récupére l'url passée en paramètre lors de l'appel a execute
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            // On lit des données puisque l'on fait un get
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream

            result = getFlux(in); // on transmet le flux a getFlux pour reconstruire la chaine


        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        // On crée un objet JSON vide
        JSONObject json = null;
        try {
            // Que l'on remplit grâce à la chaine parsée reçue de l'API
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json; // returns the result
    }

    protected void onPostExecute(JSONObject s) {
        try {
            // après le traitement de la demande a l'api
            // onrécupère l'objet JSON
            // On parcourt les items du tableau "weather"
            JSONArray items = s.getJSONArray("data");

            for (int i = 0; i<items.length(); i++)
            {
                JSONObject uneentree = items.getJSONObject(i);
                if (MainActivity.identifiant.equals(uneentree.getString("email"))){
                    if (MainActivity.motDePasse.equals(uneentree.getString("password"))){
                        idCompteConnecte = uneentree.getInt("id");
                    }
                }
            }
            if (idCompteConnecte.equals(-1)){
                Toast.makeText(myActivity, "L'identifiant ou le mot de passe est incorrect", Toast.LENGTH_SHORT).show();
            }
            Log.d("ID", idCompteConnecte.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getFlux(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}