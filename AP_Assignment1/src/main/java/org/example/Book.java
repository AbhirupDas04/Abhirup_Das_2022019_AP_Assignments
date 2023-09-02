package org.example;

import java.time.LocalDateTime;

public class Book {
    private int ID;
    private String title;
    private String author;
    private boolean isBorrowed;
    private boolean isDeleted;
    private LocalDateTime start_time;

    public Book(String title, String author, int ID){
        this.title = title;
        this.author = author;
        this.ID = ID;
        this.isBorrowed = false;
        this.isDeleted = false;
        this.start_time = LocalDateTime.now();
    }

    protected int getID(){
        return this.ID;
    }

    protected void setID(int ID){
        this.ID = ID;
    }

    protected String getTitle(){
        return this.title;
    }

    protected String getAuthor(){
        return this.author;
    }

    protected boolean getBorrowedStatus(){
        return this.isBorrowed;
    }

    protected boolean getDeletedStatus(){
        return this.isDeleted;
    }

    protected void setBorrowedStatus(boolean value){
        this.isBorrowed = value;
    }

    protected void setDeletedStatus(boolean value){
        this.isDeleted = value;
    }

    protected LocalDateTime getStart_time(){
        return this.start_time;
    }

    protected void setStart_time(LocalDateTime start_time){
        this.start_time = start_time;
    }
}
