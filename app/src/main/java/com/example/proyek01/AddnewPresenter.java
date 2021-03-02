package com.example.proyek01;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import com.example.proyek01.ui.GETAddProject;
import com.example.proyek01.ui.POSTAddProject;
import com.example.proyek01.ui.Project;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class AddnewPresenter implements POSTAddProject.IPOSTAddProject{
    IAddnewFragment ui;

    public AddnewPresenter(IAddnewFragment ui){
        this.ui = ui;
    }

    public void addProject(String name, String address, String date, Context context) throws JSONException {
        Log.d(TAG, "addProject: ");
        if(isValid(name, address, date)){
            Project project = new Project(0, name, address, date, "0000-00-00");
//            GETAddProject postAddProject = new GETAddProject(context);
//            postAddProject.makeRequest(project);
            POSTAddProject postAddProject = new POSTAddProject(this, context);
            postAddProject.makeRequest(project);
        } else {
            this.ui.showToast("Input tidak valid!");
        }
    }

    public boolean isValid(String name, String address, String date){
        if(name.equals("")){
            Log.d(TAG, "isValid: " + 1);
            return false;
        }
        if(address.equals("")){
            Log.d(TAG, "isValid: " + 2);
            return false;
        }
        if(!validateJavaDate(date)){
            Log.d(TAG, "isValid: " + 3);
            return false;
        }
        return true;
    }

    public static boolean validateJavaDate(String strDate)
    {
        /* Check if date is 'null' */
        if (strDate.trim().equals(""))
        {
            return true;
        }
        /* Date is not 'null' */
        else
        {
            /*
             * Set preferred date format,
             * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
            sdfrmt.setLenient(false);
            /* Create Date object
             * parse the string into date
             */
            try
            {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate+" is valid date format");
            }
            /* Date format is invalid */
            catch (ParseException e)
            {
                System.out.println(strDate+" is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

    public void openDate(Context context){
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String format = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                ui.inputDate(sdf.format(myCalendar.getTime()));
            }

        };
        new DatePickerDialog(context, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onSuccess() {
        this.ui.showToast("Proyek ditambah");
        this.ui.closeDialog();
    }

    public interface IAddnewFragment{
        void inputDate(String date);
        void showToast(String message);
        void closeDialog();
    }
}
