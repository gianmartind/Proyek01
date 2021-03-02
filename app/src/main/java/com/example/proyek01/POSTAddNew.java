package com.example.proyek01;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyek01.model.Material;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class POSTAddNew {
    protected static final String BASE_URL = "https://glinting-armory.000webhostapp.com/ws/";
    protected Context context;
    protected Gson gson;
    protected IPOSTAddNew ui;

    public POSTAddNew(IPOSTAddNew ui, Context context){
        this.ui = ui;
        this.gson = new Gson();
        this.context = context;
    }

    public void addMaterial(Material material) throws JSONException {
        String obj = this.gson.toJson(material);
        JSONObject jsonObject = new JSONObject(obj);
        this.callVolley(jsonObject, "add_material");
    }

    public void callVolley(JSONObject json, String url){
        Log.d(TAG, "callVolley: " + json);
        RequestQueue queue = Volley.newRequestQueue(this.context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, BASE_URL + url, json, new Response.Listener<JSONObject>() {

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

    public interface IPOSTAddNew{
        void onSuccess();
    }
}
