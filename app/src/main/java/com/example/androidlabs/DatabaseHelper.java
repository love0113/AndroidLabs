package com.example.androidlabs;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MessagesDB";
    public static final String DB_TABLE = "Messages_Table";

    //columns
    public static final String COL_MESSAGE = "Message";
    public static final String COL_ISSEND = "IsSend";
    public static final String COL_MESSAGEID = "MessageID";

    //queries
    private static final String CREATE_TABLE = "CREATE TABLE "+DB_TABLE+" ("+COL_MESSAGEID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_MESSAGE+" TEXT, "+COL_ISSEND+" BIT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }

    //insert data
    public long insertData(String message, boolean isSend) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MESSAGE, message);
        if (isSend)
            contentValues.put(COL_ISSEND, 0);
        else
            contentValues.put(COL_ISSEND, 1);

        long result = db.insert(DB_TABLE, null, contentValues);

        return result ; //if result = -1 data doesn't insert
    }

    // method to delete a Record
    public int deleteEntry(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String where="MessageID=?";
        int numberOFEntriesDeleted= db.delete(DB_TABLE, where, new String[]{Integer.toString(id)});
        return numberOFEntriesDeleted;
    }

    //view data
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
        return cursor;
    }
}

