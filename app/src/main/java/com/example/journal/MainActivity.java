package com.example.journal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
        }

        private class ListAdapter extends ArrayAdapter<String> {
            private int layout; // store reference to layout

            public ListAdapter(Context context, int resource, List<String> objects) {
                super(context, resource, objects);

                layout = resource; // Assign layout to resource
            }

            // getView method
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder mainViewHolder = null;  // ViewHolder reference
                // check if convertView is null
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext()); // then inflate it
                    convertView = inflater.inflate(layout, parent, false);

                    // ViewHolder pattern - hold reference to data inside the listView
                    ViewHolder holder = new ViewHolder();// VieHolder Object
                    holder.title = (ListView) convertView.findViewById(R.id.listView); // Assign title inside viewholder
                    // Set OnclickListen for convertview
                    holder.title.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "Working" + position, Toast.LENGTH_SHORT).show();
                            ;
                        }
                    });

                // reference holder inside convertView so we can retrieve it when convertview isn't null

                    convertView.setTag(holder);
                }
                else{
                    // Assign mainViewHolder a value (retrieve it fromm convertView when not null)
                    mainViewHolder = (ViewHolder)convertView.getTag(); // convert to ViewHolder
                    // set values of list items
                    mainViewHolder.title.getItemAtPosition(position);
                }
                return convertView;
            }
        }

        public class ViewHolder {
            // reference variable for each view in the card
            ListView title;
        }

    /*public View getView(final int position, View convertView, ViewGroup parent)
    {


        Button Button1= (Button)  convertView.findViewById(R.id.BUTTON1_ID);8U

        Button1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Your code that you want to execute on this button click
            }

        });


        return convertView ;
    }*/
    // ListView lv = listView.getListView();
   /* ListView lv = (ListView) findViewById(R.id.listView);
         lv.setAdapter(new ArrayAdapter < String > (this, R.layout.list_item, countries));
         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    // lv.setOnItemClickListener(new OnItemClickListener() {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // When clicked perform some action...
        }
    });*/

        /*@Override
        protected void onListItemClick(ListView listview,View view, int position, long id) {
                // TODO Auto-generated method stub
                super.onListItemClick(listview, view, position, id);

                new AlertDialog.Builder(this)
                        .setTitle("Title")
                        .setMessage("from " + getListView().getItemAtPosition(position))
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                        .show();

                Toast.makeText(ListviewActivity.this,
                        "ListView: " + l.toString() + "\n" +
                                "View: " + v.toString() + "\n" +
                                "position: " + String.valueOf(position) + "\n" +
                                "id: " + String.valueOf(id),
                        Toast.LENGTH_LONG).show();
            }*/
            /*// OnClickListeners for the listView
            ListView lv = getListView();
            lv.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // When clicked perform some action...
                }
            });*/
     //   }

        // Button from the layout file (custom_dialog_layout)
        //ImageButton addButton;

        // Find id
        //addButton = (ImageButton)findViewById(R.id.imageButton);

        // When button is clicked
        //addButton.setOnClickListener
        //}

        //AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
       // builder.setCancelable(true);
        // builder.setView(listView);

       // final AlertDialog dialog = builder.create();
        // Do action to Edit page new Entry
        //EditText text = (EditText) findViewById(R.id.txtDays);

        /*addButton = findViewById(R.id.AddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view){
            Snackbar.make(view, "Add new entry", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
        }

        public boolean onCreateOptionsMenu(Menu menu)
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.popup_menu, menu);
            return true;
        }



        // Pop-Up Menu
        public void showPopUp(View v) { // the view is the button
            // create pop-up menu display - pass context (this)
            // and the anchor = the viewer aka view which is the button (view)
            PopupMenu popUp = new PopupMenu(this, v);
            popUp.setOnMenuItemClickListener(this); // implement on MenuItems on interface (MainActivity)
            popUp.inflate(R.menu.popup_menu); // inflate menu layout xml
            popUp.show(); // pop-up menu will show on click button
        }
*/
    /*@Override
    public boolean onMenuItemClick(MenuItem item) { // handle clicks on each button
        int itemId = item.getItemId();// switch the id for each menu prompt
        if (itemId == R.id.prompt1) { // check for each menu prompt
            Toast.makeText(this, "Item1", Toast.LENGTH_SHORT).show();
            //Intent intent1 = new Intent(this, Prompt1.class);
           // startActivity(intent1);
            return true;
        } else if (itemId == R.id.prompt2) { // check for each menu prompt
          //  Intent intent2 = new Intent(this, Prompt2.class);
          //  startActivity(intent2);
            return true;
        } else if (itemId == R.id.prompt3) { // check for each menu prompt
          //  Intent intent3 = new Intent(this, Prompt3.class);
          //  startActivity(intent3);
            return true;
        } else if (itemId == R.id.prompt4) { // check for each menu prompt
           // Intent intent4 = new Intent(this, Prompt4.class);
            //startActivity(intent4);
            return true;
        } else if (itemId == R.id.prompt5) { // check for each menu prompt
            //Intent intent5 = new Intent(this, Prompt5.class);
            //startActivity(intent5);
            return true;
        } else if (itemId == R.id.prompt6) { // check for each menu prompt
          //  Intent intent6 = new Intent(this, Prompt6.class);
           // startActivity(intent6);
            return true;
        } else if (itemId == R.id.prompt7) { // check for each menu prompt
          //  Intent intent7 = new Intent(this, Prompt7.class);
           // startActivity(intent7);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
