package com.androidtutorialshub.loginregister.AddStudent;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.FacultyActivity.AddTeacher;
import com.androidtutorialshub.loginregister.R;

import java.util.ArrayList;
import java.util.List;

public class StudentAddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private AppCompatRadioButton appCompatRadioButton1,appCompatRadioButton;
    private RadioGroup radioGroup;
    private AppCompatButton appCompatButtonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student_add);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
        Spinner spinner2=(Spinner)findViewById(R.id.spinner2);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("");
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
        initViews();

        List<String> categories1 = new ArrayList<String>();
        categories1.add("");
        categories1.add("NTT (Nursery Teacher Training)");
        categories1.add("PTT (Primary Teacher Training)");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories1);

        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);

        List<String> categories2 = new ArrayList<String>();
        categories2.add("");
        categories2.add("Mathematics");
        categories2.add("SST");
        categories2.add("Computer");
        categories2.add("English");
        categories2.add("Environmental");
        categories2.add("Science");
        categories2.add("Hindi");
        categories2.add("Science Lab");
        categories2.add("Games");
        categories2.add("Music");
        categories2.add("Art");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories2);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);
    }
    @SuppressLint("WrongViewCast")
    private void initViews(){
        appCompatRadioButton1=(AppCompatRadioButton)findViewById(R.id.radioMale);
        appCompatRadioButton=(AppCompatRadioButton)findViewById(R.id.radioFemale);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        appCompatButtonSubmit = (AppCompatButton)findViewById(R.id.appCompatButtonSubmit);
        appCompatButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SelectedId = radioGroup.getCheckedRadioButtonId();
                appCompatRadioButton1=(AppCompatRadioButton)findViewById(SelectedId);
                appCompatRadioButton =(AppCompatRadioButton)findViewById(SelectedId);
                Toast.makeText(StudentAddActivity.this,appCompatRadioButton1.getText(),Toast.LENGTH_SHORT).show();
            }
        });
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


    }

