package com.example.proyek01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.proyek01.R;

import org.json.JSONException;

public class AddnewFragment extends DialogFragment implements AddnewPresenter.IAddnewFragment, View.OnClickListener {
    BootstrapEditText projectName, projectAddress, projectStartdate;
    BootstrapButton addButton, calendarButton;
    AddnewPresenter addnewPresenter;

    public AddnewFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new, container, false);
        this.projectName = view.findViewById(R.id.project_name);
        this.projectAddress = view.findViewById(R.id.project_address);
        this.projectStartdate = view.findViewById(R.id.project_startdate);
        this.addButton = view.findViewById(R.id.add_button);
        this.calendarButton = view.findViewById(R.id.project_calendar);

        this.addnewPresenter = new AddnewPresenter(this);
        this.projectStartdate.setOnClickListener(this);
        this.addButton.setOnClickListener(this);
        this.calendarButton.setOnClickListener(this);
        return view;
    }

    @Override
    public int getTheme() {
        return R.style.DialogTheme;
    }

    @Override
    public void inputDate(String date) {
        this.projectStartdate.setText(date);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeDialog() {
        this.dismiss();
    }

    @Override
    public void onClick(View v) {
        if(v == this.calendarButton){
            this.addnewPresenter.openDate(this.getActivity());
        } else if(v == this.addButton) {
            String name = this.projectName.getText().toString();
            String address = this.projectAddress.getText().toString();
            String startdate = this.projectStartdate.getText().toString();
            try {
                this.addnewPresenter.addProject(name, address, startdate, this.getActivity());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
