package com.example.sagar.sqldatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    EditText name,mobile,address,company,dob;
    Button btnRead,btnUpdate,load;
    FloatingActionButton btnAdd,btnDel;
    Database dataBase;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase=new Database(MainActivity.this);
        init();
    }

    public void init(){

        name=(EditText)findViewById(R.id.name);
        mobile=(EditText)findViewById(R.id.mobile);
        company=(EditText)findViewById(R.id.company);
        address=(EditText)findViewById(R.id.address);
        dob=(EditText)findViewById(R.id.dob);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnRead=(Button)findViewById(R.id.btnRead);
        load=(Button)findViewById(R.id.load);
        btnAdd=(FloatingActionButton) findViewById(R.id.btnAdd);
        btnDel=(FloatingActionButton) findViewById(R.id.btnDel);
        textView=(TextView)findViewById(R.id.textView);

        btnUpdate.setOnClickListener(btnListner);
        btnRead.setOnClickListener(btnListner);
        btnAdd.setOnClickListener(btnListner);
        btnDel.setOnClickListener(btnListner);
        load.setOnClickListener(btnListner);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dataBase.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dataBase.closeDB();
    }

    View.OnClickListener btnListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnAdd:
                    Long result= dataBase.create(getValue(name),getValue(mobile),getValue(address),getValue(company),getValue(dob));
                    if(result==-1){
                        Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btnRead:
                    Toast.makeText(MainActivity.this,"Load",Toast.LENGTH_SHORT).show();
                    Cursor res1=dataBase.read();
                    StringBuffer data1=new StringBuffer();
                    while (res1.moveToNext())
                    {
//                        data.append("ID :"+res.getString(res.getColumnIndex(dataBase.ID))+"-");
                        data1.append("Name :"+res1.getString(res1.getColumnIndex(dataBase.Name))+" | ");
                        data1.append("Mobile :"+res1.getString(res1.getColumnIndex(dataBase.Mob))+" | ");
                        data1.append("Address :"+res1.getString(res1.getColumnIndex(dataBase.Address))+" | ");
                        data1.append("Company :"+res1.getString(res1.getColumnIndex(dataBase.Company))+" | ");
                        data1.append("DOB :"+res1.getString(res1.getColumnIndex(dataBase.DOB))+"\n");
                    }textView.setText(data1);

                    Intent i=new Intent(MainActivity.this,Page2.class);
                    i.putExtra("data", (Serializable) data1);
                    startActivity(i);
                    break;
                case R.id.btnDel:
                    Long resultDel= dataBase.delete(getValue(name));
                    if(resultDel==-1){
                        Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnUpdate:
                    Long resultAdd= dataBase.update(getValue(name),getValue(mobile),getValue(address),getValue(company),getValue(dob));
                    if(resultAdd==-1){
                        Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.load:
                    Toast.makeText(MainActivity.this,"Load",Toast.LENGTH_SHORT).show();
                    Cursor res=dataBase.read();
                    StringBuffer data=new StringBuffer();
                    while (res.moveToNext())
                    {
//                        data.append("ID :"+res.getString(res.getColumnIndex(dataBase.ID))+"-");
                        data.append("Name :"+res.getString(res.getColumnIndex(dataBase.Name))+" | ");
                        data.append("Mobile :"+res.getString(res.getColumnIndex(dataBase.Mob))+" | ");
                        data.append("Address :"+res.getString(res.getColumnIndex(dataBase.Address))+" | ");
                        data.append("Company :"+res.getString(res.getColumnIndex(dataBase.Company))+" | ");
                        data.append("DOB :"+res.getString(res.getColumnIndex(dataBase.DOB))+"\n");
                    }
//                    for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToLast()){
//                        data.append(cursor.getString(cursor.getColumnIndex(dataBase.Name)));
//                        data.append(" - ");
//                        data.append(cursor.getString(cursor.getColumnIndex(dataBase.Mob)));
//                        data.append(" - ");
//                        data.append(cursor.getString(cursor.getColumnIndex(dataBase.Address)));
//                        data.append(" - ");
//                        data.append(cursor.getString(cursor.getColumnIndex(dataBase.Company)));
//                        data.append(" - ");
//                        data.append(cursor.getString(cursor.getColumnIndex(dataBase.DOB)));
                    textView.setText(data);
                    break;
            }
        }
    };

    private String getValue(EditText text) {
      return text.getText().toString().trim();
    }
}
