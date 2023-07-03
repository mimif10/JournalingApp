package com.example.journal.newEntry;

import java.util.ArrayList;

// Will hold the dataSet that will be displayed in androidx Recyclerview.
// implementing the values from DashboardModel Class
public class JournalEntry {
    public static String JOURNAL_EDIT_EXTRA =  "NoteEdit";
    public static ArrayList<JournalEntry> listOfEntries = new ArrayList();
    private int id;
    private String title;
    private  String date;
    public String getDate() {
        return date;
    }




   /* public JournalEntry(String title) {
        this.title = title;
    }*/
    public JournalEntry(String title, String date) {
        super();
        this.title = title;
        this.date = date;
    }

    public static ArrayList<JournalEntry> nonDeletedNotes()
    {
        ArrayList<JournalEntry> nonDeleted = new ArrayList<>();
        for(JournalEntry note : listOfEntries)
        {
                nonDeleted.add(note);
        }

        return nonDeleted;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // getters and setters for each property
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static JournalEntry getNoteForID(int passedNoteID)
    {
        for (JournalEntry note : listOfEntries)
        {
            if(note.getId() == passedNoteID)
                return note;
        }
        return null;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String jEntryTitle) {
        this.title = title;
    }

    //  this.jEntryDesc = desc;
    // this.image = pic;
    // this.isCompleted = isCompleted;
    // this.createdAt = createdAt;

   /* public String getjEntryDesc() {
        return jEntryDesc;
    }
    public void setjEntryDesc(String jEntryDesc) {
        this.jEntryDesc = jEntryDesc;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }*/

    // private long id = -1L;         // to store the primary key
    // to store the parent jEntry ID
   // private long jEntryId = -1L; // id

    // to store subtask name
   // private String jEntryTitle = "title"; // title

   // private static String jEntryDescription = "description"; // description
   // public String createdAt = "date"; // jourDate = date
  //  private boolean isCompleted;   // to store the status for each entry/item



    // constructors for each property


    // to string method necessary to print the properties into a single string
 /*   @Override
    public String toString() {
        return "NewEntryModel{" +
                  "id=" + id +
                 "jEntryId=" +
                //jEntryId +
                "jEntryTitle =" + jEntryTitle +
              //  ", jEntryDescription='" + jEntryDescription + '\'' +
                ", isCompleted="
                //+ isCompleted +
            //    ", createdAt " + createdAt +
                '}';
    }*/


  /*  public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }*/
}
