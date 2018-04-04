package com.androidtutorialshub.loginregister.SchoolRegistration;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.activities.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ForgetIdActivity extends AppCompatActivity implements View.OnClickListener{
    private AppCompatButton appCompatButtonSend;
    private TextInputEditText textInputEditTextEmailId;
    private TextView backforword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forget_id);
        appCompatButtonSend =(AppCompatButton)findViewById(R.id.appCompatButtonSend);
        textInputEditTextEmailId =(TextInputEditText)findViewById(R.id.textInputEditTextEmailId);
        backforword = (TextView)findViewById(R.id.backforword);
        initListeners();
    }

    private void initListeners(){
        appCompatButtonSend.setOnClickListener(this);
        backforword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonSend:
                forgetuseremail();
                break;
                //it is using for go back//
            case R.id.backforword:
                Intent intent = new Intent(getApplicationContext(),SLoginActivity.class);
                startActivity(intent);
                break;
        }

    }
    private void forgetuseremail(){
        final String forEmailID = textInputEditTextEmailId.getText().toString().trim();

        if (TextUtils.isEmpty(forEmailID)) {
            textInputEditTextEmailId.setError("Please enter user email ID");
            textInputEditTextEmailId.requestFocus();
            return;
        }
        class Forgetpassword extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("forEmailID",forEmailID);
                return requestHandler.sendPostRequest("http://xenottabyte.in/Xenotapp/school_api.php?ACTION=forgetUserID",params);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                emptyInputEditText();
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);
                    //if no error in response
                    if (obj.getString("status").equals("1")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Email Id", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        //executing the async task
        Forgetpassword fp = new Forgetpassword();
        fp.execute();
    }
    private void emptyInputEditText() {
        textInputEditTextEmailId.setText(null);

    }
}
