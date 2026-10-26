package com.afundacion.fp.clips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.queue = Volley.newRequestQueue(context);

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
}

