package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.SchoolRegistration.SLoginActivity;
import com.androidtutorialshub.loginregister.model.Data;

public class FacultyDashoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String DATA = "Data";
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this method is using for hide titlebar//
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //inside setContentView method//
        setContentView(R.layout.activity_faculty_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        //for hide title on toolbar//
        getSupportActionBar().setTitle(null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView faultyId = (TextView) findViewById(R.id.faultyId);
        TextView facultytime = (TextView) findViewById(R.id.facultytime);
//        //sharedPreference is using here for save the data of school id and name//
         SharedPreferences sharedPreferences = getSharedPreferences("SchoolData", MODE_PRIVATE);
        String sluid = sharedPreferences.getString("sluid", null);
        String time = sharedPreferences.getString("message", null);
        faultyId.setText(sluid);
        facultytime.setText(time);
//        // String name = sharedPreferences.getString("Name",null);

       TextView tvLeftMenuId = (TextView) header.findViewById(R.id.tvfaculty);
        tvLeftMenuId.setText(sluid);
//        //   TextView tvLeftMenuName = (TextView)header.findViewById(R.id.tv_right_menu_name);
//
//
//        //    tvLeftMenuName.setText(name);

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
        }
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.facultylogout) {
            SharedPreferences sharedPreferences = getSharedPreferences("SchoolData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // this method
//            editor.putString("Login", "0");

            // or this method
            editor.remove("Login");

            editor.apply();

            startActivity(new Intent(getApplicationContext(), SLoginActivity.class));
            finish();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

