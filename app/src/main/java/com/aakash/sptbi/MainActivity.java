package com.aakash.sptbi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,AdapterView.OnItemSelectedListener{
    private Button signin,signup;
    EditText et_email,et_password;
    private TextView tv_forgotpassword;

    private static final String[]paths = {"I Am Student", "I Am Startup"};
    private FirebaseAuth mAuth;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("sptbiapp");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

     signin=(Button)findViewById(R.id.button_login);
     signup=(Button)findViewById(R.id.button_signup);
        mAuth = FirebaseAuth.getInstance();
     tv_forgotpassword=(TextView)findViewById(R.id.tv_forgotpassword);


        et_email = (EditText)findViewById(R.id.et_mainactivity_email);
        et_password = (EditText)findViewById(R.id.et_mainactivity_password);


     signin.setOnClickListener(this);
     signup.setOnClickListener(this);
     tv_forgotpassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.button_login:

                final String email = et_email.getText().toString();
               final String password = et_password.getText().toString();
               String key_email=email.replace(".",",");
                key_email=key_email.replace("@","");
               final String new_key_email=key_email;
                myRef.child("users").child(key_email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Users user = dataSnapshot.getValue(Users.class);
                        assert user !=null;

                        if(dataSnapshot.exists() && password!=null) {

                            if(password.equals(user.getPassword())) {

                                String type = user.getType();

                                if(type.equals("startup")) {

                                    myRef.child("startup").child(new_key_email).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot1) {

                                            if(dataSnapshot1.exists()) {

                                                Startup startup = dataSnapshot1.getValue(Startup.class);;
                                                String status = startup.getStatus();
                                                Intent i=null;
                                      //          Toast.makeText(getApplicationContext(), ""+new_key_email+" Status:"+startup.getCompanyName(), Toast.LENGTH_SHORT).show();
                                                if(status!=null) {
                                                    if (status.equalsIgnoreCase("Accepted"))
                                                        i = new Intent(MainActivity.this, HomePage.class);


                                                        else
                                                        i = new Intent(MainActivity.this, Pending.class);

                                                }
                                                else
                                                    i = new Intent(MainActivity.this, Pending.class);

                                                i.putExtra("type", "startup");
                                                i.putExtra("email", email);
                                                startActivity(i);
                                            }
                                        }
                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                } else if(type.equals("student")) {



                                    myRef.child("student").child(new_key_email).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot1) {

                                            if(dataSnapshot1.exists()) {

                                                Student student = dataSnapshot1.getValue(Student.class);;
                                                String status = student.getStatus();
                                                Intent i=null;
                                                //          Toast.makeText(getApplicationContext(), ""+new_key_email+" Status:"+startup.getCompanyName(), Toast.LENGTH_SHORT).show();
                                                if(status!=null) {
                                                    if (status.equalsIgnoreCase("Accepted"))
                                                        i = new Intent(MainActivity.this, HomePage.class);



                                                    else
                                                        i = new Intent(MainActivity.this, Pending.class);

                                                }
                                                else
                                                    i = new Intent(MainActivity.this, Pending.class);

                                                i.putExtra("type", "student");
                                                i.putExtra("email", email);
                                                startActivity(i);
                                            }
                                        }
                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });






                                }
                                else if(type.equalsIgnoreCase("Manager"))
                                {
                                    Intent i = new Intent(MainActivity.this, Management.class);
                                    i.putExtra("type", "student");
                                    i.putExtra("email", email);
                                    startActivity(i);

                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });







                /*  mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   Intent mainintent = new Intent(MainActivity.this, HomePage.class);
                                   startActivity(mainintent);
                                } else {
                                    Toast.makeText(MainActivity.this," Try Again !!", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });*/




                break;

            case R.id.button_signup:
                Intent j = new Intent(MainActivity.this,SignUp.class);
                startActivity(j);
                break;

            case R.id.tv_forgotpassword:
                Intent k = new Intent(MainActivity.this,ForgotPassword.class);
                startActivity(k);
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
