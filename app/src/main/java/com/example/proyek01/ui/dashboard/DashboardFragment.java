package com.example.proyek01.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyek01.R;
import com.example.proyek01.ui.ProjectAdapter;
import com.example.proyek01.ui.home.HomePresenter;

public class DashboardFragment extends Fragment implements DashboardPresenter.IDashboardFragment, ProjectAdapter.RecycleViewClickListener {
    RecyclerView projectList;
    DashboardPresenter dashboardPresenter;
    ProjectAdapter projectAdapter;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.projectList = view.findViewById(R.id.project_list);
        this.progressBar = view.findViewById(R.id.progress_bar);
        this.dashboardPresenter = new DashboardPresenter(this);
        this.projectAdapter = new ProjectAdapter(this.dashboardPresenter.list, this);

        this.projectList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        this.projectList.setAdapter(this.projectAdapter);
        this.dashboardPresenter.loadData(this.getActivity());
        return view;
    }

    @Override
    public void onItemClick(View v, int position) {
        this.dashboardPresenter.openProject(position, this.getActivity());
    }

    @Override
    public void updateList() {
        this.projectAdapter.notifyDataSetChanged();
        this.progressBar.setVisibility(View.GONE);
    }
}