package com.androidtutorialshub.loginregister.FacultyActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.activities.RequestHandler;
import com.androidtutorialshub.loginregister.model.EmployeeData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class EditDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String EMPLOYEEDATA = "Employee Data";
    private TextInputEditText textInputEditTextEmployeeUid;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextCurrentAddress;
    private TextInputEditText textInputEditTextPreviousAddress;
    private TextInputEditText textInputEditTextPhoneNumber;
    private TextInputEditText textInputEditTextStatus;
    private TextInputEditText textInputEditTextJoinDate;
    private TextInputEditText textInputEditTextPastWork;
    private TextView textInputEditTextExperience;
    private TextInputEditText textInputEditTextSchoolId;
    TextView textInputEditTextCertification, textInputEditTextQualification, textInputEditTextSubject;
    private RadioButton radioMale, radioFemale, radioUnmarried, radiomarried;
    private RadioGroup gender, mariatalStatus;
    private AppCompatButton appCompatButtonSubmit;
    private TextInputEditText textInputEditTextDOB;
    private String genderText = "";
    private String mariatalStatusText = "";
    private String categoriesText = "", categoriesText1 = "", categoriesText2 = "", categoriesText3 = "";
    public static final String DATA = "Data";
    private EmployeeData employeeData;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_details);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        //find all edit text //
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);
        radioUnmarried = (RadioButton) findViewById(R.id.radioUnmarried);
        radiomarried = (RadioButton) findViewById(R.id.radioMarried);
        textInputEditTextName = (TextInputEditText)findViewById(R.id.textInputEditTextName);
        textInputEditTextSchoolId = (TextInputEditText) findViewById(R.id.textInputEditTextSchoolId);
        textInputEditTextEmployeeUid = (TextInputEditText) findViewById(R.id.textInputEditTextEmployeeUid);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextCurrentAddress = (TextInputEditText) findViewById(R.id.textInputEditTextCurrentAddress);
        textInputEditTextPreviousAddress = (TextInputEditText) findViewById(R.id.textInputEditTextPreviousAddress);
        textInputEditTextPhoneNumber = (TextInputEditText) findViewById(R.id.textInputEditTextPhoneNumber);
        textInputEditTextJoinDate = (TextInputEditText) findViewById(R.id.textInputEditTextJoinDate);
        textInputEditTextPastWork = (TextInputEditText) findViewById(R.id.textInputEditTextPastWork);
        textInputEditTextDOB = (TextInputEditText) findViewById(R.id.textInputEditTextDOB);
        textInputEditTextStatus = (TextInputEditText) findViewById(R.id.textInputEditTextStatus);
        textInputEditTextCertification = findViewById(R.id.textInputEditTextCertification);
        textInputEditTextQualification = findViewById(R.id.textInputEditTextQualification);
        textInputEditTextExperience = findViewById(R.id.textInputEditTextExperience);
        textInputEditTextSubject = findViewById(R.id.textInputEditTextSubject);
        gender = (RadioGroup) findViewById(R.id.gender);
        mariatalStatus = (RadioGroup) findViewById(R.id.mariatalStatus);
        appCompatButtonSubmit = (AppCompatButton) findViewById(R.id.appCompatButtonSubmit);
        //------------geting vuale from intent------------------------------//
        if (getIntent() != null) {
            employeeData = getIntent().getParcelableExtra(EMPLOYEEDATA);
        } else {
            finish();
        }
        textInputEditTextEmployeeUid.setText(employeeData.getmFaUid());
        textInputEditTextSchoolId.setText(employeeData.getmSchId());
        textInputEditTextEmail.setText(employeeData.getmFaEmail());
        textInputEditTextPhoneNumber.setText(employeeData.getmFaPhone());
        textInputEditTextStatus.setText(employeeData.getmFaStatus());
        textInputEditTextName.setText(employeeData.getmFaName());
        textInputEditTextCurrentAddress.setText(employeeData.getmFaAddressCurr());
        textInputEditTextPreviousAddress.setText(employeeData.getmFaAddressPar());
        textInputEditTextPastWork.setText(employeeData.getmFaPrevious());
        textInputEditTextJoinDate.setText(employeeData.getmFaJoinDate());
        textInputEditTextDOB.setText(employeeData.getmFaDob());
        textInputEditTextCertification.setText(employeeData.getmFaCertification());
        textInputEditTextQualification.setText(employeeData.getmFaExperience());
        textInputEditTextExperience.setText(employeeData.getmFaExperience());
        textInputEditTextSubject.setText(employeeData.getmFaSubject());

