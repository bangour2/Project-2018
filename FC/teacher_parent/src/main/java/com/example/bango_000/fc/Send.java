package com.example.bango_000.fc;
import com.example.bang_info_share.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Send extends Activity implements OnClickListener{
	
	EditText subject, body;
	Button send;
	
	public  EditText edit(int id_) {
        return (EditText) findViewById(id_); }
    public  Button button(int id_) {
        return (Button) findViewById(id_);}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms);
		
		subject = edit(R.id.subject_);
		body = edit(R.id.body_);
	    send = button(R.id.send_);
	    send.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent in;
		 switch (v.getId()) {
         case R.id.send_:
        	 String message = subject.getText().toString()+"\n"+
                              body.getText().toString();
        	 
        	 SmsManager manager = SmsManager.getDefault();
        	 try{
				 LoginDatabase log = new LoginDatabase(this);
				 log.open();
				 log.getData();

        	for(int i = 0; i< List_Item_View.items.length; i++)
        		if(!List_Item_View.items[i].equals(null)){
        	manager.sendTextMessage(log.phones_list.get(i)+"", null, message, null, null);
				log.close();}}
        	 
        	 catch (Exception e) {
                 String error = e.toString();
                 Dialog d = new Dialog(this);
                 d.setTitle("CATCH");
                 TextView tv = new TextView(this);
                 tv.setText(error);
                 d.setContentView(tv);
                 d.show(); }
        	 
        	 in = new Intent(this, Confirmation.class);
             startActivity(in);
        	 
        break;}
	}}
