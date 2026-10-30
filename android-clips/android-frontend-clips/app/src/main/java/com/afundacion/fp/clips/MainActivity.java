package com.afundacion.fp.clips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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
    private ProgressBar progressBar;
    private ClipsList clips;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.queue = Volley.newRequestQueue(context);
        this.mainLayout = findViewById(R.id.main_layout);
        this.progressBar = findViewById(R.id.progresBar);
        this.recyclerView = findViewById(R.id.recyclerView);


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
    public void setClips(ClipsList clips) {
        this.clips = clips;
        ClipsAdapter myAdapter = new ClipsAdapter(this.clips);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public ClipsList getClipsForTest() {
        return clips;
    }
    private void requestClips() {
        progressBar.setVisibility(View.VISIBLE);

        Response.Listener<JSONArray> x = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.INVISIBLE);
                Snackbar.make(mainLayout, "List obtained", Snackbar.LENGTH_LONG).show();
                setClips(new ClipsList(response));
            }
        };

        Response.ErrorListener y = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.INVISIBLE);
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

