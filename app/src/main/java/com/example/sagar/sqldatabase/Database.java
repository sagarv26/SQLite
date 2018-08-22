package com.example.sagar.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sagar on 8/19/2017.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DB="myDB.db";
    private static final int version=1;

    private static final String Table_Name="list";
    public static final String ID="id";
    public static final String Name="name";
    public static final String Mob="mobile";
    public static final String Address="address";
    public static final String Company="company";
    public static final String DOB="dob";

    private SQLiteDatabase myDB;

    public Database(Context context) {
        super(context, DB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+ Table_Name+
                " ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Name+" TEXT NOT NULL, "+
                Mob+" TEXT NOT NULL, "+
                Address+" TEXT NOT NULL, "+
                Company+" TEXT NOT NULL, "+
                DOB+" TEXT NOT NULL "+
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void openDB(){
        myDB=getWritableDatabase();
    }

    public void closeDB(){
        if(myDB!=null&&myDB.isOpen()){myDB.close();}
    }

    public long create(String name,String mob,String address,String company,String dob){
        ContentValues values=new ContentValues();
        values.put(Name,name);
        values.put(Mob,mob);
        values.put(Address,address);
        values.put(Company,company);
        values.put(DOB,dob);

        return myDB.insert(Table_Name,null,values);
    }

    public long update(String name,String mob,String address,String company,String dob){
        ContentValues values=new ContentValues();

        values.put(Name,name);
        values.put(Mob,mob);
        values.put(Address,address);
        values.put(Company,company);
        values.put(DOB,dob);
        return myDB.update(Table_Name,values,"name = ?",new String[]{name});
    }

    public long delete(String name){

        return myDB.delete(Table_Name,"name = ?",new String[]{name});
    }
    public Cursor read(){
        String readQuery="SELECT * FROM "+Table_Name;
        Cursor res=myDB.rawQuery(readQuery,null);
        return res;
    }
}
