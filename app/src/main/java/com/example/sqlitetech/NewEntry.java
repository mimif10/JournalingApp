package com.example.sqlitetech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sqlitetech.newEntry.NewEntryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NewEntry extends AppCompatActivity {
    public DbHelper dbHelper;
    // reference to all the buttons and controls on the layout
    // member variable for the main screen from the class
    EditText newTextEntry;
    FloatingActionButton saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        // On Click Listeners for the button
        newTextEntry = findViewById(R.id.textEditor);

        // reference DatabaseHelper


        saveButton = findViewById(R.id.saveButton);

        // Button Listeners for the save Button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(NewEntry.this);
                // create a return list (define the list)
                //List<NewEntryModel> saved = DbHelper.getAllList();

            }
        });


    }
}