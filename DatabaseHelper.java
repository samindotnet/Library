package com.example.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String Database_Name = "BookLibrary.db";
    private static final int Database_Version = 1;
    private static final String Table_Name = "my_library";
    private static final String Column_ID ="ID";
    private static final String Column_Title ="Title";
    private static final String Column_Author ="Author";
    private static final String Column_Pages = "Pages";
    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, Database_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "Create Table "+Table_Name+
                        " ("+Column_ID+ " Integer Primary Key Autoincrement, "+
                        Column_Title + " Text, "+
                        Column_Author+ " Text, "+
                        Column_Pages+ " Integer);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if exists "+Table_Name);
    }
    void Addbook(String title, String author, int pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_Title,title);
        contentValues.put(Column_Author,author);
        contentValues.put(Column_Pages, pages);
        long result = db.insert(Table_Name,null, contentValues);
        if(result ==-1){
            Toast.makeText(context,"Failed to Add Book",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Book Added Successfully",Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query =  "Select * From "+Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    void updateData(String row_id,String title, String author, String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_Title, title);
        cv.put(Column_Author, author);
        cv.put(Column_Pages,pages);
        long result =db.update(Table_Name,cv,"id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Failed to Update",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Sucessfully Updated",Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Table_Name,"id=?",new String[]{row_id});
        if(result ==-1){
            Toast.makeText(context,"Failed to Delete",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Deleted",Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase db =  this.getWritableDatabase();
        db.execSQL("DELETE FROM "+Table_Name);
    }
}
