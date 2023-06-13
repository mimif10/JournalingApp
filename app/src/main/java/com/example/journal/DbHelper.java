package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.journal.newEntry.JournalEntry;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NewEntryModel = "JournalEntry";
    public static final String COL_ENTRY_ID = "entryId";
    public static final String COL_NEW_ENTRY_NAME = "jEntryText";
    public static final String COL_IS_COMPLETED = "isCompleted";

    public DbHelper(@Nullable Context context) {
        // context finds the database, database name, default: null, version starts at version 1
        // super is the parent class for SQLiteOpenHelper
        super(context, "JournalEntry.db", null, 1);
    }

    // implement methods for the SQLiteOpenHelper class

    // onCreate is called the first time you access the Database
    // that will create a table when you call it
    @Override
    public void onCreate(SQLiteDatabase db) {
    // will generate a new table

        //define static variables
        // start by defining a string statement to create the table
        String Journal_Entry_Table = "CREATE TABLE " + TABLE_NewEntryModel + "(" + COL_ENTRY_ID + "integer PRIMARY KEY AUTOINCREMENT," + COL_NEW_ENTRY_NAME + "TEXT," + COL_IS_COMPLETED + "bool);";

        // execute SQL query that will create a table
        //db.execSQL("create Table " + TABLE_NewEntryModel + "(" + "COL_ENTRY_ID" + "INTEGER primary key autoIncrement, " + " jEntryName, activeSave bool);");
        db.execSQL(Journal_Entry_Table);
    }

    /** Add item into the database using dbHandler (DatabaseHandler)
     It expects a new customer model*/
    public boolean addOne(JournalEntry newEntry){
        // SQLiteOpenHelper property to write to the database
        SQLiteDatabase db = this.getWritableDatabase(); // since we're adding data to the Db
        ContentValues cv = new ContentValues(); // object that stores values/data in pairs (cv.put("name", value)
        // specify value name along with its content
        // D
        // cv.put(COL_NEW_ENTRY_NAME, JournalEntry.getjEntryT()); // associate a column name with a string
        cv.put(COL_IS_COMPLETED, JournalEntry.isCompleted());
        // Don't have to enter an ID bc the column's database is auto-incremented

        // Insert value into the table
        long insert = db.insert(TABLE_NewEntryModel,null, cv);
        if (insert == -1)
        {
            return false;
        }
        else{
            return true;
        }

    }

    // onUpgrade is called if the version number changes
    // prevents previous user apps from breaking when you change the Db design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Journal_Entry_Table");
    }

    // Get the list on the table
    public List<JournalEntry> getAllList()
    {
        // create a return list (define the list)
        List<JournalEntry> returnList = new ArrayList<>();
        // Get data from the database - sequel statement that will select data from the table
        String queryString = "SELECT * FROM " + TABLE_NewEntryModel;
        // Reference the database and get the data from it
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        // A cursor is a return type of the rawQuery
        // it is the result set of the query
        Cursor cursor = db.rawQuery(queryString, null); // raw-query returns a cursor
        if (cursor.moveToFirst()) // if there are results on the database
        {
            // then loop through the result, create a new journal entry for each
            // put then into the result list

            {
                // local variable of the type we expect to get (ID)
                int entryID = cursor.getInt(0); // 0 is first line in database
                String entryName = cursor.getColumnName(1); // from database at position 1
                // no boolean in SQLite, it just saves the int as 0 or 1
                // convert the int = 2 into a boolean
                Boolean isCompleted = cursor.getInt(2) == 1 ? true: false;
                                                                      // ternary operator: a result variable will be given a question
                                                                     // variable = value1 ? value2: value3
                                                                    // if 1st question = true, then we assign the next value to the ?
                                                                   // else (if not) assign the value to the the result is the 2nd value (value3)
            }
            while (cursor.moveToNext()); // while we can move the next line
        } // else the list is empty

        // close the cursor and the database when done (always)
        cursor.close();
        db.close();
        return returnList;
    }

}