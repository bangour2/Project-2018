package com.example.bango_000.fc;
import com.example.bang_info_share.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Admin extends Activity implements OnClickListener, OnItemClickListener {
	
    Button delete_;
    TextView display;
    EditText row;
    static int ini = 0;
	LoginDatabase log;
    int j = 0;
    
    ListView listview;
    Context context;
    static String[] items;
    static String names;
    static String choose;
    
    public  Button button(int id_) {
        return (Button) findViewById(id_);}
    
    public  TextView text(int id_) {
        return (TextView) findViewById(id_);}
    
    public  EditText edit(int id_) {
        return (EditText) findViewById(id_); }
    
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item_view);
        context = this;
        listview = (ListView)findViewById(R.id.list);
        delete_ = button(R.id.continue_);
        
        LoginDatabase log = new LoginDatabase(this);
        log.open();
        log.getData();
		//names = log.getstudent_info();
		

		  
		    // set adapter for listview
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, LoginDatabase.all_names_list);
		    items = new String[log.names_list.size()];
		    listview.setAdapter(adapter);
		    log.close();
		    //listview.setItemsCanFocus(false);
		        // we want multiple clicks
		    listview.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
		    
		listview.setOnItemClickListener(Admin.this);
		delete_.setOnClickListener(this);
		 
	}
	
	@Override
	public void onClick(View v) {
		 Intent i;
		 boolean didItWork = true;

		 switch (v.getId()) {
         case R.id.continue_:
             try{
             
            //to be changed
            	 for(int k = 0; k < 100; k++){
             log.deleteEntry(Long.parseLong(""+k+""));}}
            
             catch (Exception e) {
      			didItWork = false;
      			String error = e.toString();
      			Dialog d = new Dialog(this);
      			d.setTitle("CATCH");
      			TextView tv = new TextView(this);
      			tv.setText(error);
      			d.setContentView(tv);
      			d.show();}
            
            finally{
            	Dialog d = new Dialog(this);
  			d.setTitle("Title");
  			TextView tv = new TextView(this);
  			tv.setText("deleted");
  			d.setContentView(tv);
  			d.show();}
        	 
  			break;}
		 log.close();
        	 
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		items[j] = parent.getItemAtPosition(position).toString(); j++;}		
	}
