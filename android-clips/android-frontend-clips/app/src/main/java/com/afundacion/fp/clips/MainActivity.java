package com.afundacion.fp.clips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    private RequestQueue queue;
    private ConstraintLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.queue = Volley.newRequestQueue(context);
        this.mainLayout = findViewById(R.id.main_layout);

        // Solicitamos una lista de clips
        requestClips();

        // Aquí lanzaremos una petición HTTP

        JsonObjectRequest request = new JsonObjectRequest( Request.Method.GET, Server.name + "/health", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(context,"Response OK:" + response.getString("status"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse == null) {
                    Toast.makeText(context, "Connection could not be established!", Toast.LENGTH_SHORT).show();

                } else {
                    // La siguiente variable contendrá el código HTTP,
                    // por ejemplo 405, 500,...
                    int serverCode = error.networkResponse.statusCode;
                    Toast.makeText(context, "Server status is " + serverCode, Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
        this.queue.add(request);
    }
    private void requestClips() {

        Response.Listener x = new Response.Listener() {
            @Override
            public void onResponse(Object o) {
                Snackbar.make(mainLayout, "List obtained", Snackbar.LENGTH_LONG).show();
            }
        };

        Response.ErrorListener y = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError.networkResponse == null) {
                    Snackbar.make(mainLayout, "Connection could not be established!", Snackbar.LENGTH_LONG).show();
                }else{
                    int serverCode = volleyError.networkResponse.statusCode;
                    Snackbar.make(mainLayout, "Server status is: "+serverCode, Snackbar.LENGTH_LONG).show();
                }
            }
        };

        JsonArrayRequest objeto = new JsonArrayRequest(
                Request.Method.GET,
                Server.name + "/clips",
                null,
                x,
                y
        );

        this.queue.add(objeto);
    }

}

