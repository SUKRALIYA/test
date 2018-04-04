package com.androidtutorialshub.loginregister.activities;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.AddStudent.StudentAddActivity;
import com.androidtutorialshub.loginregister.FacultyActivity.AddTeacher;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.SchoolRegistration.SLoginActivity;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

public class Home extends AppCompatActivity  {
    static final Integer LOCATION = 0x1;
    static final Integer GPS_SETTINGS = 0x7;
    LocationRequest mLocationRequest;
    PendingResult<LocationSettingsResult> result;
    GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView textView1=(TextView)findViewById(R.id.school);
        TextView textView2=(TextView)findViewById(R.id.faculty);
        TextView textView3=(TextView)findViewById(R.id.student);
        client = new GoogleApiClient.Builder(this)
                .addApi(AppIndex.API)
                .addApi(LocationServices.API)
                .build();
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(), SLoginActivity.class);
                startActivity(intent);

            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddTeacher.class);
                startActivity(intent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), StudentAddActivity.class);
                startActivity(intent);
            }
        });

    }
//-------------------------------- start gps permission code---------------------------------------------------

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(Home.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(Home.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(Home.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            switch (requestCode) {
                //Location
                case 1:
                    askForGPS();
                    break;
            }
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        client.connect();
    }
    @Override
    public void onStop() {
        super.onStop();
        client.disconnect();
    }
    private void askForGPS(){
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        result = LocationServices.SettingsApi.checkLocationSettings(client, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(Home.this, GPS_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }
    //---------------------------------------------------------end gpspermission code--------------------------------//

}
