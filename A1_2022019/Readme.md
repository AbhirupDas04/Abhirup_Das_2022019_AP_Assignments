AP ASSIGNMENT-1 - Library Management System by Abhirup Das(2022019 - AP - Sec-A)


Steps to Run my Project:

1. Download the zip file and extract its contents.
2. Open Command Prompt if you are a Windows User or any other terminal if you use a Linux-based device.
3. Cd to the Downloaded file.
4. Run mvn clean package if you want to generate a new jar file though I have already generated it in the target folder.
5. Cd to the 'Target' folder.
6. Run the command 'java -jar AP_Assignment1-1.0-SNAPSHOT.jar' which should run the executable jar file.


Some Assumptions/Working Principles that I have implemented in my code:

1. The librarian can't remove a member if he hasn't paid his fines yet or hasn't returned his books.
2. People/Books' Names can have symbols/numbers in them. I haven't set certain parameters for the name.
3. Assuming that phone numbers are any +ve integer from 0 onwards.
4. Assuming age is an integer, not a float.
5. Inputting integers in my program can't have whitespaces like tabs or spacing or it won't identify the number.
6. For logging in as member, only phone number is used.
7. For removing a member, only member ID is used.
8. A phone number starting with 0 is the same as not having the digit entirely. For example, 098765 is the same as 98765.
9. While displaying members, if they are currently borrowing a book which is overdue, the fines that the member owes will not count the current book until they return the book. Once, they return the book, only then, will the fine get added to the total money due.


Classes(All inside same com.library.system Package):-

1. Main:- This contains the main interface where input regarding the choices of entering the system as a Librarian or a Member are asked. No implementation lies in this class. It calls the Librarian, Library and Member class for their respective functionalities.

2. Book:- This contains all the details of the book including its title, author, ID and its constructor. It has only setter and getter methods, and all its fields are private to ensure encapsulation.

3. Member:- This includes fields regarding a member including their name, age, phone number, books borrowed, fines to be paid and other attributes similar. It also has methods apart from the standard setter and getter methods. These methods are for returning a book, issuing a book, viewing available or their own books.

4. Librarian:- It is similar to the librarian class, but its methods include registering and removing members and books.

5. Library:- It includes fields such as the list of books, list of members, the librarian running the library, and getter methods for the same. To ensure encapsulation, arrays are duplicated into a copy and that copy is returned if any array is ever required. Also, the Library Class has helper functions for the other classes such as looking through the array to search for a book, or the initial member login. It also, includes some helper functions for the deletion and addition of members and books, all called by the Librarian class.


The code follows all OOPS principles. Those fields that are immutable are made 'final', mutable references have copies sent in getter methods. All fields are private and so on.
