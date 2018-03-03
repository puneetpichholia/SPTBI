package com.aakash.sptbi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener{

    Button btn_send_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btn_send_email = (Button)findViewById(R.id.btn_send_email);
        btn_send_email.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}