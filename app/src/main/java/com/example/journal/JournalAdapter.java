package com.example.journal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.journal.newEntry.JournalEntry;
import java.util.ArrayList;


// extends RecycleView Adapter (specify the ViewHolder for the adapter - which gives us access to the views)
public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.ViewHolder>{

    View rootView;

    Context context;
    ArrayList<JournalEntry> newEntry; // member variable for the list of entries

    ArrayList<String> enteredEntry; // List where we will store the user input

    // Next fill in the adapter - pass in the list into the constructor
    // constructor with list passed from MainActivity when Adapter is called
    public JournalAdapter(ArrayList<JournalEntry> allEntries) {
        this.newEntry = allEntries;
    }

    //Recyclerview ViewHolder
    // Provide a direct reference for each view with the list entry
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // member variables for each view within each row for an entry
        TextView titleTextView;
        TextView textDescriptionView;
        TextView dateView;
        ImageView deleteView;


        // constructor for the entire row and looks for each view for the variables we created
        public ViewHolder(View itemView) {
            super(itemView);

            // finding view by view id.
            titleTextView = (TextView)itemView.findViewById(R.id.TitleTextView);
            textDescriptionView = (TextView)itemView.findViewById(R.id.descTextView);
            dateView = (TextView)itemView.findViewById(R.id.dateTextView);
            deleteView = (ImageView) itemView.findViewById(R.id.deleteImage);
        }
    }


    // To inflate layout from XML and return the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Initialize view for recyclerview
        View newEntryView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.entry_card, parent, false);
        /*Same as
         LayoutInflater inflater = LayoutInflater.from(parent.getContext());
         Initialize view for recyclerview - thenInflate the custom layout
        View newEntryView = inflater.inflate(R.layout.entry_card, parent, false);*/

        context = parent.getContext();
        rootView = ((Activity)context).getWindow().getDecorView()
                .findViewById(android.R.id.content);

        // attach view to ViewHolder - Return a new instance of the ViewHolder
        ViewHolder viewHolder = new ViewHolder(newEntryView);
        return viewHolder;
        }

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
           // holder.deleteView.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        Toast.makeText(context, )
            //    }
           // })
        }

    @Override
    public int getItemCount() {
        return newEntry.size();
    }
}
