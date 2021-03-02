package com.example.proyek01.projectMain;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.ParseException;

import static android.content.ContentValues.TAG;

public class GETProjectDetails {
    protected static final String BASE_URL = "https://glinting-armory.000webhostapp.com/ws/";
    protected IGETProjectDetails ui;
    protected Context context;

    public GETProjectDetails(IGETProjectDetails ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    public void makeRequest(String query, int id){
        String url = BASE_URL + query + "?id=" + id;
        this.callVolley(url);
    }

    public void callVolley(String url){
        Log.d(TAG, "callVolley: " + url);
        RequestQueue queue = Volley.newRequestQueue(this.context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject object) {
                try {
                    ui.processResult(object);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("RESPONSE", "Error! " + volleyError.getMessage());
            }

        });
        queue.add(request);
    }

    public interface IGETProjectDetails{
        void processResult(JSONObject result) throws ParseException;
    }
}
