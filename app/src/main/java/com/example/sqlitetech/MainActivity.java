package com.example.sqlitetech;

import static com.example.sqlitetech.DbHelper.COL_NEW_ENTRY_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlitetech.newEntry.NewEntryModel;
import com.example.sqlitetech.newEntry.jEntryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // reference to all the buttons and controls on the layout
    // member variable for the main screen from the class
    Button newEntryBtn;
    ListView viewListBtn;

    FloatingActionButton saveButton;

    // onCreate starts the application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On Click Listeners for the button
        newEntryBtn = findViewById(R.id.btn_newEntry);
        viewListBtn = findViewById(R.id.entry_List);


        // Button Listeners for the newEntryBtn
        newEntryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Context)MainActivity.this, NewEntry.class);
                MainActivity.this.startActivity(intent);
            }

            // Add new entry to the database when we click on the prompt button

            // Reference to the DbHelper class aka new database we're creating for the journal entry
            //  context is a ref to the application
            DbHelper database = new DbHelper(MainActivity.this);

        });


    }

    protected void onResume() // Will show the list when you open the application
    {
        refreshList();
        super.onResume();
    }

    // To refresh the List (Line 31)
    private void refreshList(){
        // Set the Adapter for the RecyclerView - pass the Adapter's context and the getToDos method,

    }


}