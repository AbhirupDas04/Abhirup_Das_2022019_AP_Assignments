package com.Library.system;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Member {
    private final String name;
    private final int age;
    private int n_books;
    private final long phone_no;
    private int ID;
    private final Library library;
    private ArrayList<Book> list_books;
    private int penalty_money;
    private boolean removed_status;

    public Member(String name, int age, long phone_no, int ID, Library library){
        this.name = name;
        this.age = age;
        this.phone_no = phone_no;
        this.ID = ID;
        this.library = library;
        this.list_books = new ArrayList<>(2);
        this.n_books = 0;
        this.penalty_money = 0;
        this.removed_status = false;
    }

    protected String getName(){
        return this.name;
    }

    protected int getAge(){
        return this.age;
    }

    protected long getPhone_no(){
        return this.phone_no;
    }

    protected int getN_books(){
        return this.n_books;
    }

    protected void setN_books(int n_books){
        if(n_books < 0 || n_books > 2){
            System.out.println("No.of books has to be between 0 and 2.");
            System.exit(0);
        }
        this.n_books = n_books;
    }

    protected int getPenalty_money(){
        return this.penalty_money;
    }

    protected void setPenalty_money(int penalty_money){
        if(penalty_money < 0){
            System.out.println("Fine can't be less than 0!");
            System.exit(0);
        }
        this.penalty_money = penalty_money;
    }

    protected int getID(){
        return this.ID;
    }

    protected void setID(int ID){
        if(ID < 1 || ID > this.library.getCurr_mem_id()){
            System.out.println("ID can't be set to this. It has to be between 1 and " + this.library.getCurr_mem_id());
            System.exit(0);
        }
        this.ID = ID;
    }

    protected Library getLibrary(){
        return this.library;
    }

    protected void setRemovedStatus(boolean value){
        this.removed_status = value;
    }

    protected boolean getRemovedStatus(){
        return this.removed_status;
    }

    protected ArrayList<Book> getList_books(){
        return (ArrayList<Book>)this.list_books.clone();
    }

    protected void listAvailableBooks(){
        this.library.displayAvailableBooks(0);
    }

    protected void listOwnBooks(){
        System.out.println("---------------------------------");

        if(this.n_books == 0){
            System.out.println("No books borrowed!");
            return;
        }

        for(Book book : this.list_books){
            if(book.getBorrowedStatus() && !book.getDeletedStatus()){
                System.out.println("Book ID: " + book.getID());
                System.out.println("Name: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                long book1_time = ChronoUnit.SECONDS.between(book.getStart_time(),LocalDateTime.now());
                System.out.println("Days borrowed: " + book1_time + " days\n");
            }
        }
    }

    protected void issueBook(){
        if(this.n_books == 2){
            System.out.println("---------------------------------");
            System.out.println("You already have issued 2 books. Please return one of them to issue another one!");
            return;
        }

        if(this.penalty_money > 0){
            System.out.println("---------------------------------");
            System.out.println("Please pay the penalty money ₹" + this.penalty_money + " that you owe after which you can issue further books!");
            return;
        }

        if(this.n_books == 1){
            long time_passed = ChronoUnit.SECONDS.between(this.list_books.get(0).getStart_time(),LocalDateTime.now());
            if(time_passed > 10){
                System.out.println("---------------------------------");
                System.out.println("Please return the book " + this.list_books.get(0).getTitle() + " by " + this.list_books.get(0).getAuthor() + " which you have issued as the due date has passed by " + (time_passed - 10) + " days. After returning, you will be able to issue a new book!");
                return;
            }
        }

        System.out.println("---------------------------------");
        if(!this.library.displayAvailableBooks(1)){
            System.out.println("Sorry, no books currently available. Please come back again later!");
            return;
        }

        System.out.println("Here's the list of available books: \n");
        this.library.displayAvailableBooks(2);
        System.out.println("---------------------------------");
        System.out.println("Which book do you want to issue:");

        Scanner Main_Input = new Scanner(System.in);
        Main_Input.useDelimiter("\n");

        int book_id;

        while(true){
            try{
                System.out.print("Book ID: ");
                Main_Input = new Scanner(System.in);
                Main_Input.useDelimiter("\n");
                book_id = Main_Input.nextInt();

                if(book_id <= 0){
                    System.out.println("---------------------------------");
                    System.out.println("Not a valid ID(has to be >=1)");
                    System.out.println("---------------------------------");
                    continue;
                }
            }
            catch(InputMismatchException e){
                System.out.println("---------------------------------");
                System.out.println("Not an integer. Please try again!");
                System.out.println("---------------------------------");
                continue;
            }

            break;
        }

        System.out.print("Book Name: ");
        String book_name = Main_Input.next();
        book_name = book_name.trim();

        System.out.println("---------------------------------");

        switch (this.library.searchBook(book_id, book_name)) {
            case 0 -> System.out.println("Book with id " + book_id + " and name " + book_name + " doesn't exist.");
            case 1 -> {
                Book book = this.library.getList_books().get(book_id - 1);
                book.setBorrowedStatus(true);
                book.setStart_time(LocalDateTime.now());
                this.n_books++;
                this.list_books.add(book);
                System.out.println("Book Issued Successfully!");
            }
            case 2 ->
                    System.out.println("Book with id " + book_id + " and name " + book_name + " has been borrowed. Please come back later.");
        }
    }

    protected void returnBook(){
        System.out.println("---------------------------------");

        if(this.n_books == 0){
            System.out.println("You haven't borrowed any books!");
            return;
        }

        System.out.println("List of books borrowed:\n");

        int i = 0;
        long book1_time = 0;
        long book2_time = 0;

        for(Book book : this.list_books){
            i++;

            if(book.getBorrowedStatus() && !book.getDeletedStatus()){
                System.out.println("Book ID: " + book.getID());
                System.out.println("Name: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                if(i == 1){
                    book1_time = ChronoUnit.SECONDS.between(book.getStart_time(),LocalDateTime.now());
                    System.out.println("Days borrowed: " + book1_time + " days\n");
                }
                else{
                    book2_time = ChronoUnit.SECONDS.between(book.getStart_time(),LocalDateTime.now());
                    System.out.println("Days borrowed: " + book2_time + " days\n");
                }
            }
        }

        Scanner Main_Input = new Scanner(System.in);
        Main_Input.useDelimiter("\n");

        System.out.println("---------------------------------");
        System.out.print("Book ID: ");

        int id;

        while(true){
            try{
                Main_Input = new Scanner(System.in);
                Main_Input.useDelimiter("\n");
                id = Main_Input.nextInt();
                System.out.println("---------------------------------");
            }
            catch(InputMismatchException e){
                System.out.println("---------------------------------");
                System.out.println("Enter a valid integer!");
                System.out.println("---------------------------------");
                continue;
            }

            break;
        }

        boolean flag = false;

        for(int j = 0; j < this.n_books; j++){
            Book book = this.list_books.get(j);
            if(book.getBorrowedStatus() && !book.getDeletedStatus() && book.getID() == id){
                flag = true;

                book.setBorrowedStatus(false);
                this.list_books.remove(book);
                this.n_books--;

                long days;

                if(j == 0){
                    days = book1_time - 10;
                }
                else{
                    days = book2_time - 10;
                }

                int money = (int)(days*3);
                this.penalty_money += money;

                if(days > 0){
                    System.out.println("Book ID: " + id + " successfully returned. " + (days * 3) + " Rupees has been charged for a delay of " + days + " days.");
                }
                else{
                    System.out.println("Book ID: " + id + " successfully returned.");
                }

                return;
            }
        }

        if(!flag){
            System.out.println("You haven't borrowed a book with ID " + id);
        }
    }

    protected void payFine(){
        System.out.println("---------------------------------");

        if(this.penalty_money == 0){
            System.out.println("You have no fine to pay!");
            return;
        }

        System.out.println("You had a total fine of Rs. " + this.penalty_money + ". It has been paid successfully!");
        this.penalty_money = 0;
    }
}
