package com.aakash.sptbi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListViewAdapters extends ArrayAdapter<Management_Decision> implements View.OnClickListener {
	private List<Management_Decision> items;
	private int layoutResourceId;
	private Context context;
	FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
	DatabaseReference myRef = mDatabase.getReference("sptbiapp");

	public ListViewAdapters(Context context, int layoutResourceId, List<Management_Decision> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		Management_Decision_Holder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new Management_Decision_Holder();
		holder.management_decision = items.get(position);


		holder.tv1 = (TextView)row.findViewById(R.id.textView1);
		holder.b1=(Button)row.findViewById(R.id.accept);
		holder.b2=(Button)row.findViewById(R.id.reject);
		holder.b1.setOnClickListener(this);
		holder.b2.setOnClickListener(this);
		row.setTag(holder);

		setupItem(holder);
		return row;
	}

	private void setupItem(Management_Decision_Holder holder) {
		holder.tv1.setText(holder.management_decision.gettv1());

		holder.b1.setTag(holder.management_decision.getAccept_tag());
		holder.b2.setTag(holder.management_decision.getReject_tag());

	}

	public static class Management_Decision_Holder {
		Management_Decision management_decision;
		TextView tv1;

		Button b1;
		Button b2;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.accept:
				myRef.child("startup").child(v.getTag().toString().trim()).child("status").setValue("Accepted");
				Intent intent = new Intent(context,Management.class)
						.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
				break;
			case R.id.reject:
				myRef.child("startup").child(v.getTag().toString().trim()).removeValue();
				Intent intent1 = new Intent(context,Management.class)
						.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent1);
				break;
		}
	}

}
