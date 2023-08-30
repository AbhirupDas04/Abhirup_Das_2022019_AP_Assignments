package org.example;

public class Book {
    private int ID;
    private String title;
    private String author;
    private boolean isBorrowed;
    private boolean isDeleted;

    public Book(String title, String author, int ID){
        this.title = title;
        this.author = author;
        this.ID = ID;
        this.isBorrowed = false;
        this.isDeleted = false;
    }

    public int getID(){
        return this.ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public boolean getBorrowedStatus(){
        return this.isBorrowed;
    }

    public boolean getDeletedStatus(){
        return this.isDeleted;
    }

    public void setDeletedStatus(boolean value){
        this.isDeleted = value;
    }
}
