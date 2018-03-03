package com.aakash.sptbi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aakas on 28-02-2018.
 */

public class Users {

    public String username;
    public String password;
    public String type;
    public String email;


    public Users() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Users(String email,String type,String password) {
        this.email = email;
        this.type=type;
        this.password=password;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("type", type);
        result.put("password", password);

        return result;
    }


    public String getPassword()
    {
        return password;
    }
    public String getType()
    {
        return type;
    }
}