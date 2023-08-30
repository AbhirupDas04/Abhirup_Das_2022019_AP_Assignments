package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Librarian {
    private final Library library;

    public Librarian(Library library){
        this.library = library;
    }

    public void addBook(){
        Scanner Main_Input = new Scanner(System.in);
        Main_Input.useDelimiter("\n");

        System.out.println("---------------------------------");
        System.out.print("1.    Book Title: ");
        String title = Main_Input.next();

        System.out.print("2.    Author: ");
        String author = Main_Input.next();

        int n_copies;

        while(true){
            System.out.print("3.    Copies: ");
            try{
                Scanner Secondary_Input = new Scanner(System.in);
                Secondary_Input.useDelimiter("\n");

                n_copies = Secondary_Input.nextInt();
                if(n_copies < 1){
                    System.out.println("---------------------------------");
                    System.out.println("Input not a valid no.of copies. Please enter a valid number!");
                    System.out.println("---------------------------------");
                    continue;
                }
            }
            catch (InputMismatchException e){
                System.out.println("---------------------------------");
                System.out.println("Input not an integer. Please enter a valid(+ve integer) no. of copies!");
                System.out.println("---------------------------------");
                continue;
            }

            Book book;

            for(int i = 0; i < n_copies; i++){
                book = new Book(title,author,this.library.getCurr_book_id());
                this.library.addBook(book);
            }

            System.out.println("---------------------------------");
            if(n_copies == 1){
                System.out.println("Book Added Successfully!");
            }
            else{
                System.out.println("Books Added Successfully!");
            }

            break;
        }
    }

    public void displayBooks(){
        ArrayList<Book> list_books = this.library.getList_books();
        int n_books = this.library.getCurr_book_id();

        System.out.println("---------------------------------");

        boolean flag = false;

        for(int i = 0; i < n_books - 1; i++){
            Book book = list_books.get(i);
            if(!book.getBorrowedStatus() && !book.getDeletedStatus()){
                flag = true;
                System.out.println("Book ID - " + book.getID());
                System.out.println("Name - " + book.getTitle());
                System.out.println("Author - " + book.getAuthor() + "\n");
            }
        }

        if(!flag){
            System.out.println("No Books Currently Available!");
        }
    }

    public void removeBook(){
        int book_id;

        System.out.println("---------------------------------");

        while(true){
            System.out.print("Book ID: ");

            Scanner Main_Input = new Scanner(System.in);
            Main_Input.useDelimiter("\n");

            try{
                book_id = Main_Input.nextInt();
                if(book_id < 1){
                    System.out.println("---------------------------------");
                    System.out.println("Input not a valid ID (not >= 1). Please try again!");
                    System.out.println("---------------------------------");
                    continue;
                }
            }
            catch(InputMismatchException e){
                System.out.println("---------------------------------");
                System.out.println("Input not a valid ID (not an integer). Please try again!");
                System.out.println("---------------------------------");
                continue;
            }

            break;
        }

        if(book_id >= this.library.getCurr_book_id()){
            System.out.println("---------------------------------");
            System.out.println("Book does not exist in library!");
            return;
        }

        ArrayList<Book> list_books = this.library.getList_books();

        if(list_books.get(book_id-1).getBorrowedStatus()){
            System.out.println("---------------------------------");
            System.out.println("Book currently is being borrowed by a member. Please remove after they have returned it!");
            return;
        }

        if(list_books.get(book_id-1).getDeletedStatus()){
            System.out.println("---------------------------------");
            System.out.println("Book has been removed already!");
            return;
        }

        this.library.addDeletedObject(book_id-1);

        System.out.println("---------------------------------");
        System.out.println("Book removed successfully!");
    }
}
