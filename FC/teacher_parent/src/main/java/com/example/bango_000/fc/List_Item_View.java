package com.example.bango_000.fc;
import com.example.bang_info_share.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class List_Item_View extends Activity implements AdapterView.OnItemSelectedListener, OnItemClickListener, OnClickListener {
    ListView listview;
    Context context;
    static String[] items;
    Button continu, delete;
    static int j = 0;
    static String check = " ";
    
    public  Button button(int id_) {
        return (Button) findViewById(id_);}
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_view);
        context = this;
        //listview = new ListView(this);//(ListView)findViewById(R.id.list);
        LoginDatabase log = new LoginDatabase(this);
        log.open(); //execute getstudent_names, fill in name_list

        boolean didItWork = true;
        try {
            check = log.getData();
        } catch (Exception e) {
            didItWork = false;
            String error = e.toString();
            Dialog d = new Dialog(this);
            d.setTitle("CATCH");
            TextView tv = new TextView(this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();
        }
        //log.close();

        //names = log.getstudent_names(CLass_ID_NAME.classid_);
        String[] array = new String[]{"mohammad", "noble"};
        //LoginDatabase.names_list
        //set adapter for listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, log.names_list);
        listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);
        items = new String[log.names_list.size()]; //initialize items size
        log.close();

        //listview.setItemsCanFocus(true);
        // we want multiple clicks
        listview.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listview.setOnItemClickListener(List_Item_View.this);

        continu = button(R.id.continue_);
        delete = button(R.id.delete_);
        continu.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        //students already registered
        switch (v.getId()) {
        case R.id.continue_:
        i = new Intent(List_Item_View.this, Send.class);
        startActivity(i);
        break;

        case R.id.delete_:
            LoginDatabase log = new LoginDatabase(this);
            log.open();
            log.deleteEntry(j-1);
            log.close();
        i = new Intent(List_Item_View.this, Confirmation.class);
        startActivity(i);
        break;}}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        items[j] = parent.getItemAtPosition(position).toString(); j++;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }}
