package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = library.getLibrarian();

        Scanner Main_Input = new Scanner(System.in);
        Main_Input.useDelimiter("\n");

        String Main_Input_Result;
        String Side_Input_Result;

        System.out.println("Library Portal Initialized…");
        System.out.println("---------------------------------");

        while(true){
            System.out.println("1.  Enter as a librarian");
            System.out.println("2.  Enter as a member");
            System.out.println("3.  Exit");
            System.out.println("---------------------------------");

            Main_Input_Result = Main_Input.next();
            if(Main_Input_Result.equals("1")){
                while(true){
                    System.out.println("---------------------------------");
                    System.out.println("1.  Register a member");
                    System.out.println("2.  Remove a member");
                    System.out.println("3.  Add a book");
                    System.out.println("4.  Remove a book");
                    System.out.println("5.  View all members along with their books and fines to be paid");
                    System.out.println("6.  View all books");
                    System.out.println("7.  Back");
                    System.out.println("---------------------------------");

                    Side_Input_Result = Main_Input.next();
                    if(Side_Input_Result.equals("3")){
                        librarian.addBook();
                    }
                    else if(Side_Input_Result.equals("4")){
                        librarian.removeBook();
                    }
                    else if(Side_Input_Result.equals("6")){
                        librarian.displayBooks();
                    }
                    else if(Side_Input_Result.equals("7")){
                        System.out.println("---------------------------------");
                        break;
                    }
                    else{
                        System.out.println("---------------------------------");
                        System.out.println("Wrong Input - Input has to be an integer from 1 to 7. Please try again!");
                    }
                }
            }
            else if(Main_Input_Result.equals("3")){
                System.out.println("---------------------------------");
                System.out.println("Thanks for visiting");
                System.out.println("---------------------------------");

                break;
            }
            else{
                System.out.println("---------------------------------");
                System.out.println("Wrong Input - Input has to be 1,2 or 3. Please try again!");
                System.out.println("---------------------------------");
            }
        }
    }
}
