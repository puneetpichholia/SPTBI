package com.aakash.sptbi;

import java.io.Serializable;

public class Management_Decision implements Serializable{
	private static final long serialVersionUID = -5435670920302756945L;
	private String tv1,Accept_Button_tag,Reject_Button_tag,type;
	

	public Management_Decision(String tv1, String Accept_Button_tag, String Reject_Button_tag,String type) {
		this.tv1=tv1;

		this.Accept_Button_tag=Accept_Button_tag;
		this.Reject_Button_tag=Reject_Button_tag;
		this.type=type;
		
	}
	
	
	public String gettv1()
	{
	    return tv1;
	}

	public String getAccept_tag()
	{
	    return Accept_Button_tag;
	}
	public String getReject_tag()
	{
	    return Reject_Button_tag;
	}
	public String getType()
	{
		return type;
	}

	
	
	
}
