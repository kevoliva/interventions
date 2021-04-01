package com.example.intervention;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mbuttonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbuttonConnexion = (Button) findViewById(R.id.logiin);

        mbuttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInterventions();
            }
        });
    }

    public void openInterventions() {
        Intent dataActivity = new Intent(MainActivity.this, DataActivity.class);
        startActivity(dataActivity);
    }

}