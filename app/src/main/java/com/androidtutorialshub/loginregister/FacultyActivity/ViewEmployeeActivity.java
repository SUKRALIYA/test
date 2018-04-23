package com.androidtutorialshub.loginregister.FacultyActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.activities.MainActivity;
import com.androidtutorialshub.loginregister.activities.RequestHandler;
        import com.androidtutorialshub.loginregister.model.Data;
        import com.androidtutorialshub.loginregister.model.Employee;
import com.google.gson.Gson;
        import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewEmployeeActivity extends AppCompatActivity  {
    List<Data> dataList;
    FacultyAdapter facultyAdapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_view_employee);

        searchView=(SearchView)findViewById(R.id.searchView);
        searchView.setQueryHint("Please Type here");

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dataList = new ArrayList<>();
        facultyAdapter = new FacultyAdapter(dataList, getApplicationContext());
        recyclerView.setAdapter(facultyAdapter);

        TextView backPage=(TextView)findViewById(R.id.backPage);
        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (facultyAdapter != null) facultyAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataList.clear();
        facultyAdapter.notifyDataSetChanged();
        String sluid="";
        new ViewEmployee().execute(sluid); // ye pahle call hoga ya se koi value ni ya se jayega "sluid"

    }

    class ViewEmployee extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            }

        @Override
        protected String doInBackground(String... voids) { // yaha void mai aayega "sluid".. naki iski value..
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();
            //creating request parameters
            SharedPreferences sharedPreferences = getSharedPreferences("SchoolData", MODE_PRIVATE);
            String sluid = sharedPreferences.getString("sluid", null); //  ya ye wali

            //returing the response
//
             return requestHandler.sendGetRequest("http://xenottabyte.in/Xenotapp/school_api.php?sluid="+sluid+ "&ACTION=viewEmployee");// ya ha void mai jayega "sluid " naki koi vlue..
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            Employee employee = gson.fromJson(s,Employee.class);
            dataList.addAll(employee.getData());
            facultyAdapter.notifyDataSetChanged();
        }
    }
}
