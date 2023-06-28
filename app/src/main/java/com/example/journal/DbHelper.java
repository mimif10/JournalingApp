package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.journal.newEntry.JournalEntry;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    // Design the database
    public static final String DB_NAME = "Journal.db";
    // Create Table for Journal Entry
    public static final String TABLE_JOURNAL_ENTRY= "JournalEntry";
    // Column for ID
    public static final String COL_ENTRY_ID = "jEntryId";
    // Column for title
    public static final String COL_NEW_ENTRY_TITLE = "jEntryTitle";
  //  public  static final String COL_IMAGE = "image";
   // public static final String COL_IS_COMPLETED = "isCompleted";

    public DbHelper(@Nullable Context context) {
        // context finds the database, database name, default: null, version starts at version 1
        // super is the parent class for SQLiteOpenHelper
        super(context, "Journal.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    // implement methods for the SQLiteOpenHelper class

    // onCreate is called the first time you access the Database
    // that will create a table when you call it
    @Override
    public void onCreate(SQLiteDatabase db) {
    // will generate a new table

        // define static variables
        // start by defining a string statement to create the table
        String Journal_Entry_Table = "CREATE TABLE " + TABLE_JOURNAL_ENTRY + "( jEntryId integer PRIMARY KEY AUTOINCREMENT, jEntryTitle TEXT, image BLOG);";
                                                                                                                                                                      // ," + COL_IS_COMPLETED + "bool
        // then execute SQL query that will create a table
        //db.execSQL("create Table " + TABLE_JOURNAL_ENTRY + "(" + "COL_ENTRY_ID" + "INTEGER primary key autoIncrement, " + " jEntryName, activeSave bool);");
        db.execSQL(Journal_Entry_Table);
    }

    // onUpgrade will check if table exists or not
    // onUpgrade is called if the version number changes
    // prevents previous user apps from breaking when you change the Db design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Journal_Entry_Table");
    }



    /** Add item into the database using dbHandler (DatabaseHandler)
     It expects a new customer model*/
    // Inserting the task from MainActivity using DbHelper
    // Adding the task by passing the ToDo_obj (JournalEntry) as a parameter
    // public boolean addOne(JournalEntry newEntry){
    public boolean addOne(String title){ /** Keep parameter as string title */
        // SQLiteOpenHelper property
        // Permission to write to the database
        SQLiteDatabase db = this.getWritableDatabase(); // since we're adding data to the Db

        // Use contentValues to collect the columns with the parameter variable
         ContentValues cv = new ContentValues(); // object that stores values/data in pairs (cv.put("name", value)
        // specify value name along with its content
        cv.put(COL_NEW_ENTRY_TITLE, title);
        //cv.put(COL_IMAGE, image);
        //cv.put(COL_NEW_ENTRY_TITLE, journalEntry.getTitle()); // associate a column name with a string
        //cv.put("title", newEntry.getjEntryTitle());
        // Don't have to enter an ID bc the column's database is auto-incremented

        // Insert value into the table
        long insert = db.insert(TABLE_JOURNAL_ENTRY,(String)null, cv);
        // long result = db.insert("JournalEntry",(String)null, cv);
        //return result != (long)-1;
        if (insert == -1) return false;
        else return true;
    }

/*    public final void updateEntryDashboard(JournalEntry newEntry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", newEntry.getjEntryTitle());
        db.update("JournalEntry", cv, "id=?", new String[]{String.valueOf(newEntry.getId())});
    }*/

    public final void deleteJournalEntry(long entryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("JournalEntry", "id=?", new String[]{String.valueOf(entryId)});
    }



    // Get the list of journal entries on the table
    public ArrayList<JournalEntry> getjournalEntry()
    {
        // create a return list (define the list)
        ArrayList<JournalEntry> returnList = new ArrayList<>();
        // Get permission from the database to read the data
        SQLiteDatabase db = this.getReadableDatabase();

        // Get data from the database - sequel statement that will select data from the table
        String queryString = "SELECT * FROM " + TABLE_JOURNAL_ENTRY;

        // A cursor is a return type of the rawQuery
        // it is the result set of the query
        Cursor cursor = db.rawQuery(queryString, null); // raw-query returns a cursor
        if (cursor.moveToFirst()) // if there are results on the database
        {
            // then loop through the result, create a new journal entry for each
            // put then into the result list
            do
            {
                // local variable of the type we expect to get (ID)
                int id = cursor.getInt(0); // set this as first line in database
                String title = cursor.getString(1); // for the edit text
                // newEntry.setId(cursor.getLong(cursor.getColumnIndex("id")));
                // newEntry.setjEntryId(cursor.getLong(cursor.getColumnIndex("jEntryId")));
                // no boolean in SQLite, it just saves the int as 0 or 1
                // convert the int = 2 into a boolean

                // newEntry.setjEntryTitle(entryName);
                // Pass this data in the Journal entry model
                JournalEntry newEntry = new JournalEntry(id, title);

                // Add it to the list
                // returnList.add(newEntry);
                JournalEntry.listOfEntries.add(newEntry);

                // Boolean isCompleted = cursor.getInt(2) == 1 ? true: false;
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

    public void updateJournalEntry(JournalEntry journalEntry) {
    }


}