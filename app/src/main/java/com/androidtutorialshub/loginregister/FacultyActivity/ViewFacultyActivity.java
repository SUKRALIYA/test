package com.androidtutorialshub.loginregister.FacultyActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.androidtutorialshub.loginregister.R;

public class ViewFacultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
    }
}
