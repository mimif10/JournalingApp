package com.example.journal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.journal.newEntry.JournalEntry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // initiate database
    DbHelper journalDatabase;

    /**
     * reference to all the buttons and controls on the layout
     */
    // member variable for the main screen from the class
    ListView mainListDashboard; // for the list in main
    JournalAdapter journalAdapter; // for the listofEntries
    private HashSet<JournalEntry> listOfEntries; // member variable for the list of entries - where we will store the user input

    // For the Listview pop up menu
    ListView listView; // for the openDialog Box
    String[] prompts = {"Today I learned...",        // Add data to Array String
            "Something that made me smile today...",
            "Three things I am grateful for...",
            "Today I realized...",
            "Today, this happened...",
            "What happened today?...",
            "Add new journal entry..."};
    ArrayAdapter<String> adapter; // for the prompts


    @Override // onCreate starts the application
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void openDialog(View v) {
            // add listView to alert box
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            // inflate the layout file (custom_dialog_layout and store it in view variable)
            View rowList = getLayoutInflater().inflate(R.layout.custom_dialog_layout, null);
            // access the TextButton from this view
            listView = rowList.findViewById(R.id.listView); // in custom_dialog layout

            // Create Array adapter
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, prompts);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            alertDialog.setView(rowList);
            AlertDialog dialog = alertDialog.create();
            dialog.show();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder entryDialog = new AlertDialog.Builder(MainActivity.this);
                    //View layoutView = getLayoutInflater().inflate(R.layout.activity_new_entry, null);
                    //textEntry = layoutView.findViewById(R.id.textEditor); // in new entry layout
                    //alertDialog.setView(layoutView);

                    switch (position) {
                        case 0:
                            View layoutView1 = getLayoutInflater().inflate(R.layout.activity_prompt1, null);
                            // cast all the buttons and controls in the layout
                            EditText textEntry = layoutView1.findViewById(R.id.textEditor); // in new entry layout
                            //  ImageView imageButton = layoutView1.findViewById(R.id.AddImageButton);
                       /* imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean pickImage = true;
                                if (pickImage == true) { // from camera
                                    if (!checkCameraPermission()) { // permission is allowed
                                        requestCameraPermission();
                                    } else PickImage();
                                }else {
                                        if (!checkStoragePermission()) { // permission is allowed
                                            requestStoragePermission();
                                        } else PickImage();
                                    }
                                }
                        });*/

                            // FloatingActionButton backButton = layoutView1.findViewById(R.id.backButton);
                            //FloatingActionButton formatTxtBtn = layoutView1.findViewById(R.id.FormatButton);
                            //FloatingActionButton addImageBtn = layoutView1.findViewById(R.id.AddImageButton);
                            alertDialog.setView(layoutView1)
                                    //.setTitle(textEntry.getText())
                                    // Set Save button
                                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Pick/get information from the EditText
                                            String title = textEntry.getText().toString();

                                            /** Add the entry into the database through dbHeelper (DatabaseHelper)*/
                                            // Add new entry to the database when we click on the prompt Save button

                                            // Reference to the DbHelper class aka new database we're creating for the journal entry
                                            //  context is a ref to the application
                                            //refreshList(); // Call after an item is added*/
                                            boolean insert = journalDatabase.addOne(title);
                                            // boolean success = dbHelper.addOne(newJEntry);
                                            if (insert == true) {
                                                journalAdapter = new JournalAdapter(getApplicationContext(), JournalEntry.nonDeletedNotes());
                                                // journalAdapter = new JournalAdapter(this, listOfEntries);
                                                mainListDashboard.setAdapter(journalAdapter);
                                                adapter.notifyDataSetChanged();
                                                Toast.makeText(MainActivity.this, "Success=", Toast.LENGTH_SHORT).show();

                                            } else
                                                Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                                        }

                                    });

                            alertDialog.create().show();

                            break;

                        case 1:
                            /*Intent prompt2 = new Intent(getApplicationContext(), Prompt2.class);
                            prompt2.putExtra("Today I learned...", prompts[position]);
                            startActivity(prompt2);*/
                            // AlertDialog.Builder dialogPrompt2 = new AlertDialog.Builder(MainActivity.this);
                            View layoutView2 = getLayoutInflater().inflate(R.layout.activity_prompt2, null);
                            //textEntry = layoutView.findViewById(R.id.textEditor); // in new entry layout
                            alertDialog.setView(layoutView2);
                            AlertDialog dialog2 = alertDialog.create();
                            dialog2.show();
                            break;
                        case 3:
                            View layoutView3 = getLayoutInflater().inflate(R.layout.activity_prompt3, null);
                            //textEntry = layoutView.findViewById(R.id.textEditor); // in new entry layout
                            alertDialog.setView(layoutView3);
                            AlertDialog dialog3 = alertDialog.create();
                            dialog3.show();

                        case 4:
                            View layoutView4 = getLayoutInflater().inflate(R.layout.activity_prompt4, null);
                            //textEntry = layoutView.findViewById(R.id.textEditor); // in new entry layout
                            alertDialog.setView(layoutView4);
                            AlertDialog dialog4 = alertDialog.create();
                            dialog4.show();

                        case 5:
                            View layoutView5 = getLayoutInflater().inflate(R.layout.activity_prompt5, null);
                            //textEntry = layoutView.findViewById(R.id.textEditor); // in new entry layout
                            alertDialog.setView(layoutView5);
                            AlertDialog dialog5 = alertDialog.create();
                            dialog5.show();

                        case 6:
                            View layoutView6 = getLayoutInflater().inflate(R.layout.activity_prompt3, null);
                            //textEntry = layoutView.findViewById(R.id.textEditor); // in new entry layout
                            alertDialog.setView(layoutView6);
                            AlertDialog dialog6 = alertDialog.create();
                            dialog6.show();

                        case 7:
                            View layoutView7 = getLayoutInflater().inflate(R.layout.activity_prompt3, null);
                            //textEntry = layoutView.findViewById(R.id.textEditor); // in new entry layout
                            alertDialog.setView(layoutView7);
                            AlertDialog dialog7 = alertDialog.create();
                            dialog7.show();
                    }
                }
            });

        }

    // To refresh the List (Line 31)

    // private void refreshList(){
    // listOfEntries.clear();
    //  listOfEntries.addAll(data);
    //journalDatabase = new DbHelper(this); // will create a new journal database

    @Override
    protected void onResume() // Will show the list when you open the application
    {
        super.onResume();

        listOfEntries = new HashSet<>();
        journalDatabase = new DbHelper(this);
        mainListDashboard = findViewById(R.id.Main_ListViewlayout);
        listOfEntries = journalDatabase.getjournalEntry();
        journalAdapter = new JournalAdapter(getApplicationContext(), JournalEntry.nonDeletedNotes());
       // journalAdapter = new JournalAdapter(this, listOfEntries);
        mainListDashboard.setAdapter(journalAdapter);
        journalAdapter.notifyDataSetChanged();
        listOfEntries.clear();


        mainListDashboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override // THis open the journal entry in the ViewEntry Activity
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JournalEntry selectedEntry = (JournalEntry) mainListDashboard.getItemAtPosition(position);
                Intent mainIntent = new Intent(MainActivity.this, ViewEntryActivity.class);
                mainIntent.putExtra("NoteEdit", String.valueOf(selectedEntry.getId()));
                startActivity(mainIntent);
            }
        });
    }

}




    // This will get the actions when we insert data into the database




