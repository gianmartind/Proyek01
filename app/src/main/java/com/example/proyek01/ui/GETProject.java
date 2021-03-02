package com.example.proyek01.ui;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class GETProject {
    protected static final String BASE_URL = "https://glinting-armory.000webhostapp.com/ws/";
    protected IGETPROject ui;
    protected Context context;

    public GETProject(IGETPROject ui, Context context){
        this.ui = ui;
        this.context = context;
    }

    public void makeRequest(String query){
        String url = BASE_URL + query;
        this.callVolley(url);
    }

    public void callVolley(String url){
        RequestQueue queue = Volley.newRequestQueue(this.context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    ui.processResult(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, volleyError -> {
            Log.d("RESPONSE", "Error!");
        });
        queue.add(request);
    }

    public interface IGETPROject {
        void processResult(JSONArray result) throws JSONException;
    }
}
