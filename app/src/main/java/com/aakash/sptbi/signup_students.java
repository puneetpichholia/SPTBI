package com.aakash.sptbi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class signup_students extends Fragment {
    private static final String TAG = "signup_students_fragment";
    EditText et_signup_student_name;
    EditText et_signup_student_contact;
    EditText et_signup_student_branch;
    EditText et_signup_student_email;
    EditText et_signup_student_reg_id;
    EditText et_signup_student_Password;
    Button btn_student_signup;
    private FirebaseAuth mAuth;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("sptbiapp");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_students, container,false);

        btn_student_signup = (Button)view.findViewById(R.id.signup_students_btn);
        et_signup_student_name=(EditText)view.findViewById(R.id.student_name);
        et_signup_student_branch=(EditText)view.findViewById(R.id.student_branch);
        et_signup_student_contact=(EditText)view.findViewById(R.id.student_contact);
        et_signup_student_email=(EditText)view.findViewById(R.id.student_email);
        et_signup_student_reg_id=(EditText)view.findViewById(R.id.student_registration);
        et_signup_student_Password=(EditText)view.findViewById(R.id.student_password);
        btn_student_signup.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                String name = et_signup_student_name.getText().toString();
                String branch = et_signup_student_branch.getText().toString();
                String contact = et_signup_student_contact.getText().toString();
                String email = et_signup_student_email.getText().toString();
                String reg_id = et_signup_student_reg_id.getText().toString();
                String password = et_signup_student_Password.getText().toString();

                register_user(name,email,contact,branch,"","Not Yet Accepted","",password,reg_id);
            }
        });
        return view;
    }

    private void register_user(String name,String Email,String Contact,String Branch,String request,String status,String placement, final String password,String reg_id) {



            writeNewUser(Email,"student",password);
        studentdetails(name,Email,Contact,Branch,"","Not Yet Accepted","",password,reg_id);
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
        Intent i=new Intent(getActivity(),MainActivity.class);
        startActivity(i);





    }



    private void studentdetails(String name,String Email,String Contact,String Branch,String request,String status,String placement, final String password,String reg_id) {
        //    Users user = new Users(email, type, password);
        String key_email=Email.replace(".",",");
        key_email=key_email.replace("@","");
        //  String key = myRef.child("users").child(email).push().getKey();
        Student student = new Student(name,Email,Contact,Branch,request,status,placement,reg_id);




        // Map<String, Users> users = new HashMap<>();
        //users.put(""+email, user);

        // myRef.child("users").child(""+key_email).setValue(user);

        Map<String, Object> studentValues = student.toMap();
        //Toast.makeText(getActivity().getApplicationContext(),""+Email,Toast.LENGTH_SHORT).show();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("student/"+key_email , studentValues);


        myRef.updateChildren(childUpdates);






    }
}