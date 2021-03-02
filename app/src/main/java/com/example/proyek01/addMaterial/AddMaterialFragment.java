package com.example.proyek01.addMaterial;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.BootstrapText;
import com.example.proyek01.FragmentListener;
import com.example.proyek01.R;
import com.example.proyek01.model.Material;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddMaterialFragment extends Fragment implements View.OnClickListener, AddMaterialPresenter.IAddMaterial {
    FragmentListener fragmentListener;
    AwesomeTextView projectName;
    BootstrapEditText materialName, materialStore, materialPrice, materialDate;
    BootstrapButton materialCalendar, addMaterial;
    AddMaterialPresenter addMaterialPresenter;
    
    public AddMaterialFragment(){}

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_material, container, false);

        this.projectName = view.findViewById(R.id.project_name);
        this.materialName = view.findViewById(R.id.material_name);
        this.materialStore = view.findViewById(R.id.material_store);
        this.materialPrice = view.findViewById(R.id.material_price);
        this.materialDate = view.findViewById(R.id.material_date);
        this.materialCalendar = view.findViewById(R.id.material_calendar);
        this.addMaterial = view.findViewById(R.id.add_material_btn);

        int projectId = this.getArguments().getInt("PROJECT_ID");
        String projectName = this.getArguments().getString("PROJECT_NAME");

        this.addMaterialPresenter = new AddMaterialPresenter(this, projectId);
        this.projectName.setText(projectName);

        this.materialCalendar.setOnClickListener(this);
        this.addMaterial.setOnClickListener(this);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        this.materialDate.setText(dtf.format(now));

        return view;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.fragmentListener = (FragmentListener) context;
        } else{
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    public static AddMaterialFragment newInstance(int id, String name){
        AddMaterialFragment fragment = new AddMaterialFragment();

        Bundle args = new Bundle();
        args.putInt("PROJECT_ID", id);
        args.putString("PROJECT_NAME", name);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onClick(View v) {
        if(v == this.materialCalendar){
            this.addMaterialPresenter.openDate(this.getActivity());
        } else if(v == this.addMaterial){
            String name = this.materialName.getText().toString();
            String store = this.materialStore.getText().toString();
            String price =this.materialPrice.getText().toString();
            String date = this.materialDate.getText().toString();
            try {
                this.addMaterialPresenter.addMaterial(name, store, price, date, this.getActivity());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void inputDate(String date) {
        this.materialDate.setText(date);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
