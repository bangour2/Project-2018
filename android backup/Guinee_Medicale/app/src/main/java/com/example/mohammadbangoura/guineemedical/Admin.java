package com.example.mohammadbangoura.guineemedical;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Admin extends Activity implements OnClickListener {
    Button save, update, mise_jour;
    EditText password, numero, nom;
    static String password_, numero_, nom_, check = " ";;
    static int ini = 0, j = 0;
    LoginDatabase log;
    List_Item_View lv;

    public  Button button(int id_) {
        return (Button) findViewById(id_);}
    public  EditText edit(int id_) {
        return (EditText) findViewById(id_); }

    public void repeat()
    {
        setContentView(R.layout.admin);
        log = new LoginDatabase(this);
        log.open();

        password = edit(R.id.password);
        numero = edit(R.id.numero);
        nom = edit(R.id.nom);

        save = button(R.id.save_button);
        save.setOnClickListener(this);

        update = button(R.id.update_button);
        update.setOnClickListener(this);

        mise_jour = button(R.id.update_numero);
        mise_jour.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repeat();}

    @Override
    public void onClick(View v) {
        Intent i;

        log = new LoginDatabase(this);
        log.open();

        switch (v.getId()) {
            case R.id.save_button:
                //enregistrer le medecin
                password_ = password.getText().toString();
                numero_ = numero.getText().toString();
                nom_ = nom.getText().toString();

                //initial password == numero dattestation
                if (log.getpassword() == ""){
                    ini =  1;
                    log.createPassword(numero_, nom_);
                    log.close();
                    i = new Intent(this, Confirmation.class);
                    startActivity(i);}


                //store data in database
/*
                if (password_ != log.getpassword()) {
                    ini = 1;
                    log.close();
                    i = new Intent(this, Confirmation.class);
                    startActivity(i);}

                ini = 2;
                log.createEntry(numero_, nom_);
                log.close();
*/
                ini = 0;
                i = new Intent(this, Confirmation.class);
                startActivity(i);
                break;

            case R.id.update_button:
                //enregistrer le medecin
                password_ = password.getText().toString();
                numero_ = numero.getText().toString();

                if (!password_.equals(log.getpassword()+"")) { //change
                    ini = 3;
                    i = new Intent(this, Confirmation.class);
                    startActivity(i);}

                log.updatePassword(1, numero_);
                log.close();

                i = new Intent(this, Confirmation.class);
                startActivity(i);
                break;

            case R.id.update_numero:
                //enregistrer le medecin
                numero_ = numero.getText().toString();
                nom_ = nom.getText().toString();
                //store data in database
                i = new Intent(this, List_Item_View.class);
                startActivity(i);
                break; }}


    }



