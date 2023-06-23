package com.example.journal.newEntry;

import java.util.ArrayList;
import java.util.List;

// Will hold the dataSet that will be displayed in androidx Recyclerview.
// implementing the values from DashboardModel Class
public class JournalEntry {// this is the Note
   // private ArrayList <List> newList = (new ArrayList();
    long id;
    String jEntryTitle;
  //  String jEntryDesc;
   // byte[] image;


    public JournalEntry(long id, String title) {
        this.id = id;
        this.jEntryTitle = title;
      //  this.jEntryDesc = desc;
       // this.image = pic;
        // this.isCompleted = isCompleted;
        // this.createdAt = createdAt;
    }
// getters and setters for each property
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getjEntryTitle() {
        return this.jEntryTitle;
    }
    public void setjEntryTitle(String jEntryTitle) {
        this.jEntryTitle = jEntryTitle;
    }

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



    // public long getjEntryId() {return jEntryId;}
   // public void setjEntryId(long jEntryId) {this.jEntryId = jEntryId;}



  //  public String getjEntryDescription() {
  //      return jEntryDescription;
  //  }

   // public void setjEntryDescription(String jEntryDescription) {
   //     this.jEntryDescription = jEntryDescription;
   // }

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
