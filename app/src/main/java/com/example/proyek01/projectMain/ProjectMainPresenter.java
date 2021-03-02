package com.example.proyek01.projectMain;

import android.content.Context;

import com.example.proyek01.ui.Project;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProjectMainPresenter implements GETProjectDetails.IGETProjectDetails {
    int id;
    Gson gson;
    IProjectMain ui;

    public ProjectMainPresenter(IProjectMain ui, int id){
        this.ui = ui;
        this.id = id;
        this.gson = new Gson();
    }

    public void loadData(Context context){
        GETProjectDetails getProjectDetails = new GETProjectDetails(this, context);
        getProjectDetails.makeRequest("get_project_details", this.id);
    }

    public String formatDate(String date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd MMM yyyy");
        return simpleDateFormat.format(date1);
    }

    public String formatCurrency(long num){
        String num1 = String.format("%,d", num).replace(",", ".");
        return "Rp " + num1 + ",00";
    }

    @Override
    public void processResult(JSONObject result) throws ParseException {
        ProjectDetails projectDetails = this.gson.fromJson(result.toString(), ProjectDetails.class);
        this.ui.setData(projectDetails);
    }

    public interface IProjectMain{
        void setData(ProjectDetails projectDetails) throws ParseException;
    }
}
