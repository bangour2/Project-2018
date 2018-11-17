package com.example.bango_000.fc;
import com.example.bang_info_share.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Confirmation extends Activity implements OnClickListener {
	
    Button home;
	TextView message;
    
    public  Button button(int id_) {
        return (Button) findViewById(id_);}

	public TextView view(int id_) {
		return (TextView) findViewById(id_); }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirmation);

		message = view(R.id.messagetext);
		if(Main.ini==3){message.setText("All students are registered!");} //change message
		if(Main.ini==4){message.setText("class code does not exist.");} //change message
		home = button(R.id.mainmenu);
		home.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		 Intent i;

		 switch (v.getId()) {
         case R.id.mainmenu:
        	 i = new Intent(this, Main.class);
             startActivity(i);
       
       	 break;}
        	 
	}}
