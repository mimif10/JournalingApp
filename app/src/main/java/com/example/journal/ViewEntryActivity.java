package com.example.journal;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.journal.newEntry.JournalEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class ViewEntryActivity extends AppCompatActivity {
    /**
     * Use databasehandler to add the item in the database
     */
    public DbHelper dbHelper;

    private ImageView theimage;
    private Uri ImageURI = null;
    private Bitmap bmp = null;
    private int noteID = -1;

    public static final int IMAGE_REQ_CODE = 100;
    private JournalEntry selectedJournalEntry;
    // reference to all the buttons and controls on the layout
    // member variable for the main screen from the class
    private EditText newTextEntry;
    private ImageButton saveButtonOnView;
    //private ImageView imageView;
    private FloatingActionButton addImageButtonView;
    private FloatingActionButton backButton;
    private ImageView imageView;
    String currentDate;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);
        newTextEntry = findViewById(R.id.textEditorView);
        saveButtonOnView = findViewById(R.id.saveButtonView);
        addImageButtonView = findViewById(R.id.addImageButtonView);
        backButton = findViewById(R.id.backButton);
        imageView = findViewById(R.id.imageView);
        imageView.setBackgroundResource(0);


        // Open the list item from Main Activity
        // reference DatabaseHelper and ArrayList to get the data from the list from Main
        dbHelper = new DbHelper(this);
        // Use dbHelper to access the data from each position on the list
        HashSet<JournalEntry> list = dbHelper.getjournalEntry();
        // Get position from MainActivity using Bundle intent
        Bundle extras = getIntent().getExtras();
        String noteEntered = extras.getString("NoteEdit");
        int passedNoteID = Integer.parseInt(noteEntered); // convert the Note into int
        selectedJournalEntry = JournalEntry.getNoteForID(passedNoteID); // then get the position from the list
        String title = selectedJournalEntry.getTitle().toString();
        if (selectedJournalEntry != null) {
            // Get the student data and show it
            // Set the title entered from edit text value to show on the ViewEntry Activity
            newTextEntry.setText(title);
            newTextEntry.setId(noteID);
        }


        if(ActivityCompat.checkSelfPermission(ViewEntryActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            // request permission
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.CAMERA}, 100);}
        else
            addImageButtonView.setEnabled(true);

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
                            imageView.setVisibility(View.VISIBLE);
                        }
                        //else {
                            // cancelled
                            //Toast.makeText(ViewEntryActivity.this, "Cancelled...", Toast.LENGTH_SHORT).show();
                       // }

                    }
                });
        // launch intent to pick image from gallery
        addImageButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setBackgroundResource(0);
                // intent to pick image from gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryActivityResultLauncher.launch(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // alertDialog.setCancelable(true);
                finish();
                return;
            }
        });


    }
        private void pickImageGallery() {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/");
            startActivityForResult(intent, IMAGE_REQ_CODE);
        }



    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQ_CODE && resultCode == RESULT_OK) {
            Uri imageURI = data.getData();
            if (imageURI != null) {
                theimage.setImageURI(data.getData());
                try {
                    Bitmap bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), imageURI);
                    theimage.setImageBitmap(bmp);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }


    public void saveNote(View view) {
        DbHelper dbHelper = new DbHelper(this); // Access database
        // Declare a string for the text entered on the editText entry
        String title = String.valueOf(newTextEntry.getText());

       // if (selectedJournalEntry.equals("NoteEdit")) {
            if (title == null) {
                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Calendar c = Calendar.getInstance();
                currentDate = simpleDateFormat.format(c.getTime());
                //int id = JournalEntry.listOfEntries.size(); // list from JournalEntry model
               // JournalEntry.listOfEntries.add(newJournalEntry); // add it to the list
                // Create a new journal entry object - pass id and title
                dbHelper.addOne(new JournalEntry(title, currentDate));
                startActivity(new Intent(ViewEntryActivity.this, MainActivity.class));}
                else {
                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Calendar c = Calendar.getInstance();
                currentDate = simpleDateFormat.format(c.getTime());
                selectedJournalEntry = new JournalEntry(title, currentDate);
                selectedJournalEntry.setId(noteID);
                // selectedJournalEntry.setTitle(title);
                //selectedJournalEntry.setId(selectedJournalEntry.getId());
                boolean result = dbHelper.updateJournalEntry(selectedJournalEntry);
                startActivity(new Intent(ViewEntryActivity.this, MainActivity.class));
            }
            finish();
        }
    }




    //Getting Bitmap Image






   /*     private void checkForEditNote(Long Id, String title)
        {
        // get position from MainActivity using intent
        Intent intent = getIntent();
        // Convert string to int
        String note = intent.getStringExtra(JournalEntry.JOURNAL_EDIT_EXTRA);
        // Set title as the title for the new task page
        //int passedNoteID = Integer.parseInt(note);
        int passedNoteID =  intent.getIntExtra(note, -1);
        journalEntry = JournalEntry.getNoteForID(passedNoteID);
        //JournalEntry journalEntry = list.get(passedNoteID); // then get the position of the journal entry

        // Get the id and the title of the entry
        if (journalEntry != null) {
            // Get data of the specific Journal entry and show it
            //title = journalEntry.getjEntryTitle();
            // setting data to edit text of this ViewEntry activity.
            newTextEntry.setText(journalEntry.getTitle());
        }}*/








        //imageButton.setEnabled(false); // disable button then grant permission

        // if user has not granted permission




