package io.github.sceneview.sample.armodelviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME= "login.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(matricule TEXT PRIMARY KEY , password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String matricule, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matricule",matricule);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean CheckUsername(String matricule){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where matricule = ?",new String[] {matricule});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean checkUserPass(String matricule, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where matricule = ? and password = ?",new String[] {matricule,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }


}
