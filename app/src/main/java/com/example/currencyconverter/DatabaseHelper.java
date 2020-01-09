package com.example.currencyconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db",null,4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user( "
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"email VARCHAR,"
                +"password VARCHAR)");

        db.execSQL("CREATE TABLE favorite ("
                + "fID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "favName VARCHAR, "
                + "favImgID INTEGER," +
                "favRate DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists favorite");
    }

    public void insertFavCurrency(String favName, int favImgID, Double favRate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues currencyValues = new ContentValues();

        currencyValues.put("favName", favName);
        currencyValues.put("favImgID", favImgID);
        currencyValues.put("favRate", favRate);

        long l = db.insert("favorite", null, currencyValues);


        db.close();
    }

    public List<Product> getAllCurrencyData() {

        List<Product> favoritesDataList = new ArrayList<Product>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery( "select * from favorite", null);
        while(cursor.moveToNext()) {
            favoritesDataList.add(new Product(cursor.getString(1), cursor.getInt(2), cursor.getDouble(3)));
        }

        Collections.reverse(favoritesDataList);
        return favoritesDataList;
    }

    public void deleteCurrencyData(String favName, int favImgID, Double favRate) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("" +
                "DELETE FROM " + "favorite" +
                " WHERE " + "favName" + "= '" + favName + "'" +
                " and " + "favImgID" + "= '" + favImgID + "'" +
                " and " + "favRate" + "= '" + favRate +
                "'");
        db.close();
    }


    public boolean insert(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if(ins==-1) return false;
        else return true;
    }

    public List<User> getAllEmailData() {

        List<User> mailList = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery( "select * from user", null);
        while(cursor.moveToNext()) {
            mailList.add(new User(cursor.getString(1)));
        }

        Collections.reverse(mailList);
        return mailList;
    }

    //emailin varolup olmadıgının kontrolu
    public Boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    // email ve password kontrolü
    public Boolean emailpassword(String email,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password = ?",new String[]{email,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public void signOut(){

    }




}