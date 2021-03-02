package com.example.proyek01.projectMain;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapText;
import com.beardedhen.androidbootstrap.api.attributes.BootstrapBrand;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.beardedhen.androidbootstrap.font.FontAwesome;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.proyek01.FragmentListener;
import com.example.proyek01.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;

import static com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand.REGULAR;

public class ProjectMainFragment extends Fragment implements View.OnClickListener, ProjectMainPresenter.IProjectMain {
    FragmentListener fragmentListener;

    TextView materialCost, paymentCost, otherCost, totalCost, projectName, projectAddress, projectStartdate;
    AwesomeTextView projectStatus;
    BootstrapButton materialList, addMaterial, paymentList, addPayment, otherList, addOther;
    FloatingActionButton projectDone;
    ProgressBar progressBar;

    ProjectMainPresenter projectMainPresenter;
    
    public ProjectMainFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_main, container, false);

        this.projectName = view.findViewById(R.id.project_name);
        this.projectAddress = view.findViewById(R.id.project_address);
        this.projectStartdate = view.findViewById(R.id.project_startdate);
        this.materialCost = view.findViewById(R.id.material_cost);
        this.paymentCost = view.findViewById(R.id.payment_cost);
        this.otherCost = view.findViewById(R.id.other_cost);
        this.totalCost = view.findViewById(R.id.total_cost);
        this.projectStatus = view.findViewById(R.id.project_status);
        this.materialList = view.findViewById(R.id.material_list);
        this.addMaterial = view.findViewById(R.id.add_material);
        this.paymentList = view.findViewById(R.id.payment_list);
        this.addPayment = view.findViewById(R.id.add_payment);
        this.otherList = view.findViewById(R.id.other_list);
        this.addOther = view.findViewById(R.id.add_other);
        this.projectDone = view.findViewById(R.id.project_done);
        this.progressBar = view.findViewById(R.id.progress_bar);

//        this.projectStatus.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
//        BootstrapText text = new BootstrapText.Builder(this.getActivity()).addIcon("fa_gears", new FontAwesome()).addText(" Status").build();

        this.materialList.setOnClickListener(this);
        this.addMaterial.setOnClickListener(this);
        this.paymentList.setOnClickListener(this);
        this.addPayment.setOnClickListener(this);
        this.otherList.setOnClickListener(this);
        this.addOther.setOnClickListener(this);
        this.projectDone.setOnClickListener(this);

        int id = this.getArguments().getInt("PROJECT_ID");
        this.projectMainPresenter = new ProjectMainPresenter(this, id);
        this.projectMainPresenter.loadData(this.getActivity());

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

    public static ProjectMainFragment newInstance(int id){
        ProjectMainFragment fragment = new ProjectMainFragment();

        Bundle args = new Bundle();
        args.putInt("PROJECT_ID", id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onClick(View v) {
        if(this.progressBar.getVisibility() == View.GONE){
            if(v == this.addMaterial){
                this.fragmentListener.changeAddMaterial(this.getArguments().getInt("PROJECT_ID"), this.projectName.getText().toString());
                this.fragmentListener.changePage(2);
            }
        }
    }

    @Override
    public void setData(ProjectDetails projectDetails) throws ParseException {
        this.projectName.setText(projectDetails.getName());
        this.projectAddress.setText(projectDetails.getAddress());
        this.projectStartdate.setText(this.projectMainPresenter.formatDate(projectDetails.getStart_date()));
        this.materialCost.setText(this.projectMainPresenter.formatCurrency(projectDetails.getMaterialCost()));
        this.paymentCost.setText(this.projectMainPresenter.formatCurrency(projectDetails.getPaymentCost()));
        this.otherCost.setText(this.projectMainPresenter.formatCurrency(projectDetails.getOtherCost()));
        Long totalCost = projectDetails.getMaterialCost() + projectDetails.getPaymentCost() + projectDetails.getOtherCost();
        this.totalCost.setText(this.projectMainPresenter.formatCurrency(totalCost));
        if(projectDetails.getEnd_date().equals("0000-00-00")){
            this.projectStatus.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
            BootstrapText text = new BootstrapText.Builder(this.getActivity()).addIcon("fa_gears", new FontAwesome()).addText(" Dalam pengerjaan").build();
            this.projectStatus.setBootstrapText(text);
        } else {
            this.projectStatus.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
            BootstrapText text = new BootstrapText.Builder(this.getActivity()).addIcon("fa_check", new FontAwesome()).addText(" Selesai ").addText(projectDetails.getEnd_date()).build();
            this.projectStatus.setBootstrapText(text);
        }
        this.progressBar.setVisibility(View.GONE);
    }
}
