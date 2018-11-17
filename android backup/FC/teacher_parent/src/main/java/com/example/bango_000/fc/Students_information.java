package com.example.bango_000.fc;
import com.example.bang_info_share.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Students_information extends Activity implements OnClickListener {
    Button send_phone_number;
    EditText phonenumber, firstname, lastname;
    static String phonenumber_, firstname_, lastname_, names;
    LoginDatabase log;
    int j = 0;
    
    public  Button button(int id_) {
        return (Button) findViewById(id_);}
    public  EditText edit(int id_) {
        return (EditText) findViewById(id_); }
    
    public void repeat()
    {
        setContentView(R.layout.students_information);
        log = new LoginDatabase(this);
		log.open();
		
		phonenumber = edit(R.id.phonenumber);
		firstname = edit(R.id.firstname);
		lastname = edit(R.id.lastname);
		send_phone_number = button(R.id.send_phone_number);	
		send_phone_number.setOnClickListener(this);
    }
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		repeat();}
	
	@Override
	public void onClick(View v) {
		 Intent i;

		 switch (v.getId()) {
         case R.id.send_phone_number:

			 //register students
             if(j < Main.numberofstudents_){
            	 phonenumber_ = phonenumber.getText().toString();
            	 firstname_ = firstname.getText().toString();
            	 lastname_ = lastname.getText().toString();
            	 names = firstname_+" "+lastname_;
            	 //store data in database
            	 log.createEntry(Main.classid_string, names, phonenumber_);
            	 log.close();
	             j++;
				 //repeat steps until all students are registered
				 repeat();}

	             //CLass_ID_NAME.numberofstudents_++;
	             //if(j!=CLass_ID_NAME.numberofstudents_){repeat();}}

             //reached max number of students
			 if(j >= Main.numberofstudents_){
				 Main.ini= 3; //change display format
				 i = new Intent(this, Confirmation.class);
				 startActivity(i);
        	 break;}}
		     
        	 
	}}
