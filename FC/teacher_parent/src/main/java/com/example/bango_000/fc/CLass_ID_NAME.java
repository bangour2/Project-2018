package com.example.bango_000.fc;
import com.example.bang_info_share.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

public class CLass_ID_NAME extends	Activity implements OnClickListener {
    Button continue_;
	private Spinner spinner2;
    static String classid_, check = " ";
    static int numberofstudents_;
    LoginDatabase log;
    
    public  Button button(int id_) {
        return (Button) findViewById(id_);}
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_id_name);

		log = new LoginDatabase(this);
	     log.open();
	     
	     boolean didItWork = true;
	     try{check = log.getData();}
    	
		    catch (Exception e) {
			didItWork = false;
			String error = e.toString();
			Dialog d = new Dialog(this);
			d.setTitle("CATCH");
			TextView tv = new TextView(this);
			tv.setText(error);
			d.setContentView(tv);
			d.show();}
	     log.close();
	    System.out.println(check);
		continue_ = button(R.id.continue_5);
		spinner2 = (Spinner) findViewById(R.id.classid);
		continue_.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		 Intent i;

	 switch (v.getId()) {
     case R.id.continue_5:
		 classid_ = String.valueOf(spinner2.getSelectedItem());
        if(classid_.equals("fc0012")){
        	i = new Intent(this, Admin.class);
              startActivity(i);}     
        
        //class_id value is not in database
        else if(!check.contains(classid_)){
		  Main.ini=4;
     	  i = new Intent(this, Confirmation.class);
           startActivity(i);}
        
        //class_id is in database
        else {
        	i = new Intent(this, List_Item_View.class);
              startActivity(i);}
    	  
    	 break;} 
	}}
