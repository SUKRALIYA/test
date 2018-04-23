package com.androidtutorialshub.loginregister.FacultyActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.activities.RequestHandler;
import com.androidtutorialshub.loginregister.model.Data;
import com.androidtutorialshub.loginregister.model.EmployeeData;

public class ViewDetailsActivity extends AppCompatActivity {
    public static final String DATA = "Data";
    public static final String EMPLOYEEDATA = "Employee Data";
    private Data data;
    private EmployeeData employeeData;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        if (getIntent() != null) {
            data = getIntent().getParcelableExtra(DATA);
        } else {
            finish();
        }
        TextView textView = (TextView) findViewById(R.id.textViewName);
        TextView textView1 = (TextView) findViewById(R.id.textViewId);
        TextView textViewName = (TextView) findViewById(R.id.tvName);
        TextView fa_uid = (TextView) findViewById(R.id.tvUid);
        TextView sch_id = (TextView) findViewById(R.id.tvId);
        TextView fa_email = (TextView) findViewById(R.id.tvEmail);
        TextView fa_phone = (TextView) findViewById(R.id.tvPhone);
        TextView fa_status = (TextView) findViewById(R.id.tvStatus);
        TextView last_update_date = (TextView) findViewById(R.id.tvLastUpdateDate);
        TextView fa_eventdate = (TextView) findViewById(R.id.tvEventDate);
        TextView fa_map_class = (TextView) findViewById(R.id.tvClass);
        TextView fa_map_section = (TextView) findViewById(R.id.tvSection);
        fa_uid.setText(data.getFaUid());
        sch_id.setText(data.getSchId());
        fa_email.setText(data.getFaEmail());
        fa_phone.setText(data.getFaPhone());
        fa_status.setText(data.getFaStatus());
        last_update_date.setText(data.getLastUpdateDate());
        fa_eventdate.setText(data.getFaEventdate());
        fa_map_class.setText(data.getFaMapClass());
        fa_map_section.setText(data.getFaMapSection());
        textView.setText(data.getFaName());
        textView1.setText(data.getSchId());
        textViewName.setText(data.getFaName());

        Button button2 = (Button) findViewById(R.id.EditView);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditDetailsActivity.class);
                intent.putExtra(EMPLOYEEDATA, employeeData);
                startActivity(intent);
                EditEmployeeInfo editEmployeeInfo=new EditEmployeeInfo();
                editEmployeeInfo.execute();
            }
        });

        Button button1 = (Button) findViewById(R.id.backView);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewEmployeeActivity.class);
                startActivity(intent);
            }
        });
        Button button = findViewById(R.id.delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });

    }

    private AlertDialog AskOption() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                //set message, title, and icon
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        new DeleteEmployee().execute();
                    }

                })

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }


    class DeleteEmployee extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            //returing the response
            return requestHandler.sendGetRequest("http://xenottabyte.in/Xenotapp/school_api.php?emp_uid=" + data.getFaUid() + "&ACTION=DeleteEmployee");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
    class EditEmployeeInfo extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            //returing the response
            return requestHandler.sendGetRequest("http://xenottabyte.in/Xenotapp/school_api.php?emp_uid="+data.getFaUid()+"&ACTION=GrabEmployee");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

}
