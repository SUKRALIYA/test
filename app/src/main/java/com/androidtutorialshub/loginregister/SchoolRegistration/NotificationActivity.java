package com.androidtutorialshub.loginregister.SchoolRegistration;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.DatePicker;

import com.androidtutorialshub.loginregister.R;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText textInputEditTextJoinDate,textInputEditTextAffiliationBoard,textInputEditTextAffiliationNumber
    ,textInputEditTextAffiliationUpto,textInputEditTextSchoolStatus,textInputEditTextHeadName,textInputEditTextLandlineNumber
    ,textInputEditTextFoundDate,textInputEditTexAddress;
    private AppCompatButton appCompatButtonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initViews();
        initListeners();
    }
    private void initViews(){
        textInputEditTextJoinDate=(TextInputEditText)findViewById(R.id.textInputEditTextJoinDate);
        textInputEditTextAffiliationBoard=(TextInputEditText)findViewById(R.id.textInputEditTextAffiliationBoard);
        textInputEditTextAffiliationNumber=(TextInputEditText)findViewById(R.id.textInputEditTextAffiliationNumber);
        textInputEditTextAffiliationUpto=(TextInputEditText)findViewById(R.id.textInputEditTextAffiliationUpto);
        textInputEditTextSchoolStatus=(TextInputEditText)findViewById(R.id.textInputEditTextSchoolStatus);
        textInputEditTextFoundDate=(TextInputEditText)findViewById(R.id.textInputEditTextFoundDate);
        textInputEditTextHeadName=(TextInputEditText)findViewById(R.id.textInputEditTextHeadName);
        textInputEditTextLandlineNumber=(TextInputEditText)findViewById(R.id.textInputEditTextLandlineNumber);
        textInputEditTexAddress=(TextInputEditText)findViewById(R.id.textInputEditTexAddress);
        appCompatButtonSubmit=(AppCompatButton) findViewById(R.id.appCompatButtonSubmit);
    }
   private void initListeners(){


       textInputEditTextAffiliationUpto.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               //To show current date in the datepicker
               Calendar mcurrentDate = Calendar.getInstance();
               int mYear = mcurrentDate.get(Calendar.YEAR);
               int mMonth = mcurrentDate.get(Calendar.MONTH);
               int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

               DatePickerDialog mDatePicker = new DatePickerDialog(NotificationActivity.this, new DatePickerDialog.OnDateSetListener() {
                   public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                       // TODO Auto-generated method stub
                       /*      Your code   to get date and time    */
                       int month = selectedmonth + 1;
                       textInputEditTextAffiliationUpto.setText(selectedday + "/" + month + "/" + selectedyear);
                   }
               },mYear, mMonth, mDay);
               mDatePicker.setTitle("Select date");
               mDatePicker.show();  }
       });
       textInputEditTextFoundDate.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               //To show current date in the datepicker
               Calendar mcurrentDate = Calendar.getInstance();
               int mYear = mcurrentDate.get(Calendar.YEAR);
               int mMonth = mcurrentDate.get(Calendar.MONTH);
               int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

               DatePickerDialog mDatePicker = new DatePickerDialog(NotificationActivity.this, new DatePickerDialog.OnDateSetListener() {
                   public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                       // TODO Auto-generated method stub
                       /*      Your code   to get date and time    */
                       int month = selectedmonth + 1;
                       textInputEditTextFoundDate.setText(selectedday + "/" + month + "/" + selectedyear);
                   }
               },mYear, mMonth, mDay);
               mDatePicker.setTitle("Select date");
               mDatePicker.show();  }
       });

   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
         case R.id.appCompatButtonSubmit:
             schoolFiled();
            break;
        }

    }
    private void schoolFiled(){


    }
}
