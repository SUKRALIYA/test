package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
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

import com.androidtutorialshub.loginregister.AddStudent.StudentAddActivity;
import com.androidtutorialshub.loginregister.FacultyActivity.AddTeacher;
import com.androidtutorialshub.loginregister.FacultyActivity.ViewFacultyActivity;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.SchoolRegistration.SLoginActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this method is using for hide titlebar//
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //inside setContentView method//
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView1=(TextView)findViewById(R.id.textView1);
        TextView textView2=(TextView)findViewById(R.id.textView);
        //for hide title on toolbar//
         getSupportActionBar().setTitle(null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        //sharedPreference is using here for save the data of school id and name//
        SharedPreferences sharedPreferences = getSharedPreferences("SchoolData", MODE_PRIVATE);
        String sluid = sharedPreferences.getString("sluid", null);
        String time = sharedPreferences.getString("message", null);
        // String name = sharedPreferences.getString("Name",null);

        TextView tvLeftMenuId = (TextView) header.findViewById(R.id.tv_left_menu_id);
   //   TextView tvLeftMenuName = (TextView)header.findViewById(R.id.tv_right_menu_name);

        tvLeftMenuId.setText(sluid);
    //    tvLeftMenuName.setText(name);
        textView1.setText(sluid);
        textView2.setText(time);
        //------------------add user and faculty--------//
        TextView addUser=(TextView)findViewById(R.id.addUser);
        TextView addFaculty=(TextView)findViewById(R.id.addFaculty);
        TextView addViewUser=(TextView)findViewById(R.id.ViewUser);
        TextView addViewFaculty=(TextView)findViewById(R.id.ViewFaculty);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentAddActivity.class);
                startActivity(intent);
            }
        });
        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddTeacher.class);
                startActivity(intent);
            }
        });
        addViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addViewFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewFacultyActivity.class);
                startActivity(intent);
            }
        });


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

        } else if (id == R.id.logout) {
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

