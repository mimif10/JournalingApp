package com.example.journal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journal.newEntry.JournalEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;

public class ViewEntryActivity extends AppCompatActivity {
    /**
     * Use databasehandler to add the item in the database
     */
    public DbHelper dbHelper;
    private JournalEntry selectedJournalEntry;
    // reference to all the buttons and controls on the layout
    // member variable for the main screen from the class
    private EditText newTextEntry;
    private ImageButton saveButtonOnView;
    //private ImageView imageView;
    //private FloatingActionButton addImageButtonView;

    //private FloatingActionButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);
        newTextEntry = findViewById(R.id.textEditorView);
        saveButtonOnView = findViewById(R.id.saveButtonView);

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
        }
    }
        /*saveButtonOnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardTitle = newTextEntry.getText().toString();
                if (noteEntered.equals("NoteEdit")) {
                    if (!cardTitle.isEmpty()) {
                        //SimpleDateFormat sdf= new SimpleDateFormat("MMM, dd,yyyy");
                        //String currentDate= sdf.format(new Date());
                        JournalEntry updatedEntry = new JournalEntry(cardTitle);
                        updatedEntry.setId(passedNoteID);
                        dbHelper.updateJournalEntry(journalEntry);
                        Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } *//*else {
                        Toast.makeText(getApplicationContext(), "Please fill both the columns!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });*/

    /*  private void checkForEditNote()
      {
          Intent previousIntent = getIntent();
         // int passedNoteID = previousIntent.getIntExtra(journalEntry.JOURNAL_EDIT_EXTRA, -1);
          int passedNoteID = previousIntent.getIntExtra("NoteEdit", -1);
          journalEntry = JournalEntry.getNoteForID(passedNoteID);

          if (journalEntry != null)
          {
              newTextEntry.setText(journalEntry.getTitle());
          }
      }*/
    public void saveNote(View view) {
        DbHelper dbHelper = new DbHelper(this); // Access database
        // Declare a string for the text entered on the editText entry
        String title = String.valueOf(newTextEntry.getText());

        if (selectedJournalEntry == null) {
            int id = JournalEntry.listOfEntries.size(); // list from JournalEntry model
            // Create a new journal entry object - pass id and title
            JournalEntry newJournalEntry = new JournalEntry(id, title);
            JournalEntry.listOfEntries.add(newJournalEntry); // add it to the list
            dbHelper.addOne(String.valueOf(newJournalEntry)); // add note to database
        } else {
            selectedJournalEntry.setTitle(title);
            boolean result = dbHelper.updateJournalEntry(selectedJournalEntry.getId(), title);
            Intent i = new Intent(ViewEntryActivity.this, MainActivity.class);
            startActivity(i);
        }
        finish();

    }
}

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


         /*   saveButtonOnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = newTextEntry.getText().toString();
                    if (!title.isEmpty()) {
                        // SimpleDateFormat sdf= new SimpleDateFormat("MMM, dd,yyyy");
                        // String currentDate= sdf.format(new Date());
                        JournalEntry updateNote = new JournalEntry(Id, title);
                        updateNote.setId(Id); // set Id
                        updateNote.setjEntryTitle(title);
                        dbHelper.updateJournalEntry(journalEntry);
                        Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please fill both the columns!", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/



/*
    private void saveEntry(View view) {
        dbHelper = DbHelper.instanceOfDatabase(this);
        String title = String.valueOf(newTextEntry.getText());

        if (journalEntry == null) {
            int id = JournalEntry.newList.size();
            JournalEntry newEntry = new JournalEntry(id, title);
            JournalEntry.newList.add(newEntry);
            dbHelper.addOne(String.valueOf(newEntry));
        } else {
            journalEntry.setTitle(title);
            dbHelper.updateJournalEntry(journalEntry);
        }
        finish();
    }*/

/* // Button Listeners for the save Button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(ViewEntryActivity.this);
                // create a return list (define the list)
                //List<NewEntryModel> saved = DbHelper.getAllList();
            }
        });*/


        //imageButton.setEnabled(false); // disable button then grant permission

        // if user has not granted permission
 /*if(ActivityCompat.checkSelfPermission(ViewEntryActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            // request permission
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.CAMERA}, 100);}
        else
            addImageButton.setEnabled(true);

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
else {
                            //cancelled
                            //Toast.makeText(ViewEntryActivity.this, "Cancelled...", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        // launch intent to pick image from gallery
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent to pick image from gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryActivityResultLauncher.launch(intent);
            }
        });*/

        //  saveButton = findViewById(R.id.saveButton);

    /*    // Button Listeners for the save Button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(ViewEntryActivity.this);
                // create a return list (define the list)
                // List<NewEntryModel> saved = DbHelper.getAllList();
            }
        });*/




