package com.afundacion.fp.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Lanzamos la tostada
        Toast.makeText(this, "¡La primera tostada del día!", Toast.LENGTH_SHORT).show();
    }
}