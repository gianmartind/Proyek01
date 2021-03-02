package com.example.proyek01.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyek01.R;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<Project> list;
    private RecycleViewClickListener clickListener;

    public ProjectAdapter(List<Project> list, RecycleViewClickListener clickListener) {
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.project_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder holder, int position) {
        Project project = this.list.get(position);
        
        holder.projectName.setText(project.getName());
        holder.projectAddress.setText(project.getAddress());
        if(project.getEndDate().equals("Aktif")){
            holder.projectStatus.setTextColor(Color.GREEN);
        }
        holder.projectStatus.setText(project.getEndDate());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView projectName;
        public TextView projectAddress;
        public TextView projectStatus;
        public LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.projectName = itemView.findViewById(R.id.project_name);
            this.projectAddress = itemView.findViewById(R.id.project_address);
            this.projectStatus = itemView.findViewById(R.id.project_status);
            this.layout = itemView.findViewById(R.id.layout);
            this.layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, this.getLayoutPosition());
        }
    }

    public interface RecycleViewClickListener {
        void onItemClick(View v, int position);
    }
}