/*
    private byte[] ImageToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    private ActivityResultLauncher<Void> cropImage = registerForActivityResult(new CropImageContract(), result -> {
        if (result.isSuccessful()) {
            // Use the returned uri.
            Uri uriContent = result.getUriFilePath()
            String uriFilePath = result.getUriFilePath(context); // optional usage
        } else {
            // An error occurred.
            Exception exception = result.getError();
        }
    });
*/
/*
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
                        //imageView.setImageURI(imageUri);
                    }
                        *//*else {
                            //cancelled
                            Toast.makeText(NewEntry.this, "Cancelled...", Toast.LENGTH_SHORT).show();
                        }*//*
                }
            });*/
    // Use library to
    /*private void PickImage() {
        // start picker to get image for cropping and then use the image in cropping activity
        cropImage.launch(null, options -> options.setGuidelines(CropImageView.Guidelines.ON));
        }
    )
*/
    // Once permission is granted


    /*// launch intent to pick image from gallery
        imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // intent to pick image from gallery
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryActivityResultLauncher.launch(intent);
        }
    });*/

    /*private void requestStoragePermission() {
        requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    private void requestCameraPermission() {
        requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    private boolean checkStoragePermission() {
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return result1;
    }

    private boolean checkCameraPermission() {
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA ) == PackageManager.PERMISSION_GRANTED;
        return result1 && result2;
    }*/


//};









//dialog.setContentView(R.layout.activity_new_entry);
//entryDialog.setView(view)
                               /*             .setTitle("Today I learned")
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            entryDialog.create();
                            entryDialog.show();

                            // dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            // dialog.setCancelable(false);
                            // dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                            save_Button = dialog.findViewById(R.id.saveButton);
                            save_Button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Save", Toast.LENGTH_SHORT).show();
                                }
                            });
                            cancel_Button = dialog.findViewById(R.id.cancelButton);
                            cancel_Button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialog.show();*/
