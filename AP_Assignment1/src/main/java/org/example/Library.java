package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Library {
    private final Librarian librarian;
    private int curr_book_id;
    private ArrayList<Book> list_books;
    private ArrayList<Integer> deleted_indices_list;

    public Library(){
        this.librarian = new Librarian(this);
        this.curr_book_id = 1;
        this.list_books = new ArrayList<>();
        this.deleted_indices_list = new ArrayList<>();
    }

    public Librarian getLibrarian(){
        return this.librarian;
    }

    public int getCurr_book_id(){
        return curr_book_id;
    }

    public void setCurr_book_id(int curr_book_id){
        this.curr_book_id = curr_book_id;
    }

    public void addBook(Book book){
        if(this.deleted_indices_list.isEmpty()){
            this.list_books.add(book);
            this.setCurr_book_id(this.getCurr_book_id() + 1);
        }
        else{
            book.setID(this.deleted_indices_list.get(0) + 1);
            this.list_books.set(this.deleted_indices_list.get(0),book);
            this.deleted_indices_list.remove(0);
        }
    }

    public void addDeletedObject(int index){
        this.list_books.get(index).setDeletedStatus(true);

        this.deleted_indices_list.add(index);
        Collections.sort(this.deleted_indices_list);
    }

    public ArrayList<Book> getList_books(){
        ArrayList<Book> dup_list_book = (ArrayList<Book>)this.list_books.clone();
        return list_books;
    }

    public ArrayList<Integer> getDeleted_Indices_List(){
        ArrayList<Integer> dup_list_indices = (ArrayList<Integer>) this.deleted_indices_list.clone();
        return deleted_indices_list;
    }
}
