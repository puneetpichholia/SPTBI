package com.aakash.sptbi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class signup_startup extends Fragment {
    private static final String TAG = "signup_startup_fragment";

    EditText et_signup_startup_company;
    EditText et_signup_startup_contact;
    EditText et_signup_startup_ceo;
    EditText et_signup_startup_email;
    EditText et_signup_startup_password;
    Button btn_startup_signup;
    private FirebaseAuth mAuth;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("sptbiapp");
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_startup, container, false);
        mAuth = FirebaseAuth.getInstance();
        btn_startup_signup = (Button) view.findViewById(R.id.signup_startup_btn);
        et_signup_startup_ceo = (EditText) view.findViewById(R.id.et_signup_startup_ceo);
        et_signup_startup_company = (EditText) view.findViewById(R.id.et_signup_startup_company);
        et_signup_startup_contact = (EditText) view.findViewById(R.id.et_signup_startup_contact);
        et_signup_startup_email = (EditText) view.findViewById(R.id.et_signup_startup_email);
        et_signup_startup_password = (EditText) view.findViewById(R.id.et_signup_startup_password);

        btn_startup_signup.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                String ceo = et_signup_startup_ceo.getText().toString();
                String company = et_signup_startup_company.getText().toString();
                String contact = et_signup_startup_contact.getText().toString();
                String email = et_signup_startup_email.getText().toString();
                String password = et_signup_startup_password.getText().toString();

                register_user(email, password,ceo,company,contact);
            }
        });
        return view;
    }

    private void register_user(String email, final String password,String CEO,String Company,String Contact) {



             writeNewUser(email,"startup",password);
             startupdetails(Company,email,Contact,CEO,"","No Response","Not Yet Accepted");
        Intent i=new Intent(getActivity(),MainActivity.class);
        startActivity(i);




     /*   mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) signup_startup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i=new Intent(signup_startup.this.getActivity() ,.class);
                            startActivity(i);
                        }else {
                            Intent i=new Intent(signup_startup.this.getActivity() ,MainActivity.class);
                            startActivity(i);
                        }


                    }
                });*/
    }


    private void writeNewUser(String email, String type, String password) {
    //    Users user = new Users(email, type, password);
String key_email=email.replace(".",",");
key_email=key_email.replace("@","");
      //  String key = myRef.child("users").child(email).push().getKey();
        Users user = new Users(email, type,password);




       // Map<String, Users> users = new HashMap<>();
        //users.put(""+email, user);

       // myRef.child("users").child(""+key_email).setValue(user);

        Map<String, Object> userValues = user.toMap();
Toast.makeText(getActivity().getApplicationContext(),""+email,Toast.LENGTH_SHORT).show();
         Map<String, Object> childUpdates = new HashMap<>();
         childUpdates.put("users/"+key_email , userValues);


        myRef.updateChildren(childUpdates);
        Toast.makeText(this.getActivity().getApplicationContext(),""+myRef,Toast.LENGTH_SHORT).show();





    }




    private void startupdetails(String Company_Name,String Email,String Contact,String CEO,String requests,String status,String request_status) {
        //    Users user = new Users(email, type, password);
        String key_email=Email.replace(".",",");
        key_email=key_email.replace("@","");
        //  String key = myRef.child("users").child(email).push().getKey();
        Startup startup = new Startup(Company_Name, Email,Contact,CEO,requests,status,request_status);




        // Map<String, Users> users = new HashMap<>();
        //users.put(""+email, user);

        // myRef.child("users").child(""+key_email).setValue(user);

        Map<String, Object> startupValues = startup.toMap();
        Toast.makeText(getActivity().getApplicationContext(),""+Email,Toast.LENGTH_SHORT).show();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("startup/"+key_email , startupValues);


        myRef.updateChildren(childUpdates);






    }
}