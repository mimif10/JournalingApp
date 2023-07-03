package com.example.journal;

import static com.example.journal.ViewEntryActivity.IMAGE_REQ_CODE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.journal.databinding.ActivityMainBinding;
import com.example.journal.newEntry.JournalEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;

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

    ActivityMainBinding mainBinding;

    ActivityResultLauncher<Uri> takePhotoLauncher;
    Uri imageUri;
    ImageView imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    ImageView theimage;
    String currentDate;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    private final static Integer CAMERA_PERMISSION_CODE = 1;
    ActivityResultLauncher<Intent> galleryActivityResultLauncher;

    @Override // onCreate starts the application
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Switch to view Binding

        galleryActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // here we will handle the result of our intent
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            //image picked
                            //get uri of image
                            Intent data = result.getData();
                            Uri imageUri = data.getData();
                            imageView.setImageURI(imageUri);
                            imageView.setVisibility(View.VISIBLE);
                        }
                        /*else {
                            //cancelled
                            Toast.makeText(NewEntry.this, "Cancelled...", Toast.LENGTH_SHORT).show();
                        }*/

                    }
                });

    }

    // request permission

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhotoLauncher.launch(imageUri);
            } else {
                Toast.makeText(this, "please allows permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
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

    public Activity getActivity(Context context) {
        if (context == null)
            return null;
        else if (context instanceof ContextWrapper) {
            if (context instanceof Activity)
                return (Activity) context;
            else
                return getActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

  private void pickImageGallery() {
      Intent intent = new Intent(Intent.ACTION_PICK);
      intent.setType("image/");
      startActivityForResult(intent, IMAGE_REQ_CODE);
  }
    public void openDialog(View v) {
        // add listView to alert box
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // inflate the layout file (custom_dialog_layout and store it in view variable)
        View rowList = getLayoutInflater().inflate(R.layout.custom_dialog_layout, null);
        // access the TextButton from this view
        listView = rowList.findViewById(R.id.listView); // in custom_dialog layout
        ActivityResultLauncher<Intent> galleryActivityResultLauncher = null;
        //ActivityResultLauncher<Intent> finalGalleryActivityResultLauncher = galleryActivityResultLauncher;

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
                        View layoutView = getLayoutInflater().inflate(R.layout.activity_prompt1, null);
                        // cast all the buttons and controls in the layout
                        EditText textEntry = layoutView.findViewById(R.id.textEditor1); // in new entry layout
                        FloatingActionButton saveButton = (FloatingActionButton) layoutView.findViewById(R.id.saveButton);
                        FloatingActionButton backButton = (FloatingActionButton) layoutView.findViewById(R.id.backButton);
                        FloatingActionButton addImageButton = (FloatingActionButton) layoutView.findViewById(R.id.addImageButton);
                        imageView = layoutView.findViewById(R.id.imageView);
                        imageView.setBackgroundResource(0);
                        imageView.setVisibility(View.VISIBLE);

                        saveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = textEntry.getText().toString();
                                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                Calendar c = Calendar.getInstance();
                                currentDate = simpleDateFormat.format(c.getTime());


                                /** Add the entry into the database through dbHeelper (DatabaseHelper)*/
                                // Add new entry to the database when we click on the prompt Save button

                                // Reference to the DbHelper class aka new database we're creating for the journal entry
                                //  context is a ref to the application
                                boolean insert = journalDatabase.addOne(new JournalEntry(title, currentDate));
                                // boolean success = dbHelper.addOne(newJEntry);
                                //refreshList(); // Call after an item is added*/
                                if (insert == true) {
                                    journalAdapter = new JournalAdapter(getApplicationContext(), JournalEntry.nonDeletedNotes());
                                    // journalAdapter = new JournalAdapter(this, listOfEntries);
                                    mainListDashboard.setAdapter(journalAdapter);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Success=", Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();}
                            }
                        });
                        backButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // alertDialog.setCancelable(true);
                                finish();
                            }
                        });


                         /*// Photo picker
                          ImageView imageViewButton = findViewById(R.id.imageView);
                        imageUri = createUri();*/


                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getParent(), new String[]
                                    {Manifest.permission.CAMERA}, 100);
                        } else
                            addImageButton.setEnabled(true);

                        //TODO get the image from gallery and display it - in main

                            // launch intent to pick image from add
                            addImageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    imageView.setBackgroundResource(0);
                                    // intent to pick image from gallery

                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                                        // Picture launcher
                                    galleryActivityResultLauncher.launch(intent);
                                }
                            });

                        alertDialog.setView(layoutView);
                        AlertDialog dialog = alertDialog.create();
                        dialog.show();
                            break;

                    case 1:
                        View layoutView1 = getLayoutInflater().inflate(R.layout.activity_prompt2, null);
                        EditText textEntry1 = layoutView1.findViewById(R.id.textEditor1); // in new entry layout
                        FloatingActionButton saveButton1 = (FloatingActionButton) layoutView1.findViewById(R.id.saveButton1);
                        FloatingActionButton backButton1 = (FloatingActionButton) layoutView1.findViewById(R.id.backButton1);
                        FloatingActionButton addImageButton1 = (FloatingActionButton) layoutView1.findViewById(R.id.addImageButton1);

                        ImageView imageView1 = layoutView1.findViewById(R.id.imageView1);
                        imageView1.setBackgroundResource(0);
                        imageView1.setVisibility(View.VISIBLE);
                        saveButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = textEntry1.getText().toString();
                                simpleDateFormat = new SimpleDateFormat();
                                Calendar c = Calendar.getInstance();
                                currentDate = simpleDateFormat.format(c.getTime());

                                /** Add the entry into the database through dbHeelper (DatabaseHelper)*/
                                // Add new entry to the database when we click on the prompt Save button

                                // Reference to the DbHelper class aka new database we're creating for the journal entry
                                //  context is a ref to the application
                                boolean insert = journalDatabase.addOne(new JournalEntry(title, currentDate));
                                if (insert == true) {
                                    mainListDashboard.setAdapter(journalAdapter);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Success=", Toast.LENGTH_SHORT).show();

                                } else
                                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();}
                        });

                        backButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // alertDialog.setCancelable(true);
                                finish();
                            }
                        });

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getParent(), new String[]
                                    {Manifest.permission.CAMERA}, 100);
                        } else

                            addImageButton1.setEnabled(true);

                        addImageButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                imageView1.setBackgroundResource(0);
                                // intent to pick image from gallery

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                if (galleryActivityResultLauncher != null){
                                    galleryActivityResultLauncher.launch(intent);}
                            }
                        });

                        alertDialog.setView(layoutView1);
                        AlertDialog dialog1 = alertDialog.create();
                        dialog1.show();
                        break;

                    case 2:
                        View layoutView2 = getLayoutInflater().inflate(R.layout.activity_prompt3, null);
                        EditText textEntry2 = layoutView2.findViewById(R.id.textEditor2); // in new entry layout
                        FloatingActionButton saveButton2 = (FloatingActionButton) layoutView2.findViewById(R.id.saveButton2);
                        FloatingActionButton backButton2 = (FloatingActionButton) layoutView2.findViewById(R.id.backButton2);
                        FloatingActionButton addImageButton2 = (FloatingActionButton) layoutView2.findViewById(R.id.addImageButton2);

                        ImageView imageView2 = layoutView2.findViewById(R.id.imageView2);
                        imageView2.setBackgroundResource(0);
                        imageView2.setVisibility(View.VISIBLE);
                        saveButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = textEntry2.getText().toString();
                                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                Calendar c = Calendar.getInstance();
                                currentDate = simpleDateFormat.format(c.getTime());

                                /** Add the entry into the database through dbHeelper (DatabaseHelper)*/
                                // Add new entry to the database when we click on the prompt Save button
                                // Reference to the DbHelper class aka new database we're creating for the journal entry
                                //  context is a ref to the application
                                boolean insert = journalDatabase.addOne(new JournalEntry(title, currentDate));
                                if (insert == true) {
                                    mainListDashboard.setAdapter(journalAdapter);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Success=", Toast.LENGTH_SHORT).show();

                                } else
                                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();}
                        });

                        backButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // alertDialog.setCancelable(true);
                                finish();
                            }
                        });

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getParent(), new String[]
                                    {Manifest.permission.CAMERA}, 100);
                        } else

                            addImageButton2.setEnabled(true);

                        addImageButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                imageView2.setBackgroundResource(0);
                                // intent to pick image from gallery

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                if (galleryActivityResultLauncher != null){
                                    galleryActivityResultLauncher.launch(intent);}
                            }
                        });

                        alertDialog.setView(layoutView2);
                        AlertDialog dialog2 = alertDialog.create();
                        dialog2.show();


                    case 3:
                        View layoutView3 = getLayoutInflater().inflate(R.layout.activity_prompt4, null);
                        EditText textEntry3 = layoutView3.findViewById(R.id.textEditor3); // in new entry layout
                        FloatingActionButton saveButton3 = (FloatingActionButton) layoutView3.findViewById(R.id.saveButton3);
                        FloatingActionButton backButton3 = (FloatingActionButton) layoutView3.findViewById(R.id.backButton3);
                        FloatingActionButton addImageButton3 = (FloatingActionButton) layoutView3.findViewById(R.id.addImageButton3);

                        ImageView imageView3 = layoutView3.findViewById(R.id.imageView3);
                        imageView3.setBackgroundResource(0);
                        imageView3.setVisibility(View.VISIBLE);
                        saveButton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = textEntry3.getText().toString();
                                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                Calendar c = Calendar.getInstance();
                                currentDate = simpleDateFormat.format(c.getTime());

                                /** Add the entry into the database through dbHeelper (DatabaseHelper)*/
                                // Add new entry to the database when we click on the prompt Save button

                                // Reference to the DbHelper class aka new database we're creating for the journal entry
                                //  context is a ref to the application
                                boolean insert = journalDatabase.addOne(new JournalEntry(title, currentDate));
                                if (insert == true) {
                                    mainListDashboard.setAdapter(journalAdapter);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Success=", Toast.LENGTH_SHORT).show();

                                } else
                                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();}
                        });

                        backButton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // alertDialog.setCancelable(true);
                                finish();
                            }
                        });

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getParent(), new String[]
                                    {Manifest.permission.CAMERA}, 100);
                        } else

                            addImageButton3.setEnabled(true);

                        addImageButton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                imageView3.setBackgroundResource(0);
                                // intent to pick image from gallery

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                if (galleryActivityResultLauncher != null){
                                    galleryActivityResultLauncher.launch(intent);}
                            }
                        });


                        alertDialog.setView(layoutView3);
                        AlertDialog dialog3 = alertDialog.create();
                        dialog3.show();

                    case 4:
                        View layoutView4 = getLayoutInflater().inflate(R.layout.activity_prompt5, null);
                        EditText textEntry4 = layoutView4.findViewById(R.id.textEditor4); // in new entry layout
                        FloatingActionButton saveButton4 = (FloatingActionButton) layoutView4.findViewById(R.id.saveButton4);
                        FloatingActionButton backButton4 = (FloatingActionButton) layoutView4.findViewById(R.id.backButton4);
                        FloatingActionButton addImageButton4 = (FloatingActionButton) layoutView4.findViewById(R.id.addImageButton4);

                        ImageView imageView4 = layoutView4.findViewById(R.id.imageView4);
                        imageView4.setBackgroundResource(0);
                        imageView4.setVisibility(View.VISIBLE);
                        saveButton4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = textEntry4.getText().toString();
                                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                Calendar c = Calendar.getInstance();
                                currentDate = simpleDateFormat.format(c.getTime());

                                /** Add the entry into the database through dbHeelper (DatabaseHelper)*/
                                // Add new entry to the database when we click on the prompt Save button

                                // Reference to the DbHelper class aka new database we're creating for the journal entry
                                //  context is a ref to the application
                                boolean insert = journalDatabase.addOne(new JournalEntry(title, currentDate));
                                if (insert == true) {
                                    mainListDashboard.setAdapter(journalAdapter);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Success=", Toast.LENGTH_SHORT).show();

                                } else
                                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();}
                        });

                        backButton4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // alertDialog.setCancelable(true);
                                finish();
                            }
                        });

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getParent(), new String[]
                                    {Manifest.permission.CAMERA}, 100);
                        } else

                            addImageButton4.setEnabled(true);

                        addImageButton4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                imageView4.setBackgroundResource(0);
                                // intent to pick image from gallery

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                if (galleryActivityResultLauncher != null){
                                    galleryActivityResultLauncher.launch(intent);}
                            }
                        });

                        alertDialog.setView(layoutView4);
                        AlertDialog dialog4 = alertDialog.create();
                        dialog4.show();

                    case 5:
                        View layoutView5 = getLayoutInflater().inflate(R.layout.activity_prompt6, null);
                        EditText textEntry5 = layoutView5.findViewById(R.id.textEditor5); // in new entry layout
                        FloatingActionButton saveButton5 = (FloatingActionButton) layoutView5.findViewById(R.id.saveButton5);
                        FloatingActionButton backButton5 = (FloatingActionButton) layoutView5.findViewById(R.id.backButton5);
                        FloatingActionButton addImageButton5 = (FloatingActionButton) layoutView5.findViewById(R.id.addImageButton5);

                        ImageView imageView5 = layoutView5.findViewById(R.id.imageView5);
                        imageView5.setBackgroundResource(0);
                        imageView5.setVisibility(View.VISIBLE);
                        saveButton5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = textEntry5.getText().toString();
                                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                Calendar c = Calendar.getInstance();
                                currentDate = simpleDateFormat.format(c.getTime());

                                /** Add the entry into the database through dbHeelper (DatabaseHelper)*/
                                // Add new entry to the database when we click on the prompt Save button

                                // Reference to the DbHelper class aka new database we're creating for the journal entry
                                //  context is a ref to the application
                                boolean insert = journalDatabase.addOne(new JournalEntry(title, currentDate));
                                if (insert == true) {
                                    mainListDashboard.setAdapter(journalAdapter);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Success=", Toast.LENGTH_SHORT).show();

                                } else
                                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();}
                        });

                        backButton5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // alertDialog.setCancelable(true);
                                finish();
                            }
                        });

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getParent(), new String[]
                                    {Manifest.permission.CAMERA}, 100);
                        } else

                            addImageButton5.setEnabled(true);

                        addImageButton5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                imageView5.setBackgroundResource(0);
                                // intent to pick image from gallery

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                if (galleryActivityResultLauncher != null){
                                    galleryActivityResultLauncher.launch(intent);}
                            }
                        });

                        alertDialog.setView(layoutView5);
                        AlertDialog dialog5 = alertDialog.create();
                        dialog5.show();

                    case 6:
                        View layoutView6 = getLayoutInflater().inflate(R.layout.activity_prompt7, null);
                        EditText textEntry6 = layoutView6.findViewById(R.id.textEditor6); // in new entry layout
                        FloatingActionButton saveButton6 = (FloatingActionButton) layoutView6.findViewById(R.id.saveButton6);
                        FloatingActionButton backButton6 = (FloatingActionButton) layoutView6.findViewById(R.id.backButton6);
                        FloatingActionButton addImageButton6 = (FloatingActionButton) layoutView6.findViewById(R.id.addImageButton6);

                        ImageView imageView6 = layoutView6.findViewById(R.id.imageView6);
                        imageView6.setBackgroundResource(0);
                        imageView6.setVisibility(View.VISIBLE);
                        saveButton6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = textEntry6.getText().toString();
                                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                Calendar c = Calendar.getInstance();
                                currentDate = simpleDateFormat.format(c.getTime());
                                /** Add the entry into the database through dbHeelper (DatabaseHelper)*/
                                // Add new entry to the database when we click on the prompt Save button

                                // Reference to the DbHelper class aka new database we're creating for the journal entry
                                //  context is a ref to the application
                                boolean insert = journalDatabase.addOne(new JournalEntry(title, currentDate));
                                if (insert == true) {
                                    mainListDashboard.setAdapter(journalAdapter);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Success=", Toast.LENGTH_SHORT).show();

                                } else
                                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();}
                        });

                        backButton6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // alertDialog.setCancelable(true);
                                finish();
                            }
                        });

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getParent(), new String[]
                                    {Manifest.permission.CAMERA}, 100);
                        } else

                            addImageButton6.setEnabled(true);

                        addImageButton6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                imageView6.setBackgroundResource(0);
                                // intent to pick image from gallery

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                if (galleryActivityResultLauncher != null){
                                    galleryActivityResultLauncher.launch(intent);}
                            }
                        });

                        alertDialog.setView(layoutView6);
                        AlertDialog dialog6 = alertDialog.create();
                        dialog6.show();
                }
            }
        });

    }

    // To refresh the List (Line 31)

//     private void refreshList(HashSet<JournalEntry> list) {
//         ArrayList<JournalEntry> lists = JournalEntry.listOfEntries;
//         listOfEntries.clear();
//         listOfEntries.addAll(list);
//         mainListDashboard.setAdapter((ListAdapter) listOfEntries);
//     }
    //journalDatabase = new DbHelper(this); // will create a new journal database

    @Override
    protected void onResume() // Will show the list when you open the application
    {
        super.onResume();
        //refreshList(listOfEntries);
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
