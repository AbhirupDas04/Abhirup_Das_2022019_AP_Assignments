package com.Library.system;

import java.time.LocalDateTime;

public class Book {
    private int ID;
    private final String title;
    private final String author;
    private final Library library;
    private boolean isBorrowed;
    private boolean isDeleted;
    private LocalDateTime start_time;

    public Book(String title, String author, int ID, Library library){
        this.title = title;
        this.author = author;
        this.ID = ID;
        this.library = library;
        this.isBorrowed = false;
        this.isDeleted = false;
        this.start_time = LocalDateTime.now();
    }

    protected int getID(){
        return this.ID;
    }

    protected void setID(int ID){
        if(ID < 1 || ID > this.library.getCurr_book_id()){
            System.out.println("ID can't be set to this. It has to be between 1 and " + this.library.getCurr_book_id());
            System.exit(0);
        }
        this.ID = ID;
    }

    protected String getTitle(){
        return this.title;
    }

    protected String getAuthor(){
        return this.author;
    }

    protected Library getLibrary(){
        return this.library;
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
