package com.aakash.sptbi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Students_Fragment extends Fragment {
    private static final String TAG = "Students_Fragment";

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("sptbiapp");
    ArrayList<String> startup_details=new ArrayList<>();
    List<Management_Decision> item=new ArrayList<Management_Decision>();
    ListView studentList;
    ListViewAdapters adapter;

    Button btn_click_me_2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container,false);


        adapter = new ListViewAdapters(getActivity(), R.layout.colmn_row,item);
        studentList = (ListView)view.findViewById(R.id.student_listview);









        myRef.child("student").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<DataSnapshot> uniques = new ArrayList<>();
                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {

                    uniques.add(uniqueKeySnapshot);

                }


                Collections.sort(uniques, new Comparator<DataSnapshot>() {   //sorting
                    public int compare(DataSnapshot c1, DataSnapshot c2) {
                        if (("" + c1.child("Name").getValue()).compareToIgnoreCase("" + c2.child("Name").getValue()) < 0)
                            return -1;
                        if (("" + c1.child("Name").getValue()).compareToIgnoreCase("" + c2.child("Name").getValue()) > 0)
                            return 1;
                        return 0;
                    }
                });


                for (DataSnapshot uniqueKeySnapshot : uniques) {


                    //        l.addView(b);

                    if(!("" + uniqueKeySnapshot.child("status").getValue()).equalsIgnoreCase("Accepted")) {
                        //    startup_details.add("" + uniqueKeySnapshot.child("Company_Name").getValue());
                        //  startup_details.add("" + uniqueKeySnapshot.child("Email").getValue());
                        String email=" "+ uniqueKeySnapshot.child("Email").getValue();
                        String key_email=email.replace(".",",");
                        key_email=key_email.replace("@","");
                        item.add(new Management_Decision("" + uniqueKeySnapshot.child("Name").getValue(),key_email.trim(),key_email.trim(),"student"));
                        //   adapter.add(new Management_Decision(startup,"Accept","Reject"));
                        studentList.setAdapter(adapter);

                    }

                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //  Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });


        return view;
    }
}
