package com.Library.system;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Librarian {
    private final Library library;

    public Librarian(Library library){
        this.library = library;
    }

    protected Library getLibrary(){
        return this.library;
    }

    protected void registerMember(){
        String name;
        int age;
        long phone_no;

        Scanner Main_Input = new Scanner(System.in);
        Main_Input.useDelimiter("\n");

        System.out.println("---------------------------------");

        while(true){
            Main_Input = new Scanner(System.in);
            Main_Input.useDelimiter("\n");

            System.out.print("1. Name: ");
            name = Main_Input.next();
            name = name.trim();

            if(name.isEmpty()){
                System.out.println("---------------------------------");
                System.out.println("Member with no name not allowed. Please enter again!");
                System.out.println("---------------------------------");
                continue;
            }

            break;
        }

        while(true){
            System.out.print("2. Age: ");
            try{
                Scanner Secondary_Input = new Scanner(System.in);
                Secondary_Input.useDelimiter("\n");

                age = Integer.parseInt(Secondary_Input.nextLine());
                if(age <= 0){
                    System.out.println("---------------------------------");
                    System.out.println("Input not a valid age. Please enter again!");
                    System.out.println("---------------------------------");
                    continue;
                }
            }
            catch (NumberFormatException e){
                System.out.println("---------------------------------");
                System.out.println("Input not a valid age(Age has to be an Integer). Please enter again!");
                System.out.println("---------------------------------");
                continue;
            }
            catch (NoSuchElementException e){
                System.out.println("---------------------------------");
                System.out.println("Input not a valid age(Age has to be an Integer). Please enter again!");
                System.out.println("---------------------------------");
                continue;
            }

            break;
        }

        while(true){
            System.out.print("3. Phone no: ");
            try{
                Scanner Secondary_Input = new Scanner(System.in);
                Secondary_Input.useDelimiter("\n");

                phone_no = Long.parseLong(Secondary_Input.nextLine());
                if(phone_no < 0){
                    System.out.println("---------------------------------");
                    System.out.println("Input not a valid phone number. Please enter again!");
                    System.out.println("---------------------------------");
                    continue;
                }

                ArrayList<Member> list_members = this.library.getList_members();

                for(Member member : list_members){
                    if(member.getPhone_no() == phone_no && !member.getRemovedStatus()){
                        System.out.println("---------------------------------");
                        System.out.println("Phone number already registered! Registration Failed!");
                        return;
                    }
                }
            }
            catch (NumberFormatException e){
                System.out.println("---------------------------------");
                System.out.println("Input not a valid phone number. Please enter again!");
                System.out.println("---------------------------------");
                continue;
            }
            catch (NoSuchElementException e){
                System.out.println("---------------------------------");
                System.out.println("Input not a valid phone number. Please enter again!");
                System.out.println("---------------------------------");
                continue;
            }

            break;
        }

        Member member = new Member(name,age,phone_no,this.library.getCurr_mem_id(),this.library);
        this.library.addMember(member);

        System.out.println("---------------------------------");
        System.out.println("Member Successfully Registered with ID - " + member.getID());
    }

    protected void removeMember(){
        int mem_id;

        System.out.println("---------------------------------");

        while(true){
            System.out.print("Member ID: ");

            Scanner Main_Input = new Scanner(System.in);
            Main_Input.useDelimiter("\n");

            try{
                mem_id = Integer.parseInt(Main_Input.nextLine());;
                if(mem_id < 1){
                    System.out.println("---------------------------------");
                    System.out.println("Input not a valid ID (not >= 1). Please try again!");
                    System.out.println("---------------------------------");
                    continue;
                }
            }
            catch(NumberFormatException e){
                System.out.println("---------------------------------");
                System.out.println("Input not a valid ID (not an integer). Please try again!");
                System.out.println("---------------------------------");
                continue;
            }
            catch(NoSuchElementException e){
                System.out.println("---------------------------------");
                System.out.println("Input not a valid ID (not an integer). Please try again!");
                System.out.println("---------------------------------");
                continue;
            }

            break;
        }

        if(mem_id >= this.library.getCurr_mem_id()){
            System.out.println("---------------------------------");
            System.out.println("Member does not exist in library!");
            return;
        }

        ArrayList<Member> list_members = this.library.getList_members();

        String name = list_members.get(mem_id-1).getName();

        if(list_members.get(mem_id-1).getRemovedStatus()){
            System.out.println("---------------------------------");
            System.out.println("Member has been removed already!");
            return;
        }

        if(list_members.get(mem_id-1).getN_books() > 0){
            System.out.println("---------------------------------");
            System.out.println("Member has books to return. You may remove him after the return of the books!");
            return;
        }

        if(list_members.get(mem_id-1).getPenalty_money() > 0){
            System.out.println("---------------------------------");
            System.out.println("Member hasn't paid his pending fines of the sum " + list_members.get(mem_id-1).getPenalty_money() + " Rupees. You may him remove him after full payment of fine!");
            return;
        }

        this.library.addDeletedMember(mem_id-1);

        System.out.println("---------------------------------");
        System.out.println("Member " + name + " removed successfully!");
    }

    protected void addBook(){
        String title;
        String author;
        int n_copies;

        Scanner Main_Input = new Scanner(System.in);
        Main_Input.useDelimiter("\n");

        System.out.println("---------------------------------");

        while(true) {
            Main_Input = new Scanner(System.in);
            Main_Input.useDelimiter("\n");

            System.out.print("1.    Book Title: ");
            title = Main_Input.next();
            title = title.trim();

            if(title.isEmpty()){
                System.out.println("---------------------------------");
                System.out.println("Blank Title not allowed. Please enter again!");
                System.out.println("---------------------------------");
                continue;
            }

            break;
        }

        while(true){
            Main_Input = new Scanner(System.in);
            Main_Input.useDelimiter("\n");

            System.out.print("2.    Author: ");
            author = Main_Input.next();
            author = author.trim();

            if(author.isEmpty()){
                System.out.println("---------------------------------");
                System.out.println("Author with no name not allowed. Please enter again!");
                System.out.println("---------------------------------");
                continue;
            }

            break;
        }

        while(true){
            System.out.print("3.    Copies: ");
            try{
                Scanner Secondary_Input = new Scanner(System.in);
                Secondary_Input.useDelimiter("\n");

                n_copies = Integer.parseInt(Secondary_Input.nextLine());
                if(n_copies < 1){
                    System.out.println("---------------------------------");
                    System.out.println("Input not a valid no.of copies. Please enter a valid number(+ve integer)!");
                    System.out.println("---------------------------------");
                    continue;
                }
            }
            catch (NumberFormatException e){
                System.out.println("---------------------------------");
                System.out.println("Input not an integer. Please enter a valid(+ve integer) no. of copies!");
                System.out.println("---------------------------------");
                continue;
            }
            catch (NoSuchElementException e){
                System.out.println("---------------------------------");
                System.out.println("Input not an integer. Please enter a valid(+ve integer) no. of copies!");
                System.out.println("---------------------------------");
                continue;
            }

            Book book;

            for(int i = 0; i < n_copies; i++){
                book = new Book(title,author,this.library.getCurr_book_id(),this.library);
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

    protected void removeBook(){
        int book_id;

        System.out.println("---------------------------------");

        while(true){
            System.out.print("Book ID: ");

            Scanner Main_Input = new Scanner(System.in);
            Main_Input.useDelimiter("\n");

            try{
                book_id = Integer.parseInt(Main_Input.nextLine());
                if(book_id < 1){
                    System.out.println("---------------------------------");
                    System.out.println("Input not a valid ID (not >= 1). Please try again!");
                    System.out.println("---------------------------------");
                    continue;
                }
            }
            catch(NumberFormatException e){
                System.out.println("---------------------------------");
                System.out.println("Input not a valid ID (not an integer). Please try again!");
                System.out.println("---------------------------------");
                continue;
            }
            catch(NoSuchElementException e){
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

        this.library.addDeletedBook(book_id-1);

        System.out.println("---------------------------------");
        System.out.println("Book " + list_books.get(book_id-1).getTitle() + " by " + list_books.get(book_id-1).getAuthor() + " removed successfully!");
    }

    protected void displayMembers(){
        ArrayList<Member> list_members = this.library.getList_members();
        boolean flag = false;

        System.out.println("---------------------------------");

        int i = 0;

        for(Member member:list_members){
            if(!member.getRemovedStatus()){
                i++;
                flag = true;

                System.out.println(i + ") Member ID - " + member.getID());
                System.out.println("Name - " + member.getName());
                System.out.println("Age - " + member.getAge());
                System.out.println("Phone No. - " + member.getPhone_no());
                System.out.println("No. of books borrowed - " + member.getN_books());
                System.out.println("Fines still to be paid - " + member.getPenalty_money() + " Rs.");


                if(member.getN_books() > 0){
                    System.out.println("\nBooks borrowed - \n");
                }
                else{
                    System.out.println("");
                    continue;
                }

                for(Book book:member.getList_books()){
                    if(book.getBorrowedStatus() && !book.getDeletedStatus()){
                        System.out.println("Book ID - " + book.getID());
                        System.out.println("Name - " + book.getTitle());
                        System.out.println("Author - " + book.getAuthor());
                        long book_time = ChronoUnit.SECONDS.between(book.getStart_time(), LocalDateTime.now());
                        System.out.println("Days borrowed - " + book_time + " days\n");
                    }
                }
            }
        }

        if(!flag){
            System.out.println("No members registered!");
        }
    }

    protected void displayBooks(){
        ArrayList<Book> list_books = this.library.getList_books();
        int n_books = this.library.getCurr_book_id();

        System.out.println("---------------------------------");

        boolean flag = false;

        for(int i = 0; i < n_books - 1; i++){
            Book book = list_books.get(i);
            if(!book.getDeletedStatus()){
                flag = true;
                System.out.println("Book ID - " + book.getID());
                System.out.println("Name - " + book.getTitle());
                System.out.println("Author - " + book.getAuthor() + "\n");
            }
        }

        if(!flag){
            System.out.println("No Books in Library!");
        }
    }
}
