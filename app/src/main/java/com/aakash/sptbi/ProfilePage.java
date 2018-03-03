package com.aakash.sptbi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {


    private Button btn_profile_page_logout;
String Company_Name,Ceo_Name,Contact_No,Email_id,request_status,request,status,user_profile,type,Name,reg_id,Branch;
TextView Ceo,Contact,Email,startup_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Ceo = (TextView) findViewById(R.id.tv_profile_ceo);
        Contact = (TextView) findViewById(R.id.tv_profile_contact);
        Email = (TextView) findViewById(R.id.tv_profile_email);
        startup_name = (TextView) findViewById(R.id.startup_name);
        btn_profile_page_logout = (Button) findViewById(R.id.btn_profile_page_logout);
        btn_profile_page_logout.setOnClickListener(this);

        Intent i = getIntent();
        user_profile = i.getStringExtra("User_Profile");
        if (user_profile.equalsIgnoreCase("Yes")) {
            btn_profile_page_logout.setVisibility(View.VISIBLE);
            type = i.getStringExtra("type");




            if (type.equals("student")) {
                Name = i.getStringExtra("Name");
                Email_id = i.getStringExtra("Email");
                Branch = i.getStringExtra("Branch");
                Contact_No = i.getStringExtra("Contact");
                request = i.getStringExtra("request");
                status = i.getStringExtra("status");
                reg_id = i.getStringExtra("reg_id");

                startup_name.setText(Name + "\n" + reg_id);
                Ceo.setText(Branch);
                Contact.setText(Contact_No);
                Email.setText(Email_id);
            }
            else
            {
                Company_Name = i.getStringExtra("Company_Name");
                Ceo_Name = i.getStringExtra("Ceo");
                Contact_No = i.getStringExtra("Contact");
                Email_id = i.getStringExtra("Email");
                request_status = i.getStringExtra("request_status");
                request = i.getStringExtra("request");
                status = i.getStringExtra("status");

                Ceo.setText(Ceo_Name);
                Contact.setText(Contact_No);
                Email.setText(Email_id);
                startup_name.setText(Company_Name);
            }
        }
else {
            Company_Name = i.getStringExtra("Company_Name");
            Ceo_Name = i.getStringExtra("Ceo");
            Contact_No = i.getStringExtra("Contact");
            Email_id = i.getStringExtra("Email");
            request_status = i.getStringExtra("request_status");
            request = i.getStringExtra("request");
            status = i.getStringExtra("status");

            Ceo.setText(Ceo_Name);
            Contact.setText(Contact_No);
            Email.setText(Email_id);
            startup_name.setText(Company_Name);
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.btn_profile_page_logout:
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(ProfilePage. this,MainActivity.class);
                startActivity(i);
                break;
        }
    }
}
