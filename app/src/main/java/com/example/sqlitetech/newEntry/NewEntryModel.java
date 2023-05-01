package com.example.sqlitetech.newEntry;

public class NewEntryModel {
    private long id = -1L;         // to store the primary key
   // private long jEntryId = -1L;    // to store the parent jEntry ID
    private static String jEntryName = ""; // to store subtask name

    public NewEntryModel(long id, String jEntryText, boolean isCompleted) {
        this.id = id;
        this.jEntryName = jEntryText;
        this.isCompleted = isCompleted;
    }

    private static boolean isCompleted;   // to store the status for each entry/item

    // constructor for each property

    // default constructor
    public NewEntryModel() {
    }

    // to string method necessary to print the properties into a single string
    @Override
    public String toString() {
        return "NewEntryModel{" +
                "id=" + id +
                ", jEntryName='" + jEntryName + '\'' +
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

    public static String getjEntryName() {
        return jEntryName;
    }

    public void setjEntryName(String jEntryName) {
        this.jEntryName = jEntryName;
    }

    public static boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
