package com.example.journal.Prompts;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.journal.DbHelper;
import com.example.journal.NewEntry;
import com.example.journal.R;
import com.example.journal.newEntry.JournalEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Prompt1 extends AppCompatActivity {
    /**Use databasehandler to add the item in the database*/
    public DbHelper dbHelper;

    // reference to all the buttons and controls on the layout
    // member variable for the main screen from the class
    EditText newTextEntry;
    FloatingActionButton saveButton;
    FloatingActionButton cancelButton;
    ImageView imageView;
    ImageButton imageButton;
    Long entryId = Long.valueOf(-1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt1);

        // On Click Listeners for the button
        newTextEntry = findViewById(R.id.textEditor);
        imageView = findViewById(R.id.imageView);
        imageButton = findViewById(R.id.imageButton);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        // reference DatabaseHelper?

        // Button Listeners for the save Button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(Prompt1.this);
                EditText NoteText = (EditText)newTextEntry.getText();
                String title = String.valueOf(newTextEntry.getText());
                String description = String.valueOf(newTextEntry.getText());

                // create a return list (define the list)
                //List<NewEntryModel> saved = DbHelper.getAllList();
                if(NoteText != null); // Check the Edit Text if not empty
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM, dd, YYYY");
                    String currentDate = dateFormat.format(new Date());

                    // Create journalEntry obj to pass as a parameter
                    JournalEntry newJournal = new JournalEntry();
                    // set jEntryName (entered on EditText))
                    newJournal.setjEntryTitle(title);
                    newJournal.setjEntryDescription(description);
                    newJournal.setCreatedAt(currentDate);
                    newJournal.setId(entryId);
                    /** Add item into the database using dbHandler (DatabaseHelper)*/
                    dbHelper.addOne(newJournal);
                    //refreshList(); // Call after an item is added
/*
                    EditText entryName = (EditText)NoteText.getText();
                    newJournal.setId(Prompt1.this.getTaskId());
                    newJournal.setjEntryTitle(entryName.getText().length());
                    newJournal.setjEntryDescription(entryName.);*/
                }
            }
        });

       // private void refreshList()
       // {
        //    RecyclerView recycView = (RecyclerView)findViewById(R.id.RecyclerView_layout);
        //    recycView.setAdapter();
       // }
        // Button Listeners for the cancel Button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(Prompt1.this);
                // create a return list (define the list)
                //List<NewEntryModel> saved = DbHelper.getAllList();
            }
        });

        //imageButton.setEnabled(false); // disable button then grant permission

        // if user has not granted permission
        if(ActivityCompat.checkSelfPermission(Prompt1.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            // request permission
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.CAMERA}, 100);}
        else
            imageButton.setEnabled(true);

        // Once permission is granted

        //TODO get the image from gallery and display it
        ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // here we will handle the result of our intent
                        if (result.getResultCode() == Activity.RESULT_OK){
                            //image picked
                            //get uri of image
                            Intent data = result.getData();
                            Uri imageUri = data.getData();
                            imageView.setImageURI(imageUri);
                        }
                        /*else {
                            //cancelled
                            Toast.makeText(NewEntry.this, "Cancelled...", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
        // launch intent to pick image from gallery
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent to pick image from gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryActivityResultLauncher.launch(intent);
            }
        });

        //saveButton = findViewById(R.id.saveButton);

        // Button Listeners for the save Button
       /* saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper db = new DbHelper(Prompt1.this);
                // create a return list (define the list)
                // List<NewEntryModel> saved = DbHelper.getAllList();
            }
        });*/
    }
}