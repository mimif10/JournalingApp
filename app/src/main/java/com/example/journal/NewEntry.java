package com.example.journal;


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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewEntry extends AppCompatActivity {
    public DbHelper dbHelper;
    // reference to all the buttons and controls on the layout
    // member variable for the main screen from the class
    EditText newTextEntry;
    FloatingActionButton saveButton;
    ImageView imageView;
    ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        // On Click Listeners for the button
        newTextEntry = findViewById(R.id.textEditor);
        imageView = findViewById(R.id.imageView);
        imageButton = findViewById(R.id.imageButton);

        //imageButton.setEnabled(false); // disable button then grant permission

        // if user has not granted permission
        if(ActivityCompat.checkSelfPermission(NewEntry.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
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








        saveButton = findViewById(R.id.saveButton);

        // Button Listeners for the save Button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(NewEntry.this);
                // create a return list (define the list)
                // List<NewEntryModel> saved = DbHelper.getAllList();

            }
        });


    }
}