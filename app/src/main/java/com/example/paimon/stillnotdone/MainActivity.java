package com.example.paimon.stillnotdone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.paimon.stillnotdone.LaboratoryWork1.FirstItemFragment;
import com.example.paimon.stillnotdone.LaboratoryWork1.SecondItemFragment;
import com.example.paimon.stillnotdone.LaboratoryWork1.ThirdItemFragment;
import com.example.paimon.stillnotdone.LaboratoryWork2.FragmentLaboratoryWorkSecond;
import com.example.paimon.stillnotdone.LaboratoryWork3.FragmentLaboratoryWorkThird;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    public void headerImageOnClick(View view){
        Fragment fragment = new FragmentStartDefault();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.kekkek, fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment;
        int id = item.getItemId();

        switch (id){
            case R.id.lab1_task1:
                fragment = new FirstItemFragment();
                break;
            case R.id.lab1_task2:
                fragment = new SecondItemFragment();
                break;
            case R.id.lab1_task3:
                fragment = new ThirdItemFragment();
                break;
            case R.id.lab2:
                fragment = new FragmentLaboratoryWorkSecond();
                break;
            case R.id.lab3:
                fragment = new FragmentLaboratoryWorkThird();
                break;
            case R.id.lab4:
                fragment = new FragmentStartDefault();
                Toast.makeText(getApplicationContext(), "Wooops, this fragment in the pipeline!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lab5:
                fragment = new FragmentStartDefault();
                Toast.makeText(getApplicationContext(), "Wooops, this fragment in the pipeline!", Toast.LENGTH_SHORT).show();
                break;
            default:
                fragment = new FragmentStartDefault();
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.kekkek, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
