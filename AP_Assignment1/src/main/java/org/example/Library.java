package com.Library.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    protected Librarian getLibrarian(){
        return this.librarian;
    }

    protected int getCurr_book_id(){
        return this.curr_book_id;
    }

    protected void setCurr_book_id(int curr_book_id){
        if(curr_book_id <= 0){
            System.out.println("Book id can't be less than 1!");
            System.exit(0);
        }
        this.curr_book_id = curr_book_id;
    }

    protected int getCurr_mem_id(){
        return this.curr_mem_id;
    }

    protected void setCurr_mem_id(int curr_mem_id){
        if(curr_mem_id <= 0){
            System.out.println("Member id can't be less than 1!");
            System.exit(0);
        }
        this.curr_mem_id = curr_mem_id;
    }

    protected void addBook(Book book){
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

    protected void addMember(Member member){
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

    protected void addDeletedBook(int index){
        this.list_books.get(index).setDeletedStatus(true);

        this.deleted_indices_book_list.add(index);
        Collections.sort(this.deleted_indices_book_list);
    }

    protected void addDeletedMember(int index){
        this.list_members.get(index).setRemovedStatus(true);

        this.deleted_indices_member_list.add(index);
        Collections.sort(this.deleted_indices_member_list);
    }

    protected Member memberEntry(){
        Scanner Main_Input = new Scanner(System.in);
        Main_Input.useDelimiter("\n");

        System.out.println("---------------------------------");
        System.out.print("Name : ");
        String name = Main_Input.next();
        name = name.trim();
        long phone_no;

        try{
            System.out.print("Phone No : ");
            phone_no = Main_Input.nextLong();
            if(phone_no < 0){
                System.out.println("---------------------------------");
                System.out.println("Not a valid phone number! Returning to menu...");
                System.out.println("---------------------------------");
                return null;
            }
            System.out.println("---------------------------------");
        }
        catch(InputMismatchException e){
            System.out.println("---------------------------------");
            System.out.println("Not a valid phone number! Returning to menu...");
            System.out.println("---------------------------------");
            return null;
        }

        for(Member member : this.list_members){
            if(member.getName().equalsIgnoreCase(name) && !member.getRemovedStatus() && member.getPhone_no() == phone_no){
                System.out.println("Welcome " + member.getName() + ". Member ID: " + member.getID());
                return member;
            }
        }

        System.out.println("Member with Name: " + name + " and Phone No: " + phone_no + " doesn't exist.");
        System.out.println("---------------------------------");

        return null;
    }

    protected ArrayList<Book> getList_books(){
        ArrayList<Book> dup_list_book = (ArrayList<Book>)this.list_books.clone();
        return dup_list_book;
    }

    protected ArrayList<Member> getList_members(){
        ArrayList<Member> dup_list_members = (ArrayList<Member>)this.list_members.clone();
        return dup_list_members;
    }

    protected boolean displayAvailableBooks(int mode){
        ArrayList<Book> list_books = this.list_books;
        int n_books = this.curr_book_id;

        boolean flag;

        if(mode==0){
            System.out.println("---------------------------------");
        }

        flag = false;

        for (int i = 0; i < n_books - 1; i++) {
            Book book = list_books.get(i);
            if (!book.getDeletedStatus() && !book.getBorrowedStatus()) {
                flag = true;
                if(mode!=1){
                    System.out.println("Book ID - " + book.getID());
                    System.out.println("Name - " + book.getTitle());
                    System.out.println("Author - " + book.getAuthor() + "\n");
                }
            }
        }

        if (!flag) {
            if(mode!=1){
                System.out.println("No Books Currently Available!");
            }
            return false;
        }

        return true;
    }

    protected int searchBook(int id, String name){
        if(id > this.curr_book_id){
            return 0;
        }

        Book book = this.list_books.get(id-1);

        if(book.getTitle().equalsIgnoreCase(name)){
            if (book.getBorrowedStatus()){
                return 2;
            }
            if (book.getDeletedStatus()){
                return 0;
            }
            return 1;
        }

        return 0;
    }
}
