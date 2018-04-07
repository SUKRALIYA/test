package com.androidtutorialshub.loginregister.FacultyActivity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;

public class ViewDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        TextView textView=(TextView)findViewById(R.id.textViewName);
        TextView textView1=(TextView)findViewById(R.id.textViewId);

        SharedPreferences sharedPreferences = getSharedPreferences("SchoolData", MODE_PRIVATE);
        String fa_name=sharedPreferences.getString("fa_name",null);
        String sluid = sharedPreferences.getString("sluid", null);
        textView.setText(fa_name);
        textView1.setText(sluid);
    }
}
