package com.androidtutorialshub.loginregister.SchoolRegistration;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.activities.MainActivity;
import com.androidtutorialshub.loginregister.activities.RequestHandler;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class SLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText textInputEditTextSchoolId;
    private TextInputEditText textInputEditTextSchoolPassword;
    private ImageButton show_hide_password;
    private TextView textViewLinkForgetId;
    private TextView textViewLinkForgetPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView appCompatTextViewLoginLink;
    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slogin);
        initViews();
        initListeners();
    }
    /**
     * This method is to initialize views
     */
    private void initViews() {
        textInputEditTextSchoolId = (TextInputEditText) findViewById(R.id.textInputEditTextSchoolId);
        textInputEditTextSchoolPassword = (TextInputEditText) findViewById(R.id.textInputEditTextSchoolPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        textViewLinkForgetId = (TextView) findViewById(R.id.textViewLinkForgetId);
        textViewLinkForgetPassword = (TextView) findViewById(R.id.textViewLinkForgetPassword);
        show_hide_password = (ImageButton) findViewById(R.id.show_hide_password);
        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
        }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
        textViewLinkForgetId.setOnClickListener(this);
        textViewLinkForgetPassword.setOnClickListener(this);
        show_hide_password.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_UP:
                        textInputEditTextSchoolPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        textInputEditTextSchoolPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                return true;
            }
        });
    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonLogin:
                registerUser();
                break;
            case R.id.appCompatTextViewLoginLink:
                Intent intent = new Intent(getApplicationContext(), SRegistration.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textViewLinkForgetId:
                Intent intent1 = new Intent(getApplicationContext(), ForgetIdActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.textViewLinkForgetPassword:
                Intent intent2 = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    private void registerUser() {

        final String sluid = textInputEditTextSchoolId.getText().toString().trim();
        final String slpassword = textInputEditTextSchoolPassword.getText().toString().trim();

        //first we will do the validations

        ////////////////////////////////////////////////////////////////////
        if (TextUtils.isEmpty(sluid)) {
            textInputEditTextSchoolId.setError("Please enter your School id");
            textInputEditTextSchoolId.requestFocus();
            return;
        }

        ////////////////////////////////////////////////////////////////////
        if (!validate(slpassword)) {
            textInputEditTextSchoolPassword.setError("It contains minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
            textInputEditTextSchoolPassword.requestFocus();
            return;
        }

        //////////////////////////////////////////////////////


        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();

                params.put("sluid", sluid);
                params.put("slpassword", slpassword);

                //returing the response
                return requestHandler.sendPostRequest("http://xenottabyte.in/Xenotapp/school_api.php?ACTION=Login", params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                emptyInputEditText();

                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (obj.getString("status").equals("1")) {
                        // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response

                        String sluid = obj.getString("sluid");
                        String time = obj.getString("message");
                        SharedPreferences sharedPreferences = getSharedPreferences("SchoolData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("sluid", sluid);
                        editor.putString("Login", "1");
                        editor.putString("message", time);
                        editor.apply();
                        finish();

                        // value nikali json se
//                        String time = obj.getString("message");
//                        String id   = obj.getString("sluid");

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        // ye value put ki
//                        intent.putExtra("Time", time);
//                        intent.putExtra("id", id);
                        startActivity(intent);

//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid School Id and Password", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }
    private void emptyInputEditText() {

        textInputEditTextSchoolId.setText(null);
        textInputEditTextSchoolPassword.setText(null);

    }

    public static boolean validate(String password) {
        return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
    }
}