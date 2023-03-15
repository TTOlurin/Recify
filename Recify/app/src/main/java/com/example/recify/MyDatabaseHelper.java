package com.example.recify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "RecifyLib.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "recipe_table";
    private static final String COLUMN_ID = "r_id";
    private static final String COLUMN_NAME = "r_name";
    private static final String COLUMN_TYPE = "r_type";
    private static final String COLUMN_INGREDIENTS = "r_ingredients";
    private static final String COLUMN_METHOD = "r_method";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_INGREDIENTS + " TEXT, " +
                        COLUMN_METHOD + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addRecipe(String r_name, String r_type, String r_ingredients, String r_method){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, r_name);
        cv.put(COLUMN_TYPE, r_type);
        cv.put(COLUMN_INGREDIENTS, r_ingredients);
        cv.put(COLUMN_METHOD, r_method);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Recipe Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor orderAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }






    //Update database function
    void updateData(String row_id, String name, String type, String ingredients, String method){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_INGREDIENTS, ingredients);
        cv.put(COLUMN_METHOD, method);

        long result = db.update(TABLE_NAME, cv, "r_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Recipe Successfully Edited", Toast.LENGTH_SHORT).show();
        }
    }

    //Delete from database function
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "r_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Recipe Deleted", Toast.LENGTH_SHORT).show();
        }
    }

}
