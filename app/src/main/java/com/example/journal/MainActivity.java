package com.example.journal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journal.Prompts.Prompt1;
import com.example.journal.Prompts.Prompt2;
import com.example.journal.Prompts.Prompt3;
import com.example.journal.Prompts.Prompt4;
import com.example.journal.Prompts.Prompt5;
import com.example.journal.Prompts.Prompt6;
import com.example.journal.Prompts.Prompt7;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // FloatingActionButton addButton;

    // Add data to Array String
    String[] prompts = {"Today I learned...",
            "Something that made me smile today...",
            "Three things I am grateful for...",
            "Today I realized...",
            "Today, this happened...",
            "What happened today?...",
            "Add new journal entry..."};

    // Add data to Listview
    ListView listView;
    TextView save_Button;
    TextView cancel_Button;
    EditText textEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ArrayAdapter<String> adapter;

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
                        //textEntry = layoutView.findViewById(R.id.textEditor); // in new entry layout
                        alertDialog.setView(layoutView1);
                        AlertDialog dialog = alertDialog.create();
                        dialog.show();
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
                        View layoutView4= getLayoutInflater().inflate(R.layout.activity_prompt4, null);
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
}


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