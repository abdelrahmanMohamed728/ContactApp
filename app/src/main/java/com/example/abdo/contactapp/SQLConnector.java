package com.example.abdo.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SQLConnector extends SQLiteOpenHelper {
    static final String DB_NAME="contactsDB";
    static final String DB_TABLE="contacts";
    static final String KEY_ID = "id";
    static final String KEY_NAME ="name";
    static final String KEY_PHONE ="phone";
    static final int KEY_VERSION = 1;
    public SQLConnector(@NonNull Context context) {
        super(context, DB_NAME, null, KEY_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+DB_TABLE+"("+KEY_ID+" integer primary key ,"+KEY_NAME+" varchar(30),"+KEY_PHONE+" integer);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE_TABLE="DROP TABLE "+ DB_TABLE;
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
    public ArrayList<pair> getAllContacts(){
        ArrayList<pair> contacts=new ArrayList<>();
        String select_query="SELECT * FROM "+DB_TABLE+"";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(select_query,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String phone=cursor.getString(cursor.getColumnIndex(KEY_PHONE));
                int id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
                pair contact=new pair(id,name,phone);
                contacts.add(contact);
            }while(cursor.moveToNext());
        }

        return contacts;
    }
    public pair getContact(int id)
    {
        String query = "SELECT * FROM "+DB_TABLE+" WHERE id = "+id+"";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst())
            return new pair(cursor.getInt(cursor.getColumnIndex(KEY_ID)),cursor.getString(cursor.getColumnIndex(KEY_NAME)),cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            return null;
    }
       public void updateContact(int id,pair p)
       {
           SQLiteDatabase database = this.getWritableDatabase();
           ContentValues values = new ContentValues();
           values.put(KEY_NAME,p.getName());
           values.put(KEY_PHONE,p.getPhone());
           database.update(DB_TABLE,values,"id=?",new String[]{String.valueOf(id)});
       }
     public void addContact(pair p)
     {
         SQLiteDatabase database = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(KEY_ID,p.getId());
         values.put(KEY_NAME,p.getName());
         values.put(KEY_PHONE,p.getPhone());
         database.insert(DB_TABLE,null,values);
     }
    public void deleteContact(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(DB_TABLE,"id=?",new String[]{String.valueOf(id)});
    }
}
