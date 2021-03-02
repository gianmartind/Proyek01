package com.example.proyek01.addMaterial;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import com.example.proyek01.POSTAddNew;
import com.example.proyek01.model.Material;
import com.google.gson.Gson;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class AddMaterialPresenter implements POSTAddNew.IPOSTAddNew{
    int projectId;
    IAddMaterial ui;

    public AddMaterialPresenter(IAddMaterial ui, int projectId){
        this.ui = ui;
        this.projectId = projectId;
    }

    public void addMaterial(String name, String store, String price, String date, Context context) throws JSONException {
        POSTAddNew postAddNew = new POSTAddNew(this, context);
        if(this.isValid(name, store, price, date)){
            Material material = new Material(0, name, store, Integer.parseInt(price), date, this.projectId);
            postAddNew.addMaterial(material);
        } else {
            this.ui.showToast("Input tidak valid!");
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

    public boolean isValid(String name, String store, String price, String date){
        if(name.equals("")){
            Log.d(TAG, "isValid: " + 1);
            return false;
        }
        if(store.equals("")){
            Log.d(TAG, "isValid: " + 2);
            return false;
        }
        if(price.equals("")){
            Log.d(TAG, "isValid: " + 3);
            return false;
        }
        if(!validateJavaDate(date)){
            Log.d(TAG, "isValid: " + 4);
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

    @Override
    public void onSuccess() {
        this.ui.showToast("Material ditambah!");
    }

    public interface IAddMaterial{
        void inputDate(String date);
        void showToast(String message);
    }
}
