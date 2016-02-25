package com.donedream.sillapajarn.chawametha.donedream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

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

    }   //main method

    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText5);
        emailEditText = (EditText) findViewById(R.id.editText6);

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
            myAlertDialog.myDialog(SignUpActivity.this, "Have Space", "Please, fill in all forms");

        } else {
            // no space


        }   //if


    }   //click save data (sign up)

    private boolean checkSpace() {

        boolean bolSpace = true; // have space

        bolSpace = userEditText.equals("") || passwordString.equals("") ||
                nameString.equals("") || emailString.equals("");

        return bolSpace;
    }

}   //main class
