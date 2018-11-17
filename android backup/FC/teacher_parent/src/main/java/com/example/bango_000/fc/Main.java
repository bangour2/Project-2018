package com.example.bango_000.fc;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class Main extends Activity implements OnClickListener {
	
    Button teacher, student;
	private Spinner spinner1;
	//static EditText classid_;
	NumberPicker numberofstudents;
	static int numberofstudents_;
    static int ini = 0;
	static String classid_string;
    
    public  Button button(int id_) {
        return (Button) findViewById(id_);}
	public EditText edit(int id_) {
		return (EditText) findViewById(id_); }

	public NumberPicker num(int id_) {
		return (NumberPicker) findViewById(id_); }

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		teacher = button(R.id.teacher);
		student = button(R.id.student);
		spinner1 = (Spinner) findViewById(R.id.classid);
		//classid_ = edit(R.id.classid);
		numberofstudents = num(R.id.numberofstudents);

		//Populate NumberPicker values from minimum and maximum value range
		//Set the minimum value of NumberPicker
		numberofstudents.setMinValue(0);
		//Specify the maximum value/number of NumberPicker
		numberofstudents.setMaxValue(30);
		//Gets whether the selector wheel wraps when reaching the min/max value.
		numberofstudents.setWrapSelectorWheel(true);


		teacher.setOnClickListener(this);
		student.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		 Intent i;
         //students already registered
		 switch (v.getId()) {
         case R.id.teacher:
        	ini = 1;
       	    i = new Intent(this, CLass_ID_NAME.class);
            startActivity(i);
       	 break;
         //register all students in class
		 case R.id.student:
		    ini = 2; //get students count
			//String numofstuden = numberofstudents.toString() +""; //.getText().toString();
			numberofstudents_ = numberofstudents.getValue(); //Integer.parseInt(numofstuden);
			classid_string = String.valueOf(spinner1.getSelectedItem()); //spinner1.toString();
		    i = new Intent(this, Students_information.class);
		    startActivity(i);
		 break;
        	 
	}}}
