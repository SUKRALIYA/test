package com.androidtutorialshub.loginregister.FacultyActivity;


import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.SearchView;

        import com.androidtutorialshub.loginregister.R;
        import com.androidtutorialshub.loginregister.activities.RequestHandler;
        import com.androidtutorialshub.loginregister.model.Data;
        import com.androidtutorialshub.loginregister.model.Employee;
        import com.google.gson.Gson;
        import java.util.ArrayList;
        import java.util.List;

public class ViewFacultyActivity extends AppCompatActivity {
    List<Data> dataList;
    FacultyAdapter facultyAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);
        searchView=(SearchView)findViewById(R.id.searchView);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataList = new ArrayList<>();
        facultyAdapter = new FacultyAdapter(dataList, getApplicationContext());
        recyclerView.setAdapter(facultyAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        dataList.clear();
        facultyAdapter.notifyDataSetChanged();
        new RegisterUser().execute();

    }

    class RegisterUser extends AsyncTask<Void, Void, String> {

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
             return requestHandler.sendGetRequest("http://xenottabyte.in/Xenotapp/school_api.php?sluid=XGI43382&ACTION=viewEmployee");
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
