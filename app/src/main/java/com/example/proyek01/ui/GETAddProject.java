package com.example.proyek01.ui;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class GETAddProject {
    protected static final String BASE_URL = "https://glinting-armory.000webhostapp.com/ws/";
    protected Context context;

    public GETAddProject(Context context){
        this.context = context;
    }

    public void makeRequest(Project project) throws JSONException {
        String url = BASE_URL + "add_project?name=" + project.getName() + "&address=" + project.getAddress() + "&start_date=" + project.getStartDate();
        this.callVolley(url);
    }

    public void callVolley(String url){
        url = url.replaceAll(" ", "%20");
        Log.d(TAG, "callVolley: " + url);
        RequestQueue queue = Volley.newRequestQueue(this.context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processResult();
            }

        }, volleyError -> {
            Log.d("RESPONSE", "Error!");
        });
        queue.add(request);
    }

    public void processResult(){

    }
}
