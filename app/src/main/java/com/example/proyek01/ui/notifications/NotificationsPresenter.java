package com.example.proyek01.ui.notifications;

import android.content.Context;
import android.content.Intent;

import com.example.proyek01.ProjectActivity;
import com.example.proyek01.ui.GETProject;
import com.example.proyek01.ui.Project;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class NotificationsPresenter implements GETProject.IGETPROject {
    List<Project> list;
    INotificationsFragment ui;
    Gson gson;

    public NotificationsPresenter(INotificationsFragment ui) {
        this.list = new ArrayList<Project>();
        this.ui = ui;
        this.gson = new Gson();
    }

    public void loadData(Context context){
        this.list.clear();
        GETProject getProject = new GETProject(this, context);
        getProject.makeRequest("all_project");
    }

    public void openProject(int position, Context context){
        Intent intent = new Intent(context, ProjectActivity.class);
        int id = this.list.get(position).getId();
        String name = this.list.get(position).getName();
        intent.putExtra("PROJECT_ID", id);
        intent.putExtra("PROJECT_NAME", name);
        context.startActivity(intent);
    }

    @Override
    public void processResult(JSONArray result) throws JSONException {
        for(int i = 0; i < result.length(); i++){
            Project project = this.gson.fromJson(result.getJSONObject(i).toString(), Project.class);
            this.list.add(project);
        }
        this.ui.updateList();
    }

    public interface INotificationsFragment{
        void updateList();
    }
}

