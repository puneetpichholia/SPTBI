package com.aakash.sptbi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aakas on 28-02-2018.
 */

public class Student {

    private String Name;
    private String Contact;
    private String Branch;
    private String Email;
    private String requests;
    private String placement;
    private String status;
    private String applied;
    private String reg_id;

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Student(String Name, String Email, String Contact, String Branch, String requests, String status, String placement,String reg_id) {
        this.Name =Name;
        this.Email=Email;
        this.Contact=Contact;
        this.Branch=Branch;
        this.requests=requests;
        this.status=status;
        this.placement=placement;
        this.reg_id=reg_id;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Email", Email);
        result.put("Contact", Contact);
        result.put("Name", Name);
        result.put("Placement",placement);
        result.put("request",requests);
        result.put("status",status);
        result.put("Branch",Branch);
        result.put("reg_id",reg_id);
        return result;
    }

    public String getName()
    {
        return Name;
    }


    public String getContact()
    {
        return Contact;
    }


    public String getPlacement()
    {
        return placement;
    }


    public String getRequests()
    {
        return requests;
    }




    public String getBranch()
    {
        return Branch;
    }

    public String getStatus()
    {
        return status;
    }
    public String getRegId()
    {
        return reg_id;
    }
}
