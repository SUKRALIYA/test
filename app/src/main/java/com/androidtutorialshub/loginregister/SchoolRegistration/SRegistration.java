package com.androidtutorialshub.loginregister.SchoolRegistration;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.activities.RequestHandler;
import com.androidtutorialshub.loginregister.model.Employee;
import com.androidtutorialshub.loginregister.model.SchoolName;
import com.androidtutorialshub.loginregister.model.categoryregist.CategoryRegistration;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SRegistration extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText textInputEditTextSchoolName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextPhoneNumber;
    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;
    private Spinner spinner;
    private  String categoriesText, selectedShortName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sregistration);
        initViews();
        initListeners();
        new SpinnerData().execute();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        spinner= findViewById(R.id.spinner);
        textInputEditTextSchoolName = (TextInputEditText) findViewById(R.id.textInputEditTextSchoolName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextPhoneNumber = (TextInputEditText) findViewById(R.id.textInputEditTextPhoneNumber);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonSubmit);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonSubmit:
                registerUser();
                break;

            case R.id.appCompatTextViewLoginLink:
                Intent intent = new Intent(getApplicationContext(),SLoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


    private void registerUser() {
        final String sname = textInputEditTextSchoolName.getText().toString().trim();
        final String semail = textInputEditTextEmail.getText().toString().trim();
        final String spassword = textInputEditTextPassword.getText().toString().trim();
        final String phone_number = textInputEditTextPhoneNumber.getText().toString().trim();
        //first we will do the validations
        if (TextUtils.isEmpty(sname)) {
            textInputEditTextSchoolName.setError("Please enter school name");
            textInputEditTextSchoolName.requestFocus();
            return;
        }

        ////////////////////////////////////////////////////////////////////
        if (TextUtils.isEmpty(semail)) {
            textInputEditTextEmail.setError("Please enter your email");
            textInputEditTextEmail.requestFocus();
            return;
        }

        if (!isValidEmailId(semail)) {
            textInputEditTextEmail.setError("Please enter valid email");
            textInputEditTextEmail.requestFocus();
            return;
        }
        ////////////////////////////////////////////////////////////////////
        if (! isValidPassword(spassword)) {
            textInputEditTextPassword.setError("It must be contain alphanumeric and one upper latter and one special character at least");
            textInputEditTextPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(spassword)) {
            textInputEditTextPassword.setError("Please enter phone number");
            textInputEditTextPassword.requestFocus();
            return;
        }

        //////////////////////////////////////////////////////
        if (phone_number.length() != 10) {
            textInputEditTextPhoneNumber.setError("Please enter 10 digit number");
            textInputEditTextPhoneNumber.requestFocus();
            return;
        }

        if (!isValidMobile(phone_number)) {
            textInputEditTextPhoneNumber.setError("Number must be numeric");
            textInputEditTextPhoneNumber.requestFocus();
            return;
        }

        ////////////////////////////////////////////////////////////////////


        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("sname", sname);
                params.put("semail", semail);
                params.put("spassword", spassword);
                params.put("phone_number", phone_number);
                params.put("categories", selectedShortName);

                //returing the response
                return requestHandler.sendPostRequest("http://xenottabyte.in/Xenotapp/school_api.php?ACTION=SRegistration", params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                emptyInputEditText();

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (obj.getString("message").equals("Successfully Registered")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");
                        finish();
                        startActivity(new Intent(getApplicationContext(), SRegistration.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Email id Already Exist", Toast.LENGTH_SHORT).show();
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
        textInputEditTextSchoolName.setText(null);

        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextPhoneNumber.setText(null);

    }

    //gave validation for authorised email id //
    private boolean isValidEmailId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    //validation for authorised or valid mobile number in 10 digit//
    private boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 6 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
                textInputEditTextPhoneNumber.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }



    class SpinnerData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();
            return requestHandler.sendPostRequest("http://xenottabyte.in/Xenotapp/school_api.php?ACTION=registrationCategory");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            CategoryRegistration categoryRegistration = gson.fromJson(s,CategoryRegistration.class);
            final List<String> categories = new ArrayList<String>();
            final List<SchoolName> schoolCategories = new ArrayList<>();
            categories.add("Please Select");
            SchoolName schoolName1 = new SchoolName();
            schoolName1.setCat_full_name("Please Select");
            schoolName1.setCat_short_name("");
            schoolCategories.add(schoolName1);
            for (int i = 0; i < categoryRegistration.getData().size(); i++) {
                categories.add(categoryRegistration.getData().get(i).getCatFullName());
                SchoolName schoolName = new SchoolName();
                schoolName.setCat_full_name(categoryRegistration.getData().get(i).getCatFullName());
                schoolName.setCat_short_name(categoryRegistration.getData().get(i).getCatShortName());
                schoolCategories.add(schoolName);
            }
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SRegistration.this,
                    android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    categoriesText = categories.get(position);
                    selectedShortName = schoolCategories.get(position).getCat_short_name();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}