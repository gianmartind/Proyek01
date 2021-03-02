package com.example.proyek01.ui;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class POSTAddProject {
    protected static final String BASE_URL = "https://glinting-armory.000webhostapp.com/ws/";
    protected IPOSTAddProject ui;
    protected Context context;
    protected Gson gson;

    public POSTAddProject(IPOSTAddProject ui, Context context){
        this.ui = ui;
        this.context = context;
        this.gson = new Gson();
    }

    public void makeRequest(Project project) throws JSONException {
        String obj = this.gson.toJson(project);
        JSONObject jsonObject = new JSONObject(obj);
        this.callVolley(jsonObject);
    }

    public void callVolley(JSONObject json){
        Log.d(TAG, "callVolley: " + json);
        RequestQueue queue = Volley.newRequestQueue(this.context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, BASE_URL + "add_project", json, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject object) {
                ui.onSuccess();
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("RESPONSE", "Error!" + volleyError.getMessage());
            }

        });
        queue.add(request);
    }

    public interface IPOSTAddProject{
        void onSuccess();
    }
}
