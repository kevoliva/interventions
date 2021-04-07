package com.example.intervention.NetworkService;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intervention.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class AsyncPostJSONData extends AsyncTask<String, Void, JSONObject> {

    private AppCompatActivity myActivity;

    public AsyncPostJSONData(AppCompatActivity theActivity) {
        myActivity = theActivity;
    }

    JSONObject postData;
    // On crée un constructeur attendant une paire de clé / valeur
    public AsyncPostJSONData(Map<String, String> postData) {
        if (postData != null) {
            // et on en fait un objet JSON
            this.postData = new JSONObject(postData);
        }
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        JSONObject json = null;

        try {
            // on se connecte a l'url passée en parametre
            URL url = new URL(params[0]);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestProperty("Content-Type", "application/json");

            // On parametre la connexion en POST
            urlConnection.setRequestMethod("POST");

            // OPTIONNEL - On définit un header pour une autorisation
            // (dépends de l'API consommée)
            urlConnection.setRequestProperty("Authorization", "someAuthString");

            // Puis on envoie les données
            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }

            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {
                // si la connexion réussit, on récupére la réponse
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                String response = getFlux(inputStream);

                try {
                    json = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /*protected void onPostExecute(JSONObject s) {
        try {
            // on traite eventuellement la réponse apres l'appel

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    private String getFlux(InputStream is) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}