package com.example.journal;

import static com.example.journal.newEntry.JournalEntry.listOfEntries;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.journal.newEntry.JournalEntry;
import java.util.ArrayList;


// extends RecycleView Adapter (specify the ViewHolder for the adapter - which gives us access to the views)
public class JournalAdapter extends ArrayAdapter<JournalEntry> {
private DbHelper dbHelper;

public JournalAdapter(Context context, ArrayList<JournalEntry> allEntries) {
        super(context, 0, allEntries);
        dbHelper = new DbHelper(context);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
        convertView = LayoutInflater.from(getContext())
                .inflate(R.layout.entry_card, parent, false);
        }
        /* Same as
         LayoutInflater inflater = LayoutInflater.from(parent.getContext());
         Initialize view for convertView - then Inflate the custom layout
        convertView = inflater.inflate(R.layout.entry_card, parent, false);*/

        // Provide a direct reference for each view with the list entry
        JournalEntry journalEntry = getItem(position);
        // reference the JournalEntry model and get the list
        //journalEntry = listOfEntries.get(position);

        // Cast the variables from the entry_card layout that will show on the main Layout
        TextView cardTitle = convertView.findViewById(R.id.TitleTextView);
        // set the title as the cardTitle
        cardTitle.setText(journalEntry.getTitle());



        ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deleteEntryButton);
        deleteButton.setTag(position);
        deleteButton.setFocusable(false);
        deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        // Handle delete button click
                        int position = (Integer) v.getTag();
                        dbHelper.deleteJournalEntry(Long.parseLong(String.valueOf(position)));
                        Toast.makeText(parent.getContext(), "Entry Deleted", Toast.LENGTH_SHORT).show();
                        remove(journalEntry);
                        notifyDataSetChanged();
                }
        });





        // for the image - Tutorial 3
        // byte[] image = journalEntry.getImage();
        // Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        return convertView;
    }






        /*

        // To populate data into the the entry item through the holder
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            // initialize the view variable up there with view holder
            TextView titleView = holder.titleTextView;
            TextView descriptionView = holder.textDescriptionView;
            TextView dateView = holder.dateView;
            ImageView deleteView = holder.deleteView;

            // Get the data model based on position
            JournalEntry entry = newEntry.get(position);

            // Set item views based on each view and data model
            titleView.setText(entry.getjEntryTitle());
            descriptionView.setText(entry.getjEntryTitle());
            dateView.setText(entry.getCreatedAt());

            // implement setOnClickListener event on each entry view
            holder.itemView.setOnClickListener((View.OnClickListener)new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((JournalEntry)JournalAdapter.this.getList().get(position)).setCompleted(!((JournalEntry)JournalAdapter.this.getList().get(position)).isCompleted());
                  //  JournalAdapter.this.getDbHelper().updateJournalEntry((JournalEntry)JournalAdapter.this.getList().get(position));
                }
            });

            }

*/
    /*public DbHelper getDbHelper() {return this.dbHelper;}

    public ArrayList<JournalEntry> getList() { return this.newEntry; }*/

    //holder.deleteView.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        Toast.makeText(context, )
            //    }
           // })
        //}


}

