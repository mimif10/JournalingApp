package com.example.journal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.journal.newEntry.JournalEntry;
import java.util.ArrayList;


// extends RecycleView Adapter (specify the ViewHolder for the adapter - which gives us access to the views)
public class JournalAdapter extends BaseAdapter {
    Context context;
    View rootView;
    ArrayList<JournalEntry> listOfEntries; // member variable for the list of entries
                                          // List where we will store the user input
    DbHelper dbHelper;

    /** Next fill in the adapter - pass in context and the list into the constructor*/
    // constructor with list passed from MainActivity when Adapter is called
    public JournalAdapter(Context context, ArrayList<JournalEntry> allEntries) {
        this.context = context;
        this.listOfEntries = allEntries;
    }

    /** How many items are in the data set represented by this Adapter.
     - return Count of items.*/
    @Override
    public int getCount() {
        return listOfEntries.size();
    }

    /** Get the data item associated with the specified position in the data set.
     * @param position Position of the item whose data we want within the adapter's data set.
     - return The data at the specified position*/
    @Override
    public Object getItem(int position) {
        return listOfEntries.get(position);
    }

    /**Get the row id associated with the specified position in the list.
     * @param position The position of the item within the adapter's data set whose row id we want.
     - return The id of the item at the specified position.*/
    @Override
    public long getItemId(int position) {
        return position;
    }

    /** Get a View that displays the data at the specified position in the data set. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)} to specify a root view and to prevent attachment to the root.
     * @param position    The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     */
    @Override //  constructor for the entire row and looks for each view for the variables we created
    public View getView(int position, View convertView, ViewGroup parent) {
        // Create a View manually or inflate it from an XML layout file.
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_card, parent, false);

        /* Same as
         LayoutInflater inflater = LayoutInflater.from(parent.getContext());
         Initialize view for convertView - then Inflate the custom layout
        convertView = inflater.inflate(R.layout.entry_card, parent, false);*/

        // Provide a direct reference for each view with the list entry
        // Cast the variales from the entry_card layout that will show on the main Layout
        TextView cardTitle = convertView.findViewById(R.id.TitleTextView);
        //TextView cardDesc = convertView.findViewById(R.id.descTextView);
        TextView cadrDate = convertView.findViewById(R.id.dateTextView);
        ImageView deleteButton = convertView.findViewById(R.id.deleteEntryImage);

        // reference the JournalEntry model and get the list
        JournalEntry journalEntry = listOfEntries.get(position);
        String title = journalEntry.getjEntryTitle(); // get the title from the model

        // set the title as the cardTitle
        cardTitle.setText(title);

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

