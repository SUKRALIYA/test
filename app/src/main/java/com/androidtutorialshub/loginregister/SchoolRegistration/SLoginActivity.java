package com.androidtutorialshub.loginregister.SchoolRegistration;

import android.Manifest;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.activities.FacultyDashoardActivity;
import com.androidtutorialshub.loginregister.activities.MainActivity;
import com.androidtutorialshub.loginregister.activities.RequestHandler;
import com.androidtutorialshub.loginregister.model.SchoolName;
import com.androidtutorialshub.loginregister.model.categoryregist.CategoryLogin;
import com.androidtutorialshub.loginregister.model.categoryregist.LoginTypeNmae;
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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class SLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText textInputEditTextSchoolId;
    private TextInputEditText textInputEditTextSchoolPassword;
    private ImageButton show_hide_password;
    private TextView textViewLinkForgetId;
    private TextView textViewLinkForgetPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView appCompatTextViewLoginLink;
    private Spinner spinner;
    ProgressBar progressBar;
    private  String categoriesText;
    private SchoolName selectedLoginType;
    static final Integer LOCATION = 0x1;
    static final Integer GPS_SETTINGS = 0x7;
    LocationRequest mLocationRequest;
    PendingResult<LocationSettingsResult> result;
    GoogleApiClient client;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slogin);

            askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION);
            askForPermission(Manifest.permission.CAMERA,CAMERA);

        client = new GoogleApiClient.Builder(this)
                .addApi(AppIndex.API)
                .addApi(LocationServices.API)
                .build();
        spinner=(Spinner)findViewById(R.id.spinner);
        initViews();
        initListeners();
        new LoginType().execute();
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
                loginUser();
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

    private void loginUser() {

        final String sluid = textInputEditTextSchoolId.getText().toString().trim();
        final String slpassword = textInputEditTextSchoolPassword.getText().toString().trim();
        final String sLoginType = categoriesText;

        //first we will do the validations

        ////////////////////////////////////////////////////////////////////
        if (TextUtils.isEmpty(sluid)) {
            textInputEditTextSchoolId.setError("Please enter your School id");
            textInputEditTextSchoolId.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(sLoginType)|| TextUtils.equals(sLoginType,"Please Select")){

            Toast.makeText(this, "Please select type", Toast.LENGTH_SHORT).show();
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

        class LoginUser extends AsyncTask<Void, Void, String> {

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
                params.put("login_type", sLoginType);
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
                    if (obj.getString("stat").equals("1")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response

                        String sluid = obj.getString("sluid");
                        String time = obj.getString("message");
                        SharedPreferences sharedPreferences = getSharedPreferences("SchoolData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("sluid", sluid);
                        editor.putString("Login", "1");
                        editor.putString("message", time);
                        editor.apply();

                        if(obj.getString("status").equals("ORGANIZATION")) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        else if (obj.getString("status").equals("FACULTY")) {
                            Intent intent = new Intent(getApplicationContext(), FacultyDashoardActivity.class);
                            startActivity(intent);
                        }
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid School Id and Password", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        LoginUser ru = new LoginUser();
        ru.execute();
    }
    private void emptyInputEditText() {

        textInputEditTextSchoolId.setText(null);
        textInputEditTextSchoolPassword.setText(null);
    }

    public static boolean validate(String password) {
        return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
    }
    class LoginType extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();
            return requestHandler.sendPostRequest("http://xenottabyte.in/Xenotapp/school_api.php?ACTION=logiN_type");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            CategoryLogin categoryLogin = gson.fromJson(s,CategoryLogin.class);
            final List<String> categories = new ArrayList<String>();
            categories.add("Please Select");
            LoginTypeNmae loginTypeNmae=new LoginTypeNmae();
            loginTypeNmae.setLogin_type_name("Please Select");
            for (int i = 0; i < categoryLogin.getData().size(); i++) {
                categories.add(categoryLogin.getData().get(i).getmLoginTypeName());
            }
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SLoginActivity.this,
                    android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    categoriesText = categories.get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
    //-------------------------------- start gps permission code---------------------------------------------------

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(SLoginActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(SLoginActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(SLoginActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(SLoginActivity.this, new String[]{permission}, requestCode);
            }
        } else {
           // Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
                    askForGPS();

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
                            status.startResolutionForResult(SLoginActivity.this, GPS_SETTINGS);
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