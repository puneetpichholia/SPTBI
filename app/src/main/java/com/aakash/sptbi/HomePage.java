package com.aakash.sptbi;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    Button button_askhelp,button_profile;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("sptbiapp");
    String email,type;
    ListView list;
    GridLayout l;
    ArrayList<String> profile=new ArrayList<>();
    ArrayList<String> startup_details=new ArrayList<>();
    ArrayList<String> company_list=new ArrayList<>();
    ArrayList<String> temp_company_list;
    ArrayAdapter<String> itemsAdapter;
    LinearLayout l_body,l_listview;
    HashMap<String,StartUp_Details> startups=new HashMap<>();// for search view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        l = (GridLayout) findViewById(R.id.homepage_grid);
        l.setColumnCount(2);

l_body=(LinearLayout)findViewById(R.id.body_linear_layout);
l_listview=(LinearLayout)findViewById(R.id.search_listview_linear_layout);
        SearchView searchView = (SearchView) findViewById(R.id.searchview);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setSubmitButtonEnabled(false);



list=(ListView)findViewById(R.id.listview);











        email=getIntent().getStringExtra("email");
        type=getIntent().getStringExtra("type");
        myRef.child("startup").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                  @Override
                                                                  public void onDataChange(DataSnapshot dataSnapshot) {

                                                                      ArrayList<DataSnapshot> uniques = new ArrayList<>();
                                                                      for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {

                                                                          uniques.add(uniqueKeySnapshot);

                                                                      }


                                                                      Collections.sort(uniques, new Comparator<DataSnapshot>() {   //sorting
                                                                          public int compare(DataSnapshot c1, DataSnapshot c2) {
                                                                              if (("" + c1.child("Company_Name").getValue()).compareToIgnoreCase("" + c2.child("Company_Name").getValue()) < 0)
                                                                                  return -1;
                                                                              if (("" + c1.child("Company_Name").getValue()).compareToIgnoreCase("" + c2.child("Company_Name").getValue()) > 0)
                                                                                  return 1;
                                                                              return 0;
                                                                          }
                                                                      });


                                                                      for (DataSnapshot uniqueKeySnapshot : uniques) {

                                                                          Button b = new Button(HomePage.this);
                                                                          Toast.makeText(getApplicationContext(),""+uniqueKeySnapshot.child("Email").getValue(),Toast.LENGTH_SHORT).show();

                                                                          String CompanyName="" + uniqueKeySnapshot.child("Company_Name").getValue();
                                                                          String Ceo=""+uniqueKeySnapshot.child("Ceo").getValue();
                                                                          String Contact=""+"" + uniqueKeySnapshot.child("Contact").getValue();
                                                                          String Email=""+uniqueKeySnapshot.child("Email").getValue();
                                                                          String request_status="" + uniqueKeySnapshot.child("request_status").getValue();
                                                                          String request="" + uniqueKeySnapshot.child("request").getValue();
                                                                          String status="" + uniqueKeySnapshot.child("status").getValue();



                                                                          b.setText("" + uniqueKeySnapshot.child("Company_Name").getValue());
                                                                          //        l.addView(b);

                                                                          b.setTag("" + uniqueKeySnapshot.child("Company_Name").getValue());
                                                                          b.setHeight(500);
                                                                          b.setWidth(500);
                                                                          b.setOnClickListener(HomePage.this);
                                                                          l.addView(b);
                                                                          if (("" + uniqueKeySnapshot.child("Email").getValue()).trim().equals(email.trim())) {
                                                                              Toast.makeText(getApplicationContext(), "Accessed!", Toast.LENGTH_SHORT).show();

                                                                              //When Clicked on profile button, details of same startup will be displayed.
                                                                              // We do this using the above condition, so that it makes lesser server calls;
                                                                              profile.add("" + uniqueKeySnapshot.child("Company_Name").getValue());
                                                                              profile.add("" + uniqueKeySnapshot.child("Ceo").getValue());
                                                                              profile.add("" + uniqueKeySnapshot.child("Contact").getValue());
                                                                              profile.add("" + uniqueKeySnapshot.child("Email").getValue());
                                                                              profile.add("" + uniqueKeySnapshot.child("request_status").getValue());
                                                                              profile.add("" + uniqueKeySnapshot.child("request").getValue());
                                                                              profile.add("" + uniqueKeySnapshot.child("status").getValue());
                                                                          }

                                                                          startups.put(("" + uniqueKeySnapshot.child("Company_Name").getValue()).trim(),new StartUp_Details(CompanyName,Ceo,Contact,Email,request_status,request,status));
                                                                          company_list.add("" + uniqueKeySnapshot.child("Company_Name").getValue());

                                                                      }


                                                                  }






            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //  Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });


        if(type.equalsIgnoreCase("student")) {
            String key_email = email.replace(".", ",");
            key_email = key_email.replace("@", "");
            myRef.child("student").child(key_email.trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String Name=""+dataSnapshot.child("Name").getValue();
                    String Email=""+dataSnapshot.child("Email").getValue();
                    String Branch=""+dataSnapshot.child("Branch").getValue();
                    String Contact=""+dataSnapshot.child("Contact").getValue();
                    String request=""+dataSnapshot.child("request").getValue();
                    String status=""+dataSnapshot.child("status").getValue();
                    String reg_id=""+dataSnapshot.child("reg_id").getValue();
                    profile.add(Name);
                    profile.add(Email);
                    profile.add(Branch);
                    profile.add(Contact);
                    profile.add(request);
                    profile.add(status);
                    profile.add(reg_id);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    @Override
    public void onItemClick(AdapterView<?> arg0,View view,
                            int position, long id) {
        String Company_Name = "";

        Company_Name = list.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), Company_Name, Toast.LENGTH_SHORT).show();


        Intent k = new Intent(HomePage.this, ProfilePage.class);
        //Bundle b=new Bundle();
        //DataSnapshot dataSnapshot=new DataSnapshot();
        //dataSnapshot=(DataSnapshot) view.getTag();
        //;
        k.putExtra("Company_Name", startups.get(Company_Name).Company_Name);
        k.putExtra("Ceo", startups.get(Company_Name).Ceo);
        k.putExtra("Contact",startups.get(Company_Name).Contact);
        k.putExtra("Email",startups.get(Company_Name).Email);
        k.putExtra("request_status",startups.get(Company_Name).request_status);
        k.putExtra("request",startups.get(Company_Name).request);
        k.putExtra("status",startups.get(Company_Name).status);

        k.putExtra("User_Profile","No");

        startActivity(k);
    }
});
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length()==0)
                {
                     l_listview.setVisibility(View.INVISIBLE);
                     l_body.setVisibility(View.VISIBLE);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.length()==0)
                {
                    l_listview.setVisibility(View.INVISIBLE);
                    l_body.setVisibility(View.VISIBLE);
                    //   initList(sb);
                }
                else
                {
                     l_listview.setVisibility(View.VISIBLE);
                     l_body.setVisibility(View.INVISIBLE);

                    searchItem(newText.toString());

                }

                return false;

            }




        });
        button_profile=(Button)findViewById(R.id.button_profile);
        button_askhelp=(Button)findViewById(R.id.button_askhhelp);

        button_profile.setOnClickListener(this);
        button_askhelp.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.button_profile:
                Intent i=new Intent(HomePage.this,ProfilePage.class);
                if(type.equalsIgnoreCase("Startup"))
                {
                    Intent k=new Intent(HomePage.this,ProfilePage.class);
                    //Bundle b=new Bundle();
                    //DataSnapshot dataSnapshot=new DataSnapshot();
                    //dataSnapshot=(DataSnapshot) view.getTag();
                    //;

                        k.putExtra("Company_Name", profile.get(0));
                        k.putExtra("Ceo", profile.get(1));
                        k.putExtra("Contact", profile.get(2));
                        k.putExtra("Email", profile.get(3));
                        k.putExtra("request_status", profile.get(4));
                        k.putExtra("request", profile.get(5));
                        k.putExtra("status", profile.get(6));
                        k.putExtra("type","startup");
                        k.putExtra("User_Profile", "Yes");

                        startActivity(k);
                    }
                    else if(type.equalsIgnoreCase("student"))
                    {

                        Intent k=new Intent(HomePage.this,ProfilePage.class);

                        k.putExtra("Name", profile.get(0));
                        k.putExtra("Email", profile.get(1));
                        k.putExtra("Branch", profile.get(2));
                        k.putExtra("Contact", profile.get(3));
                        k.putExtra("request", profile.get(4));
                        k.putExtra("status", profile.get(5));
                        k.putExtra("reg_id", profile.get(6));
                        k.putExtra("type","student");
                        k.putExtra("User_Profile", "Yes");

                        startActivity(k);
                    }

               // startActivity(i);
                break;

            case R.id.button_askhhelp:
                Intent j=new Intent(HomePage.this,AskHelp.class);
                startActivity(j);
                break;

            default:
                Intent k=new Intent(HomePage.this,ProfilePage.class);
               //Bundle b=new Bundle();
               //DataSnapshot dataSnapshot=new DataSnapshot();
               //dataSnapshot=(DataSnapshot) view.getTag();
               ;
                k.putExtra("Company_Name", startups.get((""+view.getTag()).trim()).Company_Name);
                k.putExtra("Ceo", startups.get((""+view.getTag()).trim()).Ceo);
                k.putExtra("Contact",startups.get((""+view.getTag()).trim()).Contact);
                k.putExtra("Email",startups.get((""+view.getTag()).trim()).Email);
                k.putExtra("request_status",startups.get((""+view.getTag()).trim()).request_status);
                k.putExtra("request",startups.get((""+view.getTag()).trim()).request);
                k.putExtra("status",startups.get((""+view.getTag()).trim()).status);

                k.putExtra("User_Profile","No");

                startActivity(k);
        }
    }



public void initList()
{
    temp_company_list=new ArrayList<String>(company_list);
    itemsAdapter =
            new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp_company_list);
    list.setAdapter(itemsAdapter);
}

    public void searchItem(String textToSearch)
    {
        initList();
        for(String item:startup_details)
        {
            String i=item.toString().toLowerCase();
            textToSearch=textToSearch.toLowerCase();
            if(!i.contains(textToSearch))
            {
              temp_company_list.remove(item);
            }
        }
        itemsAdapter.notifyDataSetChanged();
    }
}