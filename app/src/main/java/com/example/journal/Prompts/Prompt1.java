package com.example.journal.Prompts;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
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
import android.widget.Toast;

import com.example.journal.DbHelper;
import com.example.journal.MainActivity;
import com.example.journal.R;
import com.example.journal.newEntry.JournalEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Prompt1 extends AppCompatActivity {
// Use databasehandler to add the item in the database

    public DbHelper dbHelper;

    // reference to all the buttons and controls on the layout
    // member variable for the main screen from the class
    EditText newTextEntry;
    FloatingActionButton saveButton;
    FloatingActionButton backButton;
    ImageView imageView;
    ImageButton addImageButton;
    Long entryId = Long.valueOf(-1);
    ActivityResultLauncher<Uri> takePhotoLauncher;
    Uri imageUri;
    private final static Integer CAMERA_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt1);
      /*  dbHelper = new DbHelper(this);

        ImageView imageViewButton = findViewById(R.id.imageView);
        imageUri = createUri();

        addImageButton.setOnClickListener(v -> {
                checkCameraPermissionAndOpenCamera();
            });
        // Picture launcher
        takePhotoLauncher = registerForActivityResult( // call the launcher to start the activity result so we can use the launcher
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {

                    @Override
                    public void onActivityResult(Boolean result) {
                        try {
                            if (result) {  // if result is true
                                // get uri of image (image picker)
                                imageViewButton.setImageURI(null);
                                imageViewButton.setImageURI(imageUri);

                            }
                        } catch (Exception exception) {
                            exception.getStackTrace();
                        }
                    }
                });

    }

    private void checkCameraPermissionAndOpenCamera() {
        if (ActivityCompat.checkSelfPermission(Prompt1.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // request permission
            ActivityCompat.requestPermissions(Prompt1.this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else
            takePhotoLauncher.launch(imageUri);
    }


    private Uri createUri() {
        File imageFile = new File(getApplicationContext().getFilesDir(), "camera_photo.jpg");
        return FileProvider.getUriForFile(
                getApplicationContext(),
                "com.example.journal.fileProvider", // pass authority ( copy from manifest)
                imageFile
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePhotoLauncher.launch(imageUri);
            }
            else {
                Toast.makeText(this, "please allows permission", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
    }
}


        // On Click Listeners for the button
        //newTextEntry = findViewById(R.id.textEditor);
        // imageView = findViewById(R.id.imageView);
        //imageButton = findViewById(R.id.addImageButton);
        //saveButton = findViewById(R.id.saveButton);
        //backButton = findViewById(R.id.backButton);
        //cancelButton = findViewById(R.id.cancelButton);
        // reference DatabaseHelper?

        // Button Listeners for the save Button




                /*);
                // create a return list (define the list)
                //List<NewEntryModel> saved = DbHelper.getAllList();
              //  if(NoteText != null); // Check the Edit Text if not empty
               // {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM, dd, YYYY");
                    String currentDate = dateFormat.format(new Date());

                    // Create journalEntry obj to pass as a parameter
                  //  JournalEntry newJournal = new JournalEntry();
                    // set jEntryName (entered on EditText))
                 //   newJournal.setjEntryTitle(title);
                    //newJournal.setjEntryDescription(description);
                   // newJournal.setCreatedAt(currentDate);
                 //   newJournal.setId(entryId);
// Add item into the database using dbHandler (DatabaseHelper)
*/
                //dbHelper.addOne(newJournal);
                //refreshList(); // Call after an item is added
                    /*EditText entryName = (EditText)NoteText.getText();
                    newJournal.setId(Prompt1.this.getTaskId());
                    newJournal.setjEntryTitle(entryName.getText().length());
                    newJournal.setjEntryDescription(entryName.);*/


         /*   }
        });
*/
                // private void refreshList()
                // {
                //    RecyclerView recycView = (RecyclerView)findViewById(R.id.RecyclerView_layout);
                //    recycView.setAdapter();
                // }
                // Button Listeners for the cancel Button
       /* cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(Prompt1.this);
                // create a return list (define the list)
                //List<NewEntryModel> saved = DbHelper.getAllList();
            }
        });
*/
                //imageButton.setEnabled(false); // disable button then grant permission

        // if user has not granted permission
        /*if(ActivityCompat.checkSelfPermission(Prompt1.this,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            // request permission
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.CAMERA}, 100);}
        else
            imageButton.setEnabled(true);*/
                // Once permission is granted

                //TODO get the image from gallery and display it
      /*  ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
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
                            Toast.makeText(NewEntry.this, "Cancelled...", Toast.LENGTH_SHORT).show();
                        }

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
 saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper db = new DbHelper(Prompt1.this);
                // create a return list (define the list)
                // List<NewEntryModel> saved = DbHelper.getAllList();
            }
        });
*/


