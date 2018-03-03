package com.aakash.sptbi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aakas on 28-02-2018.
 */

public class Startup {

    private String Company_Name;
    private String Contact;
    private String Ceo;
    private String Email;
    private String requests;
    private String request_status;
    private String status;
    private String applied;
    public Startup() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Startup(String Company_Name,String Email,String Contact,String Ceo,String requests,String status,String request_status) {
        this.Company_Name = Company_Name;
        this.Email=Email;
        this.Contact=Contact;
        this.Ceo=Ceo;
        this.requests=requests;
        this.status=status;
        this.request_status=request_status;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Email", Email);
        result.put("Contact", Contact);
        result.put("Company_Name", Company_Name);
        result.put("Ceo",Ceo);
        result.put("request",requests);
        result.put("status",status);
        result.put("request_status",request_status);

        return result;
    }

    public String getCompanyName()
    {
        return Company_Name;
    }


    public String getContact()
    {
        return Contact;
    }


    public String getCeo()
    {
        return Ceo;
    }


    public String getRequests()
    {
        return requests;
    }




    public String getRequest_status()
    {
        return request_status;
    }

    public String getStatus()
    {
        return status;
    }


}