//-------------------------------end-------------------------------//

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioFemale:
                        genderText = "female";
                        break;
                    case R.id.radioMale:
                        genderText = "male";
                        break;
                }
            }
        });
        mariatalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioUnmarried:
                        mariatalStatusText = "unmarried";
                        break;
                    case R.id.radioMarried:
                        mariatalStatusText = "married";
                        break;
                }
            }
        });


        appCompatButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facultySubmittion();
            }
        });
        textInputEditTextJoinDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(EditDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        int month = selectedmonth + 1;
                        textInputEditTextJoinDate.setText(selectedday + "/" + month + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

//----------------start calender code-------------------------------------------------------------------
        textInputEditTextDOB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(EditDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        int month = selectedmonth + 1;
                        textInputEditTextDOB.setText(selectedday + "/" + month + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        textInputEditTextPastWork.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(EditDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        int month = selectedmonth + 1;
                        textInputEditTextPastWork.setText(selectedday + "/" + month + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        //---------------------end calender coedes------------------//
        // Spinner Drop down elements
        //-------------spinner--------------//
        final List<String> categories = new ArrayList<String>();
        categories.add("Please Select");
        categories.add("TED(Diploma in teaching education)");
        categories.add("D.Ed");
        categories.add("B.Ed.(Bachelor in Education)");
        categories.add("B.A");
        categories.add("B.Sc");
        categories.add("MCA");
        categories.add("B.Tech");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

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
        //-------------spinner1--------------//
        final List<String> categories1 = new ArrayList<String>();
        categories1.add("Please Select");
        categories1.add("NTT (Nursery Teacher Training)");
        categories1.add("PTT (Primary Teacher Training)");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);

        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriesText1 = categories1.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //-------------spinner2--------------//
        final List<String> categories2 = new ArrayList<String>();
        categories2.add("Please Select");
        categories2.add("Mathematics");
        categories2.add("SST");
        categories2.add("Computer");
        categories2.add("English");
        categories2.add("Environmental");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriesText2 = categories2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//-------------spinner3--------------//
        final List<String> categories3 = new ArrayList<String>();
        categories3.add("Please Select");
        categories3.add("6 Months");
        categories3.add("1 Year");
        categories3.add("2 Year");
        categories3.add("3 Year");
        categories3.add("4 Year");

        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);

        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriesText3 = categories3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void facultySubmittion() {
        final String sluid = textInputEditTextSchoolId.getText().toString().trim();
        final String emp_uid = textInputEditTextEmployeeUid.getText().toString().trim();
        final String fa_name = textInputEditTextName.getText().toString().trim();
        final String fa_status = textInputEditTextStatus.getText().toString().trim();
        final String fa_gender = genderText.toString().trim();
        final String fa_email = textInputEditTextEmail.getText().toString().trim();
        final String fa_phone = textInputEditTextPhoneNumber.getText().toString().trim();
        final String fa_mariatal_status = mariatalStatusText.toString().trim();
        final String fa_dob = textInputEditTextDOB.getText().toString().trim();
        final String fa_join_date = textInputEditTextJoinDate.getText().toString().trim();
        final String fa_certification = categoriesText1;
        final String fa_experience = categoriesText3;
        final String fa_subject = categoriesText2;
        final String fa_qualification = categoriesText;
        final String fa_previous = textInputEditTextPastWork.getText().toString().trim();
        final String fa_address_par = textInputEditTextPreviousAddress.getText().toString().trim();
        final String fa_address_curr = textInputEditTextCurrentAddress.getText().toString().trim();
        //first we will do the validations
//        if (TextUtils.isEmpty(fa_name)) {
//            textInputEditTextName.setError("Please enter your name");
//            textInputEditTextName.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(emp_uid)) {
//            textInputEditTextEmployeeUid.setError("Please enter your Employee Name");
//            textInputEditTextEmployeeUid.requestFocus();
//            return;
//        }
//        //---------email validation-------//
//        if (TextUtils.isEmpty(fa_email)) {
//            textInputEditTextEmail.setError("Please enter your email");
//            textInputEditTextEmail.requestFocus();
//            return;
//        }
//        if (!isValidEmailId(fa_email)) {
//            textInputEditTextEmail.setError("Please enter valid email");
//            textInputEditTextEmail.requestFocus();
//            return;
//        }
//        //-------password validation-----//
//        if (TextUtils.isEmpty(fa_status)) {
//            textInputEditTextStatus.setError("Please enter Status");
//            textInputEditTextStatus.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(fa_phone)) {
//            textInputEditTextPhoneNumber.setError("Please enter your phone number");
//            textInputEditTextPhoneNumber.requestFocus();
//            return;
//        }
//        if (fa_phone.length()!=10) {
//            textInputEditTextPhoneNumber.setError("Number must be 10 digits");
//            textInputEditTextPhoneNumber.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(fa_address_par)) {
//            textInputEditTextPreviousAddress.setError("Please enter your Previous Address");
//            textInputEditTextPreviousAddress.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(fa_address_curr)) {
//            textInputEditTextCurrentAddress.setError("Please enter your Current Address");
//            textInputEditTextCurrentAddress.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(fa_join_date)) {
//            textInputEditTextJoinDate.setError("Please enter your Joining Date");
//            textInputEditTextJoinDate.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(fa_previous)) {
//            textInputEditTextExperience.setError("Please enter your Previous Qrganization ");
//            textInputEditTextExperience.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(fa_qualification) || fa_qualification.equals("categoriesText3")) {
//            textInputEditTextQualification.setError("Please enter your qualification");
//            textInputEditTextQualification.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(fa_experience) || fa_experience.equals("categoriesText1")) {
//            textInputEditTextExperience.setError("Please enter your experience ");
//            textInputEditTextExperience.requestFocus();
//            return;
//
//        }
//        if (TextUtils.isEmpty(fa_certification) || fa_certification.equals("categoriesText")) {
//            textInputEditTextCertification.setError("Please enter your Certification");
//            textInputEditTextCertification.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(fa_subject) || fa_subject.equals("categoriesText2")) {
//            textInputEditTextSubject.setError("Please enter your Subject");
//            textInputEditTextSubject.requestFocus();
//            return;
//        }
//
//        if (!radioMale.isChecked() && !radioFemale.isChecked()) {
//            Toast.makeText(getApplicationContext(), "Please select gender",
//                    Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(fa_dob)) {
//            textInputEditTextDOB.setError("Please enter your DOB");
//            textInputEditTextDOB.requestFocus();
//            return;
//        }
//
//        if (!radioUnmarried.isChecked() && !radiomarried.isChecked()) {
//            Toast.makeText(getApplicationContext(), "Please select Mariatal Status", Toast.LENGTH_SHORT).show();
//            return;
//        }


        class Faculty extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("sluid", sluid);
                params.put("emp_uid",emp_uid);
                params.put("fa_name", fa_name);
                params.put("fa_status", fa_status);
                params.put("fa_gender", fa_gender);
                params.put("fa_email", fa_email);
                params.put("fa_phone", fa_phone);
                params.put("fa_mariatal_status", fa_mariatal_status);
                params.put("fa_dob", fa_dob);
                params.put("fa_join_date", fa_join_date);
                params.put("fa_experience", fa_experience);
                params.put("fa_qualification", fa_qualification);
                params.put("fa_subject", fa_subject);
                params.put("fa_certification", fa_certification);
                params.put("fa_previous", fa_previous);
                params.put("fa_address_par", fa_address_par);
                params.put("fa_address_curr", fa_address_curr);
                return requestHandler.sendPostRequest("http://xenottabyte.in/Xenotapp/school_api.php?ACTION=UpdateEmployee", params);
            }
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                emptyInputEditText();

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (obj.getString("status").equals("1")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        //getting the user from the response
                        String sluid = obj.getString("sluid");
                        String emp_uid = obj.getString("emp_uid");
                        String fa_name = obj.getString("fa_name");
                        String fa_status = obj.getString("fa_status");
                        String fa_gender = obj.getString("fa_gender");
                        String fa_email = obj.getString("fa_email");
                        String fa_phone = obj.getString("fa_phone");
                        String fa_mariatal_status = obj.getString("fa_mariatal_status");
                        String fa_dob = obj.getString("fa_dob");
                        String fa_join_date = obj.getString("fa_join_date");
                        String fa_experience = obj.getString("fa_experience");
                        String fa_qualification = obj.getString("fa_qualification");
                        String fa_subject = obj.getString("fa_subject");
                        String fa_certification = obj.getString("fa_certification");
                        String fa_previous = obj.getString("fa_previous");
                        String fa_address_par = obj.getString("fa_address_par");
                        String fa_address_cur = obj.getString("fa_address_cur");
                         // puting data in intent////
                        Intent intent = new Intent(getApplicationContext(),EditDetailsActivity.class);
                        intent.putExtra("sluid",sluid);
                        intent.putExtra("emp_uid",emp_uid);
                        intent.putExtra("fa_name",fa_name);
                        intent.putExtra("fa_status",fa_status);
                        intent.putExtra("fa_gender",fa_gender);
                        intent.putExtra("fa_email",fa_email);
                        intent.putExtra("fa_phone",fa_phone);
                        intent.putExtra("fa_mariatal_status",fa_mariatal_status);
                        intent.putExtra("fa_dob",fa_dob);
                        intent.putExtra("fa_join_date",fa_join_date);
                        intent.putExtra("fa_experience",fa_experience);
                        intent.putExtra("fa_qaulification",fa_qualification);
                        intent.putExtra("fa_subject",fa_subject);
                        intent.putExtra("fa_certification",fa_certification);
                        intent.putExtra("fa_previous",fa_previous);
                        intent.putExtra("fa_address_par",fa_address_par);
                        intent.putExtra("fa_address_cur",fa_address_cur);
                        startActivity(intent);
                        finish();
                        //---------------------end-----------------//
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid School Id and Password", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        //executing the async task
        Faculty fac = new Faculty();
        fac.execute();
    }

    private void emptyInputEditText() {
        textInputEditTextEmployeeUid.setText(null);
        textInputEditTextName.setText(null);
        textInputEditTextStatus.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPhoneNumber.setText(null);
        textInputEditTextDOB.setText(null);
        textInputEditTextJoinDate.setText(null);
        textInputEditTextDOB.setText(null);
        textInputEditTextJoinDate.setText(null);
        textInputEditTextPastWork.setText(null);
        textInputEditTextPreviousAddress.setText(null);
        textInputEditTextCurrentAddress.setText(null);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private boolean isValidEmailId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


}
