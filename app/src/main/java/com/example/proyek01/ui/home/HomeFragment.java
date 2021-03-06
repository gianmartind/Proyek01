package com.example.proyek01.ui.home;

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

public class HomeFragment extends Fragment implements ProjectAdapter.RecycleViewClickListener, HomePresenter.IHomeFragment {
    RecyclerView projectList;
    HomePresenter homePresenter;
    ProjectAdapter projectAdapter;
    ProgressBar progressBar;
    
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        this.projectList = view.findViewById(R.id.project_list);
        this.progressBar = view.findViewById(R.id.progress_bar);
        this.homePresenter = new HomePresenter(this);
        this.projectAdapter = new ProjectAdapter(this.homePresenter.list, this);

        this.projectList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        this.projectList.setAdapter(this.projectAdapter);
        this.homePresenter.loadData(this.getActivity());
        return view;
    }

    @Override
    public void onItemClick(View v, int position) {
        this.homePresenter.openProject(position, this.getActivity());
    }

    @Override
    public void updateList() {
        this.projectAdapter.notifyDataSetChanged();
        this.progressBar.setVisibility(View.GONE);
    }
}