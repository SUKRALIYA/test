package com.androidtutorialshub.loginregister.FacultyActivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.activities.RequestHandler;
import com.androidtutorialshub.loginregister.model.Data;

public class ViewDetailsActivity extends AppCompatActivity {
    public static final String DATA ="Data";
    private Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        if(getIntent()!= null){
            data = getIntent().getParcelableExtra(DATA);
        }else {
            finish();
        }
        TextView textView=(TextView)findViewById(R.id.textViewName);
        TextView textView1=(TextView)findViewById(R.id.textViewId);
        TextView textViewName=(TextView)findViewById(R.id.tvName);
        TextView fa_uid=(TextView)findViewById(R.id.tvUid);
        TextView sch_id=(TextView)findViewById(R.id.tvId);
        TextView fa_email=(TextView)findViewById(R.id.tvEmail);
        TextView fa_phone=(TextView)findViewById(R.id.tvPhone);
        TextView fa_status=(TextView)findViewById(R.id.tvStatus);
        TextView last_update_date=(TextView)findViewById(R.id.tvLastUpdateDate);
        TextView fa_eventdate=(TextView)findViewById(R.id.tvEventDate);
        TextView fa_map_class=(TextView)findViewById(R.id.tvClass);
        TextView fa_map_section=(TextView)findViewById(R.id.tvSection);
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
        Button button = findViewById(R.id.delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteEmployee().execute();
            }
        });

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
            return requestHandler.sendGetRequest("http://xenottabyte.in/Xenotapp/school_api" +
                    ".php?emp_uid="+data.getFaUid()+"&ACTION=DeleteEmployee");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            finish();
        }
    }
}
