package com.example.quoraclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawer_Layout;
    Toolbar toolbar;
    FloatingActionButton fab;

    private RecyclerView recycleView;
    private ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fab = findViewById(R.id.fab);

        drawer_Layout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.nav_toolbar);
        //check here may be need to change the id(important)
        Objects.requireNonNull(getSupportActionBar()).setTitle("Unsolved App");
        try {
            // setSupportActionBar(toolbar);
            progress_circular =findViewById(R.id.progress_circular);
            recycleView=findViewById(R.id.recycleView);

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
            recycleView.setHasFixedSize(true);
            recycleView.setLayoutManager(linearLayoutManager);


            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_Layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer_Layout.addDrawerListener(toggle);
            toggle.syncState();
        }
        catch (Exception E){
            Toast.makeText(getApplication(), E.getMessage(), Toast.LENGTH_LONG).show();
        }
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AskAQuestionActivity.class);
            startActivity(intent);
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_finance:
                Intent intent= new Intent(HomeActivity.this,FinanceActivity.class);
                startActivity(intent);
                break;
        }
        drawer_Layout.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed(){
        if(drawer_Layout.isDrawerOpen(GravityCompat.START)){
            drawer_Layout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}