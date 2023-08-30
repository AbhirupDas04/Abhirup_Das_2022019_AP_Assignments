package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Library {
    private final Librarian librarian;
    private int curr_book_id;
    private int curr_mem_id;
    private ArrayList<Book> list_books;
    private ArrayList<Integer> deleted_indices_book_list;
    private ArrayList<Integer> deleted_indices_member_list;
    private ArrayList<Member> list_members;

    public Library(){
        this.librarian = new Librarian(this);
        this.curr_book_id = 1;
        this.curr_mem_id = 1;
        this.list_books = new ArrayList<>();
        this.deleted_indices_book_list = new ArrayList<>();
        this.list_members = new ArrayList<>();
        this.deleted_indices_member_list = new ArrayList<>();
    }

    public Librarian getLibrarian(){
        return this.librarian;
    }

    public int getCurr_book_id(){
        return this.curr_book_id;
    }

    public void setCurr_book_id(int curr_book_id){
        this.curr_book_id = curr_book_id;
    }

    public int getCurr_mem_id(){
        return this.curr_mem_id;
    }

    public void setCurr_mem_id(int curr_mem_id){
        this.curr_mem_id = curr_mem_id;
    }

    public void addBook(Book book){
        if(this.deleted_indices_book_list.isEmpty()){
            this.list_books.add(book);
            this.setCurr_book_id(this.getCurr_book_id() + 1);
        }
        else{
            book.setID(this.deleted_indices_book_list.get(0) + 1);
            this.list_books.set(this.deleted_indices_book_list.get(0),book);
            this.deleted_indices_book_list.remove(0);
        }
    }

    public void addMember(Member member){
        if(this.deleted_indices_member_list.isEmpty()){
            this.list_members.add(member);
            this.setCurr_mem_id(this.getCurr_mem_id() + 1);
        }
        else{
            member.setID(this.deleted_indices_member_list.get(0) + 1);
            this.list_members.set(this.deleted_indices_member_list.get(0),member);
            this.deleted_indices_member_list.remove(0);
        }
    }

    public void addDeletedBook(int index){
        this.list_books.get(index).setDeletedStatus(true);

        this.deleted_indices_book_list.add(index);
        Collections.sort(this.deleted_indices_book_list);
    }

    public void addDeletedMember(int index){
        this.list_members.get(index).setRemovedStatus(true);

        this.deleted_indices_member_list.add(index);
        Collections.sort(this.deleted_indices_member_list);
    }

    public ArrayList<Book> getList_books(){
        ArrayList<Book> dup_list_book = (ArrayList<Book>)this.list_books.clone();
        return dup_list_book;
    }

    public ArrayList<Member> getList_members(){
        ArrayList<Member> dup_list_members = (ArrayList<Member>)this.list_members.clone();
        return dup_list_members;
    }

    public int getNum_Members(){
        return this.list_members.size();
    }
}
