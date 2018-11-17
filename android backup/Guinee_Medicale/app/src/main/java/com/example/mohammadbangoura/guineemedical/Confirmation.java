package com.example.mohammadbangoura.guineemedical;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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

		LoginDatabase log = new LoginDatabase(Confirmation.this);
		log.open();

		message = view(R.id.messagetext);
		String password = (log.getpassword() == "")+"";

		if(Admin.ini == 0){message.setText(password+" 0 "+log.getpassword());}
		if(Admin.ini!=1){message.setText("password successfully saved!");}
		if(Admin.ini ==2){message.setText("erreur. " + log.getpassword().toString());}
		if(Admin.ini ==3){message.setText("enter valid password "+log.getpassword().toString()+ " "+Admin.password_);}

		log.close();
		home = button(R.id.mainmenu);
		home.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		 Intent i;

		 switch (v.getId()) {
         case R.id.mainmenu:
        	 i = new Intent(this, MainActivity.class);
             startActivity(i);
       
       	 break;}
        	 
	}}
