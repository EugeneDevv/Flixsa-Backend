package com.eujoh.uoeadminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.eujoh.uoeadminapp.ui.UserDashboard;
import com.google.android.material.textfield.TextInputEditText;

public class PassCode extends AppCompatActivity {

    TextInputEditText passCodeeditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pass_code);
        passCodeeditText = findViewById(R.id.ed_pass_code);
    }

    public void callUserDashBoard(View view) {
        final String passCode = passCodeeditText.getText().toString().trim();
        if (TextUtils.isEmpty(passCode)) {
            passCodeeditText.setError("Field cant be empty");
            passCodeeditText.requestFocus();
            return;
//        } else if (passCode != "1531"){
//            passCodeeditText.setError("Wrong PIN!");
//            passCodeeditText.requestFocus();
        } else {
            startActivity(new Intent(this, UserDashboard.class));
            finish();
        }
    }
}