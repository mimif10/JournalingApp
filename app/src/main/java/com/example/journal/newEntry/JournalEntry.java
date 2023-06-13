package com.example.journal.newEntry;

// Will hold the dataSet that will be displayed in androidx Recyclerview.
// implementing the values from DashboardModel Class
public class JournalEntry { // this is the Note
    private long id = -1L;         // to store the primary key

    // to store the parent jEntry ID
    private long jEntryId = -1L; // id

    // to store subtask name
    private static String jEntryTitle = "title"; // title

    private static String jEntryDescription = "description"; // description
    public static String createdAt = "date"; // jourDate = date
    public JournalEntry(long id, String jEntryText, boolean isCompleted) {

        this.id = id;
        this.jEntryId = jEntryId;
        this.jEntryTitle = jEntryText;
        this.jEntryDescription =  jEntryDescription;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }

    private static boolean isCompleted;   // to store the status for each entry/item

    // constructor for each property

    // default constructor
    public JournalEntry() {
    }

    // to string method necessary to print the properties into a single string
    @Override
    public String toString() {
        return "NewEntryModel{" +
                "id=" + id +
                ", jEntryName='" + jEntryTitle + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }

    // getters and setters for each property

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getjEntryTitle() { return jEntryTitle; }

    public void setjEntryTitle(long id) {
        this.jEntryId = jEntryId;
    }

    public String getjEntryDescription() {
        return jEntryDescription;
    }

    public void setjEntryDescription(String jEntryDescription) {
        this.jEntryDescription = jEntryDescription;
    }

    public static boolean isCompleted() {
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
    }
}
