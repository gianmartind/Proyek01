package com.example.proyek01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.example.proyek01.addMaterial.AddMaterialFragment;
import com.example.proyek01.databinding.ActivityProjectBinding;
import com.example.proyek01.projectMain.ProjectMainFragment;

public class ProjectActivity extends AppCompatActivity implements FragmentListener {
    ActivityProjectBinding bind;
    FragmentManager fragmentManager;

    ProjectMainFragment projectMainFragment;
    AddMaterialFragment addMaterialFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bind = ActivityProjectBinding.inflate(getLayoutInflater());
        View view = this.bind.getRoot();
        setContentView(view);

        TypefaceProvider.registerDefaultIconSets();

        this.setSupportActionBar(this.bind.toolbar);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.fragmentManager = this.getSupportFragmentManager();

        Intent intent = getIntent();
        String name = intent.getStringExtra("PROJECT_NAME");
        this.bind.toolbarTitle.setText(name);

        this.changeProject(intent.getIntExtra("PROJECT_ID", 0));
        this.changePage(1);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        );
        switch (page){
            case 1:
                ft.replace(R.id.fragment_container, this.projectMainFragment, "MAIN").addToBackStack(null);
                break;
            case 2:
                ft.replace(R.id.fragment_container, this.addMaterialFragment, "ADD_MATERIAL").addToBackStack(null);
                break;
            default:
        }
        ft.commit();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.bind.drawerLayout.getWindowToken(), 0);
    }

    @Override
    public void changeAddMaterial(int id, String name) {
        this.addMaterialFragment = AddMaterialFragment.newInstance(id, name);
    }

    public void changeProject(int id) {
        this.projectMainFragment = ProjectMainFragment.newInstance(id);
    }

    @Override
    public void onBackPressed() {
        Fragment MAIN = getSupportFragmentManager().findFragmentByTag("MAIN");
        if(MAIN != null && MAIN.isVisible()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }
}