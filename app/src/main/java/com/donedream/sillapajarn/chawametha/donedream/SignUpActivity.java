package com.donedream.sillapajarn.chawametha.donedream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    //explicit
    private EditText userEditText, passwordEditText,
            nameEditText, emailEditText;
    private DatePicker birthDatePicker;
    private Spinner countrySpinner;
    private String userString, passwordString, nameString, emailString,
            birthString, countryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //blind widget
        bindWidget();

        //create spinner
        createSpinner();

    }   //main method

    private void createSpinner() {

        final String[] countryStrings = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, countryStrings);
        countrySpinner.setAdapter(stringArrayAdapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryString = countryStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                countryString = countryStrings[0];
            }
        });


    }   //create spinner

    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText5);
        emailEditText = (EditText) findViewById(R.id.editText6);
        birthDatePicker = (DatePicker) findViewById(R.id.datePicker);
        countrySpinner = (Spinner) findViewById(R.id.spinner);


    }   //blind widget

    public void clickSaveData(View view) {

        //get value from edittext
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();

        //check complete
        if (checkSpace()) {
            //have space
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(SignUpActivity.this, "Alert!", "Please, fill in all forms");

        } else {
            // no space
            gerValueFromDatePicker();

            //confirm data
            confirmData();


        }   //if


    }   //click save data (sign up)

    private void confirmData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_myaccount);
        builder.setTitle("Confirm Data");
        builder.setMessage(getResources().getString(R.string.user) + userString + "\n" +
                getResources().getString(R.string.pass) + passwordString + "\n" +
                getResources().getString(R.string.name) + nameString + "\n" +
                getResources().getString(R.string.email) + emailString + "\n" +
                getResources().getString(R.string.country) + countryString + "\n" +
                getResources().getString(R.string.birth) + birthString);
        builder.setCancelable(false);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                updateValueToMySQL();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();


    }   //confirm data

    private void updateValueToMySQL() {

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);


        try {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            nameValuePairs.add(new BasicNameValuePair(MyManage.column_User, userString));
            nameValuePairs.add(new BasicNameValuePair(MyManage.column_Password, passwordString));
            nameValuePairs.add(new BasicNameValuePair(MyManage.column_Name, nameString));
            nameValuePairs.add(new BasicNameValuePair(MyManage.column_Email, emailString));
            nameValuePairs.add(new BasicNameValuePair(MyManage.column_Birth, birthString));
            nameValuePairs.add(new BasicNameValuePair(MyManage.column_Country, countryString));
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://swiftcodingthai.com/dream/php_add_user_kant.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpClient.execute(httpPost);

            Toast.makeText(SignUpActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, "Cannot Upadate to Database", Toast.LENGTH_SHORT).show();
        }

    }   //update value to mySQL

    private void gerValueFromDatePicker() {

        int intDay = birthDatePicker.getDayOfMonth();
        int intMonth = birthDatePicker.getMonth() + 1; //1==jan
        int intYear = birthDatePicker.getYear();

        ///Log.d("25Feb", "birth ==> " + intDay + "/" + intMonth + "/" + intYear);

        birthString = Integer.toString(intDay) + "/" +
                Integer.toString(intMonth) + "/" +
                Integer.toString(intYear);

    }   // ger value from date picker

    private boolean checkSpace() {

        boolean bolSpace = true; // have space

        bolSpace = userEditText.equals("") || passwordString.equals("") ||
                nameString.equals("") || emailString.equals("");

        return bolSpace;
    }

}   //main class
