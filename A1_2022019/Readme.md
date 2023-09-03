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
