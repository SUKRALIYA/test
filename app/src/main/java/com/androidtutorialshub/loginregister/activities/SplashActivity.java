package com.androidtutorialshub.loginregister.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.androidtutorialshub.loginregister.FacultyActivity.AddTeacher;
import com.androidtutorialshub.loginregister.FacultyActivity.ViewDetailsActivity;
import com.androidtutorialshub.loginregister.FacultyActivity.ViewFacultyActivity;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.SchoolRegistration.ForgetPasswordActivity;
import com.androidtutorialshub.loginregister.SchoolRegistration.NotificationActivity;
import com.androidtutorialshub.loginregister.SchoolRegistration.SLoginActivity;
import com.androidtutorialshub.loginregister.SchoolRegistration.SRegistration;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(getApplicationContext(), ViewFacultyActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
