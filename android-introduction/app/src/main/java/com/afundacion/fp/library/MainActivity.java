package com.afundacion.fp.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Context context = this;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "¡La primera tostada del día!", Toast.LENGTH_SHORT).show();
        ActionHandler myHandler = new ActionHandler(this);

        Button myButton = findViewById(R.id.toastButton);
        myButton.setOnClickListener(myHandler);

        Button otherButton = findViewById(R.id.bottomButton);

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Arrancando otra actividad...", Toast.LENGTH_LONG).show();
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Arrancando otra actividad...", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(context, MonstersActivity.class);
                context.startActivity(myIntent);
            }
        }
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(context, "Hasta luego, main Activity!", Toast.LENGTH_LONG).show();
    }
}